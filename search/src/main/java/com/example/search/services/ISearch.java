package com.example.search.services;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface ISearch {

    public String mergeResults(CompletableFuture<String>... results);

}
