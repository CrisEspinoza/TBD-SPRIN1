import Vue from 'vue';
import VueRouter from 'vue-router';
import VueResource from 'vue-resource';
import VueChart from 'vue-chart-js';
import User from './User.vue';
import App from './App.vue';
import VueMaterial from 'vue-material';
Vue.use(VueChart)
require("./style.scss");
Vue.use(VueMaterial);
Vue.use(VueRouter);
Vue.use(VueResource);

const routes = [
    {
      path:'*',
      redirect:'/'
    },
    { 
      path: '/', 
      name: 'user',
      component: User
    }
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


