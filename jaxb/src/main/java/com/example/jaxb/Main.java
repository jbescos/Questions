package com.example.jaxb;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.SchemaOutputResolver;
import org.eclipse.persistence.jaxb.JAXBContextFactory;

public class Main {

    public static void main(String[] args) throws JAXBException, IOException {
        JAXBContext context = JAXBContextFactory.createContext(new Class<?>[] {Foo.class}, new HashMap<>());
        context.generateSchema(new SchemaOutputResolver() {
            @Override
            public Result createOutput(String namespaceUri, String suggestedFileName) throws IOException {
                File file = new File(suggestedFileName);
                StreamResult result = new StreamResult(file);
                result.setSystemId(file.toURI().toURL().toString());
                return result;
            }
        });
    }

}
