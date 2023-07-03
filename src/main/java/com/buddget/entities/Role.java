package com.buddget.entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table("tb_roles")
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String authority;



}
