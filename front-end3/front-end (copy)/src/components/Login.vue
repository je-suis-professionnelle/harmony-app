<!-- <template>
  <div class="col-md-12">
    <div class="card card-container">
      <img id="profile-img" src="//ssl.gstatic.com/accounts/ui/avatar_2x.png" class="profile-img-card" />
      <form name="form" @submit.prevent="handleLogin">
        <div class="form-group">
          <label for="username">Username</label>
          <input v-model="user.username" v-validate="'required'" type="text" class="form-control" name="username" />
          <div v-if="errors.has('username')" class="alert alert-danger" role="alert">Username is required!</div>
        </div>
        <div class="form-group">
          <label for="password">Password</label>
          <input v-model="user.password" v-validate="'required'" type="password" class="form-control" name="password" />
          <div v-if="errors.has('password')" class="alert alert-danger" role="alert">Password is required!</div>
        </div>
        <div class="form-group">
          <button class="btn btn-primary btn-block" :disabled="loading">
            <span v-show="loading" class="spinner-border spinner-border-sm"></span>
            <span>Login</span>
          </button>
        </div>
        <div class="form-group">
          <div v-if="message" class="alert alert-danger" role="alert">{{ message }}</div>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import User from '../models/user';

export default {
  name: 'Login',
  data() {
    return {
      user: new User('', ''),
      loading: false,
      message: ''
    };
  },
  computed: {
    loggedIn() {
      return this.$store.state.auth.status.loggedIn;
    }
  },
  created() {
    if (this.loggedIn) {
      this.$router.push('/profile');
    }
  },
  methods: {
    handleLogin() {
      this.loading = true;
      this.$validator.validateAll().then(isValid => {
        if (!isValid) {
          this.loading = false;
          return;
        }

        if (this.user.username && this.user.password) {
          this.$store.dispatch('auth/login', this.user).then(
            () => {
              this.$router.push('/profile');
            },
            error => {
              this.loading = false;
              this.message =
                (error.response && error.response.data) ||
                error.message ||
                error.toString();
            }
          );
        }
      });
    }
  }
};
</script>

<style scoped>
label {
  display: block;
  margin-top: 10px;
}

.card-container.card {
  max-width: 350px !important;
  padding: 40px 40px;
}

.card {
  background-color: #f7f7f7;
  padding: 20px 25px 30px;
  margin: 0 auto 25px;
  margin-top: 50px;
  -moz-border-radius: 2px;
  -webkit-border-radius: 2px;
  border-radius: 2px;
  -moz-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
  -webkit-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
  box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
}

.profile-img-card {
  width: 96px;
  height: 96px;
  margin: 0 auto 10px;
  display: block;
  -moz-border-radius: 50%;
  -webkit-border-radius: 50%;
  border-radius: 50%;
}
</style> -->

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
