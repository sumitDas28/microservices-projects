package com.github.sumitdas28.electionresultsservice.model;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class ResultId implements Serializable {
    private String leader;
    private String party;
}
