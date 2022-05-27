package me.anass.context;

import me.anass.entity.Bean;
import me.anass.entity.Beans;
import me.anass.xml.XMLProcessor;
import me.anass.xml.XMLProcessorImpl;

import javax.xml.bind.JAXBException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BeansLoaderImpl implements BeansLoader {
    XMLProcessor xmlProcessor = new XMLProcessorImpl();

    public BeansLoaderImpl() throws JAXBException {
    }

    @Override
    public Map<String, Object> load() throws JAXBException {
        Beans beans = xmlProcessor.xmlConfigToBeans();
        Collections.sort(beans.getBeans());
        Map<String, Object> beansMap = new HashMap<>();

        beans.getBeans().forEach(b -> {
            System.out.println("b = " + b);
            try {
                if(b.getProperties().size() == 0){
                    beansMap.put(b.getId(), b.getCls().getDeclaredConstructor().newInstance());
                }else {
                    Object tmpObject = b.getCls().getDeclaredConstructor().newInstance();
                    beansMap.put(b.getId(), tmpObject);
                    b.getProperties().forEach(p-> {
                        try {
                            Field field = b.getCls().getDeclaredField(p.getName());
                            field.setAccessible(true);
                            field.set(tmpObject, beansMap.get(p.getRef()));
                        } catch (NoSuchFieldException | IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        return beansMap;
    }
}
