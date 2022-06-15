package com.github.sumitdas28.votesubmissionservice.exception;

public class VoterNotFoundException extends RuntimeException{
    public VoterNotFoundException(Long id) {
        super("No Voter found with Id: "+id);
    }
}
