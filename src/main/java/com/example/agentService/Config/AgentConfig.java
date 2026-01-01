package com.example.agentService.Config;

import com.example.agentService.Agent.SmartCartAgent;
import com.example.agentService.Tools.CartTool;
import com.example.agentService.Tools.ProductTool;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.service.AiServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AgentConfig {

    @Bean
    SmartCartAgent smartCartAgent(ChatLanguageModel model, ProductTool productTool, CartTool cartTool){
        return AiServices.builder(SmartCartAgent.class)
                .tools(productTool, cartTool)
                .chatLanguageModel(model)
                .build();
    }
}
