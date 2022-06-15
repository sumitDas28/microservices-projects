package com.github.sumitdas28.votesubmissionservice.controller;

import com.github.sumitdas28.votesubmissionservice.config.VoterRegistrationProxy;
import com.github.sumitdas28.votesubmissionservice.entity.Vote;
import com.github.sumitdas28.votesubmissionservice.entity.Voter;
import com.github.sumitdas28.votesubmissionservice.exception.VoterNotFoundException;
import com.github.sumitdas28.votesubmissionservice.repository.VoteRepository;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vote-submission")
public class VoteSubmissionController {

    private Logger logger = LoggerFactory.getLogger(VoteSubmissionController.class);

    private final VoteRepository voteRepository;
    private final VoterRegistrationProxy voterRegistrationProxy;

    public VoteSubmissionController(VoteRepository voteRepository, VoterRegistrationProxy voterRegistrationProxy) {
        this.voteRepository = voteRepository;
        this.voterRegistrationProxy = voterRegistrationProxy;
    }

    @PostMapping("/submitVote")
    public ResponseEntity<Vote> submitVote(@RequestBody Vote vote){
            try{
                var id = vote.getVoterId();
                logger.info("submitVote called with voter id {} ", id);
                var voterDetails = voterRegistrationProxy.getVoterById(id);
                if(voterDetails.getBody()!=null){
                    logger.info("Voter Id {} is valid.", id);
                    var submittedVote = voteRepository.save(vote);
                    return new ResponseEntity<>(submittedVote, HttpStatus.CREATED);
                } else{
                    throw new VoterNotFoundException(id);
                }
            } catch (FeignException fe){
                logger.error("FeignException during submitVote operation", fe);
                if(fe.status()==404){
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No voter found with Id:"+vote.getVoterId(),fe);
                } else {
                    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Oops! something went wrong...",fe);
                }
            } catch(Exception e){
                logger.error("Exception during submitVote operation", e);
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Oops! something went wrong...",e);
            }
    }

    @GetMapping("/fetchSubmittedVotes")
    public ResponseEntity<List<Vote>> fetchSubmittedVotes(){
        try{
            var result = voteRepository.findAll();
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch(Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Oops! something went wrong...",e);
        }
    }
}
