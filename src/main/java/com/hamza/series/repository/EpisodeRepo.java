package com.hamza.series.repository;

import com.hamza.series.model.Episode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface EpisodeRepo extends JpaRepository<Episode,Integer> {


      Set<Episode> findBySerieId(Integer id) ;
}
