package test.xml;

import java.io.Serializable;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "Telephone")
public class Telephone implements Serializable{

    private static final long serialVersionUID = 1L;

    protected String phoneNumber;
    protected Telephone.PhoneType phoneType;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String value) {
        this.phoneNumber = value;
    }

    public Telephone.PhoneType getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(Telephone.PhoneType value) {
        this.phoneType = value;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "id",
        "type"
    })
    public static class PhoneType implements Serializable{

        private static final long serialVersionUID = 1L;

        protected String id;
        protected String type;

        public String getId() {
            return id;
        }

        public void setId(String value) {
            this.id = value;
        }

        public String getType() {
            return type;
        }

        public void setType(String value) {
            this.type = value;
        }

    }

}
