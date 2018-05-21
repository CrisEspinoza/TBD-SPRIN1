<template>
    <div>
        <br>

        <label>Gráfico de trofeos por cada equipo:</label>
        <br><br>
        <hr>
        <br><br>
        <ul class="nav nav-pills">
            <li role="presentation" class="active"><a href="#/index">Volver</a></li>
        </ul>

        <div style="width:100%; float: left;"  >

            <vue-chart  v-if="this.chartData !== null" type="horizontalBar" :data="this.chartData"></vue-chart>
            <div v-else>
                <div class=" lds-css ng-scope">
                    <div style="width:100%;height:100%" class="lds-bars">
                        <div></div><div></div><div></div><div></div> <div></div>
                    </div>
                </div>
            </div>

        </div>

        <div style="width:50%; float:right;">
            <vue-chart v-if="this.chartData !== null" type="doughnut" :data="this.chartData"></vue-chart>
            <div v-else>
                <div class="lds-css  ng-scope">
                    <div style="width:100%;height:100%" class="lds-bars">
                        <div></div><div></div><div></div><div></div> <div></div>
                    </div>
                </div>
            </div>
        </div>


        <div>
            <h1>Descripción:</h1>

            <p> "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore
                et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut
                aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum
                dolore eu fugiat nulla pariatur.
                Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit
                anim id est laborum."
            </p>
        </div>

    </div>
</template>


<script>
    import VueChart from "vue-chart-js";
    Chart.defaults.global.legend.display = false;
    export default {
        name: "trofeos",

        components: {
            VueChart
        },

        data: () => ({
        chartData: null,
        barra:true,
        torta:false,
        clubs: [],
        colores: []

    }),
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
        mostrarBarra(){
            this.barra=true;
            this.torta=false;

        },
        mostrarTorta(){
            this.barra=false;
            this.torta=true;

        },

        /*---------------------------------------------------------------------------------------------------------*/
        /*dejar esta funcion en el for de crear grafico*/
        colorRandom(){
            var color = "#"+((1<<24)*Math.random()|0).toString(16)

          /*    for(let i = 0; i < this.colores.length-1; i++)
                  var color = "#"+((1<<24)*Math.random()|0).toString(16)
                  var n = color.localeCompare(this.colores[i]);
            console.log("")
                 if (n == 0){
                     console.log("econtre un color igual")
                     colorRandom();
                 }
                 else{
                     this.colores.push(color)
                 }*/
            this.colores.push(color)
            console.log("color", color, "agregado")
            console.log("el arreglo de colores es:", this.colores)

        },

        /*---------------------------------------------------------------------------------------------------------*/


        crearGrafico() {

            this.chartData = {
                labels: [],
                datasets: [
                    {
                        backgroundColor: this.colores,
                        data: [],
                    },


                ]

            };
            /* console.log("char data es " + this.chartData);


             console.log("el tamaño es :" + this.clubs.length);*/
            for (let i = 0; i < this.clubs.length-1; i++) {
                this.colorRandom();
                this.chartData.labels = this.chartData.labels.concat([
                    this.clubs[i].name

                ]);
                this.chartData.datasets[0].data.push( this.clubs[i].trophies)
            }
            /*
            console.log("char:" + this.chartData.labels);
             console.log("char2:" +  this.chartData.datasets[0].data);
              console.log("char3:" +  this.chartData.datasets[1].data);*/

        }
    }
    };
</script>
