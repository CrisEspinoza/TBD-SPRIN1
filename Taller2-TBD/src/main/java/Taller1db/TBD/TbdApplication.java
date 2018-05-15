package Taller1db.TBD;

import Taller1db.TBD.Analyzer.Analizador;
import Taller1db.TBD.Respository.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;
import java.util.ArrayList;

@SpringBootApplication
@EnableJpaRepositories("Taller1db.TBD.Respository")
@EntityScan("Taller1db.TBD.Entities")
@Configuration
@EnableAutoConfiguration
@EnableScheduling
public class TbdApplication {
	@Autowired
	ClubRepository clubRepository;

	public static void main(String[] args) throws IOException {
		SpringApplication.run(TbdApplication.class, args);
//
//		Analizador analizador = new Analizador();
//		ArrayList<Integer> resultado = analizador.analisisCompleto();
//		System.out.println("#################################################################");
//        System.out.println("#################################################################");
//        System.out.println("#################################################################");
//        System.out.println("#################################################################");
//		System.out.println("El resultado es :"+resultado.toString());
//        System.out.println("#################################################################");
//        System.out.println("#################################################################");
//        System.out.println("#################################################################");
//////        //analizador.AnalisisEspecifico();


	}
}
