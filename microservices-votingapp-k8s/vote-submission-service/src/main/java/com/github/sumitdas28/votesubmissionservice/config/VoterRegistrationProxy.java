package com.github.sumitdas28.votesubmissionservice.config;

import com.github.sumitdas28.votesubmissionservice.entity.Voter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name = "voter-registration", url = "${VOTER_REGISTRATION_SERVICE_HOST:http://localhost}:8000")
@FeignClient(name = "voter-registration", url = "${VOTER_REGISTRATION_URI}:8000")
public interface VoterRegistrationProxy {

    @GetMapping("/api/v1/voter-registration/getVoterById/{id}")
    public ResponseEntity<Voter> getVoterById(@PathVariable Long id);

}
