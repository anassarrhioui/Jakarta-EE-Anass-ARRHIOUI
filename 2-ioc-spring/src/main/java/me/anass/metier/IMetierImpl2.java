package me.anass.metier;

import me.anass.dao.IDao;

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
