package com.uu.userservice.repository.model;

import com.uu.microservice.core.config.UserType;
import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;

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

    @JsonIgnore
    private String password;
    @Enumerated(EnumType.STRING)
    private UserType type;

    private Integer shopId;

    private boolean active = true;
}
