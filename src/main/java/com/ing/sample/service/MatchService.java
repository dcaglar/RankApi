package com.ing.sample.service;

import com.ing.sample.model.Match;

import java.util.List;

/**
 * @author dogan.caglar
 */
public interface MatchService {
   List<Match> getPlayerMatches(String name);
}
