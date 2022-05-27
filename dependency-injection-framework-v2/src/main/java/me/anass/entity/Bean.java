package me.anass.entity;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@XmlRootElement(name = "bean")
@XmlAccessorType(XmlAccessType.FIELD)
public class Bean implements Comparable<Bean>{

    public Bean() {
    }

    public Bean(String id, Class<?> cls, Class<?> parentCls) {
        this.id = id;
        this.cls = cls;
    }

    @XmlAttribute
    String id;

    @XmlAttribute(name = "class")
    Class<?> cls;

    @XmlElement(name = "property")
    List<Property> properties = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Class<?> getCls() {
        return cls;
    }

    public void setCls(Class<?> cls) {
        this.cls = cls;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

    @Override
    public String toString() {
        return "Bean{" +
                "id='" + id + '\'' +
                ", cls=" + cls +
                ", properties=" + Arrays.toString(properties.toArray()) +
                '}';
    }

    @Override
    public int compareTo(Bean o) {
        return Integer.compare(this.properties.size(), o.properties.size());
    }
}
