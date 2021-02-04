package es.tododev.yassonbug;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

public class Main {

    public static void main(String[] args) {
        Jsonb json = JsonbBuilder.create();
        printModules(json.getClass().getModule(), Data.class.getModule());
        String data = "{\"prop\": \"hey\"}";
        Data deserialized = json.fromJson(data, Data.class);
        System.out.println(deserialized);
    }

    private static void printModules(Module a, Module b) {
        StringBuilder builder = new StringBuilder();
        builder.append(a.getName()).append(" has access to ").append(b.getName()).append("?: ").append(a.canRead(b));
        builder.append("\nPackages in ").append(a.getName()).append(": ").append(a.getPackages());
        builder.append("\nPackages in ").append(b.getName()).append(": ").append(b.getPackages());
        System.out.println(builder.toString());
    }
}
