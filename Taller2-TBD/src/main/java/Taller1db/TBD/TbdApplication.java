package Taller1db.TBD;

import Taller1db.TBD.Analyzer.Analizador;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

@SpringBootApplication
public class TbdApplication {

	public static void main(String[] args) {

		//Analizador analizador = new Analizador();
		//ArrayList<Integer> resultado = analizador.analisisCompleto();
		//System.out.println("El resultado es :"+resultado.toString());
		SpringApplication.run(TbdApplication.class, args);
	}
}
