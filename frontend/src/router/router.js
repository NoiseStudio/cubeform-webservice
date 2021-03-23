import Vue from 'vue';

import VueRouter from "vue-router";
import routes from './routes'

Vue.use(VueRouter);

const Router = new VueRouter({
    mode: "history",
    routes: routes
});

export default Router;
