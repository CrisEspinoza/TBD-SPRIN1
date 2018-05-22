<template>
<div v-if="this.clubs !== null">
    <div class="container">
        <ul class="nav nav-pills">
            <li role="presentation" class="dropdown"  v-on:click="setSelectedItemHome">
                <a href="#"><span class="glyphicon glyphicon-home" aria-hidden="true"></span> Home</a>
            </li>
            <li role="presentation" class="dropdown"  v-on:click="setSelectedItemLiga">
                <a href="#" >Estadísticas equipo liga chilena</a>
            </li>
           <li role="presentation" class="dropdown" v-on:click="setSelectedItem"><router-link to="">Datos por Equipo</router-link></li>
             <li role="presentation" class="dropdown" v-on:click="setSelectedItemE"><router-link to="">Estadisticas Equipos</router-link></li>
            <li role="presentation" class="dropdown" v-on:click="setSelectedItemTrofeos">
                <a href="#" >Trofeos por Equipo Liga Chilena</a>
            </li>
        </ul>
    </div>
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
    </div>
</div>
<div v-else style="margin: 0 auto;">
    <div style="margin: 0 auto;">
        <div class="lds-css ng-scope" style="margin: 0 auto;">
            <div style="width:100%;height:100%;margin:0 auto;" class="lds-facebook">
                <div></div><div></div><div></div></div></div>
        
                <!-- <div class=" lds-css ng-scope" style="margin: 0 auto;">
                    <div style="width:100%;height:100%;margin:0 auto;" class="lds-bars">
                        <div></div><div></div><div></div><div></div> <div></div>
                    </div>
                </div> -->
            </div>
    </div>
</template>

<script>
    import home from './home.vue';
    import dataEquipo from './dataEquipos.vue';
    import estadisticasEquipo from './EstadisticasEquipos.vue';
    import ligachilena from './ligaChilena.vue';
    import trofeos from './trofeos.vue';
    export default {
        components: {
            'home-component': home,
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
        this.$http.get("http://159.65.128.52:8080/TBD-G7/club").then(response => {
            this.clubs = response.data;
        console.log("club:" + this.clubs);
        console.log("data de clubes obtenida con exito!", this.clubs);
        this.eleccion = 1;
    });
    },

        methods: {
            setSelectedItemHome(){
                this.eleccion=1;
            },
            setSelectedItemLiga(){
                this.eleccion=2;
            },
            setSelectedItem(nombre,equipoid,id){
                this.eleccion=3;

            },
            setSelectedItemE(){
               this.eleccion=4;
            },
            setSelectedItemTrofeos(){
               this.eleccion=5;
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