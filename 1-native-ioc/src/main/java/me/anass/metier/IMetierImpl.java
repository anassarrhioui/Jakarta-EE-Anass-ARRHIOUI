package me.anass.metier;

import me.anass.dao.IDao;

public class IMetierImpl implements IMetier {

    IDao dao;

    @Override
    public double calcul() {

        double tmp = dao.getData();
        double result = tmp*540/Math.cos(tmp*Math.PI);
        return result;
    }

    public void setDao(IDao dao) {
        this.dao = dao;
    }
}
