package es.tododev.yassonbug;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

public class Main {
    public static void main(String[] args) {
        Jsonb json = JsonbBuilder.create();
        String data = "{\"prop\": \"hey\"}";
        Data deserialized = json.fromJson(data, Data.class);
        System.out.println(deserialized);
    }
}
