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
                    {{ depense.amount }}€
                </div>
                <div>
                    <button class="button is-primary" @click="ouvrirSuppressionDepenseModal(depense)">Supprimer</button>
                </div>
            </div>
        </div>
    </a>
    <SuppressionDepense ref="suppressionDepenseModal" @expenseDeleted="expenseDeletedHandler" :depense="clickedExpense" />
</template>
  
<script>
import SuppressionDepense from "../components/SuppressionDepense.vue";
import Expense from '../models/expense.js';

export default {
    name: "DepenseItem",
    components: {
        SuppressionDepense,
    },
    props: {
        depense: {
            type: Expense,
            required: true,
        },
    },
    methods: {
        ouvrirSuppressionDepenseModal(depense) {
            this.clickedExpense = depense;
            this.$refs.suppressionDepenseModal.ouvrirModal();
            this.$emit('expenseDeletedD');
        },
    },
};
</script>