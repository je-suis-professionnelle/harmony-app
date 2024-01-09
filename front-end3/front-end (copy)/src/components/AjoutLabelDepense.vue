<template>
    <div v-if="visible" class="modal is-active" id="myModal">
        <div class="modal-background">
        </div>
        <div class="modal-card">
            <header class="modal-card-head">
                <p class="modal-card-title">Créer un label de dépense</p>
                <button class="delete" aria-label="close" @click="fermerModal"></button>
            </header>

            <Form ref="form" :validation-schema="schema" @submit="handleSubmit">
                <section class="modal-card-body">
                    <div class="field">
                        <label class="label">Nom du label</label>
                        <Field v-model="this.label.name" name="labelName" type="text" class="input"
                            placeholder="Nom du label" />
                        <ErrorMessage class="help is-danger" name="labelName" />
                        <p class="help is-danger">{{ errorMessage }}</p>
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
    name: 'AjoutLabelDepense',
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
            visible: false,
            errorMessage: '',
            schema: yup.object().shape({
                labelName: yup.string()
                    .required("Le nom du label est requis.")
                    .min(2, "Le nom doit avoir au moins 2 caractères.")
                    .max(50, "Le nom ne doit pas dépasser 50 caractères."),
            }),
            label: {
                name: '',
                idGroup: this.groupId,
            }
        };
    },
    methods: {
        ouvrirModal() {
            this.visible = true;
        },
        fermerModal() {
            this.visible = false;
            this.errorMessage = '';
        },

        async handleSubmit() {
            await this.$refs.form.validate();

            if (!this.$refs.form.errors) {
                this.createLabel();
            }
        },


        async createLabel() {
            const token = this.$store.state.auth.user.accessToken;

            const headers = {
                headers: {
                    Authorization: `Bearer ${token}`,
                }
            };

            try {
                await axios.post("http://localhost:8080/label", this.label, headers);
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