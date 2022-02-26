package me.anass.dao;

public class DaoImpl1 implements IDao{
    @Override
    public double getData() {
        System.out.println("Version 1: Capteur");
        return Math.random()*40;
    }
}


