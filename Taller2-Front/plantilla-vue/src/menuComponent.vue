<template>
<div v-if="this.clubs !== null">
    <div class="container">
        <ul class="nav nav-pills">
            <li role="presentation" class="dropdown"  v-on:click="setSelectedItemLiga"><a href="#">Home</a>
            </li>
           <li role="presentation" class="dropdown" v-on:click="setSelectedItem"><router-link to="">Datos por Equipo</router-link></li>
             <li role="presentation" class="dropdown" v-on:click="setSelectedItemE"><router-link to="">Estadisticas Equipos</router-link></li>
            <li role="presentation" class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">
                    Graficos <span class="caret"></span>
                </a>
                <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                    <li v-on:click="setSelectedItemTrofeos"><a href="#">Grafico Trofeos por Equipo Liga Chilena</a> </li>
                </ul>
            </li>
            <li role="presentation"><a href="#">Help</a></li>
        </ul>
    </div>
    <div>
        <div v-if="this.eleccion == 1">
            <Liga-component v-bind:datos="clubs"></Liga-component>
        </div>
        <div v-if="this.eleccion == 2">
            <EquipoDatacomponente v-bind:datos="clubs"></EquipoDatacomponente>
        </div>
        <div v-if="this.eleccion == 3">
            <Equipoestadisticascomponente v-bind:datos="clubs"></Equipoestadisticascomponente>
        </div>
        <div v-if="this.eleccion == 4">
            <Trofeos-component v-bind:datos="clubs"></Trofeos-component>
        </div>
    </div>
</div>
<div v-else>
                <div class=" lds-css ng-scope">
                    <div style="width:100%;height:100%" class="lds-bars">
                        <div></div><div></div><div></div><div></div> <div></div>
                    </div>
                </div>
            </div>
</template>

<script>
import dataEquipo from './dataEquipos.vue';
import estadisticasEquipo from './EstadisticasEquipos.vue';
import ligachilena from './ligaChilena.vue';
import trofeos from './trofeos.vue';
    export default {
        components: {
        'Liga-component': ligachilena,
        'Trofeos-component': trofeos,
        'EquipoDatacomponente': dataEquipo,
        'Equipoestadisticascomponente': estadisticasEquipo
        },
        name: "menuComponent.vue",
        data(){
            return{
                eleccion: 0,
                clubs:null,
            }
        },

    created() {
        console.log("estoy creando");
        this.$http.get("http://159.65.128.52:8080/TBD-G7/club").then(response => {
            this.clubs = response.data;
        console.log("club:" + this.clubs);
        console.log("data de clubes obtenida con exito!", this.clubs);
        this.eleccion = 1;
    });
    },

        methods: {
                setSelectedItem(nombre,equipoid,id){
                    this.eleccion=2;
               
               },
               setSelectedItemE(){
                   this.eleccion=3;
               },
               setSelectedItemTrofeos(){
                   this.eleccion=4;
               },
               setSelectedItemLiga(){
                   this.eleccion=1;
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

<style scoped>

</style>