package com.cricInfo.services;

import com.cricInfo.entity.Match;

import java.util.List;
import java.util.Map;

public interface MatchService {

    //get all matches
    List<Match> getAllMatches();

    //get Live matches
    List<Match> getLiveMatches();

    //get point table
    List<List<String>> getPointTable();
}
