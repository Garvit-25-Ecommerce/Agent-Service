package com.example.agentService.Tools;

import com.example.agentService.Dto.ProductDto;
import com.example.agentService.service.SearchService;
import dev.langchain4j.agent.tool.Tool;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SearchTool {
    private final SearchService searchService;


    @Tool("Search products semantically")
    public List<ProductDto> semanticSearch(String query) {
        try {
            return searchService.search(query);
        } catch (Exception e) {
            return null;
        }
    }
}
