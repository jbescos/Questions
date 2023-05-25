package test;

import java.io.Serializable;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "generalCorrespondenceAddress")
public class GeneralCorrespondenceAddress implements Serializable {

    private final static long serialVersionUID = 1L;
    protected AddressInformation addressInformation;

    public AddressInformation getAddressInformation() {
        return addressInformation;
    }

    public void setAddressInformation(AddressInformation value) {
        this.addressInformation = value;
    }

}
