package com.github.sumitdas28.votesubmissionservice.repository;

import com.github.sumitdas28.votesubmissionservice.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {
}
