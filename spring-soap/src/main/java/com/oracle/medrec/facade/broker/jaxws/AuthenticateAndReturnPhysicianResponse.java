
package com.oracle.medrec.facade.broker.jaxws;

import com.oracle.medrec.facade.model.AuthenticatedPhysician;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "authenticateAndReturnPhysicianResponse", namespace = "http://www.oracle.com/medrec")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "authenticateAndReturnPhysicianResponse", namespace = "http://www.oracle.com/medrec")
public class AuthenticateAndReturnPhysicianResponse {

    @XmlElement(name = "return", namespace = "")
    private AuthenticatedPhysician _return;

    /**
     * 
     * @return
     *     returns AuthenticatedPhysician
     */
    public AuthenticatedPhysician getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(AuthenticatedPhysician _return) {
        this._return = _return;
    }

}
