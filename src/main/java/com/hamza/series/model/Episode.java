package com.hamza.series.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
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



}
