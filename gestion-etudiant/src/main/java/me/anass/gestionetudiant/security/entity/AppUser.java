package me.anass.gestionetudiant.security.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AppUser {

    @Id
    private String id;

    @Column(unique = true)
    private String username;

    private String password;

    private Boolean active;

    @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
    private List<AppRole> roles = new ArrayList<>();
}
