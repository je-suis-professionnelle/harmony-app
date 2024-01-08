<template>
  <div class="box">
    <h2 class="title">S'inscrire</h2>

    <form @submit.prevent="handleRegister">
      <div class="field">
        <label class="label">Pseudo</label>
        <div class="control has-icons-left has-icons-right">
          <input class="input" type="text" v-model="user.username" placeholder="Entrer votre pseudo">
          <span class="icon is-small is-left">
            <i class="fas fa-user"></i>
          </span>
          <span class="icon is-small is-right">
            <i class="fas fa-check" v-if="user.username.length >= 5"></i>
          </span>
        </div>
        <p class="help is-success" v-if="user.username.length >= 5">This username is available</p>
      </div>

      <div class="field">
        <label class="label">Mot de passe</label>
        <div class="control has-icons-left has-icons-right">
          <input class="input" v-model="user.password" type="password" placeholder="8 caracteres minimum" required />
          <!-- You can add password strength indicators here if needed -->
        </div>
      </div>

      <div class="field">
        <label class="label">Adresse Mail</label>
        <div class="control">
          <input class="input" v-model="user.email" type="email" placeholder="Entrer votre adresse mail" />
        </div>
      </div>

      <div class="field">
        <label class="label">Confirmer l'adresse mail</label>
        <div class="control">
          <input class="input" v-model="user.emailConfirm" type="email" placeholder="Confirmer l'adresse mail ..." />
        </div>
      </div>

      <!-- Add other fields as needed -->

      <button class="button is-info">
        S'inscrire
      </button>
    </form>

    <span>Déja inscrit ? <a href="/">Se connecter</a> </span>
  </div>
</template>
    
    
<script>


import User from '../models/user';

export default {
  name: 'inscrire',
  data() {
    return {
      user: new User('', '', ''),
      submitted: false,
      successful: false,
      message: ''
    };
  },
  computed: {
    loggedIn() {
      return this.$store.state.auth.status.loggedIn;
    }
  },
  mounted() {
    if (this.loggedIn) {
      this.$router.push('/profile');
    }
  },
  methods: {
    handleRegister() {
      this.message = '';
      this.submitted = true;
      this.$validator.validate().then(isValid => {
        if (isValid) {
          this.$store.dispatch('auth/register', this.user).then(
            data => {
              this.message = data.message;
              this.successful = true;
            },
            error => {
              this.message =
                (error.response && error.response.data) ||
                error.message ||
                error.toString();
              this.successful = false;
            }
          );
        }
      });
    }
  }
};

</script>
    
    
    
    
    