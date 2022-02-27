package me.anass.context;

import me.anass.entity.Bean;
import me.anass.entity.Beans;
import me.anass.test.metier.Metier;
import org.junit.Before;
import org.junit.Test;

import javax.xml.bind.JAXBException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class BeansLoaderImplTest {
    BeansLoader beansLoader;

    @Before
    public void setUp() throws JAXBException {
        beansLoader = new BeansLoaderImpl();
    }

    @Test
    public void load() throws JAXBException {
        Map<String, Object> beans = beansLoader.load();
        Metier l = (Metier) beans.get("metier");
        System.out.println("l = " + l);
    }
}