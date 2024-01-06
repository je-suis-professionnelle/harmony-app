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
                <button class="button is-primary" @click="ouvrirModal">Supprimer</button>
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
        
    }
};
</script>