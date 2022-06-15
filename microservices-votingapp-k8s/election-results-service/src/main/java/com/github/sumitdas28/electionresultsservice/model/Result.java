package com.github.sumitdas28.electionresultsservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@IdClass(ResultId.class)
public class Result{
    @Id
    private String leader;
    @Id
    private String party;

    private Long position;
    private Long evmVotes;
    private Long postalVotes;
    private Long totalVotes;
    private Double votePercentage;
}
