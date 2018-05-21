<template>
<div>
  <br>

  <label>Gráfico del club {{datos.nombreEleccion}}:</label>
  <br><br>
  <hr>
  <br><br>
  
  <div style="width:50%; float: left;"  >
    <vue-chart  v-if="this.chartData !== null" type="horizontalBar" :data="this.chartData"></vue-chart>
    <div v-else>
      <div class=" lds-css ng-scope">
        <div style="width:100%;height:100%" class="lds-bars">
          <div></div><div></div><div></div><div></div> <div></div>
        </div>
      </div>
    </div>

  </div>

  <div style="width:50%; float: right;"  >

    <vue-chart  v-if="this.chartData !== null" type="pie" :data="this.chartData"></vue-chart>
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
        name: "App",
        props:['datos'],
        components: {
            VueChart
        },

        data: () => ({
        chartData: null,
        barra: true,
        torta: false,
    }),

    created() {
        console.log("estoy creando");
        this.crearGrafico();
        console.log("grafico creado", this.chartData);
    },

    methods: {

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
            var time = date + ' ' + month + ' ' + year  ;
            console.log("el tiempo que va a salir es; ", time);
            return (time);

        },


        crearGrafico()
        {
            console.log(this.idClub)
            this.chartData = {
                labels: [],
                datasets: [
                    {
                        label: "Comentarios positivos",
                        backgroundColor: "#56FB4C",
                        data: []
                    },

                    {
                        label: "Comentarios negativos",
                        backgroundColor: "#FB5C57",
                        data: []
                    },


                ]

            };
            /* Largo  */
            var tam = this._props.datos.dataEquipoSeleccionado.statistics.length
            console.log("el timestamp que quiero cambiar es: ", this._props.datos.dataEquipoSeleccionado.statistics[tam - 1].lastUpdate)

            console.log("el timestamp cambiado es: ", this.timeConverter(this._props.datos.dataEquipoSeleccionado.statistics[tam - 1].lastUpdate))
            /*Primer elemento de fecha*/
            this.chartData.labels.push(this.timeConverter(this._props.datos.dataEquipoSeleccionado.statistics[0].lastUpdate))
            /*Segundo elemento intermedio de fecha*/
            this.chartData.labels.push(this.timeConverter(this._props.datos.dataEquipoSeleccionado.statistics[Math.trunc(tam / 2)].lastUpdate))
            /*Ultima fecha*/
            this.chartData.labels.push(this.timeConverter(this._props.datos.dataEquipoSeleccionado.statistics[tam - 1].lastUpdate))
            /**Comentarios positivos/
             /*Primer elemento de fecha*/
            this.chartData.datasets[0].data.push(this._props.datos.dataEquipoSeleccionado.statistics[0].positive_value)
            /*Segundo elemento intermedio de fecha*/
            this.chartData.datasets[0].data.push(this._props.datos.dataEquipoSeleccionado.statistics[Math.trunc(tam / 2)].positive_value)
            /*Ultima fecha*/
            this.chartData.datasets[0].data.push(this._props.datos.dataEquipoSeleccionado.statistics[tam - 1].positive_value)

            /**Comentarios negativos/
             /*Primer elemento de fecha*/
            this.chartData.datasets[1].data.push(this._props.datos.dataEquipoSeleccionado.statistics[0].negative_value)
            /*Segundo elemento intermedio de fecha*/
            this.chartData.datasets[1].data.push(this._props.datos.dataEquipoSeleccionado.statistics[Math.trunc(tam / 2)].negative_value)
            /*Ultima fecha*/
            this.chartData.datasets[1].data.push(this._props.datos.dataEquipoSeleccionado.statistics[tam - 1].negative_value)

        },

    }

    };
</script>

<style scoped>

</style>