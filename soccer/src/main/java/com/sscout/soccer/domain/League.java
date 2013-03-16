package com.sscout.soccer.domain;

import java.util.Calendar;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class League {

	@NotEmpty
	private String name = null;

	@NotNull
	private Calendar year = null;

	/**
	 * Teams which belong to this league. The minimum number is 2 teams.
	 */
	@NotNull
	@Size(min = 2, max = 40)
	@Valid
	private Set<Team> leagueTeams = null;

}
