/* eslint-disable no-unused-vars */
/* eslint-disable no-console */
import Vue from "vue";
import Vuex from "vuex";
import axios from "axios";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    base: "/dashboard",
    apiUrl: "/api/",
    lastUpdated:new Date,
    thisMonth: (new Date).toLocaleString('default', { month: 'long' }),
    thisDay: (new Date).toLocaleDateString()
  },
  mutations: {
    SET_LAST_UPDATED_TIME(state, now) {
      state.lastUpdated = now;
      state.thisMonth = now.toLocaleString('default', { month: 'long' });
    }
  },
  actions: {
    setLastUpdatedTime({ commit }) {
      commit("SET_LAST_UPDATED_TIME", new Date);
    },
    getCurrencyValue({ commit }, sqlView) {
      console.log("getCurrencyValue->sqlView=", sqlView);
      return axios({
        method: "get",
        url: this.state.base+"/api/dashboard/fetch_currency_value",
        params: { sqlView: sqlView }
      });
    },
    getQuntityValue({ commit }, sqlView) {
        console.log("getQuntityValue->sqlView=", sqlView);
        return axios({
          method: "get",
          url: this.state.base+"/api/dashboard/fetch_quntity_value",
          params: { sqlView: sqlView }
        });
    },
    getSalesTarget({ commit }) {
        return axios({
          method: "get",
          url: this.state.base+"/api/dashboard/fetch_sales_target"
        });
    }
  }
})