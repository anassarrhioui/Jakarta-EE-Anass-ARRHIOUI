package me.anass.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "property")
@XmlAccessorType(XmlAccessType.FIELD)
public class Property {

    @XmlAttribute
    String name;

    @XmlAttribute
    String ref;

    @XmlAttribute
    String injectionType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInjectionType() {
        return injectionType;
    }

    public void setInjectionType(String injectionType) {
        this.injectionType = injectionType;
    }


    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    @Override
    public String toString() {
        return "Property{" +
                "name='" + name + '\'' +
                ", ref='" + ref + '\'' +
                ", injectionType='" + injectionType + '\'' +
                '}';
    }
}


