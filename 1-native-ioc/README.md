# Injection des dépendances sans l’utilisation de framework

**Date**: *14/02/2022*

Le but c’est d’utiliser le **couplage faible**, et pour cela il faut dépendre sur les **interfaces** non pas sur les **classes**.
###IDao
```java
package me.anass.dao;

public interface IDao {
    double getData();
}
```
### Une première implémntation
```java
package me.anass.dao;

public class DaoImpl1 implements IDao{
    @Override
    public double getData() {
        System.out.println("Version 1: Capteur");
        return Math.random()*40;
    }
}
```

### Une deuxième implémntation
```java
package me.anass.dao;

public class DaoImpl2 implements IDao{
    @Override
    public double getData() {
        System.out.println("Version 2: WS");
        return Math.random()*80;
    }
}
```

Pour exploiter ce couplage faible, on met en place un fichier de configuration qui va nous permettre de changer le comportement de la méthode ```getData()```, sans changer son code. C'est le principe de fonctionnement du Design Pattern **Strategy**.

## Résultat
- Pour la configuration suivante
```text
me.anass.dao.DaoImpl2
me.anass.metier.IMetierImpl
```
- Output
```text
Version 2: WS
-15157.30878952226
```

- Pour la configuration suivante
```text
me.anass.dao.DaoImpl1
me.anass.metier.IMetierImpl
```
- Output
```text
Version 1: Capteur
-7862.610094662442
```

Le même principe est appliqué pour la couche metier.

### Maintenant pour automatiser cette injection de dépendances on doit passer par un framework d'inversion de contrôle comme Spring. Et cela est le but du prochain chapitre.

