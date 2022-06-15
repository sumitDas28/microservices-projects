package com.github.sumitdas28.electionresultsservice.utils;

import com.github.sumitdas28.electionresultsservice.model.Vote;
import com.github.sumitdas28.electionresultsservice.model.VoteType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Utils {

    public static <E, K> Map<K, List<E>> groupBy(List<E> list, Function<E, K> keyFunction) {
        return Optional.ofNullable(list)
                .orElseGet(ArrayList::new)
                .stream()
                .collect(Collectors.groupingBy(keyFunction));
    }

    public static long getCountByVoteType (List<Vote> votes, VoteType voteType){
        return votes.stream()
                .filter(x -> x.getVoteType()==voteType)
                .count();
    }
}
