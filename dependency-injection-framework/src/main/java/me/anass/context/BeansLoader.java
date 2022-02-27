package me.anass.context;

import me.anass.entity.Beans;

import javax.xml.bind.JAXBException;
import java.util.Map;

public interface BeansLoader {
    Map<String, Object> load() throws JAXBException;
}
