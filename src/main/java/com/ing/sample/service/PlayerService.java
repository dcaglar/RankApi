package com.ing.sample.service;

import com.ing.sample.model.Player;

import java.util.List;

/**
 * @author dogan.caglar
 */
public interface PlayerService {
	List<Player> getRankingList();
	Player getPlayerByName(String name);
	public List<Player> getOtherPlayers(String name);
	}
