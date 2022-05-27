package me.anass.xml;

import me.anass.entity.Beans;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class XMLProcessorImpl implements XMLProcessor {
    private final JAXBContext context;

    public XMLProcessorImpl() throws JAXBException {
        context = JAXBContext.newInstance(Beans.class);
    }

    @Override
    public Beans xmlConfigToBeans() throws JAXBException {
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (Beans) unmarshaller.unmarshal(new File("config.xml"));
    }
}
