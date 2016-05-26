package com.ing.sample.model;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.Digits;

/**
 * @author dogan.caglar
 */
@Entity
@Table(name = "player")
public class Player {

	@Id
	@Column(name="ID", nullable = false)
	private int id;

	@Column(name = "NAME", nullable = false)
	private String name;

	@Column(name="WIN", nullable = false)
	private int win;

	@Column(name="LOSS", nullable = false)
	private int loss;

	@Digits(integer=10, fraction=2)
	@Column(name = "SCORE", nullable = false)
	private double score;

	public Player(){
	}

	public Player(int id, String name, int win, int loss, double score){
		this.id=id;
		this.name=name;
		this.win=win;
		this.loss=loss;
		this.score=score;

	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWins() {
		return win;
	}

	public void setWins(int wins) {
		this.win = wins;
	}

	public int getLoss() {
		return loss;
	}

	public void setLoss(int loss) {
		this.loss = loss;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}
}
