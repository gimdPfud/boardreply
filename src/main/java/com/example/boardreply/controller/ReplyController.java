package com.example.boardreply.controller;

import com.example.boardreply.dto.ReplyDTO;
import com.example.boardreply.service.BoardService;
import com.example.boardreply.service.ReplyService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reply")
@Log4j2
@RequiredArgsConstructor
public class ReplyController {
    private final ReplyService replyService;
    private final BoardService boardService;

    @PostMapping("/register")
    public ResponseEntity postRegister(ReplyDTO replyDTO){
        log.info("댓글 등록 : "+replyDTO);
        replyService.register(replyDTO);
        return new ResponseEntity<>("저장되었습니다.",HttpStatus.OK);
    }

    @GetMapping("/list/{bno}")
    public ResponseEntity getList(@PathVariable Long bno){
        log.info("댓글 목록 : "+bno);
        List<ReplyDTO> replyDTOList = replyService.list(bno);
        return new ResponseEntity<>(replyDTOList,HttpStatus.OK);
    }

    @PostMapping("/edit/")
    public ResponseEntity postEdit(ReplyDTO replyDTO){
        log.info("댓글 수정 : "+replyDTO);
        try {
            Long rno = replyService.edit(replyDTO);
            return new ResponseEntity<>(rno, HttpStatus.OK);
        }catch (EntityNotFoundException e){
            return new ResponseEntity<>("수정할 수 없습니다.",HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/delete/{rno}")
    public ResponseEntity getDelete(@PathVariable Long rno){
        log.info("댓글 삭제 : "+rno);
        replyService.delete(rno);
        return new ResponseEntity<>("삭제되었습니다.",HttpStatus.OK);
    }
}
