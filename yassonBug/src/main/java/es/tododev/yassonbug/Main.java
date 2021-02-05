package es.tododev.yassonbug;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

public class Main {
    
    private static final Jsonb json = JsonbBuilder.create();

    public static void main(String[] args) {
        
        printModules(json.getClass(), Data.class);
        deserializeData("{\"prop\": \"hey\"}", Data.class);
        deserializeData("{\"name\":\"John\",\"password\":\"test\",\"email\":\"John@gmail.com\"}", Account.class);
    }
    
    private static <T> void deserializeData(String data, Class<T> type) {
        T deserialized = json.fromJson(data, type);
        System.out.println(deserialized);
        String serialized = json.toJson(deserialized);
        System.out.println(serialized);
    }

    private static void printModules(Class<?> ca, Class<?> cb) {
        Module a = ca.getModule();
        Module b = cb.getModule();
        StringBuilder builder = new StringBuilder();
        boolean access = a.canRead(b);
        boolean exported = a.isExported(cb.getPackageName());
        builder.append("\n").append(a.getName()).append(" has access to ").append(b.getName()).append("?: ").append(access);
        builder.append("\n").append(a.getName()).append(" is exported to ").append(b.getName()).append("?: ").append(exported);
        System.out.println(builder.toString());
        descriptor(a);
        descriptor(b);
    }
    
    private static void descriptor(Module module) {
        StringBuilder builder = new StringBuilder();
        builder.append("\nDescriptor: ").append(module.getDescriptor());
        builder.append("\nPackages: ").append(module.getPackages());
        System.out.println(builder.toString());
    }
}
