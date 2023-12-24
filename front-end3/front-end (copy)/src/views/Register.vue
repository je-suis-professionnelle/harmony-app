<template>
  <div class="box">
    <h2 class="title">S'inscrire {{ this.$store.state.auth.loggedIn }}</h2>

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

    <span>Déjà inscrit ? <router-link to="/login">Se connecter</router-link> </span>
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
          this.$router.push({ path: '/login', query: { inscriptionSuccess: true } });
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
