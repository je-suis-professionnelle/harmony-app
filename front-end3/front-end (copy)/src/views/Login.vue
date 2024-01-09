<template>
  <section class="hero is-fullheight">
    <div class="hero-body">
      <div class="container">
        <div class="columns is-centered">
          <div class="column is-5-tablet is-4-desktop is-3-widescreen">

            <div v-if="inscriptionSuccess">
              <p>Félicitations, votre inscription a bien été réalisée. Veuillez vous connecter.</p>
            </div>
            <div class="box custom-box">
              <div class="banner_high">
                <div v-if="inscriptionSuccess">
                  <p>Félicitations, votre inscription a bien été réalisée. Veuillez vous connecter.</p>
                </div>
              </div>
              <h2 class="title" style="color: black;">Connexion</h2>
              <Form @submit="handleLogin" :validation-schema="schema">
                <div class="field">
                  <label class="label">Pseudo</label>
                  <div class="control has-icons-left has-icons-right">
                    <Field name="pseudo" type="text" class="input" placeholder="Entrer votre pseudo" />
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
                <div class="">
                  <button class="button is-info" :disabled="loading">
                    Se connecter
                  </button>
                </div>

                <div class="form-group">
                  <div v-if="message" class="alert alert-danger" role="alert">
                    {{ message }}
                  </div>
                </div>
              </Form>
            </div>

          </div>
        </div>
      </div>
    </div>
  </section>
</template>
        
        
<script>
import { Field, ErrorMessage, Form } from "vee-validate";
import * as yup from "yup";

export default {
  name: "Login",
  components: {
    Form,
    Field,
    ErrorMessage,
  },
  data() {
    const schema = yup.object().shape({
      pseudo: yup.string().required("Username is required!"),
      mdp: yup.string().required("Password is required!"),
    });

    return {
      loading: false,
      message: "",
      schema,
      inscriptionSuccess: false,
    };
  },
  created() {
    if (this.$route.query.inscriptionSuccess) {
      this.inscriptionSuccess = true;
    }
  },
  computed: {
    loggedIn() {
      return this.$store.state.auth.status.loggedIn;
    },
  },
  created() {
    if (this.loggedIn) {
      this.$router.push("/groupes");
    }
  },
  methods: {
    handleLogin(user) {
      this.loading = true;

      this.$store.dispatch("auth/login", user).then(
        () => {
          this.$router.push("/groupes");
        },
        (error) => {
          this.loading = false;
          this.message =
            (error.response &&
              error.response.data &&
              error.response.data.message) ||
            error.message ||
            error.toString();
        }
      );
    },
  },
};

</script>  

<style>
.custom-box {
  box-shadow: blue 0px 0px 0px 2px inset, rgb(255, 255, 255) 10px -10px 0px -3px, rgb(31, 193, 27) 10px -10px, rgb(255, 255, 255) 20px -20px 0px -3px, rgb(255, 217, 19) 20px -20px, rgb(255, 255, 255) 30px -30px 0px -3px, rgb(255, 156, 85) 30px -30px, rgb(255, 255, 255) 40px -40px 0px -3px, rgb(255, 85, 85) 40px -40px;
}
</style>