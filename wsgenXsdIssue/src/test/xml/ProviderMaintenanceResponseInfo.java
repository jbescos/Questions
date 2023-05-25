package test.xml;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "status", "errorMessage", "hccIdentifier", "identifier", "businessType", "signDate" })
@XmlRootElement(name = "ProviderMaintenanceResponseInfo")
public class ProviderMaintenanceResponseInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String status;
    private List<String> errorMessage;
    private String hccIdentifier;
    private Long identifier;
//    private CVCTransactionType providerChangeType;
//    private TransactionInformation transactionInformation;
        private String businessType;
        private String signDate;

    public ProviderMaintenanceResponseInfo() {
        super();
        errorMessage = new ArrayList<String>();
    }

    @XmlElement(name = "Status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @XmlElement(name = "ErrorMessage")
    public List<String> getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(List<String> errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getHccIdentifier() {
        return hccIdentifier;
    }

    public void setHccIdentifier(String hccIdentifier) {
        this.hccIdentifier = hccIdentifier;
    }

    public Long getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Long identifier) {
        this.identifier = identifier;
    }
    
//   @XmlElement(name = "providerChangeType")
//      public CVCTransactionType getProviderChangeType() {
//              return providerChangeType;
//      }
//
//      public void setProviderChangeType(CVCTransactionType providerChangeType) {
//              this.providerChangeType = providerChangeType;
//      }
//
//      @XmlElement(name = "transactionInformation")
//      public TransactionInformation getTransactionInformation() {
//              return transactionInformation;
//      }
//
//      public void setTransactionInformation(
//                      TransactionInformation transactionInformation) {
//              this.transactionInformation = transactionInformation;
//      }

        @XmlElement(name = "businessType")
        public String getBusinessType() {
                return businessType;
        }

        public void setBusinessType(String businessType) {
                this.businessType = businessType;
        }

        @XmlElement(name = "signDate")
        public String getSignDate() {
                return signDate;
        }

        public void setSignDate(String signDate) {
                this.signDate = signDate;
        }

        @Override
        public String toString() {
                return "ProviderMaintenanceResponseInfo [status=" + status
                                + ", errorMessage=" + errorMessage + ", hccIdentifier="
                                + hccIdentifier + ", identifier=" + identifier
//                              + ", providerChangeType=" + providerChangeType
//                              + ", transactionInformation=" + transactionInformation + ", businessType=" + businessType
                                + ", signDate=" + signDate + "]";
        }
        
        

}
