import Vue from 'vue'
import App from './App.vue'
import Router from "@/router/router";
import Store from "@/storage/Store";

import Elements from "@/components/Elements";
import './app.less'
import '@mdi/font/css/materialdesignicons.min.css'

Vue.config.productionTip = true;

Vue.use(Elements);


new Vue({
  router: Router,
  store: Store,
  render: h => h(App),
}).$mount('#app')
