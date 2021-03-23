import Vue from 'vue'
import App from './App.vue'
import VueMaterial from "vue-material";
import Router from "@/router/router";
import Store from "@/storage/Store";

import 'vue-material/dist/vue-material.min.css'
import './vue-material-theme.scss'
import './app.less'


import '@mdi/font/css/materialdesignicons.min.css'

Vue.config.productionTip = true;

Vue.use(VueMaterial);


new Vue({
  router: Router,
  store: Store,
  render: h => h(App),
}).$mount('#app')
