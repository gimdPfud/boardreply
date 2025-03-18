package com.example.boardreply.service;

import com.example.boardreply.dto.ReplyDTO;
import com.example.boardreply.entity.Reply;
import com.example.boardreply.repository.BoardRepository;
import com.example.boardreply.repository.ReplyRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ToString
@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class ReplyService {
    private final ReplyRepository replyRepository;
    private final ModelMapper modelMapper = new ModelMapper();
    private final BoardRepository boardRepository;

    public void register(ReplyDTO replyDTO){
//        log.info(replyDTO);
        Reply reply = modelMapper.map(replyDTO, Reply.class);
//        log.info(reply);
        reply.setBoard(boardRepository.findById(replyDTO.getBno()).get());
        reply.setRegitime(LocalDateTime.now());
        replyRepository.save(reply);
    }

    public List<ReplyDTO> list(Long bno){
        log.info("들어온 값 : "+bno);
        List<Reply> replyList = replyRepository.findByBoardBno(bno);
        List<ReplyDTO> replyDTOList = new ArrayList<>();

        for(Reply reply : replyList){
            ReplyDTO replyDTO = modelMapper.map(reply, ReplyDTO.class);
            replyDTOList.add(replyDTO);
            log.info("디티오 : {}", replyDTO);
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

    public void deleteReply(Long bno){
        List<Reply> replyList = replyRepository.findByBoardBno(bno);
        replyRepository.deleteAll(replyList);
    }
}
