package com.sscout.soccer.domain;

import java.util.Calendar;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Domain entity class responsible for having a league data and domain logic.
 * 
 * @author joao
 * 
 */
public class League {

	/**
	 * League name for a given year.
	 */
	@NotEmpty
	private String name = null;

	/**
	 * League calendar. Defines the league year entity
	 */
	@NotNull
	private Calendar year = null;

	/**
	 * Teams which belong to this league. The minimum number is 2 teams.
	 */
	@NotNull
	@Size(min = 2, max = 40)
	@Valid
	private Set<Team> leagueTeams = null;

	public League(String name, Calendar year, Set<Team> leagueTeams) {
		super();
		this.name = name;
		this.year = year;
		this.leagueTeams = leagueTeams;
	}

}
