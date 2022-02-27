package me.anass.xml;

import me.anass.entity.Beans;

import javax.xml.bind.JAXBException;

public interface XMLProcessor {
    Beans xmlConfigToBeans() throws JAXBException;
}
