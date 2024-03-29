import { createRouter, createWebHistory } from 'vue-router'
import Groupe from '../views/Groupe.vue';
import Accueil from '../views/Accueil.vue';

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
{
      path: '/',
      name: 'Accueil',
      component: Accueil
    },
    {
      path: '/groupes/:groupId/:title',
      name: 'Groupe',
      component: Groupe,
      props: (route) => ({
        groupId: parseInt(route.params.groupId, 10),
        title: route.params.title,
      }),
    },
    {
      path: '/register',
      name: 'Register',
      component: () => import('../views/Register.vue')
    },
    {
      path: '/login',
      name: 'Login',
      component: () => import('../views/Login.vue')
    },
    {
      path: '/groupes',
      name: 'Groupes',
      component: () => import('../views/Groupes.vue')
    },
  ]
})

export default router