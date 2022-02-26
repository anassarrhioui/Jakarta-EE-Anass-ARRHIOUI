package me.anass.dao;

public class DaoImpl2 implements IDao{
    @Override
    public double getData() {
        System.out.println("Version 2: WS");
        return Math.random()*40;
    }
}
