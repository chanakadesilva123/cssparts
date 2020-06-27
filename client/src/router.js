import Vue from "vue";
import Router from "vue-router";
import Dashboard from "./components/views/Dashboard.vue";
import ThisMonthCharts from "./components/views/ThisMonthCharts.vue";
import LastMonthCharts from "./components/views/LastMonthCharts.vue";
import Last3MonthsCharts from "./components/views/Last3MonthsCharts.vue";

Vue.use(Router);

const router = new Router({
  base: "",
  mode: "history",
  routes: [
    {
      path: "/admin/home",
      name: "home",
      component: Dashboard
    },
    {
      path: "/admin/chartToday",
      name: "chartToday"
    },
    {
      path: "/admin/chartThisMonth",
      name: "chartThisMonth",
      component: ThisMonthCharts
    },
    {
      path: "/admin/chartLastMonth",
      name: "chartLastMonth",
      component: LastMonthCharts
    },
    {
      path: "/admin/chartLast3Months",
      name: "chartLast3Months",
      component: Last3MonthsCharts
    },
    {
      path: "/logout",
      name: "logout"
    }
  ]
});

export default router;
