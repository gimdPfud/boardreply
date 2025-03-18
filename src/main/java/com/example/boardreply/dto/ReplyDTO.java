package com.example.boardreply.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
//@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class ReplyDTO {
    private Long rno;
    private String content;
    private String writer;
    private LocalDateTime regitime;

//    private Board board;
    private Long bno;
}
