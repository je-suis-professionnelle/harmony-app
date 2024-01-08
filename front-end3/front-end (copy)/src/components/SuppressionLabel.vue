<template>
    <div v-if="visible" class="modal is-active" id="myModal">
        <div class="modal-background">
        </div>
        <div class="modal-card">
            <header class="modal-card-head">
                <p class="modal-card-title">Retirer un label de dépense</p>
                <button class="delete" aria-label="close" @click="fermerModal"></button>
            </header>
            <section class="modal-card-body">

                <Form ref="form" :validation-schema="schema">
                    <div class="field">
                        <label class="label">Label</label>
                        <div class="control">
                            <select v-model="label" name="label">
                                <option v-for="label in this.labelsList" v-bind:value="label">
                                    {{ label.name }}
                                </option>
                            </select>
                        </div>
                        <p class="help is-danger">{{ errorMessage }}</p>
                    </div>
                </Form>
            </section>
            <footer class="modal-card-foot">
                <button type="submit" class="button is-success" @click="removeLabel">Retirer</button>
                <button class="button" @click="fermerModal">Annuler</button>
            </footer>
        </div>
    </div>
</template>

<script>
import axios from "axios";
import { Field, Form } from "vee-validate";

export default {
    name: 'SuppressionLabel',
    components: {
        Form,
        Field,
    },
    props: {
        groupId: {
            type: Number,
            required: true,
        },
        labelsList: {
            type: Array,
            required: true,
        },
    },
    data() {
        return {
            label: null,
            visible: false, // Initialiser la modal comme non visible
            selected: this.labelsList == null ? "" : this.labelsList[0],
            errorMessage: '',
        };
    },
    created() {
        // this.getLabels();
    },
    methods: {
        ouvrirModal() {
            this.visible = true;
        },
        fermerModal() {
            this.visible = false;
        },

        removeLabel() {

            const token = this.$store.state.auth.user.accessToken;

            let label = {
                name: this.label.name,
                idGroup: this.groupId
            }

            let config = {
                headers: {
                    Authorization: `Bearer ${token}`,
                },
                data: label  // Utilisez la propriété 'data' pour envoyer les données de la requête DELETE
            };

            axios.delete('http://localhost:8080/label', config)
                .then(response => {
                    console.log("response", response);
                    this.$emit("labelRemoved");
                    this.fermerModal();
                })
                .catch(error => {
                    console.error("Erreur lors de la requête :", error);
                    if (error.response && error.response.status === 409) {
                        this.errorMessage = "Veuillez supprimer les dépenses ayant ce label.";
                    }
                });
        }
    },
}

</script>