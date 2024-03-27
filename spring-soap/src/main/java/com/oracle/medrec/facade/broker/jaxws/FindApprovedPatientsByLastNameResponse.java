
package com.oracle.medrec.facade.broker.jaxws;

import java.util.List;
import com.oracle.medrec.facade.model.FoundPatient;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "findApprovedPatientsByLastNameResponse", namespace = "http://www.oracle.com/medrec")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "findApprovedPatientsByLastNameResponse", namespace = "http://www.oracle.com/medrec")
public class FindApprovedPatientsByLastNameResponse {

    @XmlElement(name = "return", namespace = "")
    private List<FoundPatient> _return;

    /**
     * 
     * @return
     *     returns List<FoundPatient>
     */
    public List<FoundPatient> getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(List<FoundPatient> _return) {
        this._return = _return;
    }

}
