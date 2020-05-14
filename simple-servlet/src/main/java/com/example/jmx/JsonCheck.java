package com.example.jmx;

public class JsonCheck implements JsonCheckMBean {

    private static final String[] PROVIDERS = new String[] {"javax.json.spi.JsonProvider", "jakarta.json.spi.JsonProvider"};
    
    @Override
    public String jsonProvider() {
        String result = null;
        for (String provider : PROVIDERS) {
            try {
                Class.forName(provider);
                result = provider;
                break;
            } catch (ClassNotFoundException e) {
                result = e.getMessage();
            }
        }
        return result;
    }

}
