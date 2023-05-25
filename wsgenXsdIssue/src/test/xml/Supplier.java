package test.xml;

import java.io.Serializable;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "supplier")
public class Supplier implements Serializable {

    private final static long serialVersionUID = 1L;
    protected String nationalProviderId;
    protected GeneralCorrespondenceAddress generalCorrespondenceAddress;

    public String getNationalProviderId() {
        return nationalProviderId;
    }

    public void setNationalProviderId(String value) {
        this.nationalProviderId = value;
    }

    public GeneralCorrespondenceAddress getGeneralCorrespondenceAddress() {
        return generalCorrespondenceAddress;
    }

    public void setGeneralCorrespondenceAddress(GeneralCorrespondenceAddress value) {
        this.generalCorrespondenceAddress = value;
    }

}
