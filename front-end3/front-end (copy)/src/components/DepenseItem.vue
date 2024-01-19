<template>
    <a class="panel-block">
        <div class="container">
            <div class="columns is-centered has-text-centered">
                <div class="column is-centered has-text-centered">
                    {{ depense.pseudo }}
                </div>
                <div class="column is-centered has-text-centered">
                    {{ depense.label }}
                </div>
                <div class="column is-centered has-text-centered">
                    {{ depense.description }}
                </div>
                <div class="column is-centered has-text-centered">
                    {{ depense.amount }}€
                </div>
                <div class="column is-centered has-text-centered">
                    <div class="container" @click="ouvrirSuppressionDepenseModal(depense)">

                        <a class="button is-hidden-tablet is-danger">
                            <span class="icon">
                                <i class="fa fa-times"></i>
                            </span>
                        </a>

                        <a class="button is-hidden-mobile is-danger">
                            <span class="icon">
                                <i class="fa fa-times"></i>
                            </span>
                            <span>Supprimer</span>
                        </a>
                    </div>
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
    emits: ['expenseDeletedD'],
    props: {
        depense: {
            type: Expense,
            required: true,
        },
    },
    inheritAttrs: false,
    data() {
        return {
            clickedExpense: null,
        };
    },
    methods: {
        expenseDeletedHandler() {
            console.log("expensedelted depenseitem");
            this.$emit('expenseDeletedD');
        },
        ouvrirSuppressionDepenseModal(depense) {
            this.clickedExpense = depense;
            this.$refs.suppressionDepenseModal.ouvrirModal();
        },
        handleClick() {
            this.$emit('itemClicked', this.depense);
        }
    },
};
</script>
