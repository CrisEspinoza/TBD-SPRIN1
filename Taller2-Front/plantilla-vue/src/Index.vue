<template>
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h1>Bienvenidos al Sprint 1 del Grupo 7 - TDB <small>The beast team :3</small></h1>
        </div>

        <div class="panel-body">
            <hr>
            <menu-component></menu-component>
            <hr>
        </div>
    </div>
</template>

<script>
import menuComponent from './menuComponent.vue';
export default{

    name: '',
    components: {
        'menu-component': menuComponent
    },
    data(){
        return{

        }
    }
}
</script>

<style scoped>

</style>
