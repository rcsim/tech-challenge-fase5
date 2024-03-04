package com.postech30.msusermanager.entity;


import com.postech30.msusermanager.entity.enums.UserRole;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name = "tb_users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id")
    private String userId;

    private String name;
    private String email;
    private  String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        System.out.println(this.role);
        if (this.role == UserRole.ADMIN){
            System.out.println("admin cara");
            return List.of(
                    new SimpleGrantedAuthority("ROLE_ADMIN"),
                    new SimpleGrantedAuthority("ROLE_USER")
            );
        } else {
            System.out.println("user cara");
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public String getPassword(){
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
