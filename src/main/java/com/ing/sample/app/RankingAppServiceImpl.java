package com.ing.sample.app;

import com.ing.sample.model.Match;
import com.ing.sample.model.Player;
import com.ing.sample.model.PlayerResults;
import com.ing.sample.service.MatchService;
import com.ing.sample.service.PlayerService;
import com.ing.sample.service.RankingUtilService;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author dogan.caglar
 */
@Transactional
@Service("rankingAppService")
public class RankingAppServiceImpl implements RankingAppService {

	@Autowired
	PlayerService playerService;

	@Autowired
	MatchService matchService;

	@Autowired
	RankingUtilService rankingUtilService;

	public List<Player> getRankingList() {
		return playerService.getRankingList();
	}

	public Player getPlayerByName(final String name) {
		return playerService.getPlayerByName(name);
	}

	public List<PlayerResults> getPlayerResults(final String name) {
		return rankingUtilService.getPlayerResults(name);
	}

	public List<String> getSuggestedMatches(String name) {
		return rankingUtilService.getSuggestedMatches(name);
	}
}

