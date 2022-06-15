package com.github.sumitdas28.electionresultsservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResultStatusResponse {

    private List<Result> results;
    private String lastUpdated;
}
