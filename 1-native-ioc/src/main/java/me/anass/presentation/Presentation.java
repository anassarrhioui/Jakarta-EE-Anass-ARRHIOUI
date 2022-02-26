package me.anass.presentation;

import me.anass.dao.DaoImpl1;
import me.anass.dao.IDao;
import me.anass.metier.IMetier;
import me.anass.metier.IMetierImpl;

import java.io.File;
import java.util.Scanner;


public class Presentation {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(new File("/home/arrhioui/Documents/S4/JEE-Spring-Angular/TPs Cours/1-native-ioc/src/main/java/me/anass/presentation/config.txt"));

        // Instanciation statique
        IDao dao1 = new DaoImpl1();
        IMetier metier1 = new IMetierImpl();
        IMetierImpl.class.getMethod("setDao", IDao.class).invoke(metier1, dao1);
        System.out.println(metier1.calcul());

        // instanciation dynamique
        String daoPath = scanner.nextLine();
        String metierPath = scanner.nextLine();

        Class<?> daoClazz = Class.forName(daoPath);
        Class<?> metierClazz = Class.forName(metierPath);

        IDao dao = (IDao) daoClazz.getDeclaredConstructor().newInstance();
        IMetierImpl metier = (IMetierImpl) metierClazz.getDeclaredConstructor().newInstance();

        metierClazz.getMethod("setDao", IDao.class).invoke(metier, dao);
        System.out.println(metier.calcul());
    }
}
