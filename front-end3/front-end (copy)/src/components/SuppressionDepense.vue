<template>
    <div v-if="visible" class="modal is-active" id="myModal">
        <div class="modal-background">
        </div>
        <div class="modal-card">
            <header class="modal-card-head">
                <p class="modal-card-title">Supprimer la dépense</p>
                <button class="delete" aria-label="close" @click="fermerModal"></button>
            </header>
            <footer class="modal-card-foot">
                <button type="submit" class="button is-success" @click="deleteExpense">Supprimer</button>
                <button class="button" @click="fermerModal">Annuler</button>
            </footer>
        </div>
    </div>
</template>

<script>
import axios from "axios";
import Expense from '../models/expense.js';

export default {
    name: 'SuppressionDepense',
    props: {
        depense: {
            type: Expense,
            required: false,
        },
    },
    data() {
        return {
            visible: false,
        };
    },
    methods: {
        ouvrirModal() {
            this.visible = true;
        },
        fermerModal() {
            this.visible = false;
        },

        deleteExpense() {
            let config = {
                headers: { 'Authorization': 'Bearer ' + this.$store.state.auth.user.accessToken },
                params: {
                    pseudo: this.depense.pseudo,
                    timestamp: this.depense.timestamp,
                    idGroup: this.depense.idGroup
                },
            }
            axios.delete("http://localhost:8080/expenses/expense", config)
                .then(response => {
                    this.$emit('expenseDeleted');
                    this.fermerModal();
                })
                .catch(error => {
                    console.error("Erreur lors de la suppression :", error.response.data.message);
                    console.error("Erreur lors de la suppression :", error.response.data);
                    console.error("Erreur lors de la suppression :", error.response);
                });
        },
    },
}

</script>