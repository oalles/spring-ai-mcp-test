package es.omarall.geocoder;

import es.omarall.geocoder.adapters.mcp.GeocoderTools;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public ToolCallbackProvider tools(GeocoderTools geocoderTools) {
        return MethodToolCallbackProvider.builder()
                .toolObjects(geocoderTools)
                .build();
    }

}
