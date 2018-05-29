<template>
    <div class="panel panel-primary">
        <div class="panel-body">
            <header>
                <label class="center">Observatorio Liga Nacional - OLN</label>
            </header>
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
    @import url('//maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css');
    header {

        padding: 10px 20px;
        background-color: midnightblue;
        display: flex;
        align-items: center;
        justify-content: space-between;
    }
</style>
