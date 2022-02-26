package me.anass.dao;

public class DaoImpl implements IDao{
    @Override
    public double getData() {
        return Math.random()*40;
    }
}
