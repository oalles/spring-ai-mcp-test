package es.omarall.mcphost;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
@Slf4j
public class McpHostApplication {

    public static void main(String[] args) {
        SpringApplication.run(McpHostApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(final ChatClient.Builder chatClientBuilder, List<ToolCallback> toolCallbacks) {

        final ChatClient agent = chatClientBuilder.build();

        return args -> {
            try (Scanner scanner = new Scanner(System.in)) {
                while (true) {
                    System.out.print("\n\nEnter city (or type 'exit' to quit): ");
                    String city = scanner.nextLine();
                    if ("exit".equalsIgnoreCase(city)) {
                        break;
                    }

                    String queryTemplate = """
                            Please use the available tools to find the latitude and longitude for the city `{city}`. Once you have this information, 
                            use the tools to determine and provide all the timezone details for that location in the same language.
                            """;

                    String systemTemplate = """
                            You are an AI assistant specialized in providing geographical information. Your task is to use the provided tools to gather and deliver accurate data.
                            """;

                    String llmResponse = agent
                            .prompt()
                            .advisors(new SimpleLoggerAdvisor())
                            .system(systemSpec -> systemSpec.text(systemTemplate))
                            .user(userSpec -> userSpec.text(queryTemplate).param("city", city))
                            .tools(toolCallbacks)
                            .call()
                            .content();

                    log.info("\n\n{}", llmResponse);
                }
            }
        };
    }
}