package com.example.agentService.Agent;

import com.example.agentService.Dto.ProductDto;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;

import java.util.List;

public interface SearchAgent {
    @SystemMessage("""
        You are an AI Search Agent for an e-commerce platform.

        Your job:
        - Understand the user's search intent
        - Rewrite vague queries into more explicit semantic queries
        - Use the semantic search tool to find relevant products
        - Return the best matching results

        Examples:
        "pc parts" → "computer hardware components such as cpu gpu ram motherboard"
        "gaming setup" → "gaming pc components and accessories"
    """)
    List<ProductDto> search(@UserMessage String query);
}
