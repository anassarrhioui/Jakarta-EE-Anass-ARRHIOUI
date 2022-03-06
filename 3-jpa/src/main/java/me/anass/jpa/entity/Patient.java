package me.anass.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Patient implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOM", length = 60)
    private String nom;

    @Temporal(TemporalType.DATE)
    private Date dateNaisance;
    private Boolean malade;
    private Integer score;

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", dateNaisance=" + dateNaisance +
                ", malade=" + malade +
                ", score=" + score +
                '}';
    }
}
