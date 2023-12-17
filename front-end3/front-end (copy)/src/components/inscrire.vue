<!-- <template>
  <div class="col-md-12">
    <div class="card card-container">
      <img id="profile-img" src="//ssl.gstatic.com/accounts/ui/avatar_2x.png" class="profile-img-card" />
      <form name="form" @submit.prevent="handleRegister">
        <div v-if="!successful">
          <div class="form-group">
            <label for="username">Username</label>
            <input v-model="user.username" v-validate="'required|min:3|max:20'" type="text" class="form-control"
              name="username" />
            <div v-if="submitted && errors.has('username')" class="alert-danger">{{ errors.first('username') }}</div>
          </div>
          <div class="form-group">
            <label for="email">Email</label>
            <input v-model="user.email" v-validate="'required|email|max:50'" type="email" class="form-control"
              name="email" />
            <div v-if="submitted && errors.has('email')" class="alert-danger">{{ errors.first('email') }}</div>
          </div>
          <div class="form-group">
            <label for="password">Password</label>
            <input v-model="user.password" v-validate="'required|min:6|max:40'" type="password" class="form-control"
              name="password" />
            <div v-if="submitted && errors.has('password')" class="alert-danger">{{ errors.first('password') }}</div>
          </div>
          <div class="form-group">
            <button class="btn btn-primary btn-block">Sign Up</button>
          </div>
        </div>
      </form>

      <div v-if="message" class="alert" :class="successful ? 'alert-success' : 'alert-danger'">{{ message }}</div>
    </div>
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


// export default {

//   data() {
//     return {
//       password: "",
//       userLoggedIn: false,
//       username: "",
//       // mail:"",
//       // mail_confirm:"",
//       password: "",
//       isActive: false,

//     };

//   },
//   toggle() {
//     this.isActive = !this.enable;
//   },
//   methods: {
//     createUser() {
//       const user = {
//         mdp: this.password,
//         pseudo: this.username
//       };
//       fetch("http://localhost:8080/api/users", {
//         method: "POST",
//         headers: {
//           "Content-Type": "application/json",
//         },
//         body: JSON.stringify(user),
//       })
//         .then((res) => res.json())
//         .then((res) => {
//           console.log(res);
//           if (res.error) {
//             alert(res.error);
//           } else {
//             this.userLoggedIn = true;
//             // this.$router.push("/home");
//           }
//         });
//     },
//   }
// };

</script>
    
    
    
    
    