<template>
 <div class="page-container">
     <md-app md-waterfall md-mode="overlap">
         <md-app-toolbar class="md-accent md-large">
             <div class="md-toolbar-row">
                 <md-button class="md-icon-button" @click="menuVisible = !menuVisible">
                     <md-icon>menu</md-icon>
                 </md-button>

                 <span class="md-title">Observatorio Liga Nacional Admin</span>
             </div>
         </md-app-toolbar>

         <md-app-drawer :md-active.sync="menuVisible" md-persistent="full">
             <md-toolbar class="md-transparent" md-elevation="0">
                 Elija la opción que desea ver:
             </md-toolbar>

             <div class="list-group" >
                 <a v-on:click="setSelectedItemHome" href="#" class="list-group-item">Home<md-icon class="position">home</md-icon></a>
                 <a v-on:click="setSelectedItemTrofeos" href="#" class="list-group-item">Trofeos<md-icon class="position">local_play</md-icon></a>
                 <a v-on:click="setSelectedItemLiga" href="#" class="list-group-item">Valoración general<md-icon class="position">insert_chart_outlined</md-icon></a>
                 <a v-on:click="setSelectedItem" href="#" class="list-group-item">Valoraciones por equipo<md-icon class="position">insert_chart_outlined</md-icon></a>
                 <a v-on:click="setSelectedItemE" href="#" class="list-group-item">Comparaciones por equipo<md-icon class="position">insert_chart_outlined</md-icon></a>
                 <a v-on:click="setSelectedComunas" href="#" class="list-group-item">Mapa de Santiago<md-icon class="position">public</md-icon></a>
                 <a v-on:click="setSelectedMapas" href="#" class="list-group-item">Mapa de Chile<md-icon class="position">public</md-icon></a>
                 <a v-on:click="setSelectedBurbuja" href="#" class="list-group-item">Burbujas general<md-icon class="position">bubble_chart</md-icon></a>
                 <a v-on:click="setSelectedBurbUsuario" href="#" class="list-group-item">Burbujas usuarios<md-icon class="position">bubble_chart</md-icon></a>
                 <a v-on:click="setSelectedHerramientas" href="#" class="list-group-item">Herramientas<md-icon class="position">public</md-icon></a>
                 <a v-on:click="setSelectedRegistro" href="#" class="list-group-item">Registro<md-icon class="position">public</md-icon></a>
                 <a v-on:click="setSelectedLogout" href="#" class="list-group-item">Logout<md-icon class="position">public</md-icon></a>
             </div>
         </md-app-drawer>

         <md-app-content class="md-content">
             <div>
                 <div v-if="this.eleccion == 1">
                     <home-component v-bind:datos="clubs"></home-component>
                 </div>
                 <div v-if="this.eleccion == 2">
                     <Liga-component v-bind:datos="clubs"></Liga-component>
                 </div>
                 <div v-if="this.eleccion == 3">
                     <EquipoDatacomponente v-bind:datos="clubs"></EquipoDatacomponente>
                 </div>
                 <div v-if="this.eleccion == 4">
                     <Equipoestadisticascomponente v-bind:datos="clubs"></Equipoestadisticascomponente>
                 </div>
                 <div v-if="this.eleccion == 5">
                     <Trofeos-component v-bind:datos="clubs"></Trofeos-component>
                 </div>
                 <div v-if="this.eleccion == 6">
                     <mapa-component v-bind:datos="mapas"></mapa-component>
                 </div>
                 <div v-if="this.eleccion == 7">
                     <comunas-component v-bind:datos="comunas"></comunas-component>
                 </div>
                 <div v-if="this.eleccion == 8">
                     <login-component></login-component>
                 </div>
                 <div v-if="this.eleccion == 9">
                     <burbujas-component v-bind:datos="burbujas"></burbujas-component>
                 </div>
                 <div v-if="this.eleccion == 10">
                     <burb-usuario-component v-bind:datos="burbujas"></burb-usuario-component>
                 </div>
                 <div v-if="this.eleccion == 11">
                     <tools-component  v-bind:datos="clubs"></tools-component>
                 </div>
                 <div v-if="this.eleccion == 12">
                     <register-component></register-component>
                 </div>
             </div>
         </md-app-content>
     </md-app>
 </div>
 </template>

