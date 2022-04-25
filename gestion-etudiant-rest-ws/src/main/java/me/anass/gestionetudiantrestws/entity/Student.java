package me.anass.gestionetudiantrestws.entity;

import lombok.*;
import me.anass.gestionetudiantrestws.enumeration.GenreEnum;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 20)
    private String nom;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 20)
    private String prenom;

    @Email
    private String email;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateNaissance;

    @Enumerated(EnumType.STRING)
    private GenreEnum genre;

    private Boolean enRegle;
}
