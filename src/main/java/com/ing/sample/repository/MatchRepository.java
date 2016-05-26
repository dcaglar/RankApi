package com.ing.sample.repository;

import com.ing.sample.model.Match;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author dogan.caglar
 */
public interface MatchRepository extends JpaRepository<Match,Integer> {

	@SuppressWarnings("JpaQlInspection")
	@Query("select m from Match m where m.winner= :name or m.loser= :name")
	public List<Match> getPlayerMatches(@Param("name") String name);

}
