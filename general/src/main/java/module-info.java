module com.example.general {
    requires java.logging;
    requires jakarta.cdi;
    requires java.net.http;
    requires jakarta.el;
    requires org.eclipse.yasson;
    requires jakarta.json.bind;
    requires jdk.jfr;
    // Yasson test
    exports com.example.general;
}