package io.helidon.example.cloud.google;

import com.google.cloud.functions.HttpFunction;
import com.google.cloud.functions.HttpRequest;
import com.google.cloud.functions.HttpResponse;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import io.helidon.microprofile.cloud.common.CloudFunction;

@CloudFunction
@ApplicationScoped
// Entry point: io.helidon.microprofile.cloud.googlecloudfunctions.http.GoogleCloudHttpFunction
public class GoogleFunctionExample implements HttpFunction {

    @Inject
    private MyService myService;

    @Override
    public void service(HttpRequest request, HttpResponse response) throws Exception {
        String value = request.getFirstQueryParameter("param").orElse("no param provided");
        response.getWriter().write(myService.toUpperCase(value));
    }
    
    @ApplicationScoped
    public static class MyService {

        public String toUpperCase(String str) {
            return str.toUpperCase();
        }

    }
    
}
