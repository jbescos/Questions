
package com.oracle.medrec.facade.broker.jaxws;

import com.oracle.medrec.facade.model.FoundPatient;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "findApprovedPatientBySsnResponse", namespace = "http://www.oracle.com/medrec")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "findApprovedPatientBySsnResponse", namespace = "http://www.oracle.com/medrec")
public class FindApprovedPatientBySsnResponse {

    @XmlElement(name = "return", namespace = "")
    private FoundPatient _return;

    /**
     * 
     * @return
     *     returns FoundPatient
     */
    public FoundPatient getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(FoundPatient _return) {
        this._return = _return;
    }

}
