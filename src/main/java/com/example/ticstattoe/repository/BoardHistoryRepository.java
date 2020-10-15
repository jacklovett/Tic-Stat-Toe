package com.example.ticstattoe.repository;

import java.util.Optional;

import com.example.ticstattoe.model.BoardHistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardHistoryRepository extends JpaRepository<BoardHistory, Long> {

  Optional<BoardHistory> findByGameId(Long gameId);
}
