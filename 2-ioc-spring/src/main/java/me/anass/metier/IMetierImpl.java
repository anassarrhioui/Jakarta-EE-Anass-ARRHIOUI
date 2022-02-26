package me.anass.metier;

import me.anass.dao.IDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
