package com.postech30.msusermanager.entity;


import com.postech30.msusermanager.entity.enums.UserRole;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String login;
    private  String password;
    private UserRole role;
}
