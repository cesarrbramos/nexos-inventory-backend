package com.nexos.inventory.model;

import javax.persistence.*;

import com.querydsl.core.annotations.QueryEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@QueryEntity
@Entity
@Table(name="PRODUCTS")
public class Product extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Long id;

    @Column(name="NAME")
    private String name;

    @Column(name="QUANTITY")
    private Integer quantity;

    @Column(name="ENTRY_DATE")
    private LocalDate entryDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private User createUser;

    @ManyToOne(fetch = FetchType.LAZY)
    private User updateUser;

}
