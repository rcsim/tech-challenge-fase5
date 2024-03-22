package com.postech30.msusermanager.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity(name = "User")
@Table(name = "tb_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String email;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    private List<Role> roles;

}
