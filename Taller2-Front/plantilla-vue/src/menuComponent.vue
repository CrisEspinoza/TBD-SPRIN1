<template>
<div v-if="this.clubs !== null">
    <div class="container">
        <ul class="nav nav-pills">
            <li role="presentation" class="active"><a href="#/index">Home</a></li>
           <li role="presentation" class="active" @click="setSelectedItem"><router-link to="#">Datos por Equipo</router-link></li>
             <li role="presentation" class="active" @click="setSelectedItemE"><router-link to="#">Estadisticas Equipos</router-link></li>
            <li role="presentation" class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">
                    Graficos <span class="caret"></span>
                </a>
                <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                    <li><a href="#/trofeos">Grafico Trofeos por Equipo Liga Chilena</a> </li>
                    <li><a href="#/ligaChilena">Grafico Liga Chilena</a></li>
                </ul>
            </li>
            <li role="presentation"><a href="#">Help</a></li>
        </ul>
    </div>
    <div>
        <div v-if="this.eleccion == 1">
            <EquipoDatacomponente v-bind:datos="clubs"></EquipoDatacomponente>
        </div>
        <div v-else>
            <Equipoestadisticascomponente v-bind:datos="clubs"></Equipoestadisticascomponente>
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
    export default {
        components: {
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
    });
    },

        methods: {
                setSelectedItem(nombre,equipoid,id){
                    this.eleccion=1;
               
               },
               setSelectedItemE(){
                   this.eleccion=0
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