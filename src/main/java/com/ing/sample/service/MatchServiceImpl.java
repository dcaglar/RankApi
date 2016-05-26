package com.ing.sample.service;

import com.ing.sample.model.Match;
import com.ing.sample.repository.MatchRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author dogan.caglar
 */
@Service("matchService")
public class MatchServiceImpl implements MatchService {

	@Autowired
	MatchRepository matchRepository;

	public List<Match> getPlayerMatches(String name){

		return matchRepository.getPlayerMatches(name);

	}

}
