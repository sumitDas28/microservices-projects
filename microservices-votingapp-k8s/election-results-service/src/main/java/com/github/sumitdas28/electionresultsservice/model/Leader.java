package com.github.sumitdas28.electionresultsservice.model;

public enum Leader {

    ARVIND_KEJRIWAL("Arvind Kejriwal"), NARENDRA_MODI("Narendra Modi"), RAHUL_GANDHI("Rahul Gandhi"), NOTA("NOTA");
    private final String leaderName;

    Leader(String leaderName) {
        this.leaderName = leaderName;
    }

    public String getLeaderName() {
        return leaderName;
    }

//    @JsonCreator
//    public static Leader getLeaderFromName(String leaderName){
//        for (Leader leader: Leader.values()){
//            if(leader.getLeaderName().equals(leaderName)){
//                return leader;
//            }
//        }
//        return null;
//    }
}
