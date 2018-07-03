<template>
<div class="form-style-10">
    <h1>Bienvenido!<span>Herramientas de Administrador</span></h1>
    <div class="section"><span>1</span>Agregar un nuevo Equipo a la liga</div>
        <div class="inner-wrap">
            <div>
                <label class="section">Nuevo Equipo</label>
                <br>
                <div>
                    <label class="section">Nombre Equipo</label>
                    <br>
                    <input v-model="nombre" type="text"/>
                </div>
                <br>
                <div>
                    <label class="section">Apodo</label>
                    <br>
                    <input v-model="nickname" type="text"/>
                </div>
                <br>
                <div>
                    <label class="section">Nuevo Equipo</label>
                    <br>
                    <input v-model="url" type="text"/>
                </div>
                <br>
                <div>
                    <label class="section">Nuevo Equipo</label>
                    <br>
                    <input v-model="trofeos" type="number"/>
                </div>
            </div>
            <br>
          	<div class="button-section">
              <button type="submit" v-on:click="AgregarEquipo()">Agregar</button>
            </div>
        </div>

    <div class="section"><span>2</span>Eliminar un Equipo</div>
          <div class="inner-wrap">
            <label>Seleciconar un equipo</label>
                  <select class="custom-select"  v-model="value_e"  name="key" id="select">
                      <option v-if="i!==16" :key="i" :value="i + 1" v-for="(clubs,i) in datos">{{clubs.name}}</option>
                  </select>
            <br>
            <div class="button-section">
              <button type="submit" v-on:click="listaKeywork()">Eliminar</button>
            </div>
          </div>
  
 	<div class="section"><span>3</span>Eliminar Keywords</div>
        <div class="inner-wrap">
            <label>Seleciconar un equipo</label>
                  <select class="custom-select"  v-model="value_ek"  name="key" id="select"  v-on:change="listaKeyworks()">
                      <option v-if="i!==16" :key="i" :value="i + 1"  v-for="(clubs,i) in datos">{{clubs.name}}</option>
                  </select>
            <br>
          	<label>Seleccionar Keywork</label>
                  <select class="custom-select" v-model="keywordsEquiposid"  name="select" id="select">
                      <option v-if="i!==0" :key="i" :value="keywords"  v-for="(keywords,i) in keywordsEquipos">{{keywords.name_keyword}}</option>
                  </select>
            <br>
          	<div class="button-section">
              <button type="submit" v-on:click="EliminarKeywork()">Eliminar keyworkd</button> 
            </div>
        </div>
    <div class="section"><span>4</span>Agregar Keywords</div>
        <div class="inner-wrap">
            <label>Seleciconar un equipo</label>
                  <select class="custom-select" v-model="value_ak"  name="key" id="select">
                      <option v-if="i!==16" :key="i" :value="i + 1"  v-for="(clubs,i) in datos">{{clubs.name}}</option>
                  </select>
            <br>
            <div>
                <label class="section">Nuevo Equipo</label>
                <br>
                <div> <input v-model="keywordAgregar" type="text"/> </div>
            </div>
            <br>
          	<div class="button-section">
              <button type="submit" v-on:click="AgregarKeywork()">Agregar keyworkd</button>
            </div>
        </div>
</div>
</template>


