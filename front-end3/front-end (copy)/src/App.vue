<template>
  <div id="app">
    <Navigation :loggedIn="loggedIn" @logout="logout" />
    <router-view />
  </div>
</template>

<script setup>
import { RouterView } from 'vue-router'
import Navigation from './components/navigation.vue'
import { ref, watch } from 'vue';
import { useRouter } from 'vue-router';
import setupAxiosInterceptor from './router/axios-interceptor';

setupAxiosInterceptor();

const loggedIn = ref(false);

const router = useRouter();

const logout = () => {
  loggedIn.value = false;
  router.push('/login');
};

watch(loggedIn, (newValue) => {
  if (!newValue) {
    router.push('/login');
  }
});
</script>

