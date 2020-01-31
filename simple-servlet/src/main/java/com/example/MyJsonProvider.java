package com.example;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.util.Map;

import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonBuilderFactory;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonReader;
import jakarta.json.JsonReaderFactory;
import jakarta.json.JsonWriter;
import jakarta.json.JsonWriterFactory;
import jakarta.json.spi.JsonProvider;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonGeneratorFactory;
import jakarta.json.stream.JsonParser;
import jakarta.json.stream.JsonParserFactory;

public class MyJsonProvider extends JsonProvider{

    @Override
    public JsonParser createParser(Reader reader) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public JsonParser createParser(InputStream in) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public JsonParserFactory createParserFactory(Map<String, ?> config) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public JsonGenerator createGenerator(Writer writer) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public JsonGenerator createGenerator(OutputStream out) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public JsonGeneratorFactory createGeneratorFactory(Map<String, ?> config) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public JsonReader createReader(Reader reader) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public JsonReader createReader(InputStream in) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public JsonWriter createWriter(Writer writer) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public JsonWriter createWriter(OutputStream out) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public JsonWriterFactory createWriterFactory(Map<String, ?> config) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public JsonReaderFactory createReaderFactory(Map<String, ?> config) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public JsonObjectBuilder createObjectBuilder() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public JsonArrayBuilder createArrayBuilder() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public JsonBuilderFactory createBuilderFactory(Map<String, ?> config) {
        // TODO Auto-generated method stub
        return null;
    }

}