<script>
    export default {
        name: "tools",
        props:['datos'],
        data(){
            return{
                disabled: 0,
                nombre: "",
                nickname: "",
                trofeos: 0,
                url: "",
                keywordAgregar: "",
                equipoEliminar: 0,
                clubs: this.datos,
                value_e: -1,
                value_ek: -1,
                value_ak: -1,
                total_equipos: 16,
                keywordsEquipos: ["vacio"],
                keywordsEquiposid: {}
            }
        },

    methods: {

        listaKeyworks(){
            console.log(this.datos);
            console.log(this.value_ek);
            console.log(this.datos[this.value_ek -1].keywords);
            this.keywordsEquipos = this.datos[this.value_ek -1].keywords
            console.log(this.keywordsEquipos);
            this.$nextTick(() => {
                    this.showd = true
                    console.log('re-render start')
                    this.$nextTick(() => {
                        console.log('re-render end')
                    })
            })
        },

        EliminarEquipo(){
            console.log(this.clubs)
            console.log(this.value_e)
            if(this.value_e == -1){
                alert('No a seleccionado ningun equipo')
            }
            else{
                if(this.total_equipos < 1){
                    alert('No existen equipos para seguir eliminando')
                }
                else{
                    console.log( "se va a eliminar: " + this.value_e)
                    var dir = "http://206.189.184.79:8091/TDBG7/club/"
                    var dirEquipo = dir + this.value_e
                    var direccionKeywords = "http://206.189.184.79:8091/TDBG7/keyword"
                    var dataEquipo = null;
                    var flet = 0;
                    var contador = 0;
                    console.log(dirEquipo)
                    // agarro las keywords del equipo
                    //console.log(response.data.keywords)
                    dataEquipo = this.datos[this.value_e - 1].keywords
                    console.log(dataEquipo)
                    dataEquipo.forEach( function(valor, indice, array) {
                        console.log("En el índice " + indice + " hay este valor: " + valor.name_keyword);
                        this.$http.delete(direccionKeywords + valor.id).then(response => {
                            if(response == 'OK'){
                                console.log("keywords Eliminada" + valor.name_keyword);
                            }
                        });
                    });
                    this.$http.delete(dirEquipo).then(response => {
                    console.log("club eliminado con exito!", this.clubs);
                    });

                    this.$http.get(dir).then(response => {
                    this.clubs = response.data;
                    console.log("club:" + this.clubs);
                    console.log("data de clubes obtenida con exito!", this.clubs);
                    });
                    this.$nextTick(() => {
                        console.log('re-render start')
                        this.$nextTick(() => {
                            console.log('re-render end')
                        })
                    }) 
                    this.value_e = -1;
                    this.total_equipos = this.total_equipos - 1;
                }
            }
            
        },

        AgregarEquipo(){
            //Agregamos a el nuevo equipo a la lista
            dir = "http://206.189.184.79:8091/TDBG7/club/";
            var json = {
                'nombre': this.nombre,
                'nickename':this.nickename,
                'url':this.url,
                'trophies':this.trofeos
            }
            if( this.total_equipos < 17){
                if( this.nombre == "" ){
                    alert('No se se ingreso el nombre del nuevo equipo')
                }
                else{
                    if( this.nickname == ""){
                        alert('No se se ingreso el apodo del nuevo equipo')
                    }
                    else{
                        if( this.url == "" ){
                            alert('No se se ingreso la nueva direccion (url) del nuevo equipo')
                        }
                        else{
                            this.$http.post(dir,json).then(response => {
                            this.clubs = response.data;
                            console.log("club:" + this.clubs);
                            console.log("data de clubes obtenida con exito!", this.clubs);
                            });
                            //Actualizamos la lista de equipos 
                            this.$http.get(dir).then(response => {
                            this.clubs = response.data;
                            console.log("club:" + this.clubs);
                            console.log("data de clubes obtenida con exito!", this.clubs);
                            });
                            this.total_equipos = this.total_equipos + 1
                            this.$nextTick(() => {
                                console.log('re-render start')
                                this.$nextTick(() => {
                                    console.log('re-render end')
                                })
                            }) 
                        }
                    }
                }
            }
            else{
                alert('No se pueden ingresar mas de 16 equipos a el sistema proceda a eliminar uno primero')
            }
        },

        EliminarKeywork(){
            if(this.keywordsEquiposid == {}){
                 alert('Ingrese una keywords para eliminar antes de proceder con la ejecucion')
            }
            else{
                console.log(this.keywordsEquiposid)
                var url_club =  "http://206.189.184.79:8091/TDBG7/club/" + this.value_ek
                var url_keyword = "http://206.189.184.79:8091/TDBG7/keyword/" + this.keywordsEquiposid.id
                console.log(url_club)
                console.log(url_keyword)
                // this.$http.delete(url).then(response => {
                //     console.log("keywords elimianda con existo: " + response);
                // });
                this.$http.get(url_club).then(response => {
                    this.keywordsEquipos = response.data.keywords;
                    console.log("keywords:" + this.keywordsEquipos);
                    console.log("data keywords de clubes obtenida con exito!", this.keywordsEquipos);
                });
                this.total_equipos = this.total_equipos - 1
                this.$nextTick(() => {
                    console.log('re-render start')
                    this.$nextTick(() => {
                        console.log('re-render end')
                    })
                })   
            }
        },

        AgregarKeywork(){
            if(this.value_ak == -1){
                alert('seleccione una equipo antes de proceder con la ejecucion')
            }
            else{
                if(this.keywordAgregar != ""){
                    var url_club =  "http://206.189.184.79:8091/TDBG7/club/" + this.value_ek
                    var url_agregar = "http://206.189.184.79:8091/TDBG7/club/" + this.value_ak + "/keyword" 
                    var json = {
                        "name_keyword": this.keywordAgregar
                    }
                    this.$http.post(url_agregar,json).then(response => {
                        console.log("keyword agregada con exito")
                        console.log("keyword agregada al equipo: ", this.datos[this.value_ak - 1])
                    });
                    this.keywordAgregar = ""

                    this.$http.get(url_club).then(response => {
                    this.keywordsEquipos = response.data.keywords;
                        console.log("keywords:" + this.keywordsEquipos);
                        console.log("data keywords de clubes obtenida con exito!", this.keywordsEquipos);
                    });
                    this.$nextTick(() => {
                        console.log('re-render start')
                        this.$nextTick(() => {
                            console.log('re-render end')
                        })
                    })   

                }
                else{
                    alert('Ingrese una keywords para agregar antes de proceder con la ejecucion')
                }
            }
        },

    }
    };
