package com.github.sumitdas28.voterregistrationservice.exception;

public class VoterNotFoundException extends RuntimeException{
    public VoterNotFoundException(Long id) {
        super("No Voter found with Id: "+id);
    }
}
