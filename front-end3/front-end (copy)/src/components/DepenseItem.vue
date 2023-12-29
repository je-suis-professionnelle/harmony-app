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

        </div>
        </div>

        
    </a>
</template>
  
<script>
import Expense from '../models/expense.js';
export default {
    props: {
        // Définir une propriété "message" qui sera passée depuis le composant parent
        depense: {
            type: Expense,
            required: true,
        },
    },
    data() {
        return {};
    },
    methods: {}
};
</script>