package com.example.general;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.junit.Test;

public class URLEncoderTest {

    @Test
    public void encode() throws UnsupportedEncodingException {
        String result = URLEncoder.encode("http://fa-internal.oracleoutsourcing.com:10663/analytics-ws/saw.dll?SoapImpl=userPersonalizationService", "UTF-8");
        System.out.println(result);
    }
}
