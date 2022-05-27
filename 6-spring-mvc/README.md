# JEE : Spring MVC

## Server Side redering

**Date**: *21/03/2022*

Dans cette partie nous allons commencer la partie web avec Spring MVC.
Dans l'écosystème de spring, il y a le projet Spring MVC qui nous permet de gérer tout ce qui relation avec le WEB.  
Il permet de faire un rendu côté serveur avec un moteur de template comme Thymeleaf et aussi de retourner des données au
format JSON pour faire un rendu côté client, pour faire la sérialisation de données JSON, Spring MVC utilise Jackson.

Spring MVC utilise un contrôleur frontale est chargé d'acheminer la requête avec le contrôleur adéquat, ce contrôleur
est nommé ServletDispatcher

Dans ce TP nous allons essayer de faire une gestion des patients avec un rendu cote Serveur avec Thymeleaf

**Dépendance dans POM.xml**

```xml
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
</dependency>
```

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>
<dependency>
    <groupId>org.thymeleaf.extras</groupId>
    <artifactId>thymeleaf-extras-springsecurity5</artifactId>
</dependency>
<dependency>
    <groupId>org.webjars</groupId>
    <artifactId>bootstrap</artifactId>
    <version>5.1.3</version>
</dependency>
<dependency>
    <groupId>nz.net.ultraq.thymeleaf</groupId>
    <artifactId>thymeleaf-layout-dialect</artifactId>
</dependency>
```

**Configuration dans application.properties**

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/patient_db2?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=anassadmin
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.ddl-auto=create
spring.thymeleaf.cache=false
server.port=8081
server.error.include-binding-errors=always
spring.jpa.show-sql=true
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

### Patient

```java
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 20)
    private String nom;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent
    private Date dateNaissance;

    private boolean estMalade;
    @Min(0)
    @Max(10)
    private int score;
}
```
## Controlleur
Quand on utilise un rendu côté serveur, on doit affecter les données que nous voulons afficher dans la vue, dans le modèle, après on retourne le nom de la vue à générer
```java
@GetMapping("/formPatient")
public String formPatient(Model model){
    model.addAttribute("patient", new Patient());
    return "editPatient";
}
```

## Vue
```xml
<!DOCTYPE html>
<html
        lang="en"
        xmlns:th="http://www.thymeleaf.org"
        xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout"
        layout:decorate="layout">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <div layout:fragment="container" class="container mt-5">
        <form th:method="post" th:action="@{addPatient}" th:object="${patient}">
            <div class="form-group">
                <label for="id">ID</label>
                <input type="text" name="id" class="form-control" id="id" placeholder="Id du patient" th:value="${patient.id}">
                <span th:errors="${patient.id}" class="invalid-feedback text-danger"></span>
            </div>
            <div class="form-group">
                <label for="nom">Nom</label>
                <input type="text" name="nom" class="form-control" id="nom" placeholder="Nom du patient" th:value="${patient.nom}">
                <span th:errors="${patient.nom}" class="invalid-feedback text-danger"></span>
            </div>
            <div class="form-group">
                <label for="estMalade">Est Malade</label>
                <input type="checkbox" name="estMalade" class="form-control" style="width: auto;" id="estMalade" th:checked="${patient.estMalade}">
                <span th:errors="${patient.estMalade}" class="invalid-feedback text-danger"></span>
            </div>
            <div class="form-group">
                <label for="dateNaissance">Date de Naissance</label>
                <input type="date" name="dateNaissance" class="form-control" id="dateNaissance" placeholder="Date de Naissance du patient" th:value="${patient.dateNaissance}">
                <span th:errors="${patient.dateNaissance}" class="invalid-feedback text-danger"></span>
            </div>
            <div class="form-group">
                <label for="score">Score</label>
                <input type="number" name="score" min="0" max="10" class="form-control" id="score" placeholder="Score du patient" th:value="${patient.score}">
                <span th:errors="${patient.score}" class="invalid-feedback text-danger"></span>
            </div>

            <button type="submit" class="btn btn-success">Ajouter</button>
        </form>
    </div>
</body>
</html>
```
