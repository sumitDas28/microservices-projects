package com.github.sumitdas28.votesubmissionservice.entity;

public enum Party {

    INC("Indian National Congress"), BJP("Bharatiya Janata Party"),
    AAP("Aam Aadmi Party"), NOTA("None of the above");
    private String partyName;

    Party(String partyName) {
        this.partyName = partyName;
    }

    public String getPartyName() {
        return partyName;
    }
}
