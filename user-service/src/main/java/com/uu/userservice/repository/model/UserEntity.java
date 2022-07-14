package com.uu.userservice.repository.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "`user`")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    private String username;

    private String password;

    private String role;

    private Integer shopId;

    private boolean active = true;
}
