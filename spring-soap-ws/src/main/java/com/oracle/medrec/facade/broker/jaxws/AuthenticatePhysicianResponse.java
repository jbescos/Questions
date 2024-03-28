
package com.oracle.medrec.facade.broker.jaxws;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "authenticatePhysicianResponse", namespace = "http://www.oracle.com/medrec")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "authenticatePhysicianResponse", namespace = "http://www.oracle.com/medrec")
public class AuthenticatePhysicianResponse {

    @XmlElement(name = "return", namespace = "")
    private boolean _return;

    /**
     * 
     * @return
     *     returns boolean
     */
    public boolean isReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(boolean _return) {
        this._return = _return;
    }

}
