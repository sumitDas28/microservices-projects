package com.github.sumitdas28.electionresultsservice.model;

public enum VoteType {
    EVM("evm"), POSTAL("postal");
    private final String value;

    VoteType(String value) {
        this.value = value;
    }
}
