package com.lovettj.ticstattoe.repository;

import com.lovettj.ticstattoe.model.BoardHistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardHistoryRepository extends JpaRepository<BoardHistory, Long> {

}
