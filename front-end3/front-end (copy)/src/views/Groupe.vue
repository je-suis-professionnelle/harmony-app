<template>
    <nav class="panel">
        <!-- <div class="panel-heading">
            <p>
                {{title}}
            </p>
            <div>
                <button class="button is-primary" @click="ouvrirModal">Créer une dépense</button>
            </div>
        </div> -->

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
                                <a class="navbar-item" @click="addMember(this.idGroup, 'cricri')">
                                    Ajouter un membre
                                </a>
                                <a class="navbar-item">
                                    Retirer un membre
                                </a>
                                <a class="navbar-item">
                                    Supprimer le groupe
                                </a>
                                <hr class="navbar-divider">
                                <a class="navbar-item">
                                    Report an issue
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="panel-block">
            <p class="control has-icons-left">
                <input class="input is-primary" type="text" placeholder="Search">
                <span class="icon is-left">
                    <i class="fas fa-search" aria-hidden="true"></i>
                </span>
            </p>
            <div>
                <button class="button is-primary" @click="ouvrirModal">Créer une dépense</button>
            </div>
        </div>

        <DepenseItem v-for="depense in this.depenses" :key="depense.identifiant" :depense="depense" />

        <CreationDepense ref="creationDepenseModal" @expenseCreated="getDepenses" :groupId=this.groupId />
    </nav>
</template>

<script>
import axios from "axios";

import Expense from '../models/expense.js';

import DepenseItem from '../components/DepenseItem.vue'
import CreationDepense from '../components/CreationDepense.vue'
import { RouterLink } from "vue-router";
export default {
    name: 'Groupe',
    props: {
        // Utilise `props: true` dans la configuration de la route pour passer les paramètres en tant que props
        groupId: {
            type: Number,
            required: true,
            // coerce: value => Number(value), //pas sur
        },
        title: {
            type: String,
            required: true,
        },
        groupDetails: {
            type: Object,
        },
    },
    components: {
        DepenseItem,
        CreationDepense,
        RouterLink
    },
    data() {
        return {
            groupe: null,
            loggedInUserPseudo: '',
            depenses: [], // Liste des depenses récupérée du serveur
            creationDepenseModal: null, // Ajoute une référence à la modal
        };
    },
    computed: {
        getGroupeDetails() {
            return this.$store.getters.getGroupeById(this.groupId);
        },
        loggedIn() {
            return this.$store.state.auth.status.loggedIn;
        }
    },
    mounted() {
        // this.groupDetails = this.getGroupeDetails();
        // this.getDepenses(groupId);
    },
    created() {
        console.log("groupid created", this.groupId);
        this.getDepenses(this.groupId);
        // Convertir groupId en nombre
        const groupIdNumber = Number(this.groupId);

        // Utiliser groupIdNumber dans le reste de ta logique
    },
    // created() {
    // const group = this.group;
    // this.getDepenses(group.id);
    // },
    methods: {
        getDepenses(groupId) {
            console.log("groupId", groupId);
            this.loading = true;
            const token = this.$store.state.auth.user.accessToken;

            // Configure les headers avec le token JWT
            const headers = {
                headers: {
                    Authorization: `Bearer ${token}`,
                },
            };
            axios.get('http://localhost:8080/expenses', { params: { idGroup: groupId } }, headers)
                .then(response => {
                    console.log("depenses", response.data);
                    this.depenses = response.data.map(depenseData => new Expense(depenseData));
                    this.loading = false;
                })
                .catch(error => {
                    console.error("Erreur lors de la requête :", error);
                    this.loading = false;
                });
        }

        , ouvrirModal() {
            // Utilise la référence pour ouvrir la modal
            this.$refs.creationDepenseModal.ouvrirModal();
        },

        addMember(groupId, pseudoUser) {
            axios.post('http://localhost:8080/groupUser', {groupId: groupId, pseudoUser: pseudoUser })
                .then(response => {
                    console.log("response", response);
                })
                .catch(error => {
                    console.error("Erreur lors de la requête :", error);
                });
        }
    },
    computed: {
        loggedIn() {
            return this.$store.state.auth.status.loggedIn;
        },
    },
};

</script>