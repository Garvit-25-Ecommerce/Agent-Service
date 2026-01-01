package com.example.agentService.Controller;

import com.example.agentService.Agent.SearchAgent;
import com.example.agentService.Dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ai/search")
public class SearchController {

    private final SearchAgent searchAgent;

    @GetMapping
    ResponseEntity<List<ProductDto>> search(@RequestParam String query) {
        List<ProductDto> relatedProducts = searchAgent.search(query);

        return new ResponseEntity<>(relatedProducts, HttpStatus.OK);
    }
}
