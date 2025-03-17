package com.example.boardreply.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;

    private String content;
    private String writer;

    private LocalDateTime regitime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bno")
    private Board board;
}
