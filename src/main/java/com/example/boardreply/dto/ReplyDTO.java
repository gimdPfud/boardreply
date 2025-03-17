package com.example.boardreply.dto;

import com.example.boardreply.entity.Board;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReplyDTO {
    private Long rno;
    private String content;
    private String writer;
    private LocalDateTime regitime;
    private BoardDTO boardDTO;
}
