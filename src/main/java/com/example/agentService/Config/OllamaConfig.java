package com.example.agentService.Config;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OllamaConfig {
    @Value("${ollama.base.url}")
    private String baseUrl;

    @Value("${ollama.model}")
    private String model;

    @Bean
    ChatLanguageModel chatLanguageModel(){
        return OllamaChatModel.builder()
                .baseUrl(baseUrl)
                .modelName(model)
                .temperature(0.2)
                .build();
    }
}
