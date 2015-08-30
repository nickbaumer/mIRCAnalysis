package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class MircString {
    @XmlAttribute
	private String string;
    
    public MircString() {
    }
    
    public static MircString of(String string) {
        System.out.println("Added successfully.");
        return new MircString(string);
    }

    private MircString(String string) {
        this.string = string;
    }

    // getters/setters

    @Override
    public String toString() {
        return "MircString{" + "string=" + string + "}";
    }
}