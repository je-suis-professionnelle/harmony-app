<template>
    <div v-if="visible" class="modal is-active" id="myModal">
        <div class="modal-background">
        </div>
        <div class="modal-card">
            <header class="modal-card-head">
                <p class="modal-card-title">Retirer un label de dépense</p>
                <button class="delete" aria-label="close" @click="fermerModal"></button>
            </header>

            <Form ref="form" :validation-schema="schema" @submit="handleSubmit">
                <section class="modal-card-body">
                    <div class="field">
                        <label class="label">Label</label>
                        <div class="control">
                            <select v-model="label" name="label">
                                <option v-for="label in this.labelsList" v-bind:value="label">
                                    {{ label.name }}
                                </option>
                            </select>
                        </div>
                        <ErrorMessage class="help is-danger" name="label" />
                        <p class="help is-danger">{{ errorMessage }}</p>
                    </div>
                </section>
                <footer class="modal-card-foot">
                    <button type="submit" class="button is-success">Retirer</button>
                    <button class="button" @click="fermerModal">Annuler</button>
                </footer>
            </Form>

        </div>
    </div>
</template>

<script>
import axios from "axios";
import { ErrorMessage, Form } from "vee-validate";

export default {
    name: 'SuppressionLabel',
    components: {
        Form,
        ErrorMessage,
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
            visible: false,
            errorMessage: '',
            selected: this.labelsList == null ? "" : this.labelsList[0],
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
            console.log("handleSubmit", this.selected);

            if (!this.$refs.form.errors) {
                this.removeLabel();
            }
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
                data: label
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