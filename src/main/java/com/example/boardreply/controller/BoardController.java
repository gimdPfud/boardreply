package com.example.boardreply.controller;

import com.example.boardreply.dto.BoardDTO;
import com.example.boardreply.entity.Board;
import com.example.boardreply.service.BoardService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.awt.*;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/register")
    public String getRegister(){

        return "/register";
    }

    @PostMapping("/register")
    public String postRegister(BoardDTO boardDTO){

        boardService.register(boardDTO);

        return "redirect:/boardlist";
    }

    @GetMapping("/list")
    public String getList(Model model) {

        List<BoardDTO> boardDTOS = boardService.list();
        model.addAttribute("boardDTOS", boardDTOS);

        return "/boardList";
    }

    @GetMapping("/read/{bno}")
    public String getRead(@PathVariable("bno") Long bno, Model model){

        BoardDTO boardDTO = boardService.read(bno);
        model.addAttribute("boardDTO", boardDTO);

        return "/boardread";
    }

    @GetMapping("/edit/{bno}")
    public String getEdit(@PathVariable("bno") Long bno, Model model){

        BoardDTO boardDTO = boardService.read(bno);
        model.addAttribute("boardDTO", boardDTO);

        return "/boardedit";
    }

    @PostMapping("/edit")
    public String postEdit(BoardDTO boardDTO) {

        boardService.edit(boardDTO);
        Long bno = boardDTO.getBno();

        return "redirect:/board/read/" + bno;
    }

    @GetMapping("/delete")
    public String postDelete(Long bno){

        boardService.delete(bno);

        return "redirect:/boardlist";
    }
}
