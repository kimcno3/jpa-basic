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
public class Member {

    public Member(long id, String username) {
        this.id = id;
        this.username = username;
    }

    @Id
    @Column(name = "MEMBER_ID")
    private long id;

    @Column(name = "USERNAME")
    private String username;

}
