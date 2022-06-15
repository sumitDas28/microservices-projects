package com.github.sumitdas28.votesubmissionservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Voter {

    @Id
    private Long voterId;
    private String name;
    private String dob;
    private String gender;
    private String address;
    private String status;

    @Size(min = 8, max = 12, message = "Phone number should be between 8-12 characters")
    private String phoneNumber;
}
