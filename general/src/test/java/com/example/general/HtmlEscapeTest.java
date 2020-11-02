package com.example.general;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HtmlEscapeTest {

    private static final String HTML = "<img src=\"x&\\\" \' onerror=alert(1) />";
    private static final Map<Character, String> ESCAPE_MAPPER = new HashMap<>();

    static {
        ESCAPE_MAPPER.put('\\', "&quot;");
        ESCAPE_MAPPER.put('&', "&amp;");
        ESCAPE_MAPPER.put('<', "&lt;");
        ESCAPE_MAPPER.put('>', "&gt;");
        ESCAPE_MAPPER.put('\'', "&#39;");
        ESCAPE_MAPPER.put('/', "&#x2F;");
        ESCAPE_MAPPER.put('=', "&#x3D;");
        ESCAPE_MAPPER.put('`', "&#x60;");
    }

    @Test
    public void escape() {
        assertEquals("&lt;img src&#x3D;\"x&amp;&quot;\" &#39; onerror&#x3D;alert(1) &#x2F;&gt;", escaped(HTML));
    }

    private String escaped(String text) {
        StringBuilder escapedText = new StringBuilder();
        for (char c : text.toCharArray()) {
            String replaced = ESCAPE_MAPPER.get(c);
            if (replaced != null) {
                escapedText.append(replaced);
            } else {
                escapedText.append(c);
            }
        }
        return escapedText.toString();
    }

}
