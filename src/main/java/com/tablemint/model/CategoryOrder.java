package com.tablemint.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "category_order")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category_name", nullable = false,unique = true,length = 100)
    private String categoryName;

    @Column(name = "sort_order", nullable = false)
    private int sortOrder;
}
