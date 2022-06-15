package com.github.sumitdas28.voterregistrationservice.entity;

public enum VoterStatus {
    ACTIVE("active"), INACTIVE("inactive");
    private final String value;

    VoterStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
