package com.example.jpabasic.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "MEMBER")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    public Member(String id, String username) {
        this.id = id;
        this.username = username;
    }

    @Id
    @Column(name = "MEMBER_ID")
    private String id;

    @Column(name = "USERNAME")
    private String username;

    @OneToOne
    @JoinColumn(name = "TEAM_ID") // 외래키를 가지고 있는 쪽, 연관관계의 주인
    private Team team;

}
