<template>
    <div v-if="visible" class="modal is-active" id="myModal">
        <div class="modal-background">
        </div>
        <div class="modal-card">
            <header class="modal-card-head">
                <p class="modal-card-title">Créer une dépense</p>
                <button class="delete" aria-label="close" @click="fermerModal"></button>
            </header>

            <Form ref="form" :validation-schema="schema" @submit="handleSubmit" style="max-height:70vh; overflow: scroll;">
                <section class="modal-card-body">
                    <div class="field">
                        <label class="label">Label</label>
                        <div class="control">
                            <Field v-model="expenseData.label" name="label" as="select">
                                <option v-for="label in this.labelsList" :value="label.name">
                                    {{ label.name }}
                                </option>
                            </Field>
                        </div>
                        <ErrorMessage class="help is-danger" name="label" />
                    </div>
                    <div class="field">
                        <label class="label">Montant</label>
                        <Field v-model="expenseData.amount" name="amount" type="number" class="input" min="0.01"
                            placeholder="Montant de la dépense" />
                        <ErrorMessage class="help is-danger" name="amount" />
                    </div>

                    <div class="field">
                        <label class="label">Membre ayant payé</label>
                        <div class="control">
                            <Field v-model="this.expenseData.pseudo" name="owner" as="select">
                                <option v-for="owner in this.memberList" :value="owner">
                                    {{ owner }}
                                </option>
                            </Field>
                        </div>
                        <ErrorMessage class="help is-danger" name="label" />
                    </div>

                    <div class="field">
                        <label class="label">Description</label>
                        <Field v-model="expenseData.description" name="description" type="text" class="input"
                            placeholder="Description de la dépense" />
                        <ErrorMessage class="help is-danger" name="description" />
                    </div>

                    <div class="field">
                        <div class="file has-name">
                            <label class="file-label">
                                <input class="file-input" type="file" accept=".jpg, .jpeg, .png"
                                    @change="handleFileUpload" />
                                <span class="file-cta">
                                    <span class="file-icon">
                                        <i class="fas fa-upload"></i>
                                    </span>
                                    <span class="file-label">
                                        Choose a file…
                                    </span>
                                </span>
                                <span class="file-name">
                                    {{ uploadedFileName ? uploadedFileName : 'Aucun fichier sélectionné' }}
                                </span>
                            </label>
                        </div>
                    </div>
                    <img class="image" v-if="imagePreview" :src="imagePreview" alt="Image preview" />
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
    name: 'CreationDepense',
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
        labelsList: {
            type: Array,
            required: true,
        },
        memberList: {
            type: Array,
            required: true,
        },
    },
    data() {
        return {
            visible: false,
            loggedInUserPseudo: '',
            schema: yup.object().shape({
                label: yup.string().required("Le champ est requis."),
                amount: yup.number()
                    .typeError('Le montant doit être un nombre.')
                    .min(0.01, "Le montant doit être supérieur ou égal à un centime.")
                    .required("Le champ Montant est requis."),
                description: yup.string().max(50, "La description ne doit pas dépasser 50 caractères."),
            }),
            expenseData: {
                pseudo: '',
                idGroup: 0,
                timestamp: '',
                label: '',
                amount: 0.01,
                description: '',
            },
            label: null,
            imagePreview: null,
            imageFile: null,
            uploadedFileName: '',
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
                this.createExpense();
            }
        },

        async createExpense() {
            this.expenseData.idGroup = Number(this.groupId);
            this.expenseData.timestamp = Date.now();
            const formData = new FormData();

            formData.append('expense', JSON.stringify(this.expenseData));

            if (this.imageFile) {
                formData.append('imageBlob', this.imageFile, this.imageFile.name);
            }

            const token = this.$store.state.auth.user.accessToken;

            const headers = {
                headers: {
                    'Content-Type': 'multipart/form-data',
                    Authorization: `Bearer ${token}`,
                }
            };

            try {
                const response = await axios.post("http://localhost:8080/expenses", formData, headers);
                this.fermerModal();
                this.$emit('expenseCreated');
            } catch (error) {
                console.error("Erreur lors de la création de dépense :", error.response.data);
            }
        },

        handleFileUpload(event) {
            const file = event.target.files[0];
            if (file && file.type.startsWith('image/')) {
                const reader = new FileReader();
                reader.onload = (e) => {
                    this.imagePreview = e.target.result;
                    this.uploadedFileName = file.name;
                    this.imageFile = file;
                };
                reader.readAsDataURL(file);
            }
        },
    },
}

</script>