<template>
    <div>
            <div class="container">
                <div class="page-header">
                    <h1>Bienvenidos al Sprint 1 del Grupo 7 - TDB <small>The beast team :3</small></h1>
                </div>
            </div>
        <menu-component></menu-component>
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
