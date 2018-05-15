import Vue from 'vue';
import App from './App.vue';
import VueRouter from 'vue-router';
import approvalTeamsGraph from './approvalTeamsGraph.vue';
import Index from './Index.vue';
import VueResource from 'vue-resource';


require("./style.scss");



Vue.use(VueRouter);
Vue.use(VueResource);


const routes = [
  { path: '/index', alias: '/', component: Index},
    { path: '/approvalTeamsGraph', component: approvalTeamsGraph}
];

// Create the router instance and pass the `routes` option
const router = new VueRouter({
    routes
});

new Vue({
    el: '#app',
    router,
    render: h => h(App)
});


