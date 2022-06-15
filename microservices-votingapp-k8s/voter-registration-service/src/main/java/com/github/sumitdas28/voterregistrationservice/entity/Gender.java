package com.github.sumitdas28.voterregistrationservice.entity;

import java.util.Arrays;

public enum Gender {

    MALE("male"), FEMALE("female"), OTHER("other");

    private final String value;

    Gender(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
