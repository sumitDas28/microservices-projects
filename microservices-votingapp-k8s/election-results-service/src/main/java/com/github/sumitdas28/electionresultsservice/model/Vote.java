package com.github.sumitdas28.electionresultsservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@ToString
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
