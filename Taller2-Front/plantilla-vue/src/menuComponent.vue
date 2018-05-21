<template>
<div>
    <div class="container">
        <ul class="nav nav-pills">
            <li role="presentation" class="active"><a href="#/index">Home</a></li>
            <li role="presentation" class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">
                    Equipos <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                    <li v-for="item in menuItems" v-bind:class="{'dropdown-submenu': item.children}">
                        <a class="test" tabindex="-1" >{{item.name}}<span class="caret" v-if="item.children"></span></a>
                        <ul class="dropdown-menu" v-if="item.children">
                        <li v-for="child in item.children">
                            <a tabindex="-1" v-if="child.id == 1" href ="/#" @click="setSelectedItem(item.name,item.IdEquipo,child.id)" >
                                {{child.name}}
                            </a>
                            <a tabindex="-1" v-else="child.id === 2" href ="/#" @click="setSelectedItem(item.name,item.IdEquipo,child.id)" >{{child.name}}</a></li>
                        </ul>
                </li>  
                </ul>
            </li>
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
            <EquipoDatacomponente v-bind:datos="datos"></EquipoDatacomponente>
        </div>
        <div v-else-if="this.eleccion == 2">
            <Equipoestadisticascomponente v-bind:datos="datos"></Equipoestadisticascomponente>
        </div>

    </div>
</div>
</template>

<script>
import dataEquipo from './dataEquipos.vue';
import estadisticasEquipo from './estadisticasEquipos.vue';
    export default {
        components: {
        'EquipoDatacomponente': dataEquipo,
        'Equipoestadisticascomponente': estadisticasEquipo
        },
        name: "menuComponent.vue",
        data(){
            return{
                eleccion: 0,
                clubs:[],
                datos: {
                    nombreEleccion: '',
                    idEquipoEleccion: 0,
                    dataEquipoSeleccionado:[],
                    dataEquipoFantasma:[],
                    chartdata: null,
                },
                IdEquipo: 0,
                menuItems: [
                {
                    name: 'Curico Unido',
                    children: [{name: 'Graficos Club',id:1},{name: 'Estadisticas Club',id:2}],
                    IdEquipo: 6
                },
                {
                    name: 'Colo Colo',
                    children: [{name: 'Graficos Club',id:1},{name: 'Estadisticas Club',id:2}],
                    IdEquipo: 2
                },
                {
                    name: 'Universidad Catolica',
                    children: [{name: 'Graficos Club',id:1},{name: 'Estadisticas Club',id:2}],
                    IdEquipo: 1
                },
                {
                    name: 'Universidad de Chile',
                    children: [{name: 'Graficos Club',id:1},{name: 'Estadisticas Club',id:2}],
                    IdEquipo: 3
                },
                {
                    name: 'Universidad de Concepcion',
                    children: [{name: 'Graficos Club',id:1},{name: 'Estadisticas Club',id:2}],
                    IdEquipo: 14
                },
                {
                    name: 'Antofagasta',
                   children: [{name: 'Graficos Club',id:1},{name: 'Estadisticas Club',id:2}],
                    IdEquipo: 4
                },
                {
                    name: 'Audax Italiano',
                    children: [{name: 'Graficos Club',id:1},{name: 'Estadisticas Club',id:2}],
                    IdEquipo: 5
                },
                {
                    name: 'Everton CD',
                    children: [{name: 'Graficos Club',id:1},{name: 'Estadisticas Club',id:2}],
                    IdEquipo: 9
                },
                {
                    name: 'Deportes Iquique',
                    children: [{name: 'Graficos Club',id:1},{name: 'Estadisticas Club',id:2}],
                    IdEquipo: 7
                },
                {
                    name: 'Deportes Temuco',
                    children: [{name: 'Graficos Club',id:1},{name: 'Estadisticas Club',id:2}],
                    IdEquipo: 8
                },
                {
                    name: 'OHiggins',
                    children: [{name: 'Graficos Club',id:1},{name: 'Estadisticas Club',id:2}],
                    IdEquipo: 11
                },
                {
                    name: 'Huachipato',
                    children: [{name: 'Graficos Club',id:1},{name: 'Estadisticas Club',id:2}],
                    IdEquipo: 10
                },
                {
                    name: 'Paletino',
                    children: [{name: 'Graficos Club',id:1},{name: 'Estadisticas Club',id:2}],
                    IdEquipo: 12
                },
                {
                    name: 'San Luis',
                    children: [{name: 'Graficos Club',id:1},{name: 'Estadisticas Club',id:2}],
                    IdEquipo: 13
                },
                {
                    name: 'Union Española',
                    children: [{name: 'Graficos Club',id:1},{name: 'Estadisticas Club',id:2}],
                    IdEquipo: 15
                },
                {
                    name: 'Union la calera',
                    children: [{name: 'Graficos Club',id:1},{name: 'Estadisticas Club',id:2}],
                    IdEquipo: 16
                }
            ],
                selectedDropdown: 'None'
            }
        },

    created() {
        console.log("estoy creando");
        this.$http.get("http://159.65.128.52:8080/TBD-G7/club").then(response => {
            this.clubs = response.data;
        console.log("club:" + this.clubs);
        this.crearGrafico();
        console.log("grafico creado", this.chartData);
    });
    },

        methods: {
                setSelectedItem(nombre,equipoid,id){
                    console.log(nombre);
                    console.log(equipoid);
                    console.log(id);
                    this.eleccion = id;
                    this.datos.idEquipoEleccion = equipoid;
                    this.datos.dataEquipoSeleccionado = this.clubs[equipoid];
                    this.datos.nombreEleccion = nombre;
                    this.datos.dataEquipoFantasma = this.clubs[16];
                    console.log(this.eleccion);
                    console.log(this.idEquipoEleccion);
                    console.log(this.clubs);
                    console.log(this.datos);
                    
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