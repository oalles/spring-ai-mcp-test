package es.omarall.timezone;

import es.omarall.timezone.adapters.mcp.TimeZoneTools;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import us.dustinj.timezonemap.TimeZoneMap;

@SpringBootApplication
public class TimezoneApplication {

    public static void main(String[] args) {
        SpringApplication.run(TimezoneApplication.class, args);
    }

    @Bean
    TimeZoneMap timeZoneMap() {
        return TimeZoneMap.forEverywhere();
    }

    @Bean
    public ToolCallbackProvider tools(TimeZoneTools timeZoneTools) {
        return MethodToolCallbackProvider.builder()
                .toolObjects(timeZoneTools)
                .build();
    }

}
