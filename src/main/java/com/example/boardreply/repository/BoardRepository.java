package com.example.boardreply.repository;

import com.example.boardreply.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {



}
