package com.ing.sample.repository;

import com.ing.sample.model.Player;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author dogan.caglar
 */
public interface PlayerRepository extends JpaRepository<Player,Integer> {

	List<Player> findByName(String name);

	@SuppressWarnings("JpaQlInspection")
	@Query("select p from Player p where p.name<> :name")
	public List<Player> findAllPlayersExcluding(@Param("name") String name);

	@SuppressWarnings("JpaQlInspection")
	@Query("select p from Player p order by p.score desc")
	public List<Player> getListByScore();

	//Player save(Player player);



}
