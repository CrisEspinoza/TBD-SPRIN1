import Vue from 'vue';
import VueRouter from 'vue-router';
import trofeos from './trofeos.vue';
import VueResource from 'vue-resource';
import bubble from './bubbleChart.vue';
import VueChart from 'vue-chart-js';
import Admin from './Admin.vue';
import User from './User.vue';
import App from './App.vue';
import VueMaterial from 'vue-material';
Vue.use(VueChart)
require("./style.scss");
Vue.use(VueMaterial);
Vue.use(VueRouter);
Vue.use(VueResource);

const routes = [
    { path: '/', component: User},
    { path: '/Admin', component: Admin}
]

// Create the router instance and pass the `routes` option
const router = new VueRouter({
  routes
})

new Vue({
  el: '#app',
  router,
  render: h => h(App)

})


