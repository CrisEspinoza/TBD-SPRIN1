package Taller1db.TBD;


import Taller1db.TBD.Analyzer.Classifier;
import Taller1db.TBD.Analyzer.Indice;
import Taller1db.TBD.Entities.Club;
import Taller1db.TBD.Entities.Keyword;
import Taller1db.TBD.Entities.Statistics;
import Taller1db.TBD.Respository.ClubRepository;
import Taller1db.TBD.Respository.StatisticsRepository;
import com.mongodb.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
@Transactional
@Component
public class schedulerAnalisis {

    @Autowired
    private ClubRepository clubRepository;

    @Autowired
    private StatisticsRepository statisticsRepository;

    @Autowired
    private Classifier classifier;




    @Scheduled(cron="0 0 * * * * ")
    public void analizador() throws IOException {
        this.analisisGeneral();

        this.analisisEspecifico();







    }

    public void analisisGeneral() throws IOException {

        MongoClient mongoClient= new MongoClient("138.197.128.130",27017);
        DB db = mongoClient.getDB("twitter7");
        DBCollection collection = db.getCollection("futbol");
        DBCursor cursor = collection.find();

        int[] acumulador= new int[3];
        acumulador[0]=0;
        acumulador[1]=0;
        acumulador[2]=0;

        while (cursor.hasNext()) {
            DBObject tweet = cursor.next();

            System.out.println(">>>>>"+tweet.get("text").toString());
            HashMap<String,Double> resultado = classifier.classify(tweet.get("text").toString());
            if (resultado.get("positive")> resultado.get("negative")){
                acumulador[0]+=1;
            }
            else if(resultado.get("positive")< resultado.get("negative")){
                acumulador[1]+=1;
            }
            else {
                acumulador[2]+=1;
            }


        }


//
        Club equipo = clubRepository.findClubById( Long.valueOf(17));


        // se crea una fecha tipo timestamp para el registro historico
        Date date = new Date();
        long time = date.getTime();



        System.out.println("#################################################################");
        System.out.println("#################################################################");
        System.out.println("#################################################################");
        System.out.println("#################################################################");
         System.out.println("El resultado es :"+acumulador[0]+","+acumulador[1]+","+acumulador[2]);
        System.out.println("#################################################################");
        System.out.println("#################################################################");
        System.out.println("#################################################################");

        mongoClient.close();

        // se crea una clase statistics y se guardan en la bd
        Statistics statistics = new Statistics();
        statistics.setPositive_value(acumulador[0]);
        statistics.setNegative_value(acumulador[1]);
        statistics.setNeutro_value(acumulador[2]);

         statistics.setLastUpdate(new Timestamp(time));
        statistics.setName_statics("estadistica de generales");
        statistics.setLastUpdate(new Timestamp(time));
        equipo.getStatistics().add(statistics);
        clubRepository.save(equipo);


    }


    public void analisisEspecifico(){



            Iterable<Club>  clubs= clubRepository.findAll();
            Indice indice = new Indice();
            indice.indexar();
            int[] acumulador= new int[3];
            acumulador[0]=0;
            acumulador[1]=0;
            acumulador[2]=0;


            for (Club equipo: clubs) {
                if (equipo.getId() != 17){
                    ArrayList<String> tweets;
                String busqueda = equipo.getName();
                for (Keyword apodo: equipo.getKeywords()) {
                    busqueda = " "+apodo.getName_keyword();
                }

                System.out.println("%%%%% " + busqueda + "%%%%%%%");
                tweets = indice.buscar(busqueda);
//
//                System.out.println("%%%%% " + tweets.toString() + "%%%%%%%");


                for (String tweet : tweets) {
                    HashMap<String, Double> resultado = classifier.classify(tweet);

                    if (resultado.get("positive") > resultado.get("negative")) {
                        acumulador[0] += 1;
                    } else if (resultado.get("positive") < resultado.get("negative")) {
                        acumulador[1] += 1;
                    } else {
                        acumulador[2] += 1;
                    }

                }

                Date date = new Date();
                long time = date.getTime();
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
                Statistics statistics = new Statistics();
                statistics.setPositive_value(acumulador[0]);
                statistics.setNegative_value(acumulador[1]);
                statistics.setNeutro_value(acumulador[2]);

                statistics.setLastUpdate(new Timestamp(time));
                statistics.setName_statics("estadistica de equipos");

                equipo.getStatistics().add(statistics);
                clubRepository.save(equipo);


                System.out.println("***************" + equipo.getName() + "******************************");
                System.out.println("*********************************************");
                System.out.println("El resultado es :" + acumulador[0] + "," + acumulador[1] + "," + acumulador[2]);
                System.out.println("*********************************************");
                System.out.println("*********************************************");
            }

            }



        System.out.println("*********************************************");
        System.out.println("*********************************************");
        System.out.println("Finalizoooooooo");
        System.out.println("*********************************************");
        System.out.println("*********************************************");


    }

}
