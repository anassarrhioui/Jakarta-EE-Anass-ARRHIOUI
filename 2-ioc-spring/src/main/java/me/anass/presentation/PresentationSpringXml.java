package me.anass.presentation;

import me.anass.metier.IMetier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PresentationSpringXml {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        IMetier metier = applicationContext.getBean(IMetier.class);
        System.out.println(metier.calcul());
    }
}
