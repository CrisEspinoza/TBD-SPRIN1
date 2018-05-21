<template>
    <div>
        <br>

         <div class="">
  <label class="">Gráfico del club {{datos[this.value].name}}:</label>
      <div style="float:right;">
    <label for="select">Seleccione un equipo:</label>
    <select @change="modificarGrafico" v-model="value"  name="" id="select">
        <option :key="i" :value="i" v-for="(equipo,i) in datos">{{equipo.name}}</option>
    </select>

      </div>
  </div>
        <br><br>
        <hr>
        <br><br>

        <div  v-if="this.showd" style="width:50%; float: left;"  >

            <vue-chart   v-if="this.chartData !== null"  type="horizontalBar" :data="this.chartData"></vue-chart>
            <div v-else>
                <div class=" lds-css ng-scope">
                    <div style="width:100%;height:100%" class="lds-bars">
                        <div></div><div></div><div></div><div></div> <div></div>
                    </div>
                </div>
            </div>

        </div>

        <div style="width:50%; float: right;"  >

            <vue-chart  v-if="this.fantasmaData !== null" type="horizontalBar" :data="this.fantasmaData"></vue-chart>
            <div v-else>
                <div class=" lds-css ng-scope">
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
    export default {
        name: "uDeChile",
        props:['datos'],
        components: {
            VueChart
        },

        data: () => ({
        chartData: null,
        value:{},
        showd:false,
        fantasmaData: null,
        barra: true,
        torta: false,
        clubs: []

    }),

    created() {
        this.showd=true;
         this.value=0;
        console.log("estoy creando");
        this.chartData=this.crearGrafico();
        this.fantasmaData=this.crearGraficoFantasma();
        console.log("grafico creado", this.chartData);
    },
  


    methods: {
         modificarGrafico(){
            console.log("*********"+this.datos);
            this.showd=false;
           this.chartData= this.crearGrafico();
           console.log(this.chartData.datasets[0].data);
           this.showd=false;
            this.$nextTick(() => {
                    this.showd = true
                    console.log('re-render start')
                    this.$nextTick(() => {
                        console.log('re-render end')
                    })
                     })
        },

        mostrarBarra()
        {
            this.barra = true;
            this.torta = false;

        }
    ,
        mostrarTorta()
        {
            this.barra = false;
            this.torta = true;

        }
    ,

        timeConverter(timestamp){
            var ent = parseInt(timestamp);
            var a = new Date(ent);
            var months = ['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec'];
            var year = a.getFullYear();
            var month = months[a.getMonth()];
            var date = a.getDate();
            var hour = a.getHours();
            var min = a.getMinutes();
            var sec = a.getSeconds();
            var time = date + ' ' + month + ' ' + year;
            console.log("el tiempo que va a salir es; ", time);
            return (time);

        },

        crearGrafico()
        {
            console.log("*******values es :"+ this.value)
            let chartData = {
                labels: [],
                datasets: [
                    {
                        title: "Gráfico por fechas, popularidad del club",
                        backgroundColor: ["red", "blue", "gray"],
                        data: []
                    },


                ]

            };
            /* Largo  */
            var tam = this.datos[this.value].statistics.length
            console.log("estoy trabjando con el club:", this.datos[this.value].name)
            /*Primer elemento de fecha*/
            chartData.labels.push(this.timeConverter(this.datos[this.value].statistics[0].lastUpdate))
            /*Segundo elemento intermedio de fecha*/
            chartData.labels.push(this.timeConverter(this.datos[this.value].statistics[Math.trunc(tam / 2)].lastUpdate))
            /*Ultima fecha*/
            chartData.labels.push(this.timeConverter(this.datos[this.value].statistics[tam - 1].lastUpdate))
            // console.log("Los datos obtenidos son:", this.chartData.labels)

            /**Comentarios positivos/
             /*Primer elemento de fecha*/
            chartData.datasets[0].data.push(this.datos[this.value].statistics[0].positive_value)
            /*Segundo elemento intermedio de fecha*/
            chartData.datasets[0].data.push(this.datos[this.value].statistics[Math.trunc(tam / 2)].positive_value)
            /*Ultima fecha*/
            chartData.datasets[0].data.push(this.datos[this.value].statistics[tam - 1].positive_value)

            return chartData;
        },

        crearGraficoFantasma()
        {

            let fantasmaData = {
                labels: [],
                datasets: [
                    {
                        backgroundColor: ["red", "blue", "gray"],
                        data: []
                    },


                ]

            };
            /* Largo  */
            var tam = this.datos[16].statistics.length
            /*Primer elemento de fecha*/
           fantasmaData.labels.push(this.timeConverter(this.datos[16].statistics[0].lastUpdate))
            /*Segundo elemento intermedio de fecha*/
            fantasmaData.labels.push(this.timeConverter(this.datos[16].statistics[Math.trunc(tam / 2)].lastUpdate))
            /*Ultima fecha*/
            fantasmaData.labels.push(this.timeConverter(this.datos[16].statistics[tam - 1].lastUpdate))
            console.log("Los datos obtenidos son:", this.chartData.labels)

            /**Comentarios positivos/
             /*Primer elemento de fecha*/
            fantasmaData.datasets[0].data.push(this.datos[16].statistics[0].positive_value)
            /*Segundo elemento intermedio de fecha*/
            fantasmaData.datasets[0].data.push(this.datos[16].statistics[Math.trunc(tam / 2)].positive_value)
            /*Ultima fecha*/
            fantasmaData.datasets[0].data.push(this.datos[16].statistics[tam - 1].positive_value)
            return fantasmaData

        }

    }

    };
</script>