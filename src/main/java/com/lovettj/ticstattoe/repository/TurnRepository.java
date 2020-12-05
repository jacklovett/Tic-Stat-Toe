package com.lovettj.ticstattoe.repository;

import com.lovettj.ticstattoe.model.Turn;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurnRepository extends JpaRepository<Turn, Long> {

}
