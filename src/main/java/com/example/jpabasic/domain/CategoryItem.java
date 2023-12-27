package com.example.jpabasic.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "category_item")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryItem {

    @Id
    @Column(name = "category_item_id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    Category category;

    @ManyToOne
    @JoinColumn(name = "item_id")
    Item item;

}
