module com.example.general {
    requires java.logging;
    requires jakarta.cdi;
    requires java.net.http;
    requires jakarta.el;
    opens com.example.general to jakarta.cdi;
}