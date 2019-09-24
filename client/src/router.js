import Vue from "vue";
import Router from "vue-router";
import Dashboard from "./components/views/Dashboard.vue";

Vue.use(Router);

const router = new Router({
  mode: "history",
  routes: [
    {
      path: "/admin/home",
      name: "home",
      component: Dashboard
    },
    {
      path: "/logout",
      name: "logout"
    }
  ]
});

export default router;
