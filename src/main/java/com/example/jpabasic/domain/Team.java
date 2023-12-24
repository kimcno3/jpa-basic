package com.example.jpabasic.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.mapping.Join;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TEAM")
public class Team {

    public Team(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Id
    @Column(name = "TEAM_ID")
    private String id;

    @Column(name = "NAME")
    private String name;

    @OneToOne(mappedBy = "team") // 외래키가 없는 쪽, 일(1) 쪽은 연관관계의 주인이 될 수 없다.
    private Member member;
}
