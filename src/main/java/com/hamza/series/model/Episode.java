package com.hamza.series.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
public class Episode {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Integer id ;
 private  Integer numero ;
 private  String titre ;
 private String description ;
 private Boolean vue ;
 @JsonIgnore
 @ManyToOne
 private Serie serie ;

 public  Episode(){
 }

 public Episode(Integer id, Integer numero, String titre, String description, Boolean vue, Serie serie) {
  this.id = id;
  this.numero = numero;
  this.titre = titre;
  this.description = description;
  this.vue = vue;
  this.serie = serie;
 }

 public Integer getId() {
  return id;
 }

 public void setId(Integer id) {
  this.id = id;
 }

 public Integer getNumero() {
  return numero;
 }

 public void setNumero(Integer numero) {
  this.numero = numero;
 }

 public String getTitre() {
  return titre;
 }

 public void setTitre(String titre) {
  this.titre = titre;
 }

 public String getDescription() {
  return description;
 }

 public void setDescription(String description) {
  this.description = description;
 }

 public Boolean getVue() {
  return vue;
 }

 public void setVue(Boolean vue) {
  this.vue = vue;
 }

 public Serie getSerie() {
  return serie;
 }

 public void setSerie(Serie serie) {
  this.serie = serie;
 }
}
