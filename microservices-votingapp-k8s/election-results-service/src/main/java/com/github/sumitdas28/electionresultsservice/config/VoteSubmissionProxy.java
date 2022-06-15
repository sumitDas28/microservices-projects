package com.github.sumitdas28.electionresultsservice.config;

import com.github.sumitdas28.electionresultsservice.model.Vote;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

//@FeignClient(name = "vote-submission", url = "${VOTER_SUBMISSION_URI:http://localhost}:8100")
@FeignClient(name = "vote-submission", url = "${VOTER_SUBMISSION_URI}:8100")
public interface VoteSubmissionProxy {

    @GetMapping("/api/v1/vote-submission/fetchSubmittedVotes")
    public ResponseEntity<List<Vote>> fetchSubmittedVotes();
}
