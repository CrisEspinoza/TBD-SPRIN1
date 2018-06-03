<template>
<div id="register">
        <h1>Registro</h1>
        <input type="text" name="username" v-model="input.name" placeholder="Username" />
        <input type="password" name="password" v-model="input.password" placeholder="Password" />
        <input type="text" name="email" v-model="input.email" placeholder="email" />
        <div>
            <button type="button" v-on:click="CrearUser()">Registrarse</button>
        </div>
</div>
</template>

<script>
    export default {
        name: "register",
        data(){
            return{
                respuesta: {},
                input: {
                    name: "",
                    password: "",
                    email: ""
                }
            }
        },

        methods: {
            CrearUser(){
                if(this.input.name != "" && this.input.password != "" && this.input.email != "") {
                    //realizar el post a la base de datos para guardar a el usuario
                    let json = {
                        name: this.input.name,
                        password: this.input.password,
                        email: this.input.email
                    }
                    console.log(json)
                    this.$http.post("http://159.65.128.52:8080/TBD-G7/user",json).then(response => {
                        this.respuesta = response.data;
                        this.input.name = ""
                        this.input.password = ""
                        this.input.email = ""
                        console.log("Administrador ingresado con exito!")
                        alert("Persona registrada con exito!")
                    });
                } else {
                    console.log("No se ingreso user/clave o email");
                }
                this.eleccion=1;
            },
            setSelectedItemLiga(){
                this.eleccion=2;
            }

        }
    }
</script>

<style scoped>
    #register {
        width: 500px;
        border: 1px solid #CCCCCC;
        background-color: #FFFFFF;
        margin: auto;
        margin-top: 200px;
        padding: 20px;
    }
</style>