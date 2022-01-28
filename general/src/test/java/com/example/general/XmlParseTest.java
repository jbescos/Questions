package com.example.general;

import static org.junit.Assert.assertEquals;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.junit.Test;

public class XmlParseTest {

    @Test
    public void parse() throws JAXBException {
        RunReportResponse runReport = new RunReportResponse();
        ReportResponse report = new ReportResponse();
        runReport.setRunReportReturn(report);
        report.setReportBytes("test".getBytes());

        JAXBContext context = org.eclipse.persistence.jaxb.JAXBContextFactory
                .createContext(new Class[] { RunReportResponse.class, ReportResponse.class }, null);
        StringWriter writer = new StringWriter();
        context.createMarshaller().marshal(runReport, writer);
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\"?><runReportResponse><runReportReturn><reportBytes>dGVzdA==</reportBytes></runReportReturn></runReportResponse>", writer.toString());
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    @XmlRootElement(name = "runReportResponse")
    public static class RunReportResponse {
        @XmlElement(required = true)
        protected ReportResponse runReportReturn;

        public ReportResponse getRunReportReturn() {
            return runReportReturn;
        }

        public void setRunReportReturn(ReportResponse value) {
            this.runReportReturn = value;
        }
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "ReportResponse")
    public static class ReportResponse {
        @XmlElement(required = true, nillable = true)
        @XmlMimeType(value = "test")
        protected byte[] reportBytes;

        public byte[] getReportBytes() {
            return reportBytes;
        }

        public void setReportBytes(byte[] value) {
            this.reportBytes = value;
        }
    }
}
