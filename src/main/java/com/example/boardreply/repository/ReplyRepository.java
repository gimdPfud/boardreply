package com.example.boardreply.repository;

import com.example.boardreply.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    public List<Reply> findByBoardBno (Long bno);
}
