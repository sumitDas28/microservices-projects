package com.github.sumitdas28.electionresultsservice.controller;

import com.github.sumitdas28.electionresultsservice.model.Result;
import com.github.sumitdas28.electionresultsservice.model.ResultStatusResponse;
import com.github.sumitdas28.electionresultsservice.repository.ResultRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;

@RestController
@RequestMapping("/api/v1/election-results")
public class ElectionResultController {
    private Logger logger = LoggerFactory.getLogger(ElectionResultController.class);

    private final ResultRepository resultRepository;

    public ElectionResultController(ResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }

    @GetMapping("/getResultStatus")
    public ResponseEntity<ResultStatusResponse> getResultStatus(){
        try{
            ResultStatusResponse resultStatusResponse = new ResultStatusResponse();
            var results = resultRepository.findAll();
            Comparator<Result> compareByVotes = Comparator.comparing(Result::getTotalVotes);
            Collections.sort(results, compareByVotes.reversed());
            resultStatusResponse.setResults(results);

            LocalDateTime dateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            resultStatusResponse.setLastUpdated(dateTime.format(formatter));
            return new ResponseEntity<>(resultStatusResponse, HttpStatus.OK);
        } catch(Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Oops! something went wrong...",e);
        }
    }
}
