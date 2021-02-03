package es.tododev.yassonbug;

import jakarta.json.bind.annotation.JsonbCreator;
import jakarta.json.bind.annotation.JsonbProperty;

public final class Data {

    public final String prop;

    @JsonbCreator
    public Data(@JsonbProperty("prop") String prop) {
        this.prop = prop;
    }

    @Override
    public String toString() {
        return "Data [prop=" + prop + "]";
    }

}