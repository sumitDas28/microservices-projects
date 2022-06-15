package com.github.sumitdas28.votesubmissionservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Vote {
    @Id
    private Long voterId;
    private Leader leader;
    private Party party;
    private VoteType voteType;
}
