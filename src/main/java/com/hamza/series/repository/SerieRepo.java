package com.hamza.series.repository;

import com.hamza.series.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SerieRepo extends JpaRepository< Serie,Integer> {
}
