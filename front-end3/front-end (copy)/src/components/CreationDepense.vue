<template>
    <div v-if="visible" class="modal is-active" id="myModal">
        <div class="modal-background">
        </div>
        <div class="modal-card">
            <header class="modal-card-head">
                <p class="modal-card-title">Créer une dépense</p>
                <button class="delete" aria-label="close" @click="fermerModal"></button>
            </header>

            <Form ref="form" :validation-schema="schema" @submit="handleSubmit">
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
                        <label class="label">Description</label>
                        <Field v-model="expenseData.description" name="description" type="text" class="input"
                            placeholder="Description de la dépense" />
                        <ErrorMessage class="help is-danger" name="description" />
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
        }
    },
    data() {
        return {
            visible: false,
            loggedInUserPseudo: '',
            successful: false,
            loading: false,
            message: '',
            schema: yup.object().shape({
                label: yup.string().required("Le champ Label est requis."),
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
            console.log("dans exepensecreate");
            console.log("groupdId", this.groupId);
            this.expenseData.pseudo = this.$store.state.auth.user.username;
            this.expenseData.idGroup = Number(this.groupId);
            this.expenseData.timestamp = Date.now();
            console.log(this.expenseData);

            this.message = "";
            this.successful = false;
            this.loading = true;

            const token = this.$store.state.auth.user.accessToken;

            const headers = {
                headers: {
                    Authorization: `Bearer ${token}`,
                }
            };

            try {
                console.log("dans try");
                const response = await axios.post("http://localhost:8080/expenses", this.expenseData, headers);
                this.message = response.data.message;
                this.successful = true;
                this.loading = false;
                this.fermerModal();
                this.$emit('expenseCreated');
            } catch (error) {
                console.log("dans catch", error.response);
                console.error("Erreur détaillée :", error.response.data);
                console.log("catch :", error);
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
    },
}

</script>