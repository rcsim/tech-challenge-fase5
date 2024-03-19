package com.postech30.msusermanager.entity;

import com.postech30.msusermanager.enums.UserRole;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private UserRole roleName;
}
