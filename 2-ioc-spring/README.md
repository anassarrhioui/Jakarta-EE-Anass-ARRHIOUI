# Injection des dépendances avec le framework spring

**Date**: *21/02/2022*

Le but c'est de faciliter l'injection de dépendances en utilisant un framework d'inversion de contrôle. Dans notre cas nous allons utiliser Spring.

    Pour faire de l'injection de dépendances avec Spring, on doit ajouter les fichiers Jar de spring-core, spring-context et spring-beans dans le classpath du projet.
    Pour éviter cela, on peut utiliser Maven, pour faire la gestion des dépendances du projet, et aussi la gestion du cycle de vie du logiciel.

## Declaration des dépendances dans pom.xml

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-core</artifactId>
        <version>5.3.16</version>
    </dependency>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-context</artifactId>
        <version>5.3.16</version>
    </dependency>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-beans</artifactId>
        <version>5.3.16</version>
    </dependency>
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.12</version>
        <scope>test</scope>
    </dependency>
</dependencies>
```
- **JUnit** c'est pour faire les tests unitaires du projets.
- Dans un projet **Maven**, on peut lancer les tests avec la commande mvn test.

Spring nous donne la possibilité de faire l'injection des dépendances avec deux manières différentes, soit avec la configuration XML ou bien avec les annotations (à partir de la version 5 de java).

## Configuration XML
Dans cette configuration, nous indiquons que nous disposons de deux **Beans** dao et metier, et que dans ce deuxième bean il y a un attribut de type dao que spring doit injecter en utilisant le setter.

### applicationContext.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="dao" class="me.anass.dao.DaoImpl"/>
    <bean id="metier" class="me.anass.metier.IMetierImpl2">
        <property name="dao" ref="dao"/>
    </bean>
</beans>
```

Nous commencerons par créer le context de l'application en ce basant sur le fichier de configuration xml, après on a accès à tous les beans qui sont gérés par spring.
Spring a aussi injecter une bean doa dans l'attribut doa qui se trouve dans l'implémentation **DaoImpl**.
 
### PresentationSpringXml.java
```java
public class PresentationSpringXml {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        IMetier metier = applicationContext.getBean(IMetier.class);
        System.out.println(metier.calcul());
    }
}
```

### IMetier.java
```java
public interface IMetier {
    double calcul();
}
```

### IMetierImpl2.java
```java
public class IMetierImpl2 implements IMetier {

    IDao dao;

    @Override
    public double calcul() {

        double tmp = dao.getData();
        System.out.println("Version 2");
        double result = tmp*540/Math.cos(tmp*Math.PI);
        return result;
    }

    public void setDao(IDao dao) {
        this.dao = dao;
    }
}
```

## Annotation
Avec l'introduction des annotations dans Java 5, Spring a ajouté une nouvelle manière pour faire l'injection des dependances, c'est en utilisant des annotations comme @autowired et @Qualifier...  

Cette manière pour faire DI *(dependency injection)* est très facile et ne fait pas perdre du temps. Il suffit d'ajouter l'annotation Component, ou bien même l'annotation @Service qui est un alias de Component.
```java
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Service {
    @AliasFor(
        annotation = Component.class
    )
    String value() default "";
}
```

### IMetier.java
```java
public interface IMetier {
    double calcul();
}
```

### IMetierImpl.java
```java
@Component("metier1")
public class IMetierImpl implements IMetier {

    IDao dao;

    @Autowired
    public IMetierImpl(IDao dao) {
        System.out.println("Con");
        this.dao = dao;
    }

    @Override
    public double calcul() {

        double tmp = dao.getData();
        System.out.println("Version 1");
        double result = tmp*540/Math.cos(tmp*Math.PI);
        return result;
    }

    public void setDao(IDao dao) {
        this.dao = dao;
    }
}
```

###PresentationSpringAnnotation.java
```java
public class PresentationSpringAnnotation {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("me");
        IMetier metier = context.getBean(IMetier.class);
        System.out.println(metier.calcul());
    }
}
```

