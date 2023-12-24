<template>
    <div v-if="visible" class="modal is-active" id="myModal">
        <div class="modal-background">
        </div>
        <div class="modal-card">
            <header class="modal-card-head">
                <p class="modal-card-title">Créer un groupe</p>
                <button class="delete" aria-label="close" @click="fermerModal"></button>
            </header>
            <section class="modal-card-body">
                <!-- Contenu de ta modal ici -->
                <Form @submit="createGroup" :validation-schema="schema">
                    <Field name="idOwner" type="text" :value="loggedInUserPseudo" />
                    <div class="field">
                        <label class="label">Titre du groupe</label>
                        <div class="control has-icons-left has-icons-right">
                            <Field name="title" type="text" class="input" placeholder="Entrer le titre" />
                        </div>
                        <ErrorMessage name="title" class="help is-danger" />
                    </div>

                    <!-- ajouter des participants -->
                    <!-- <button class="button is-success">Sauvegarder</button> -->
                </Form>
            </section>
            <footer class="modal-card-foot">
                <!-- Boutons ou autres éléments de pied de modal -->
                <button type="submit" class="button is-success" @click="createGroup">Sauvegarder</button>
                <button class="button" @click="fermerModal">Annuler</button>
            </footer>
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
    props: {

    },
    data() {
        return {
            visible: false, // Initialiser la modal comme non visible
            loggedInUserPseudo: '',
            successful: false,
            loading: false,
            message: '',
            schema: yup.object().shape({
                title: yup.string()
                    .required("Le titre est requis !")
                    .min(5, "Le titre doit avoir au moins 5 caractères !")
                    .max(50, "Le titre ne doit pas dépasser 50 caractères !"),
            }),
        };
    },
    created() {
        if (this.$store.state.auth.user) {
            this.loggedInUserPseudo = this.$store.state.auth.user.username;
        } else {
            this.loggedInUserPseudo = "problème";
        }
    },
    methods: {
        ouvrirModal() {
            this.visible = true;
        },
        fermerModal() {
            this.visible = false;
        },

        async createGroup(groupData) {
            console.log("dans createGroup");
            groupData.idOwner = this.loggedInUserPseudo;
            this.message = "";
            this.successful = false;
            this.loading = true;

            try {
                console.log("dans try");
                // Utilisation d'Axios pour effectuer la requête POST
                const response = await axios.post("http://localhost:8080/groups/groups", groupData);
                console.log("response :");
                // Traitement de la réponse
                this.message = response.data.message;
                this.successful = true;
                this.loading = false;
                this.fermerModal();
            } catch (error) {
                console.log("catch :", error);
                // Gestion des erreurs
                this.message =
                    (error.response &&
                        error.response.data &&
                        error.response.data.message) ||
                    error.message ||
                    error.toString();
                this.successful = false;
                this.loading = false;
                console.log(this.message);
            }
        },
        // saveAndCloseModal() {
        //     // Sauvegarder les données et fermer la modal
        //     // this.fermerModal();
        //     this.createGroup();
        //     this.fermerModal();
        // },
    },
}

</script>