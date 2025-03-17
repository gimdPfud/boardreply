package com.example.boardreply.controller;

import com.example.boardreply.dto.BoardDTO;
import com.example.boardreply.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/register")
    public String getRegister(BoardDTO boardDTO, Model model){

        boardService.register(boardDTO);
        model.addAttribute("boardDTO", )

        return
    }

    @PostMapping("/regiser")



}
