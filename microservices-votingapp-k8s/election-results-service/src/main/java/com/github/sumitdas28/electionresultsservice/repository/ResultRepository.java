package com.github.sumitdas28.electionresultsservice.repository;

import com.github.sumitdas28.electionresultsservice.model.Result;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultRepository extends JpaRepository<Result, Long> {
}
