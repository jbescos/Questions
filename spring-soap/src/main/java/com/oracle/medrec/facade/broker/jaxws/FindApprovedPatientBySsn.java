
package com.oracle.medrec.facade.broker.jaxws;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "findApprovedPatientBySsn", namespace = "http://www.oracle.com/medrec")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "findApprovedPatientBySsn", namespace = "http://www.oracle.com/medrec")
public class FindApprovedPatientBySsn {

    @XmlElement(name = "arg0", namespace = "")
    private String arg0;

    /**
     * 
     * @return
     *     returns String
     */
    public String getArg0() {
        return this.arg0;
    }

    /**
     * 
     * @param arg0
     *     the value for the arg0 property
     */
    public void setArg0(String arg0) {
        this.arg0 = arg0;
    }

}
