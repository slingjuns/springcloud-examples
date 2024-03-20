package com.example.search.services;

import com.example.search.SearchApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class SearchService implements ISearch {

    private final RestTemplate restTemplate;
    @Autowired
    public SearchService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public CompletableFuture<String> callEmpDeptService(Long id) {
        return CompletableFuture.supplyAsync(() -> restTemplate.getForObject("http://management/jpa/employees/" + id, String.class));
    }

    public CompletableFuture<String> callDetailsService() {
        return CompletableFuture.supplyAsync(() -> restTemplate.getForObject("http://details/details/port", String.class));
    }

    public String mergeResults(CompletableFuture<String>... futures) {
        CompletableFuture<Void> allDone = CompletableFuture.allOf(futures);
        return allDone.thenApply(v -> {
            Stream<String> resultsStream = Stream.of(futures)
                    .map(CompletableFuture::join);
            return resultsStream.collect(Collectors.joining(" | "));
        }).join();
    }
}
