<template>
  <nav class="navbar is-primary" role="navigation" aria-label="main navigation">
    <div class="navbar-brand">
      <router-link class="navbar-item" to="/accueil">
        <img src="./assets/images/a-rainbow-shaped-dollar-emoji.png" width="50" height="80">
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
      </div>

      <div class="navbar-end">
        <div class="navbar-item">
          <div class="buttons">
            <router-link class="button is-primary" to="/register">
              <strong>Sign up</strong>
            </router-link>
            <router-link class="button is-light" to="/login">
              Log in
            </router-link>
            <!-- ou afficher le profil hihi -->
          </div>
        </div>
      </div>
    </div>
  </nav>
</template>

<script>
export default {
  data() {
    return {}
  },
  methods: {}
};
</script>

<style scoped>
/* Vos styles spécifiques à ce composant, s'ils sont nécessaires */
</style>