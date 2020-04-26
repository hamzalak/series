package com.hamza.series.userInscritLogic;


import com.hamza.series.model.Episode;
import com.hamza.series.model.Serie;

import com.hamza.series.model.SerieForm;
import com.hamza.series.model.dto.MessageResponse;
import com.hamza.series.repository.EpisodeRepo;
import com.hamza.series.repository.SerieRepo;

import com.hamza.series.utility.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/manage")
public class UserInscritController {

    @Autowired
    private SerieRepo serieRepo;

    @Autowired
    private EpisodeRepo episodeRepo ;



    @PreAuthorize("hasRole('ROLE_USER_INSCRIT')")
    @PostMapping(value = "/serie/create")
    public ResponseEntity<?> postSerie(@RequestBody SerieForm _serie) {

        Serie serie = new Serie() ;
        serie.setTitre(_serie.getTitre());
        try {
            serie.setAnneDeSortie(DateUtils.parseDate(_serie.getAnneDeSortie()));
        }catch (ParseException ex){
            ex.getMessage() ;

        }
        serie.setDescription(_serie.getDescription());
        serie.setNbrEpisode(_serie.getNbrEpisode());
        serie.setRealisateur(_serie.getRealisateur());
        serieRepo.save(serie) ;
        return new ResponseEntity(serie, HttpStatus.OK) ;
    }

    @PostMapping(value = "/episode/{id}")
    @PreAuthorize("hasRole('ROLE_USER_INSCRIT')")
    public ResponseEntity<?> postEpisode(@PathVariable Integer id, @RequestBody Episode episode) {

        Optional<Serie> _serie = serieRepo.findById(id) ;
        _serie.get().setNbrEpisode(_serie.get().getNbrEpisode()+1);
        serieRepo.save(_serie.get()) ;
        episode.setSerie(_serie.get());
        episodeRepo.save(episode) ;
        return new ResponseEntity(episode, HttpStatus.OK) ;
    }

    @DeleteMapping("/serie/{id}")
    @PreAuthorize("hasRole('ROLE_USER_INSCRIT')")
    public ResponseEntity<?> deleteSerie(@PathVariable("id") Integer id) {
        serieRepo.deleteById(id);
        return new ResponseEntity(new MessageResponse("La serie est supprimé"), HttpStatus.OK);
    }

    @DeleteMapping("/episode/{id}")
    @PreAuthorize("hasRole('ROLE_USER_INSCRIT')")
    public ResponseEntity<?> deleteEpisode(@PathVariable("id") Integer id , @RequestBody Episode _episode) {
        Optional<Serie> _serie = serieRepo.findById(id) ;
        _serie.get().setNbrEpisode(_serie.get().getNbrEpisode()-1);
        serieRepo.save(_serie.get()) ;
        episodeRepo.deleteById(_episode.getId());
        return new ResponseEntity<>(new MessageResponse("L'épisode est supprimé"), HttpStatus.OK);
    }

    @PutMapping("/serie/{id}")
    @PreAuthorize("hasRole('ROLE_USER_INSCRIT')")
    public ResponseEntity<?> updateSerie(@PathVariable("id") Integer id, @RequestBody SerieForm _serie) {
        Optional<Serie> serieData = serieRepo.findById(id);
        if (serieData.isPresent()) {
            Serie serie = serieData.get();
            serie.setTitre(_serie.getTitre());
            try {
                serie.setAnneDeSortie(DateUtils.parseDate(_serie.getAnneDeSortie()));
            }catch (ParseException ex){
                ex.getMessage() ;

            }
            serie.setDescription(_serie.getDescription());
            serie.setNbrEpisode(_serie.getNbrEpisode());
            serie.setRealisateur(_serie.getRealisateur());
            return new ResponseEntity<>(serieRepo.save(serie), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/episode/{id}")
    @PreAuthorize("hasRole('ROLE_USER_INSCRIT')")
    public ResponseEntity<?> updateEpisode(@PathVariable("id") Integer id, @RequestBody Episode _episode) {
        Optional<Episode> episodeData = episodeRepo.findById(id);
        if (episodeData.isPresent()) {
            Episode episode = episodeData.get();
            episode.setTitre(_episode.getTitre());
            episode.setDescription(_episode.getDescription());
            episode.setNumero(_episode.getNumero());
            episode.setVue(false);
            return new ResponseEntity<>(episodeRepo.save(_episode), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PreAuthorize("hasRole('ROLE_USER_INSCRIT')")
    @GetMapping("/serie/{id}")
    public ResponseEntity<?> getSerieEpisodes(@PathVariable("id") int id) throws ParseException {
        Optional<Serie> serie = serieRepo.findById(id) ;
        Serie _serie = serie.get() ;
         return new ResponseEntity(_serie, HttpStatus.OK);
    }


}


