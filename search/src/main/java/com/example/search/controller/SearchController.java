package com.example.search.controller;

import com.example.search.pojos.SearchResponse;
import com.example.search.services.ISearch;
import com.example.search.services.SearchService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
public class SearchController {
    private final SearchService searchService;
    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }
    @GetMapping("/weather/search/{id}")
    @HystrixCommand(fallbackMethod = "emptySearch")
    public ResponseEntity<?> getDetails(@PathVariable Long id) {
        CompletableFuture<String> employee = searchService.callEmpDeptService(id);
        CompletableFuture<String> details = searchService.callDetailsService();
        String res = searchService.mergeResults(employee, details);
        SearchResponse response = new SearchResponse();
        response.setCode(HttpStatus.OK.name());
        response.setTimestamp(System.currentTimeMillis());
        response.setData(res);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<?> emptySearch(Long id) {
        // Fallback implementation here
        return new ResponseEntity<>("No Content", HttpStatus.NO_CONTENT);
    }
}
