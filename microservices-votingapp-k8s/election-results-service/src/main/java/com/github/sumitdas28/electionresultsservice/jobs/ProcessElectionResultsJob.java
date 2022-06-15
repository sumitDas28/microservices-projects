package com.github.sumitdas28.electionresultsservice.jobs;

import com.github.sumitdas28.electionresultsservice.config.VoteSubmissionProxy;
import com.github.sumitdas28.electionresultsservice.controller.ElectionResultController;
import com.github.sumitdas28.electionresultsservice.model.*;
import com.github.sumitdas28.electionresultsservice.repository.ResultRepository;
import com.github.sumitdas28.electionresultsservice.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@Service
public class ProcessElectionResultsJob {

    private Logger logger = LoggerFactory.getLogger(ProcessElectionResultsJob.class);

    private final ResultRepository resultRepository;
    private final VoteSubmissionProxy voteSubmissionProxy;

    public ProcessElectionResultsJob(ResultRepository resultRepository, VoteSubmissionProxy voteSubmissionProxy) {
        this.resultRepository = resultRepository;
        this.voteSubmissionProxy = voteSubmissionProxy;
    }

    @Scheduled(fixedDelay = 30000)
    public void processElectionResults(){
        logger.info("Calling processElectionResults...");
        Result result = null;
        Map<Leader, List<Vote>> submittedVotesGroupedByLeader = new HashMap<>();
            var submittedVotesResponse = voteSubmissionProxy.fetchSubmittedVotes();
            var submittedVotes = submittedVotesResponse.getBody();

            if (submittedVotesResponse.getStatusCode()== HttpStatus.OK && !submittedVotes.isEmpty()){
                submittedVotesGroupedByLeader = Utils.groupBy(submittedVotes, Vote::getLeader);
                long totalSubmittedvotes = submittedVotes.size();
                for(var entry : submittedVotesGroupedByLeader.entrySet()) {
                    result = new Result();
                    Leader leader = entry.getKey();
                    List<Vote> votes = entry.getValue();
                    result.setLeader(leader.getLeaderName());
                    result.setParty(votes.get(0).getParty().getPartyName());

                    long evmVotes = Utils.getCountByVoteType(votes, VoteType.EVM);
                    long postalVotes = Utils.getCountByVoteType(votes, VoteType.POSTAL);
                    long totalVotes = evmVotes + postalVotes;
                    double percentage = calculatePercentage(totalVotes, totalSubmittedvotes);
                    result.setEvmVotes(evmVotes);
                    result.setPostalVotes(postalVotes);
                    result.setTotalVotes(totalVotes);
                    result.setVotePercentage(new BigDecimal(percentage).setScale(2, RoundingMode.HALF_UP).doubleValue());
                    resultRepository.save(result);
                }

                var results = resultRepository.findAll();
                Comparator<Result> compareByVotes = Comparator.comparing(Result::getTotalVotes);
                Collections.sort(results, compareByVotes.reversed());
                long position=0;
                Set<Long> totalVotesSet = new HashSet<>();
                for(Result result1 : results){
                    if(totalVotesSet.contains(result1.getTotalVotes())){
                            result1.setPosition(position);
                        } else{
                            result1.setPosition(++position);
                        }
                    totalVotesSet.add(result1.getTotalVotes());
                    resultRepository.save(result1);
                }
            }
    }

    public double calculatePercentage(double obtained, double total) {
        return obtained * 100 / total;
    }
}
