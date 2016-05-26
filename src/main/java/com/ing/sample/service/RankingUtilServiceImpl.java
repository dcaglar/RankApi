package com.ing.sample.service;

import com.ing.sample.model.Match;
import com.ing.sample.model.Player;
import com.ing.sample.model.PlayerResults;
import com.ing.sample.repository.MatchRepository;
import com.ing.sample.repository.PlayerRepository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author dogan.caglar
 */
@Service("rankingUtilService")
public class RankingUtilServiceImpl implements RankingUtilService {

	private static double INITIAL_SCORE = 1000;
	private static String SPACE_REG_EXP = "\\s+";
	private static int K = 30;
	DecimalFormat df = new DecimalFormat("#.##");

	@Autowired
	PlayerRepository playerRepository;

	@Autowired
	MatchRepository matchRepository;

	@Value("${player.file.path}")
	private String playerFilePath;

	@Value("${match.file.path}")
	private String matchFilePath;

	@Transactional
	public void initDatabase() throws IOException {
		Hashtable<Integer, Player> playerMap = new Hashtable<>();
		loadPlayerToHashTable(playerMap);
		runElo(playerMap);
	}

	private void loadPlayerToHashTable(Hashtable<Integer, Player> playerMap) throws IOException {
		FileReader fileReader = new FileReader(playerFilePath);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String line = bufferedReader.readLine();
		while (line != null) {
			String player[] = line.split(SPACE_REG_EXP);
			Player playerInstance = new Player(Integer.valueOf(player[0]), player[1], 0, 0, INITIAL_SCORE);
			playerMap.put(playerInstance.getId(), playerInstance);
			line = bufferedReader.readLine();
		}

		bufferedReader.close();
		fileReader.close();
	}

	private void runElo(Hashtable<Integer, Player> playerMap) throws IOException {
		FileReader fileReader = new FileReader(matchFilePath);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String line = bufferedReader.readLine();
		while (line != null) {
			String match[] = line.split(SPACE_REG_EXP);
			//first one is always winner
			Player winner = playerMap.get(Integer.valueOf(match[0]));
			Player loser = playerMap.get(Integer.valueOf(match[1]));
			applyMatchResult(winner, loser);
			line = bufferedReader.readLine();
		}

		for (Player player : playerMap.values()) {
			playerRepository.save(player);
		}
	}

	private void applyMatchResult(Player winner, Player loser) {
		double expectedScoreWinner = winner.getScore() / (winner.getScore() + loser.getScore());
		double expectedScoreLoser = loser.getScore() / (winner.getScore() + loser.getScore());
		winner.setWins(winner.getWins() + 1);
		loser.setLoss(loser.getLoss() + 1);
		winner.setScore(Double.valueOf(df.format(winner.getScore() + (K * (1 - expectedScoreWinner)))));//actual score for winner is always 1
		loser.setScore(Double.valueOf(df.format(loser.getScore() + (K * (0 - expectedScoreLoser)))));        //actual score for loser is always 0
		Match matchInstance = new Match();
		matchInstance.setWinner(winner.getName());
		matchInstance.setLoser(loser.getName());
		matchRepository.save(matchInstance);
	}

	public List<PlayerResults> getPlayerResults(final String name) {
		final List<Match> matchList = matchRepository.getPlayerMatches(name);
		List<PlayerResults> playerResults = new ArrayList<>();
		for (Match m : matchList) {
			PlayerResults currentMatchResult = new PlayerResults();
			if (m.getWinner().equals(name)) {
				currentMatchResult.setOpponent(m.getLoser());
				currentMatchResult.setWin(true);
			} else {
				currentMatchResult.setOpponent(m.getWinner());
				currentMatchResult.setWin(false);
			}
			playerResults.add(currentMatchResult);
		}

		return playerResults;
	}

	public List<String> getSuggestedMatches(String name) {
		ArrayList<String> suggestedMatchArrayList = new ArrayList<>();
		List<Player> otherPlayers = playerRepository.findAllPlayersExcluding(name);
		List<Match> existingMatches = matchRepository.getPlayerMatches(name);
		Hashtable<String, Integer> matchTable = new Hashtable<>();
		for (int i = 0; i < otherPlayers.size(); i++) {
			matchTable.put(otherPlayers.get(i).getName(), 0);
		}

		for (int i = 0; i < existingMatches.size(); i++) {
			Match match = existingMatches.get(i);
			String opponent;
			if (match.getWinner().equals(name)) {
				opponent = match.getLoser();
			} else {
				opponent = match.getWinner();
			}
			int matchCount = matchTable.get(opponent);
			matchTable.put(opponent, matchCount + 1);

		}

		for (int i = 0; i < otherPlayers.size(); i++) {
			if (matchTable.get(otherPlayers.get(i).getName()) == 0) {
				suggestedMatchArrayList.add(otherPlayers.get(i).getName());
			}
		}
		return suggestedMatchArrayList;
	}

}
