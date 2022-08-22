package com.example.general;

import static org.junit.Assert.assertEquals;

import java.io.StringWriter;

import org.junit.Test;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlMimeType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

public class XmlParseTest {

    @Test
    public void parse() throws JAXBException {
        RunReportResponse runReport = new RunReportResponse();
        ReportResponse report = new ReportResponse();
        runReport.runReportReturn = report;
        report.reportBytes = "test".getBytes();

        JAXBContext context = org.eclipse.persistence.jaxb.JAXBContextFactory
                .createContext(new Class[] { RunReportResponse.class, ReportResponse.class }, null);
        StringWriter writer = new StringWriter();
        Marshaller marshaller = context.createMarshaller();
        marshaller.marshal(runReport, writer);
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\"?><runReportResponse><runReportReturn><reportBytes>dGVzdA==</reportBytes></runReportReturn></runReportResponse>", writer.toString());
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    @XmlRootElement(name = "runReportResponse")
    public static class RunReportResponse {
        @XmlElement(required = true)
        public ReportResponse runReportReturn;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "ReportResponse")
    public static class ReportResponse {
        @XmlElement(required = true, nillable = true)
        @XmlMimeType(value = "test")
        public byte[] reportBytes;
    }
}
