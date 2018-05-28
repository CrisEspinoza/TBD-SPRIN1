<template>
    <div class="panel panel-primary">
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
