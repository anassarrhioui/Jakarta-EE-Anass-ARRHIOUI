package me.anass.presentation;

import me.anass.dao.IDao;
import me.anass.metier.IMetierImpl;

import java.io.File;
import java.util.Scanner;


public class Presentation {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(new File("/home/arrhioui/Documents/S4/JEE-Spring-Angular/TPs Cours/1-native-ioc/src/main/java/me/anass/presentation/config.txt"));

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
