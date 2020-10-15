package com.example.ticstattoe.repository;

import org.springframework.stereotype.Repository;

import java.util.Optional;

import com.example.ticstattoe.model.Game;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

  Optional<Game> findById(Long id);
}
