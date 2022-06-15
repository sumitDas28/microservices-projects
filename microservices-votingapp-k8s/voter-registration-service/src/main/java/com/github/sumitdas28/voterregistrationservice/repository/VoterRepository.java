package com.github.sumitdas28.voterregistrationservice.repository;

import com.github.sumitdas28.voterregistrationservice.entity.Voter;
import com.github.sumitdas28.voterregistrationservice.entity.VoterStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VoterRepository extends JpaRepository<Voter, Long> {

    public  List<Voter> findByVoterStatus(VoterStatus voterStatus);
}
