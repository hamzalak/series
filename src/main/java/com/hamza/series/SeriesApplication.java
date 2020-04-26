package com.hamza.series;

import com.hamza.series.model.Episode;
import com.hamza.series.model.Serie;
import com.hamza.series.model.auth.ERole;
import com.hamza.series.model.auth.Role;
import com.hamza.series.repository.EpisodeRepo;
import com.hamza.series.repository.RoleRepo;
import com.hamza.series.repository.SerieRepo;
import com.hamza.series.utility.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SeriesApplication {


	@Autowired
	private SerieRepo serieRepo ;
	@Autowired
	private EpisodeRepo episodeRepo ;

	@Autowired
	private RoleRepo roleRepo ;

	public static void main(String[] args) {
		SpringApplication.run(SeriesApplication.class, args);
	}



	@Bean
	CommandLineRunner start(){


		return  args->{

			/////Instanciation d'un nouveau voyage///////
			serieRepo.deleteAll();

			Serie serie1 =  new Serie(null,"money call","sucidaire","jonny stoke", DateUtils.parseDate("20/5/2019"),2,null) ;
			serieRepo.save(serie1) ;
			Episode episode1 = new Episode(null, 1,"le debut" ,"visite des extras ",false,null ) ;
			Episode episode2 = new Episode(null, 2,"la seconde" ,"rencontre des extras ",false,null ) ;
			Episode episode3 = new Episode(null, 3,"la troisième" ,"bataille des extras ",false,null ) ;

			episode1.setSerie(serie1);
            episode2.setSerie(serie1);
            episode3.setSerie(serie1);
            episodeRepo.save(episode1) ;
            episodeRepo.save(episode2) ;
            episodeRepo.save(episode3) ;

			Serie serie2 =  new Serie(null,"gangsta","sucidaire","alain", DateUtils.parseDate("20/5/2019"),2,null) ;
			serieRepo.save(serie2) ;
			Episode episode21 = new Episode(null, 1,"le debut" ,"death of the father ",false,null ) ;
			Episode episode22 = new Episode(null, 2,"la seconde" ,"familly conflict ",false,null ) ;
			Episode episode23 = new Episode(null, 3,"la troisième" ,"memory ",false,null ) ;
			episode21.setSerie(serie2);
			episode22.setSerie(serie2);
			episode23.setSerie(serie2);
			episodeRepo.save(episode21) ;
			episodeRepo.save(episode22) ;
			episodeRepo.save(episode23) ;
			Role role = new Role(ERole.ROLE_USER_INSCRIT) ;

			roleRepo.save(role) ;
		} ;
	}

}
