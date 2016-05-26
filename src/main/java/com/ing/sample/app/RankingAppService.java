package com.ing.sample.app;

import com.ing.sample.model.Match;
import com.ing.sample.model.Player;
import com.ing.sample.model.PlayerResults;
import com.ing.sample.service.MatchService;
import com.ing.sample.service.PlayerService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author dogan.caglar
 */
public interface RankingAppService{

	List<Player> getRankingList();
	Player getPlayerByName(String name);
	List<PlayerResults> getPlayerResults(String name);
	List<String> getSuggestedMatches(String name);

	}
