<template>
    <a class="panel-block">
        <div class="container">
            <div class="columns is-centered has-text-centered">
            <div class="column">
                {{ depense.pseudo }}
            </div>
            <div class="column">
                {{ depense.label }}
            </div>

            <div class="column">
                {{ depense.description }}
            </div>
            <div class="column">
                {{ depense.amount }}
            </div>
            <div>
                <button class="button is-primary" @click="deleteExpense">Supprimer</button>
            </div>

        </div>
        </div>

        
    </a>
</template>
  
<script>
import Groupe from '@/models/group';
import Expense from '../models/expense.js';
import axios from "axios";

export default {
    name: "DepenseItem",
    props: {
        depense: {
            type: Expense,
            required: true,
        },
    },
    data() {
        return {};
    },
    methods: {
        deleteExpense() {
            let config = {
                headers: { 'Authorization': 'Bearer ' + this.$store.state.auth.user.accessToken },
                params: {
                    pseudo: this.depense.pseudo,
                    timestamp: this.depense.timestamp,
                    idGroup: this.depense.idGroup,
                },
            }
            axios.delete("http://localhost:3000/expense/", config)
            .then(response => {
                    console.log("response", response);
                    this.fermerModal();
                })
                .catch(error => {
                    console.error("Erreur lors de la suppression :", error.response.data.message);
                    console.error("Erreur lors de la suppression :", error.response.data);
                    console.error("Erreur lors de la suppression :", error.response);
                });
            this.$emit("expenseDeleted", this.depense);
        },
    }
};
</script>