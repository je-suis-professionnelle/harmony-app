<template>
    <nav class="panel">

        <div class="panel-heading">
            <div class="container">
                <div class="columns is-centered has-text-centered">
                    <div class="column">
                        {{ title }}
                    </div>
                    <div class="column">
                        <div class="navbar-item has-dropdown is-hoverable">
                            <a class="navbar-link">
                                +
                            </a>

                            <div class="navbar-dropdown">
                                <a class="navbar-item" @click="ouvrirAjoutMembre">
                                    Ajouter un membre
                                </a>
                                <a class="navbar-item" @click="ouvrirSuppressionMembre">
                                    Retirer un membre
                                </a>
                                <hr class="navbar-divider">
                                <a class="navbar-item" @click="ouvrirSuppressionGroupe" style="color:red;">
                                    Supprimer le groupe
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="panel-block">
            <p class="control has-icons-left">
                <input v-model="search" class="input is-primary" type="text" placeholder="Chercher">
                <span class="icon is-left">
                    <i class="fas fa-search" aria-hidden="true"></i>
                </span>
            </p>
            <div>
                <button class="button is-primary" @click="ouvrirModal">Créer une dépense</button>
            </div>
        </div>

        <Onglets @expenseDeletedP="this.expensesFiltered" :total="this.total" :expenses='this.expensesFiltered'
            :division="this.division" :myTotal="this.myTotal" :totalByMember="this.totalByMember"
            :equilibres="this.equilibres" />

        <Resultat :total="this.total" :myTotal="this.myTotal" :division="this.division" />

        <CreationDepense ref="creationDepenseModal" @expenseCreated="getDepenses" :groupId=this.groupId />

        <AjouterMembre ref="ajoutMembreModal" :groupId=this.groupId />

        <RetirerMembre ref="suppressionMembreModal" :groupId=this.groupId :memberList="this.memberList" />

        <SuppressionGroupe ref="suppressionGroupeModal" :groupId=this.groupId />

        <AjoutLabelDepense ref="AjoutLabelModal" :groupId=this.groupId />

    </nav>
</template>

<script>
import axios from "axios";

import Expense from '../models/expense.js';

import Onglets from '../components/Onglets.vue'
import DepenseItem from '../components/DepenseItem.vue'
import Resultat from '../components/Resultat.vue'
import CreationDepense from '../components/CreationDepense.vue'
import AjouterMembre from '../components/AjouterMembre.vue'
import RetirerMembre from '../components/RetirerMembre.vue'
import SuppressionGroupe from '../components/SuppressionGroupe.vue'
import { RouterLink } from "vue-router";
export default {
    name: 'Groupe',
    props: {
        groupId: {
            type: Number,
            required: true,
        },
        title: {
            type: String,
            required: true,
        },
    },
    components: {
        Onglets,
        DepenseItem,
        CreationDepense,
        AjouterMembre,
        RetirerMembre,
        SuppressionGroupe,
        RouterLink,
        Resultat
    },
    data() {
        return {
            groupe: null,
            loggedInUserPseudo: this.$store.state.auth.user.username,
            depenses: [],
            totalByMember: new Map(),
            creationDepenseModal: null,
            nbMembers: null,
            total: 0,
            myTotal: 0,
            division: 0,
            memberList: [],
            equilibres: [],
            search: '',
        };
    },
    computed: {
        expensesFiltered() {

            if (!this.depenses || !this.depenses.length) {
                return [];
            }

            if (!this.search) {
                return this.depenses;
            }

            return this.depenses.filter(depense => 
                depense.pseudo.toLowerCase().includes(this.search.toLowerCase())
            );
        }
    },
    created() {
        console.log("groupid created", this.groupId);
        this.getDepenses();

        const groupIdNumber = Number(this.groupId);
    },
    methods: {
        getNbMembers() {
            this.loading = true;
            const token = this.$store.state.auth.user.accessToken;

            let config = {
                headers: { 'Authorization': 'Bearer ' + token },
                params: {
                    idGroup: this.groupId
                },
            }

            axios.get('http://localhost:8080/groupUsers', config)
                .then(response => {
                    this.memberList = response.data.map(member => member.pseudoUser);
                    this.nbMembers = response.data.length;
                })
                .catch(error => {
                    console.error("Erreur lors de la récupération du groupe :", error);
                    this.loading = false;
                });
        },

        getDepenses() {
            this.getNbMembers();
            const token = this.$store.state.auth.user.accessToken;

            let config = {
                headers: { 'Authorization': 'Bearer ' + token },
                params: {
                    idGroup: this.groupId
                },
            }

            axios.get('http://localhost:8080/expenses', config)
                .then(response => {
                    this.depenses = response.data.map(depenseData => new Expense(depenseData));
                    this.totalByMember = new Map(this.memberList.map(member => [member, 0]));

                    this.depenses.forEach(expense => {
                        this.totalByMember.set(expense.pseudo, (this.totalByMember.get(expense.pseudo) || 0) + expense.amount);
                    });
                    this.$nextTick(() => {
                        this.$forceUpdate();
                    });
                    this.total = this.depenses.reduce((acc, expense) => acc + expense.amount, 0);
                    this.myTotal = this.depenses.reduce((acc, expense) => (this.loggedInUserPseudo == expense.pseudo ? acc + expense.amount : acc), 0);
                    this.division = this.total / this.nbMembers;
                    this.calculerEquilibres();
                })
                .catch(error => {
                    console.error("Erreur lors de la récupération du groupe :", error);
                    this.loading = false;
                });
        },

        calculerEquilibres() {
            this.equilibres = [];
            this.totalByMember.forEach((total, member) => {
                if (total < this.division) {
                    let amountOwed = this.division - total;

                    this.totalByMember.forEach((otherTotal, otherMember) => {
                        if (otherTotal > this.division && otherMember !== member) {
                            const amountToPay = Math.min(amountOwed, otherTotal - this.division);
                            this.equilibres.push({
                                from: member,
                                debt: amountToPay,
                                to: otherMember,
                            });
                            amountOwed -= amountToPay;
                        }
                    });
                }
            });
        },

        ouvrirModal() {
            // Utilise la référence pour ouvrir la modal
            this.$refs.creationDepenseModal.ouvrirModal();
        },
        ouvrirAjoutMembre() {
            // Utilise la référence pour ouvrir la modal
            this.$refs.ajoutMembreModal.ouvrirModal();
        },
        ouvrirSuppressionMembre() {
            // Utilise la référence pour ouvrir la modal
            this.$refs.suppressionMembreModal.ouvrirModal();
        },
        ouvrirSuppressionGroupe() {
            // Utilise la référence pour ouvrir la modal
            this.$refs.suppressionGroupeModal.ouvrirModal();
        },
    },
};

</script>