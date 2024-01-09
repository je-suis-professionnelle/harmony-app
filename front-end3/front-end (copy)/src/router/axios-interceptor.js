// axios-interceptor.js

import axios from 'axios';
import router from './index.js';
import store from '../store/index.js';

function setupAxiosInterceptor() {
  axios.interceptors.response.use(
    (response) => response,
    (error) => {
      if (error.response && (error.response.status === 401)) {
        console.log(error.response.data);
        console.log(error.response.headers); // || error.response.status === 403
        console.log('erreur ', error.response.status, ' interceptée');
        store.dispatch('auth/logout');
        router.push('/login');
      }
      return Promise.reject(error);
    }
  );
}

export default setupAxiosInterceptor;
