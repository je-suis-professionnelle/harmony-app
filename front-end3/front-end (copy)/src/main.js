import './assets/main.css'

import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from "./store";
// import { VeeValidatePlugin } from 'vee-validate';

const app = createApp(App)

app.use(router)
app.use(store)
// app.use(VeeValidate);
// app.use(VeeValidatePlugin);

app.mount('#app')
