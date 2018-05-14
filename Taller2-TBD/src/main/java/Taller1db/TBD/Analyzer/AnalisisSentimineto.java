package Taller1db.TBD.Analyzer;

import java.util.ArrayList;

public class AnalisisSentimineto {
    int  palabrasBuenas;
    int  palabrasMalas;
    String[] buenas;
    String[] malas;

    public AnalisisSentimineto(String[] buenas,String[] malas) {
        this.palabrasBuenas = 0;
        this.palabrasMalas=0;
        this.buenas=buenas;
        this.malas=malas;
    }

    public String analizar(String tweet){

        String tweetMin = tweet.toLowerCase().replace(" es ","").replace(" ","");

        for (int i = 0; i < buenas.length; i++) {
            int tweetIndex= tweetMin.indexOf(buenas[i]);
            if(tweetIndex >-1){
                if(tweetMin.substring(tweetIndex-2,tweetIndex).equals("no")){
                    palabrasMalas++;
                }
                else{
                    palabrasBuenas++;
                }

            }

                tweetIndex= tweetMin.indexOf(malas[i]);
            if(tweetIndex >-1){
                if(tweetMin.substring(tweetIndex-2,tweetIndex).equals("no")){
                    palabrasBuenas++;
                }
                else{
                    palabrasMalas++;
                }

            }

        }

        if (palabrasBuenas>palabrasMalas){

            return "positivo";
        }
        else if(palabrasMalas>palabrasBuenas){
            return "negativo";
        }

        else {
            return "neutro";
        }



    }
}
