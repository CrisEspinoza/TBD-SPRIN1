package Taller1db.TBD.Analyzer;

import Taller1db.TBD.Respository.ClubRepository;
import Taller1db.TBD.Respository.StatisticsRepository;
import Taller1db.TBD.Entities.Club;
import Taller1db.TBD.Entities.Keyword;
import Taller1db.TBD.Entities.Statistics;
import com.mongodb.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class Analizador {


    private int modalidad;
    private  ArrayList<String> buenas= new  ArrayList<String>();
    private ArrayList<String> malas = new ArrayList<String>();

    @Autowired
    private ClubRepository clubRepository;

    @Autowired
    private StatisticsRepository statisticsRepository;



    public Analizador() throws IOException {

        this.buenas=leerArchivo("/home/sebastian/Documentos/tdb/spring1/Taller2-TBD/src/main/java/Taller1db/TBD/Analyzer/PalabrasBuenas.txt");
        this.malas=leerArchivo("/home/sebastian/Documentos/tdb/spring1/Taller2-TBD/src/main/java/Taller1db/TBD/Analyzer/PalabrasMalas.txt");


    }

    public ArrayList<Integer> analisisCompleto(){

        ArrayList<Integer> acumulador = new ArrayList<Integer>();
        acumulador.add(0);
        acumulador.add(0);
        acumulador.add(0);
        String resultado ;

        Club equipo = clubRepository.findById(Long.valueOf(17)).orElse(null);
        //conexion mongo
        MongoClient mongoClient = new MongoClient("localhost",27017);
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


        // se crea una fecha tipo timestamp para el registro historico
        Date date = new Date();
        long time = date.getTime();

        // se crea una clase statistics y se guardan en la bd
        Statistics statistics = new Statistics();
        statistics.setPositive_value(acumulador.get(0));
        statistics.setNegative_value(acumulador.get(1));
        statistics.setNeutro_value(acumulador.get(2));
        //statistics.setName(equipo);
        statistics.setName_statics("estadistica de generales");
        statistics.setLastUpdate(new Timestamp(time));
        statisticsRepository.save(statistics);

        return acumulador;
    }


    public void AnalisisEspecifico(){

        Iterable<Club>  clubs= clubRepository.findAll();
        Indice indice = new Indice();
        indice.indexar();
        ArrayList<Integer> acumulador = new ArrayList<Integer>();
        acumulador.add(0);
        acumulador.add(0);
        acumulador.add(0);

        for (Club equipo: clubs) {
            ArrayList<String> tweets;
            String busqueda = equipo.getName();
            for (Keyword apodo: equipo.getKeywords()) {
                busqueda = " "+apodo.getName_keyword();
            }
            tweets = indice.buscar(busqueda);


            AnalisisSentimineto analisis = new AnalisisSentimineto(this.buenas,this.malas);


            for (String tweet: tweets) {
                String resultado= analisis.analizar(tweet);

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

//            Date date = new Date();
//            long time = date.getTime();
//
//            Statistics statistics = new Statistics();
//            statistics.setPositive_value(acumulador.get(0));
//            statistics.setNegative_value(acumulador.get(1));
//            statistics.setNeutro_value(acumulador.get(2));
//            statistics.setName(equipo);
//            statistics.setLastUpdate(new Timestamp(time));
//            //clasificacion ?
//            statistics.setName_statics("estadistica de equipos");
//            statisticsRepository.save(statistics);

            System.out.println("*********************************************");
            System.out.println("*********************************************");
            System.out.println(acumulador.toString());
            System.out.println("*********************************************");
            System.out.println("*********************************************");

        }




    }


    public  ArrayList<String> leerArchivo(String name) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(name));
        String linea,acumulador;
        acumulador="";
        ArrayList<String> palabras= new  ArrayList<String>() ;

        while((linea=br.readLine())!=null){
            palabras.add(linea);

        }

//        palabras= acumulador.split("\n");
//        System.out.println("*********************************************");
//        System.out.println("*********************************************");
//
        System.out.println(palabras.toString());
//
//
//        System.out.println("*********************************************");
        return palabras;

    }




}
