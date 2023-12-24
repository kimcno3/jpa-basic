package com.example.jpabasic.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "BOARD")
//@SequenceGenerator(
//        name = "BOARD_SEQ_GENERATOR", // 엔티티에서 매핑할 이름
//        sequenceName = "BOARD_SEQ", // DB에 매핑할 시퀀스 이름
//        initialValue = 1,
//        allocationSize = 1
//)
@Getter
public class Board {

    @Id
    @Column(name = "ID")
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BOARD_SEQ_GENERATOR")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "DATA")
    private String data;
}
