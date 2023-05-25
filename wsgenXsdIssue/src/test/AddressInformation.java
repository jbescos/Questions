package test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "addressInformation")
public class AddressInformation implements Serializable {

    private final static long serialVersionUID = 1L;
    protected AddressInformation.AddressPhoneList addressPhoneList;

    public AddressInformation.AddressPhoneList getAddressPhoneList() {
        return addressPhoneList;
    }

    public void setAddressPhoneList(AddressInformation.AddressPhoneList value) {
        this.addressPhoneList = value;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "telephone" })
    public static class AddressPhoneList implements Serializable {

        private final static long serialVersionUID = 1L;
        protected List<Telephone> telephone;

        public List<Telephone> getTelephone() {
            if (telephone == null) {
                telephone = new ArrayList<Telephone>();
            }
            return this.telephone;
        }

    }

}
