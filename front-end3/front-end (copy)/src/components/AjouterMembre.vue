<template>
    <div v-if="visible" class="modal is-active" id="myModal">
        <div class="modal-background">
        </div>
        <div class="modal-card">
            <header class="modal-card-head">
                <p class="modal-card-title">Ajouter un membre</p>
                <button class="delete" aria-label="close" @click="fermerModal"></button>
            </header>

            <Form ref="form" :validation-schema="schema" @submit.prevent()="handleSubmit">
            <section class="modal-card-body">
                    <div class="field">
                        <label class="label">Pseudo</label>
                        <Field v-model="pseudo" name="pseudo" type="text" class="input"
                            placeholder="Pseudo du membre à ajouter" />
                        <ErrorMessage name="pseudo" />
                        <p v-if="errorMessage" class="help is-danger">{{ errorMessage }}</p>
                    </div>
            </section>
            <footer class="modal-card-foot">
                <button type="submit" class="button is-success">Ajouter</button>
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
    name: 'AjouterMembre',
    components: {
        Form,
        Field,
        ErrorMessage,
    },
    props: {
        groupId: {
            type: Number,
            required: true,
        },
    },
    data() {
        return {
            visible: false, // Initialiser la modal comme non visible
            loggedInUserPseudo: '',
            successful: false,
            loading: false,
            message: '',
            schema: yup.object().shape({
                pseudo: yup.string()
                    .required("Le pseudo est requis !")
            }),
            pseudo: '',
            errorMessage: '',
        };
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
                this.addMember();
            }
        },

        addMember() {

            const headers = {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + this.$store.state.auth.user.accessToken
            }

            const data = {
                pseudoUser: this.pseudo,
                idGroup: this.groupId
            }

            console.log("data", data);
            console.log("headers", headers);

            axios.post('http://localhost:8080/groupUser', data, {
                headers: headers
            })
                .then(response => {
                    console.log("response", response);
                    this.$emit('memberAdded');
                    this.fermerModal();
                })
                .catch(error => {
                    console.error("Erreur lors de la requête :", error);
                    if (error.response && error.response.status === 409) {
                        this.errorMessage = "Le membre appartient déjà à ce groupe.";
                    } else if (error.response && error.response.status === 404) {
                        this.errorMessage = "Le membre ne possède pas de compte.";
                    }
                });
        }
    },
}

</script>