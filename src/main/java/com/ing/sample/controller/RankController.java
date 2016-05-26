package com.ing.sample.controller;

import com.ing.sample.app.RankingAppService;
import com.ing.sample.model.Match;
import com.ing.sample.model.Player;
import com.ing.sample.model.PlayerResults;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dogan.caglar
 */

@RestController
@RequestMapping("/rest/ranking")
public class RankController {

	private static final String APPLICATION_JSON = "application/json";
	private static final String APPLICATION_MSEXCEL = "application/vnd.ms-excel";

	@Autowired
	RankingAppService rankingAppService;

	@RequestMapping(value = "", produces = "application/json", method = RequestMethod.GET)
	public List<Player> getRanking(){
		return rankingAppService.getRankingList();
	}

	@RequestMapping(value = "/{name}", produces = APPLICATION_JSON, method = RequestMethod.GET)
	public Player getPlayer(@PathVariable final String name){
		return rankingAppService.getPlayerByName(name);
	}

	@RequestMapping(value = "/{name}/results", produces = APPLICATION_JSON, method = RequestMethod.GET)
	public List<PlayerResults> getPlayerResults(@PathVariable final String name){
		return rankingAppService.getPlayerResults(name);
	}

	@RequestMapping(value = "/{name}/suggestedmatches", produces = APPLICATION_JSON, method = RequestMethod.GET)
	public List<String> getSuggestedMatches(@PathVariable final String name){
		return rankingAppService.getSuggestedMatches(name);
	}
}
