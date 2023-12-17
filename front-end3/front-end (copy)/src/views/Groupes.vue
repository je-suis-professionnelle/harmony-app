<template>
    <article class="panel is-primary">
        <p class="panel-heading">
            Vos groupes
        </p>
        <!-- <p class="panel-tabs">
    <a class="is-active">All</a>
    <a>Public</a>
    <a>Private</a>
    <a>Sources</a>
    <a>Forks</a>
  </p> -->
        <div class="panel-block">
            <p class="control has-icons-left">
                <input class="input is-primary" type="text" placeholder="Search">
                <span class="icon is-left">
                    <i class="fas fa-search" aria-hidden="true"></i>
                </span>
            </p>
            <div>
                <button class="button is-primary" @click="createGroupe">Créer un groupe</button>
            </div>
        </div>
        <Groupe v-for="groupe in groupes" :key="groupe.id" :groupe="groupe" />
    </article>
</template>

<script>
import Groupe from '../components/Groupe.vue'
export default {
    name: 'Groupes',
    props: {
        pseudo: {
            type: String,
            required: true,
        },
    },
    components: {
        Groupe
    },
    data() {
        return {
            groupes: [], // Liste des groupes récupérée du serveur
        };
    },
    methods: {
        getGroupes() {

            fetch(`http://localhost:8080/users/${pseudo}/groupes`)
                .then(response => {
                    if (!response.ok) {
                        throw new Error("Erreur lors de la requête");
                    }
                    return response.json();
                })
                .then(data => {
                    this.groupes = data;
                })
                .catch(error => {
                    console.error("Erreur lors de la récupération des groupes :", error);
                });
        },
        createGroupe() {
            fetch(`http://localhost:8080/groupes/${pseudo}`, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(user),
            })
                .then((res) => {
                    if (!res.ok) {
                        throw new Error("Erreur lors de la requête");
                    }
                    return res.json();
                })
                .then((res) => {
                    console.log(res);
                    if (res.error) {
                        alert(res.error);
                    } else {
                        this.userLoggedIn = true;
                        console.log("Vous êtes connecté");
                        // Vérifiez si l'utilisateur est renvoyé dans la réponse
                        // if (res) {
                        console.log("Utilisateur reçu :", res);
                        router.push({ name: 'Groupes', params: { pseudo: this.username } });
                        // }
                    }
                })
                .catch((error) => {
                    console.error("Erreur lors de la connexion :", error);
                });
        }
    },
    mounted() {
        // Appeler la méthode getGroupes lorsque le composant est monté
        this.getGroupes();
    },
};

</script>