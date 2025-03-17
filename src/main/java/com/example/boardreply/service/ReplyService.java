package com.example.boardreply.service;

import com.example.boardreply.dto.ReplyDTO;
import com.example.boardreply.entity.Board;
import com.example.boardreply.entity.Reply;
import com.example.boardreply.repository.ReplyRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class ReplyService {
    private final ReplyRepository replyRepository;
    private final ModelMapper modelMapper;
    private final BoardRepository boardRepository;

    public void register(ReplyDTO replyDTO){
        log.info("들어온 값 : "+replyDTO);
        Reply reply = modelMapper.map(replyDTO, Reply.class);
        replyRepository.save(reply);
    }

    public List<ReplyDTO> list(Long bno){
        log.info("들어온 값 : "+bno);
        List<Reply> replyList = replyRepository.findByBoardBno(bno);
        List<ReplyDTO> replyDTOList = new ArrayList<>();
        for(Reply reply : replyList){
            ReplyDTO replyDTO = modelMapper.map(reply, ReplyDTO.class);
            replyDTOList.add(replyDTO);
        }
        return replyDTOList;
    }

    public Long edit (ReplyDTO replyDTO){
        log.info("들어온 값 : "+replyDTO);
        Reply reply = replyRepository.findById(replyDTO.getRno())
                .orElseThrow(EntityNotFoundException::new);
        reply.setContent(replyDTO.getContent());
        return reply.getRno();
    }

    public void delete(Long rno){
        log.info("들어온 값 : "+rno);
        replyRepository.deleteById(rno);
    }
}
