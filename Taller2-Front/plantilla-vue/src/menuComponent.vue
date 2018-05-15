<template>
    <div class="container">
        <ul class="nav nav-pills">
            <li role="presentation" class="active"><a href="#/index">Home</a></li>
            <li role="presentation"><a href="#">Help</a></li>
            <li role="presentation"><a href="#">Equipos</a></li>
            <li role="presentation" class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">
                    Graficos <span class="caret"></span>
                </a>
                <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                    <li><a href="#/approvalTeamsGraph">Grafico1</a></li>
                    <li><a href="#/approvalTeamsGraph">Grafico2</a></li>
                    <li><a href="#/approvalTeamsGraph">Grafico3</a></li>
                </ul>
            </li>

        </ul>
    </div>
</template>

<script>
    export default {
        name: "menuComponent.vue",
        data(){
            return{

            }
        }
    }
</script>

<style scoped>

</style>