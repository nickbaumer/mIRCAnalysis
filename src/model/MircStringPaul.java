package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class MircStringPaul {
    private String data;
    private String DEFAULT_DATA = "No Data";
    
    public MircStringPaul() {
    	this.setData(DEFAULT_DATA);
    }
    
    public MircStringPaul(String data) {
    	this.setData(data);
    }
    
    public String getData() {
		return data;
	}

	public void setData(String data) {
		if (!data.isEmpty()) {
			this.data = data;
		} else {
			this.data = DEFAULT_DATA;
		}
	}

    @Override
    public String toString() {
        return "MircString{" + "string=" + this.getData() + '}';
    }
}