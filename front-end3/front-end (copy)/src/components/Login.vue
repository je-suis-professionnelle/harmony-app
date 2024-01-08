<template>
  <div class="box">
    <div class="banner_high">
      <div class="language">
        <div class="flag" :class="{ active: isActive }" v-on:click="isActive = !isActive"><img src="../assets/fr_flag.png"
            alt=""></div>
        <div class="flag" :class="{ active: !isActive }" v-on:click="isActive = !isActive"><img
            src="../assets/uk_flag.png" alt=""></div>
      </div>
    </div>

    <h2 class="title">Se connecter</h2>

    <div class="field">
      <label class="label">Pseudo</label>
      <div class="control has-icons-left has-icons-right">
        <input class="input is-success" type="text" v-model="username" placeholder="Entrer votre pseudo">
        <span class="icon is-small is-left">
          <i class="fas fa-user"></i>
        </span>
        <span class="icon is-small is-right">
          <i class="fas fa-check"></i>
        </span>
      </div>
    </div>

    <div class="field">
      <label class="label">Mot de passe</label>
      <div class="control has-icons-left has-icons-right">
        <v-text-field type="password">
          <input class="input" v-model="password" type="password" placeholder="8 caracteres minimum" required />
        </v-text-field>
      </div>
    </div>

    <button class="button is-info" @click="getUser">
      S'inscrire
    </button>

  </div>
</template>
      
      
<script>
import router from '../router'; // Ajoutez cette ligne
export default {

  data() {
    return {
      password: "",
      userLoggedIn: false,
      username: "",
      // mail:"",
      // mail_confirm:"",
      password: "",
      isActive: false,

    };

  },
  toggle() {
    this.isActive = !this.enable;
  },
  methods: {
    getUser() {
      const user = {
        mdp: this.password,
        pseudo: this.username
      };

      fetch("http://localhost:8080/api/users/login", {
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
    },

  }
};

</script>  
