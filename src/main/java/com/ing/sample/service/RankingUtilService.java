package com.ing.sample.service;

import com.ing.sample.model.Match;
import com.ing.sample.model.Player;
import com.ing.sample.model.PlayerResults;

import java.io.IOException;
import java.util.List;

/**
 * @author dogan.caglar
 */
public interface RankingUtilService {

	void initDatabase() throws IOException;
	List<PlayerResults> getPlayerResults(final String name);
	List<String> getSuggestedMatches(String name);
	}
