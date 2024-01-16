<template>
    <div v-if="visible" class="modal is-active" id="myModal">
        <div class="modal-background">
        </div>
        <div class="modal-card">
            <header class="modal-card-head">
                <p class="modal-card-title">Créer un groupe</p>
                <button class="delete" aria-label="close" @click="fermerModal"></button>
            </header>
            <Form ref="form" :validation-schema="schema" @submit="handleSubmit">

            <section class="modal-card-body">
                    <div class="field">
                        <label class="label">Titre du groupe</label>
                        <div class="control has-icons-left has-icons-right">
                            <Field v-model="groupData.title" name="title" type="text" class="input"
                                placeholder="Entrer le titre" />
                        </div>
                        <ErrorMessage name="title" class="help is-danger" />
                        <p class="help is-danger">{{ errorMessage }}</p>
                    </div>

            </section>
            <footer class="modal-card-foot">
                <button type="submit" class="button is-success">Sauvegarder</button>
                <button class="button" @click="fermerModal">Annuler</button>
            </footer>
        </Form>
        </div>
    </div>
</template>

<script>
import axios from "axios";
import { Field, ErrorMessage, Form } from "vee-validate";
import * as yup from "yup";

export default {
    name: 'CreationGroupe',
    components: {
        Form,
        Field,
        ErrorMessage,
    },
    data() {
        return {
            visible: false,
            loggedInUserPseudo: '',
            schema: yup.object().shape({
                title: yup.string()
                    .required("Le titre est requis !")
                    .min(1, "Le titre doit avoir au moins 1 caractère !")
                    .max(50, "Le titre ne doit pas dépasser 50 caractères !"),
            }),
            groupData: {
                idOwner: '',
                title: '',
            },
            errorMessage: "",
        };
    },
    created() {
        if (this.$store.state.auth.user) {
            this.loggedInUserPseudo = this.$store.state.auth.user.username;
        } else {
            this.loggedInUserPseudo = "problème";
        }
        this.errorMessage = "";
    },
    methods: {
        ouvrirModal() {
            this.visible = true;
        },
        fermerModal() {
            this.visible = false;
        },

        async handleSubmit() {
            await this.$refs.form.validate();

            if (!this.$refs.form.errors) {
                this.createGroup();
            }
        },

        async createGroup() {
            this.groupData.idOwner = this.loggedInUserPseudo;

            try {
                const token = this.$store.state.auth.user.accessToken;

                const headers = {
                    headers: {
                        Authorization: `Bearer ${token}`,
                        'Content-Type': 'application/json',
                    },
                };
                // Utilisation d'Axios pour effectuer la requête POST
                const response = await axios.post("http://localhost:8080/groups/groups", this.groupData, headers);

                this.createLabel({
                    name: "Restaurant",
                    idGroup: response.data.identifiant,
                });
                this.createLabel({
                    name: "Hotel",
                    idGroup: response.data.identifiant,
                });
                this.createLabel({
                    name: "Loisir",
                    idGroup: response.data.identifiant,
                });

                this.fermerModal();
                this.$emit('groupCreated');
            } catch (error) {
                this.errorMessage = "Une erreur est survenue lors de la création du groupe.";
                console.error("Erreur lors de la récupération des groupes :", error.response.data);
            }
        },

        async createLabel(label) {
            const token = this.$store.state.auth.user.accessToken;

            const headers = {
                headers: {
                    Authorization: `Bearer ${token}`,
                }
            };

            try {
                await axios.post("http://localhost:8080/label", label, headers);
                this.$emit("labelCreated");
                this.fermerModal();
            } catch (error) {
                if (error.response && error.response.status === 409) {
                    this.errorMessage = "Le label existe déjà.";
                }
            }
        },
    },
}

</script>