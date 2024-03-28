
package com.oracle.medrec.facade.broker.jaxws;

import com.oracle.medrec.facade.model.RecordDetail;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getRecordDetailResponse", namespace = "http://www.oracle.com/medrec")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getRecordDetailResponse", namespace = "http://www.oracle.com/medrec")
public class GetRecordDetailResponse {

    @XmlElement(name = "return", namespace = "")
    private RecordDetail _return;

    /**
     * 
     * @return
     *     returns RecordDetail
     */
    public RecordDetail getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(RecordDetail _return) {
        this._return = _return;
    }

}
