package com.example.jpabasic.domain;

import com.example.jpabasic.domain.enums.DeliveryStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "delivery")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Delivery {

    @Id
    @Column(name = "delivery_id")
    private long id;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @Column(name = "zipCode")
    private String zipCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private DeliveryStatus status;

}
