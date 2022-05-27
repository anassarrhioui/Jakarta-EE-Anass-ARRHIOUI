package me.anass.xml;

import me.anass.entity.Beans;
import org.junit.Before;
import org.junit.Test;

import javax.xml.bind.JAXBException;

public class XMLProcessorImplTest {


    private XMLProcessorImpl xmlProcessor;

    @Before
    public void setUp() throws Exception {
        xmlProcessor = new XMLProcessorImpl();
    }

    @Test
    public void xmlToObject() throws JAXBException {
        Beans beans = xmlProcessor.xmlConfigToBeans();
    }
}