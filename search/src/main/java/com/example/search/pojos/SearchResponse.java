package com.example.search.pojos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class SearchResponse {
    private String code;
    private long timestamp;
    private String data;
}
