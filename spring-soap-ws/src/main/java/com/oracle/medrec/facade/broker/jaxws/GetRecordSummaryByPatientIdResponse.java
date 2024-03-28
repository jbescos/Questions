
package com.oracle.medrec.facade.broker.jaxws;

import com.oracle.medrec.facade.model.RecordSummary;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getRecordSummaryByPatientIdResponse", namespace = "http://www.oracle.com/medrec")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getRecordSummaryByPatientIdResponse", namespace = "http://www.oracle.com/medrec")
public class GetRecordSummaryByPatientIdResponse {

    @XmlElement(name = "return", namespace = "")
    private RecordSummary _return;

    /**
     * 
     * @return
     *     returns RecordSummary
     */
    public RecordSummary getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(RecordSummary _return) {
        this._return = _return;
    }

}
