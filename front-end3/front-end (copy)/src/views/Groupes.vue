<template>
    <article class="panel is-primary m-5">
        <p class="panel-heading">
            Vos groupes
        </p>
        <div class="panel-block">
            <p class="control has-icons-left">
                <input v-model="search" class="input is-primary" type="text" placeholder="Chercher">
                <span class="icon is-left">
                    <i class="fas fa-search" aria-hidden="true"></i>
                </span>
            </p>
            <div>
                <button class="button is-primary ml-1" @click="ouvrirModal">Créer un groupe</button>
            </div>
        </div>
        <GroupeItem v-for="groupe in this.groupesFiltered" :key=groupe.identifiant :groupe="groupe" />
        <CreationGroupe ref="creationGroupeModal" @groupCreated="getGroupes" />
    </article>
</template>

<script>
import axios from "axios";
import Groupe from '../models/group.js';

import GroupeItem from '../components/GroupeItem.vue'
import CreationGroupe from '../components/CreationGroupe.vue'
import { RouterLink } from "vue-router";

export default {
    name: 'Groupes',
    props: {
    },
    components: {
        GroupeItem,
        CreationGroupe,
        RouterLink
    },
    data() {
        return {
            search: '',
            loggedInUserPseudo: '',
            groupes: [], // Liste des groupes récupérée du serveur
            creationGroupeModal: null, // Ajoute une référence à la modal
        };
    },
    computed: {
        loggedIn() {
            return this.$store.state.auth.status.loggedIn;
        },
        groupesFiltered() {
            return this.groupes.filter(group => {
                return group.title.toLowerCase().includes(this.search.toLowerCase())
            })
        },
    },
    mounted() {
        if (!this.loggedIn) {
            this.$router.push('/login');
        }
        if (this.$store.state.auth.user) {
            this.loggedInUserPseudo = this.$store.state.auth.user.username;
        } else {
            alert("Vous n'êtes pas connecté");
        }
        this.getGroupes();
    },
    methods: {

        getGroupes() {
            this.loading = true;
            const token = this.$store.state.auth.user.accessToken;

            // Configure les headers avec le token JWT
            const headers = {
                headers: {
                    Authorization: `Bearer ${token}`,
                },
            };
            axios.get('http://localhost:8080/groups', headers)
                .then(response => {
                    console.log("response : ", response);
                    if (response.data.length != 0) {
                        this.groupes = response.data.map(groupData => new Groupe(groupData));
                    }
                    console.log("groupes : ", this.groupes);
                    this.loading = false;
                })
                .catch(error => {
                    console.error("Erreur lors de la récupération des groupes :", error);

                    if (error.response.status === 401 || error.response.status === 403) {
                        this.$store.dispatch('auth/logout');
                        this.$router.push('/login');
                    } else {
                        console.error("Erreur lors de la récupération des groupes :", error);
                        this.loading = false;
                    }
                });
        },
        ouvrirModal() {
            // Utilise la référence pour ouvrir la modal
            this.$refs.creationGroupeModal.ouvrirModal();
        },
    },
};

</script>