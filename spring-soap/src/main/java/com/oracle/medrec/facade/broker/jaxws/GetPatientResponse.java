
package com.oracle.medrec.facade.broker.jaxws;

import com.oracle.medrec.model.Patient;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getPatientResponse", namespace = "http://www.oracle.com/medrec")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getPatientResponse", namespace = "http://www.oracle.com/medrec")
public class GetPatientResponse {

    @XmlElement(name = "return", namespace = "")
    private Patient _return;

    /**
     * 
     * @return
     *     returns Patient
     */
    public Patient getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(Patient _return) {
        this._return = _return;
    }

}
