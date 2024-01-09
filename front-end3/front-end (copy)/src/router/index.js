import { createRouter, createWebHistory } from 'vue-router'
import Groupe from '../views/Groupe.vue';

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
{
      path: '/accueil',
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

router.beforeEach((to, from, next) => {
  const publicPages = ['/login', '/register'];
  const authRequired = !publicPages.includes(to.path);
  const loggedIn = localStorage.getItem('user');

  if (authRequired && !loggedIn) {
    next('/login');
  } else {
    next();
  }
});

export default router