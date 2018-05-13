package Taller1db.TBD.Analyzer;

import com.mongodb.*;

import java.util.ArrayList;

public class Analizador {

    int modalidad;
    ArrayList<String> buenas;
    ArrayList<String> malas;


    public Analizador() {
        this.buenas.add("bueno");
        this.buenas.add("grande");
        this.malas.add("malo");
        this.malas.add("pesimo");
    }

    public ArrayList<Integer> analisisCompleto(){

        ArrayList<Integer> acumulador = new ArrayList<Integer>();
        acumulador.add(0);
        acumulador.add(0);
        acumulador.add(0);
        String resultado ;

        //conexion mongo
        MongoClient mongoClient = new MongoClient();
        DB db = mongoClient.getDB("twitter7");
        DBCollection collection = db.getCollection("futbol");
        DBCursor cursor = collection.find();

        while (cursor.hasNext()) {
            DBObject tweet = cursor.next();

            AnalisisSentimineto analisis = new AnalisisSentimineto(this.buenas,this.malas);
            resultado= analisis.analizar(tweet.get("text").toString());

            if (resultado.equals("positivo")){
                acumulador.set(0,acumulador.get(0)+1);
            }
            else if(resultado.equals("negativo")){
                acumulador.set(1,acumulador.get(1)+1);
            }
            else {
                acumulador.set(2,acumulador.get(2)+1);
            }


        }



        return acumulador;
    }


}
