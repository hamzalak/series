package com.hamza.series.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;
@Entity
public class SerieForm {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;
    private String titre ;
    private  String description ;
    private String realisateur ;
    private String anneDeSortie ;
    private Integer nbrEpisode ;
    @JsonIgnore
    @OneToMany(mappedBy = "serie",fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.DELETE})
    private Set<Episode> episodes ;


    public SerieForm(){

        super();
    }

    public SerieForm(Integer id, String titre, String description, String realisateur, String anneDeSortie, Integer nbrEpisode, Set<Episode> episodes) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.realisateur = realisateur;
        this.anneDeSortie = anneDeSortie;
        this.nbrEpisode = nbrEpisode;
        this.episodes = episodes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRealisateur(String realisateur) {
        this.realisateur = realisateur;
    }


    public void setNbrEpisode(Integer nbrEpisode) {
        this.nbrEpisode = nbrEpisode;
    }

    public void setEpisodes(Set<Episode> episodes) {
        this.episodes = episodes;
    }

    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }

    public String getRealisateur() {
        return realisateur;
    }

    public void setAnneDeSortie(String anneDeSortie) {
        this.anneDeSortie = anneDeSortie;
    }

    public String getAnneDeSortie() {
        return anneDeSortie;
    }

    public Integer getNbrEpisode() {
        return nbrEpisode;
    }

    public Set<Episode> getEpisodes() {
        return episodes;
    }

    @Override
    public String toString() {
        return "Serie\":{" +
                "\"id\":\"" + id + "\"" +
                "\"titre\":\"" + titre + "\"" +
                ", \"description\":\"" + description + "\"" +
                ", \"realisateur\":'" + realisateur + "\"" +
                ", \"anneDeSortie\":" + anneDeSortie +
                ", \"nbrEpisode\":" + nbrEpisode +
                '}';
    }
}
