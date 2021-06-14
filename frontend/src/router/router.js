import Vue from 'vue';

import Store from "@/storage/Store";
import VueRouter from "vue-router";
import routes from './routes'

Vue.use(VueRouter);

const Router = new VueRouter({
    mode: "history",
    routes: routes
});

Store.subscribe((mutation) => {
    if(mutation.type === "User/setUserLogged") {
        if(mutation.payload !== true){
            console.log("User logged out");
            let routeMeta = Router.currentRoute.meta;
            if(!routeMeta.allowWithoutLogin || !routeMeta.blockLogged)
                Router.push("/login");
        }
    }
});

Router.beforeEach((to, from, next)=> {
    if(Store.state.User.isLogged){
        if(to.matched.some(record => record.meta.blockLogged)){
            next({path: '/'})
            return;
        }
    }
    else {
        if(to.matched.some(record =>
            record.meta.allowWithoutLogin
            || record.meta.blockLogged)
        ){
            next();
            return;
        }
        next({path: '/'})
    }
    next();
})

export default Router;
