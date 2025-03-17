package com.example.boardreply.service;

import com.example.boardreply.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class ReplyService {
    private final ReplyRepository replyRepository;

}
