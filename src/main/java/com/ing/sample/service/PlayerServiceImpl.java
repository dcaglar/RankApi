package com.ing.sample.service;

import com.ing.sample.model.Player;
import com.ing.sample.repository.PlayerRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author dogan.caglar
 */
@Service("playerService")
public class PlayerServiceImpl implements PlayerService {

	@Autowired
	PlayerRepository playerRepository;

	public List<Player> getRankingList() {
		return playerRepository.getListByScore();
	}

	public Player getPlayerByName(String name) {
		return playerRepository.findByName(name).get(0);
	}
	public List<Player> getOtherPlayers(String name){
		return playerRepository.findAllPlayersExcluding(name);
	}

}
