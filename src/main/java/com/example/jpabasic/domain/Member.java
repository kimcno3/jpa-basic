package com.example.jpabasic.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "member")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @Column(name = "member_id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @Column(name = "zipCode")
    private String zipCode;

// 기타 컬럼
//    @ManyToOne
//    @JoinColumn(name = "team_id")
//    private Team team;

//    @Enumerated(EnumType.STRING)
//    @Column(name = "role_type")
//    private RoleType roleType;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name = "created_date")
//    private LocalDateTime createdDate;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name = "updated_date")
//    private LocalDateTime updatedDate;
//
//    @Lob
//    private String description;
//
//    @Transient // DB 반영 X
//    private String temp;

}
