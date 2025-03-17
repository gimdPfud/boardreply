package com.example.boardreply.service;

import com.example.boardreply.dto.BoardDTO;
import com.example.boardreply.entity.Board;
import com.example.boardreply.repository.BoardRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
@Transactional
public class BoardService {

    private final BoardRepository boardRepository;
    private final ModelMapper modelMapper;
    private final ReplyService replyService;

    public void register(BoardDTO boardDTO){
        log.info("들어온 값 : " + boardDTO);

        boardDTO.setRegitime(LocalDateTime.now());

        //dto를 가져와서 entity로 변환
        Board board = modelMapper.map(boardDTO, Board.class);
        boardRepository.save(board);
    }

    public List<BoardDTO> list() {
        //entity를 dto로 변환
        List<Board> list = boardRepository.findAll();

        List<BoardDTO> boardDTOS = new ArrayList<>();

        for(Board board : list) {
            BoardDTO boardDTO = modelMapper.map(board, BoardDTO.class);
            boardDTOS.add(boardDTO);
        }

        return boardDTOS;
    }

    public BoardDTO read(Long bno){
        log.info("들어온 값 : " + bno);

        //entity를 dto로 변환
        Optional<Board> optionalBoard = boardRepository.findById(bno);

        Board board = optionalBoard.orElseThrow(EntityNotFoundException::new);

        BoardDTO boardDTO = modelMapper.map(board, BoardDTO.class);

        return boardDTO;
    }

    public void update(BoardDTO boardDTO) {
        log.info("들어온 값 : " + boardDTO);

        //pk로 글 찾기
        Long bno = boardDTO.getBno();
        Board board = boardRepository.findById(bno).orElseThrow(EntityNotFoundException::new);
        board.setTitle(boardDTO.getTitle());
        board.setContent(boardDTO.getContent());

    }

    public void delete(Long bno) {
        log.info("들어온 값 : " + bno);

        replyService.deleteReply(bno);
        boardRepository.deleteById(bno);

    }




}
