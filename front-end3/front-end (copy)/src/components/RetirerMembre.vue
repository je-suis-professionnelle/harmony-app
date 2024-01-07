<template>
    <div v-if="visible" class="modal is-active" id="myModal">
        <div class="modal-background">
        </div>
        <div class="modal-card">
            <header class="modal-card-head">
                <p class="modal-card-title">Retirer un membre</p>
                <button class="delete" aria-label="close" @click="fermerModal"></button>
            </header>
            <section class="modal-card-body">

                <Form :validation-schema="schema">
                    <!-- <div class="field">
                        <label class="label">Pseudo</label>
                        <Field v-model="pseudo" name="pseudo" type="text" class="input"
                            placeholder="Pseudo du membre à ajouter" />
                        <ErrorMessage name="pseudo" />
                        <p v-if="errorMessage" class="help is-danger">{{ errorMessage }}</p>
                    </div> -->
                    <div class="field">
                        <label class="label">Pseudo</label>
                        <div class="control">
                            <select v-model="pseudo" name="pseudo">
                                <option v-for="pseudo in this.memberList" v-bind:value="pseudo">
                                    {{ pseudo }}
                                </option>
                            </select>
                        </div>
                        <ErrorMessage name="title" />
                    </div>
                </Form>
            </section>
            <footer class="modal-card-foot">
                <button type="submit" class="button is-success" @click="removeMember">Retirer</button>
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
    name: 'RetirerMembre',
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
        memberList: {
            type: Array,
            required: true,
            default: null
        },
    },
    data() {
        return {
            visible: false, // Initialiser la modal comme non visible
            loggedInUserPseudo: '',
            successful: false,
            loading: false,
            message: '',
            selected: this.memberList == null ? "" : this.memberList[0],
            // / schema: yup.object().shape({
            //     title: yup.string()
            //         .required("Le titre est requis !")
            //         .min(5, "Le titre doit avoir au moins 5 caractères !")
            //         .max(50, "Le titre ne doit pas dépasser 50 caractères !"),
            // }),
            pseudo: '',
            errorMessage: '', // New property for error message
        };
    },
    methods: {
        ouvrirModal() {
            this.visible = true;
        },
        fermerModal() {
            this.visible = false;
        },

        removeMember() {

            const token = this.$store.state.auth.user.accessToken;

            let config = {
                headers: { 'Authorization': 'Bearer ' + token },
                params: {
                    pseudoUser: this.pseudo,
                    idGroup: this.groupId
                },
            }

            axios.delete('http://localhost:8080/groupUser', config)
                .then(response => {
                    console.log("response", response);
                    if (this.pseudo == this.$store.state.auth.user.username) {
                        this.$router.push({ name: 'Groupes' });
                    } else {
                        this.fermerModal();
                    }
                })
                .catch(error => {
                    console.error("Erreur lors de la requête :", error);
                    if (error.response && error.response.status === 404) {
                        this.errorMessage = "Le membre n'appartient pas au groupe !";
                    } else if (error.response && error.response.status === 403) {
                        this.errorMessage = "Le membre n'existe pas !";
                    }
                });
        }
    },
}

</script>