<template>
  <nav class="navbar is-primary" role="navigation" aria-label="main navigation">
    <div class="navbar-brand">
      <router-link class="navbar-item" style="max-height: 128px;" to="/">
        <img src="@/assets/logo2.png" style="max-height: 80px;">
        <p class="navbar-item" style="color: white;">HARMONY</p>
      </router-link>
      <a role="button" class="navbar-burger" aria-label="menu" aria-expanded="false" data-target="navbarBasicExample">
        <span aria-hidden="true"></span>
        <span aria-hidden="true"></span>
        <span aria-hidden="true"></span>
      </a>
    </div>

    <div id="navbarBasicExample" class="navbar-menu">
      <div class="navbar-start">
        <!-- <div class="buttons">
          <router-link v-if="this.$store.state.auth.status.loggedIn" class="button is-ghost" to="/groupes">
            <strong>Mes groupes</strong>
          </router-link>
        </div> -->

      </div>
      <!-- <div class="buttons">
        
        <router-link v-if="!this.$store.state.auth.status.loggedIn" class="button is-light" to="/login">
          Se connecter
        </router-link>
        <div v-if="this.$store.state.auth.status.loggedIn" class="button is-light" @click="logout">
          Se déconnecter
        </div>
      </div> -->
      <!-- <div class="navbar-start">
        <router-link class="navbar-item" to="/home">
          Home
        </router-link>

        <div class="navbar-item has-dropdown is-hoverable">
          <a class="navbar-link">
            More
          </a>

          <div class="navbar-dropdown">
            <router-link class="navbar-item" to="/about">
              About
            </router-link>
            <router-link class="navbar-item" to="/contact">
              Contact
            </router-link>
            <hr class="navbar-divider">
            <router-link class="navbar-item" to="/report">
              Report an issue
            </router-link>
          </div>
        </div>
      </div> -->

      <div class="navbar-end">
        <div class="navbar-item">
          <div class="buttons">
            <router-link v-if="!this.$store.state.auth.status.loggedIn" class="button is-primary" to="/register">
              <strong>S'inscrire</strong>
            </router-link>
            <router-link v-if="!this.$store.state.auth.status.loggedIn" class="button is-light" to="/login">
              Se connecter
            </router-link>
            <router-link v-if="this.$store.state.auth.status.loggedIn" class="button is-info" to="/groupes">
              <strong>Mes groupes</strong>
            </router-link>
            <div v-if="this.$store.state.auth.status.loggedIn" class="button is-light" @click="logout">
              Se déconnecter
            </div>
          </div>
        </div>
      </div>
    </div>
  </nav>
</template>

<script>
export default {
  mounted() {
    this.initializeNavbarBurger();
  },
  methods: {
    logout() {
      this.$store.dispatch('auth/logout')
      this.$router.push('/login')
    },
    initializeNavbarBurger() {
      const navbarBurgers = Array.prototype.slice.call(document.querySelectorAll('.navbar-burger'), 0);

      navbarBurgers.forEach(el => {
        el.addEventListener('click', () => {
          const target = el.dataset.target;
          const $target = document.getElementById(target);

          el.classList.toggle('is-active');
          $target.classList.toggle('is-active');
        });
      });
    }
  }
};
</script>