<script>
     import home from './home.vue';
     import dataEquipo from './dataEquipos.vue';
     import estadisticasEquipo from './EstadisticasEquipos.vue';
     import ligachilena from './ligaChilena.vue';
     import trofeos from './trofeos.vue';
     import mapa from './mapa.vue'
     import comunas from './mapaComunas.vue';
     import burbujas from './bubbleChart.vue';
     import burbUsuario from './bubbleUsuarios.vue';
     import register from './register.vue';
     import tools_equipos from './tools_equipo.vue';
     export default {
         components: {
             'home-component': home,
             'Liga-component': ligachilena,
             'Trofeos-component': trofeos,
             'EquipoDatacomponente': dataEquipo,
             'Equipoestadisticascomponente': estadisticasEquipo,
             'mapa-component': mapa,
             'comunas-component': comunas,
             'burbujas-component': burbujas,
             'burb-usuario-component': burbUsuario,
             'tools-component':tools_equipos,
             'register-component':register
         },

         name: 'Overlap',
         admin: 0,
         data: () => ({
            menuVisible: false,
            eleccion: 0,
            clubs:null,
            mapas: null,
            comunas: null,
            burbujas: null,
         }),
         created() {
             this.$http.get("http://206.189.184.79:8091/TDBG7/club").then(response => {
                 this.clubs = response.data;
                 /* console.log("club:" + this.clubs);
                  console.log("data de clubes obtenida con exito!", this.clubs);*/
                 this.eleccion = 1;
             });
             this.$http.get("http://206.189.184.79:8091/TDBG7/maps").then(response => {
                 this.mapas = response.data;
                /* console.log("club:" + this.mapas);
                 console.log("data de mapas obtenida con exito!", this.mapas);*/
                 this.eleccion = 1;
             });
             this.$http.get("http://206.189.184.79:8091/TDBG7/maps-santiago").then(response => {
                 this.comunas = response.data;
               /*  console.log("club:" + this.comunas);
                 console.log("data de comunas obtenida con exito!", this.comunas);*/
                 this.eleccion = 1;
             });
             this.$http.get("http://206.189.184.79:8091/TDBG7/neo4j").then(response => {
                 this.burbujas = response.data;
                 console.log("club:" + this.burbujas);
                 console.log("data de burbujas obtenida con exito!", this.burbujas);
                 this.eleccion = 1;
             });
         },

         methods: {
             setSelectedItemHome(){
                 this.eleccion=1;
                 this.menuVisible = false;
             },
             setSelectedItemLiga(){
                 this.eleccion=2;
                 this.menuVisible = false;
             },
             setSelectedItem(nombre,equipoid,id){
                 this.eleccion=3;
                 this.menuVisible = false;

             },
             setSelectedItemE(){
                 this.eleccion=4;
                 this.menuVisible = false;
             },
             setSelectedItemTrofeos(){
                 this.eleccion=5;
                 this.menuVisible = false;
             },
             setSelectedMapas(){
                 this.eleccion=6;
                 this.menuVisible = false;
             },
             setSelectedComunas(){
                 this.eleccion=7;
                 this.menuVisible = false;
             },
             setSelectedLogin(){
                 this.eleccion=8;
                 this.menuVisible = false;
             },
             setSelectedBurbuja(){
                 this.eleccion=9;
                 this.menuVisible = false;
             },
             setSelectedBurbUsuario(){
                 this.eleccion=10
                 this.menuVisible = false;
             },
             setSelectedHerramientas(){
                 this.eleccion=11
                 this.menuVisible = false;
             },
             setSelectedRegistro(){
                 this.eleccion=12
                 this.menuVisible = false;
             },
             setSelectedLogout(){
                 this.$router.replace({ path: "/" });
             }

         },
         ready: function() {
             $('.dropdown-submenu a.test').on("click", function(e){
                 $(this).next('ul').toggle();
                 e.stopPropagation();
                 e.preventDefault();
             });
         }
     }
 </script>

 <style>
     .position {
         position: absolute;
         right: 15px;
     }
     .md-toolbar.md-theme-default.md-accent {
         background-color: #787778;
     }
     .md-toolbar.md-theme-default.md-accent .md-title{
     @import url('https://fonts.googleapis.com/css?family=Francois+One');
         font-family: 'Francois One', sans-serif;
         font-size: 35px;
         color: #ffa800;
     }
 </style>