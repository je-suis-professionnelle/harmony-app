<template>
  <div class="box">
    <h2 class="title">S'inscrire</h2>

    <Form @submit="handleRegister" :validation-schema="schema">
      <div v-if="!successful">
        <div class="field">
          <label class="label">Pseudo</label>
          <div class="control has-icons-left has-icons-right">
            <Field name="pseudo" type="text" class="input" placeholder="Entrer votre pseudo" />
            <!-- <span class="icon is-small is-left">
              <i class="fas fa-user"></i>
            </span> -->
            <!-- <span class="icon is-small is-right">
              <i class="fas fa-check" v-if="user.pseudo.length >= 5"></i>
            </span> -->
          </div>
          <ErrorMessage name="pseudo" class="help is-danger" />
        </div>

        <div class="field">
          <label class="label">Mot de passe</label>
          <div class="control has-icons-left has-icons-right">
            <Field name="mdp" type="password" class="input" placeholder="8 caracteres minimum" />
          </div>
          <ErrorMessage name="mdp" class="help is-danger" />
        </div>
      </div>
      <div class="">
        <button class="button is-info" :disabled="loading">
          Sign Up
        </button>
      </div>

    </Form>
    <div v-if="message" class="alert" :class="successful ? 'alert-success' : 'alert-danger'">
      {{ message }}
    </div>

    <span>Déjà inscrit ? <router-link to="/">Se connecter</router-link> </span>
  </div>
</template>

<script>
import { Field, ErrorMessage, Form } from "vee-validate";
import * as yup from "yup";

export default {
  name: "Register",
  components: {
    Form,
    Field,
    ErrorMessage,
  },
  data() {
    return {
      successful: false,
      loading: false,
      message: '',
      schema: yup.object().shape({
        pseudo: yup.string()
                    .required("Le pseudo est requis !")
                    .min(5, "Le Pseudo doit avoir au moins 5 caractères !")
                    .max(20, "Le Pseudo ne doit pas dépasser 20 caractères !"),
        mdp: yup.string()
                    .required("Le mot de passe est requis !")
                    .min(8, "Le mot de passe doit avoir au moins 8 caractères !")
                    .max(20, "Le mot de passe ne doit pas dépasser 20 caractères !"), 
        // Add other fields and validations as needed
      }),
    };
  },
  computed: {
    loggedIn() {
      return this.$store.state.auth.status.loggedIn;
    }
  },
  mounted() {
    if (this.loggedIn) {
      this.$router.push('/groupes');
    }
  },
  // watch: {
  // loggedIn(newValue) {
  //   if (newValue) {
  //     this.$router.push('/groupes');
  //   }
  // },
// },
  methods: {
    handleRegister(user) {
      this.message = "";
      this.successful = false;
      this.loading = true;

      this.$store.dispatch("auth/register", user).then(
        (data) => {
          this.message = data.message;
          this.successful = true;
          this.loading = false;
          this.$router.push('/groupes');
        },
        (error) => {
          this.message =
            (error.response &&
              error.response.data &&
              error.response.data.message) ||
            error.message ||
            error.toString();
          this.successful = false;
          this.loading = false;
        }
      );
    }
  }
};
</script>

<!-- <template>
  <inscrire/>
</template>
  
<script>
// @ is an alias to /src

import inscrire from "@/components/inscrire.vue";
export default {
  name: "About",
  components: {
    inscrire
  },
};
</script> -->

<!-- <template>
  <div class="box">
    <h2 class="title">S'inscrire</h2>

    <form @submit.prevent="handleRegister">
      <div class="field">
        <label class="label">Pseudo</label>
        <div class="control has-icons-left has-icons-right">
          <input class="input" type="text" v-model="user.pseudo" placeholder="Entrer votre pseudo">
          <span class="icon is-small is-left">
            <i class="fas fa-user"></i>
          </span>
          <span class="icon is-small is-right">
            <i class="fas fa-check" v-if="user.pseudo.length >= 5"></i>
          </span>
        </div>
        <p class="help is-success" v-if="user.pseudo.length >= 5">This pseudo is available</p>
      </div>

      <div class="field">
        <label class="label">Mot de passe</label>
        <div class="control has-icons-left has-icons-right">
          <input class="input" v-model="user.mdp" type="mdp" placeholder="8 caracteres minimum" required />
        </div>
      </div>

      <button class="button is-info">
        S'inscrire
      </button>
    </form>

    <span>Déjà inscrit ? <a href="/">Se connecter</a> </span>
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
      // Implement your form validation logic here if needed

      // Assuming validation passed, dispatch the registration
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
  }
};
</script> -->