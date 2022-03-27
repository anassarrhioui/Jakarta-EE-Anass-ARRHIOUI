# JEE : ORM, JPA, Hibernate, Spring Data
## Associations Avancées

**Date**: *14/03/2022*

Dans le TP précédent nous avons pratiqué mise en place une application de gestion des patients, dans laquelle nous avons utilisé spring data JPA pour faire l’ORM, dans les cas OneToMany et OneToOne.  

Alors dans ce TP nous allons pratique un autre type d’association qui est le ManyToMany. L’exemple le plus typique est celui de la gestion des rôles d’utilisateur. un utilisateur peut avoir plusieurs rôles, et un rôle peut être affecté à plusieurs utilisateurs. et cela sera l’objectif de ce TP.

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

### User
```java
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    private String id;
    @Column(unique = true, length = 50)
    private String username;
    private String password;
    @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
    private List<Role> roles = new ArrayList<>();
}
```

### Role
```java
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, length = 20)
    private String roleName;
    @Column(name = "DESCRIPTION")
    private String desc;
    @ManyToMany
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<User> users;
}
```
Dans le cas d’une relation @ManyToMany, une nouvelle table d’association sera créé qui va contenir les clés étrangère des deux autres tables.

    Dans le cas où on veut que la table d’association contient d’autres données que les clés étranges, alors l’utilisation de ManyToMany n’est pas le meilleur choix, et pour remédier à ce problème on doit tout simplement convertir la relation ManyToMany en OneToMany ManyToOne, cela veut dire qu’on doit nous même créer la table d’association

## Hachage des mot de passe
Il  est recommandé de hasher les mots de passe des utilisateurs avant de les stockées dans la base de données, alors nous allons utiliser BCrypt qui est une fonction de hachage basée sur l'algorithme de chiffrement Blowfish

Nous allons utilisé bcrypt de at.favre.lib
```xml
    <dependency>
        <groupId>at.favre.lib</groupId>
        <artifactId>bcrypt</artifactId>
        <version>0.9.0</version>
    </dependency>
```

Avant l'ajout de l'utilisateur dans la base de données nous allons tout d'abord commencé par hasher le mot de passe


```java
    @Override
    public User saveUser(User user) {
        user.setId(UUID.randomUUID().toString());
        String bcryptHashString = BCrypt.withDefaults().hashToString(12, user.getPassword().toCharArray());
        user.setPassword(bcryptHashString);
        return userRepository.save(user);
    }
```

Durant l'authentification, nous devons aussi hasher l'input de l'utilisateur et la comparer avec le mot de passe stocké dans la base de données
```java
    @Override
    public User authenticate(String username, String password) {
        User user = userRepository.findByUsername(username);
        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
        return result.verified ? user : null;
    }
```