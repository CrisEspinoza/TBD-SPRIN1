<template>
<div>
    <br><br>
    <label>Contenidos de esta página:</label>
    <br><br><br><br>
    <a><img class="imagePos" src="https://image.ibb.co/cD2UOJ/logo.png" alt="logo" border="0"
            width="400" height="400" id="imagePosition"></a>
    <md-content class="md-primary">Gráficos: <br>
        Podrás encontrar las estadísticas de las valoraciones positivas y negativas
    de los equipos de primera liga chilena.</md-content>
    <md-content class="md-accent">Mapas: <br>
    Podrás encontrar en que zonas de Santiago y Chile son en las que más se habla positiva o negativamente de los
    equipos de primera liga chilena</md-content>

</div>
</template>

<style lang="scss" scoped>

    .md-content {
        width: 650px;
        height: 200px;
        display: inline-flex;
        justify-content: center;
        align-items: center;
    }
    /* Color del cuadrado primary */
    .md-content.md-theme-default.md-primary {
        background-color: #ffbe33;
        color: #5d5d5d;
        font-size: 17px;
        @import url('https://fonts.googleapis.com/css?family=Francois+One');
        font-family: 'Francois One', sans-serif;
    }
    /* Color del cuadrado accent*/
    .md-content.md-theme-default.md-accent {
        background-color: #ff8b09;
        color: #474747;
        font-size: 17px;
        @import url('https://fonts.googleapis.com/css?family=Francois+One');
        font-family: 'Francois One', sans-serif;
    }
   .imagePos{
       display: inline-flex;
       justify-content: right;
       align-items: right;
       float: right;
   }

</style>

<script>
    export default {
        name: 'home.vue'
    }
</script>