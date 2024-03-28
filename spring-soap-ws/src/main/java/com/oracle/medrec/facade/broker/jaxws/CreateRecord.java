
package com.oracle.medrec.facade.broker.jaxws;

import com.oracle.medrec.facade.model.RecordToCreate;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "createRecord", namespace = "http://www.oracle.com/medrec")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createRecord", namespace = "http://www.oracle.com/medrec")
public class CreateRecord {

    @XmlElement(name = "arg0", namespace = "")
    private RecordToCreate arg0;

    /**
     * 
     * @return
     *     returns RecordToCreate
     */
    public RecordToCreate getArg0() {
        return this.arg0;
    }

    /**
     * 
     * @param arg0
     *     the value for the arg0 property
     */
    public void setArg0(RecordToCreate arg0) {
        this.arg0 = arg0;
    }

}