</script>

<style scoped>
.custom-select {
  position: relative;
  display: block;
  max-width: 400px;
  min-width: 180px;
  margin: 0 auto;
  border: 1px solid #2196F3;
}
.custom-select select {
  border: none;
  border-radius: 0;
  margin: 0;
  display: block;
  width: 100%;
  padding: 12px 55px 15px 20px;
  font-size: 15px;
  color: #fe6d3c;
}


body {
  margin-top: 80px;
  background-color: #fedcb6;
}
.slate   { background-color: #ddd; }

  /* -------------------- Colors: Text */
.slate select   { color: #000; }

.form-style-10{
    padding:30px;
    margin:40px auto;
    background: #FFF;
}
.form-style-10 .inner-wrap{
    padding: 35px;
    background: #fe6d3c;
    border-radius: 20px;
    margin-bottom: 25px;
}
.form-style-10 h1{
    background: #fe6d3c;
    padding: 20px 30px 15px 30px;
    margin: -30px -30px 30px -30px;
    border-radius: 10px 10px 0 0;
    color: #fff;
    text-shadow: 1px 1px 3px rgba(0, 0, 0, 0.12);
    font: normal 30px 'Bitter', serif;
    border: 1px solid #257C9E;
}
.form-style-10 h1 > span{
    display: block;
    margin-top: 2px;
    font: 13px Arial, Helvetica, sans-serif;
}

.form-style-10 label{
    display: block;
    font: 13px Arial, Helvetica, sans-serif;
    color: #000000  ;
    margin-bottom: 15px;
}

.form-style-10 .section{
    font: normal 20px 'Bitter', serif;
    color: rgb(0, 19, 124);
    margin-bottom: 5px;
}
.form-style-10 .section span {
    background: #fe6d3c;
    padding: 5px 10px 5px 10px;
    position: absolute;
    border-radius: 50%;
    border: 4px solid #fff;
    font-size: 14px;
    margin-left: -35px;
    color: #fff;
    margin-top: -6px;
}
.form-style-10 button, 
.form-style-10 input[type="submit"]{
    width:330px;
    background: #fe6d3c;
    padding: 8px 20px 8px 20px;
    border-radius: 50px;
    color: #fff;
    text-shadow: 1px 1px 3px rgba(0, 0, 0, 0.12);
    font: normal 30px 'Bitter', serif;
    border: 1px solid #000000 ;
    font-size: 15px;
}
.form-style-10 button:hover, 
.form-style-10 input[type="submit"]:hover{
    background: #2A6881;
}
.cuadro{
    display: block;
    box-sizing: border-box;
    width: 100%;
    padding: 8px;
    border-radius: 6px;
    border: 2px solid #fff;
}
</style>