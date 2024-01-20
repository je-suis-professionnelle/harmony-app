<template>
    <nav class="panel is-primary" style="margin-top:3%; margin-bottom: 5%; margin-left: 15%; margin-right: 15%;">

        <div class="panel-heading">
            <div class="container">
                <div class="columns is-centered has-text-centered">
                    <div class="column">
                        <div class="navbar-item has-text-white">
                            {{ title.toUpperCase() }}
                        </div>
                    </div>
                    <div class="column">
                        <div class="navbar-end">
                            <div class="navbar-item dropdown is-hoverable">
                                <div class="dropdown-trigger">
                                    <button class="navbar-link">
                                        <span>Actions</span>
                                    </button>
                                    <div class="dropdown-menu">
                                        <div class="dropdown-content">
                                            <a class="dropdown-item" @click="ouvrirAjoutMembre">
                                                Ajouter un membre
                                            </a>
                                            <a class="dropdown-item" @click="ouvrirSuppressionMembre">
                                                Retirer un membre
                                            </a>
                                            <a class="dropdown-item" @click="ouvrirAjouterLabelDepense">
                                                Ajouter un label de dépense
                                            </a>
                                            <a class="dropdown-item" @click="ouvrirSuppressionLabel">
                                                Supprimer un label de dépense
                                            </a>
                                            <hr class="dropdown-divider">
                                            <a class="dropdown-item" @click="ouvrirSuppressionGroupe" style="color:red;">
                                                Supprimer le groupe
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="panel-block m-5">
            <p class="control has-icons-left">
                <input v-model="search" class="input is-primary" type="text" placeholder="Chercher">
                <span class="icon is-left">
                    <i class="fas fa-search" aria-hidden="true"></i>
                </span>
            </p>
            <div>
                <button class="button is-primary ml-5" @click="ouvrirModal">Créer une dépense</button>
            </div>
        </div>

        <Onglets @expenseDeletedO="getDepenses" :expenses='this.expensesFiltered' :division=Number(this.division)
            :totalByMember="this.filteredTotalByMember" :equilibres="this.filteredEquilibres" />

        <Resultat :total=Number(this.total) :myTotal=Number(this.myTotal) :division=Number(this.division) />

        <CreationDepense ref="creationDepenseModal" @expenseCreated="getDepenses" :groupId=this.groupId
            :labelsList="this.labelsList" :memberList="this.memberList" />

        <AjouterMembre ref="ajoutMembreModal" @memberAdded="this.handleMemberAdded" :groupId=this.groupId />

        <RetirerMembre ref="suppressionMembreModal" @memberDeleted="this.handleMemberAdded" :groupId=this.groupId
            :memberList=this.memberList :expenses=this.depenses />

        <SuppressionGroupe ref="suppressionGroupeModal" :groupId=this.groupId />

        <AjoutLabelDepense ref="ajoutLabelModal" @labelCreated="this.getLabels" :groupId=this.groupId />

        <SuppressionLabel ref="suppressionLabelModal" @labelRemoved="this.getLabels" :groupId=this.groupId
            :labelsList="this.labelsList" />

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
import AjoutLabelDepense from '@/components/AjoutLabelDepense.vue'
import SuppressionLabel from "@/components/SuppressionLabel.vue";

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
        Resultat,
        AjoutLabelDepense,
        SuppressionLabel,
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
            labelsList: [],
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
        },
        filteredTotalByMember() {
            if (!this.search) {
                return this.totalByMember;
            }
            let filteredMap = new Map();
            for (let [key, value] of this.totalByMember) {
                if (key.toLowerCase().includes(this.search.toLowerCase())) {
                    filteredMap.set(key, value);
                }
            }
            return filteredMap;
        },
        filteredEquilibres() {
            if (!this.search) {
                return this.equilibres;
            }
            return this.equilibres.filter(equilibre =>
                equilibre.from.toLowerCase().includes(this.search.toLowerCase()) ||
                equilibre.to.toLowerCase().includes(this.search.toLowerCase())
            );
        },
    },
    created() {
        this.getDepenses();
        this.getLabels();
    },
    methods: {
        async handleMemberAdded() {
            await this.getNbMembers();
            await this.getDepenses();
        },
        async getNbMembers() {
            const token = this.$store.state.auth.user.accessToken;

            let config = {
                headers: { 'Authorization': 'Bearer ' + token },
                params: {
                    idGroup: this.groupId
                },
            }

            await axios.get('http://localhost:8080/groupUsers', config)
                .then(response => {
                    this.memberList = response.data.map(member => member.pseudoUser);
                    this.nbMembers = response.data.length;
                })
                .catch(error => {
                    console.error("Erreur lors de la récupération du groupe :", error);
                });
        },

        async getDepenses() {
            this.getNbMembers();
            const token = this.$store.state.auth.user.accessToken;

            let config = {
                headers: { 'Authorization': 'Bearer ' + token },
                params: {
                    idGroup: this.groupId
                },
            }

            await axios.get('http://localhost:8080/expenses', config)
                .then(response => {
                    this.depenses = response.data.map(depenseData => new Expense(depenseData));
                    this.totalByMember = new Map(this.memberList.map(member => [member, 0]));

                    this.depenses.forEach(expense => {
                        this.totalByMember.set(expense.pseudo, (this.totalByMember.get(expense.pseudo) || 0) + expense.amount);
                    });
                    this.$nextTick(() => {
                        this.$forceUpdate();
                    });
                    this.total = this.depenses.reduce((acc, expense) => acc + expense.amount, 0).toFixed(2);
                    this.myTotal = this.depenses.reduce((acc, expense) => (this.loggedInUserPseudo == expense.pseudo ? acc + expense.amount : acc), 0).toFixed(2);
                    if (this.nbMembers > 0) {
                        this.division = (this.total / this.nbMembers).toFixed(2);
                    } else {
                        this.division = 0;
                    }
                    this.calculerEquilibres();
                })
                .catch(error => {
                    console.error("Erreur lors de la récupération des dépenses :", error);
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

        getLabels() {
            const token = this.$store.state.auth.user.accessToken;

            let config = {
                headers: { 'Authorization': 'Bearer ' + token },
                params: {
                    idGroup: this.groupId
                },
            }

            axios.get('http://localhost:8080/labels', config)
                .then(response => {
                    this.labelsList = response.data;
                })
                .catch(error => {
                    console.error("Erreur lors de la requête :", error);
                });
        },

        ouvrirModal() {
            this.$refs.creationDepenseModal.ouvrirModal();
        },
        ouvrirAjoutMembre() {
            this.$refs.ajoutMembreModal.ouvrirModal();
        },
        ouvrirSuppressionMembre() {
            this.$refs.suppressionMembreModal.ouvrirModal();
        },
        ouvrirSuppressionGroupe() {
            this.$refs.suppressionGroupeModal.ouvrirModal();
        },
        ouvrirAjouterLabelDepense() {
            this.$refs.ajoutLabelModal.ouvrirModal();
        },
        ouvrirSuppressionLabel() {
            this.$refs.suppressionLabelModal.ouvrirModal();
        },
    },
};

</script>