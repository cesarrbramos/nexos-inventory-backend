package com.nexos.inventory.model;

import javax.persistence.*;

import com.querydsl.core.annotations.QueryEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@QueryEntity
@Table(name = "USERS")
public class User extends Auditable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "FIRSTNAME")
    private String firstname;

    @Column(name = "LASTNAME")
    private String lastname;

    @Column(name = "BIRTH_DATE")
    private LocalDate birthDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private Role role;

    @Column(name = "ENTRY_DATE")
    private LocalDate entryDate;

}
