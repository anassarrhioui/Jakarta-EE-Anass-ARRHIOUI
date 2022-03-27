# JEE : ORM, JPA, Hibernate, Spring Data
## Associations

**Date**: *07/03/2022*

Un des points fort de Spring data JPA c’est la facilité de faire les associations entre les classes, en fait Data Jpa fournit des annotations qui peuvent prendre en considération tous les cas possibles, comme @OneToMany, @ManyToOne, @OneToOne et @ManyToMany. 

Dans cette partie nous allons pratiquer les trois premières annotations dans une application de gestion des patients, rendez-vous et consultation avec un médecin.

Nous allons utiliser les même dépendances que la dernière fois nous allons rien ajouté
###Utilisation de Spring Data JAP
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
```

##Connecteur JDBC pour la base de données H2
H2 est une base de données en memoire.

**Dépendance dans POM.xml**
```xml
<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <scope>runtime</scope>
</dependency>
```

**Configuration dans application.properties**
```properties
spring.datasource.url=jdbc:h2:mem:patient-db
spring.h2.console.enabled=true
```

##Connecteur JDBC pour la base de données MySql
```xml
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.28</version>
</dependency>
```
**Configuration dans application.properties**
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/db_patient?serverTimezone=UTC
spring.datasource.username = root
spring.datasource.password = motdepasse
spring.jpa.show-sql = true
spring.jpa.hibernate.ddl-auto = create
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL8Dialect
```

## Entités JPA
Nous allons créer les entités JPA qui sont Medecin, Patient, RendezVous, Consultation

### Patient
```java
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;
    private Boolean malade;
}
```

### Medecin
```java
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Medecin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String email;
    private String specialite;
}
```

### RendezVous
```java
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RendezVous {
    @Id

    private String id;
    private Date date;
    @Enumerated(EnumType.STRING)
    private StatusRendeVous status;
}
```

### Consultation
```java
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Consultation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private String rapport;
}
```

## Associations entre les entités JPA

### Association Patient - RendezVous

Une patient peut avoir plusieurs rendez-vous et un rendez-vous appartient a un seul patient. Alors cela est modelisable avec la relation @ManyToOne @OneToMany


Dans la classe Patient on ajoute la liste des rendez-vous annotée avec @OneToMany.  

**mappedBy** : le nom de l'attribut qui se trouve dans la classe RendezVous et qui va refléter cette relation  

**fetch** : le type de récupération de données de cette liste, il peut être soit LAZY ou bien EAGER. dans le premier cas les données de la liste sont pas récupérer au moment de la récupération de l’objet patent mais c’est juste quand on les demande, par contre dans le deuxième cas qui est EAGER, les données sont récupérées en même temps avec l’objet
```
    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
    private List<RendezVous> rendezVousCollections;
```

Dans la classe RendezVous on trouve l'association suivante:

On utilise @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) pour éviter le problème de l'inclusion infini d'un objet dans un autre et ce dernier contient aussi le premier, cela engendre une exception de type StackOverFlowError. C'est juste un problème d'affichage avec jackson
```
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    private Patient patient;
```

de la même manière nous allons continuer pour avoir le résultats suivant: 
### Patient
```java
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;
    private Boolean malade;
    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
    private List<RendezVous> rendezVousCollections;
}
```

### Medecin
```java
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Medecin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String email;
    private String specialite;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "medecin")
    private List<RendezVous> rendezVousList;
}
```

### RendezVous
```java
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RendezVous {
    @Id

    private String id;
    private Date date;
    @Enumerated(EnumType.STRING)
    private StatusRendeVous status;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    private Patient patient;
    @ManyToOne
    private Medecin medecin;
    @OneToOne(mappedBy = "rendezVous")
    private Consultation consultation;
}
```

### Consultation
```java
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Consultation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private String rapport;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToOne
    private RendezVous rendezVous;
}
```