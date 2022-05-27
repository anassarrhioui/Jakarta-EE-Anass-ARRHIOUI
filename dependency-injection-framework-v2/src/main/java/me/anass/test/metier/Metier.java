package me.anass.test.metier;

import me.anass.test.dao.Dao;

public class Metier {
    Dao dao;

    @Override
    public String toString() {
        return "Metier{" +
                "dao=" + dao +
                '}';
    }
}
