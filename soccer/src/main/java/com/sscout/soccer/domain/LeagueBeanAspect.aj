/**
 * 
 */
package com.sscout.soccer.domain;

import java.util.Calendar;
import java.util.Set;

/**
 * @author joao
 *
 */
privileged aspect LeagueBeanAspect {

	public String League.getName() {
		return name;
	}

	public void League.setName(String name) {
		this.name = name;
	}

	public Calendar League.getYear() {
		return year;
	}

	public void League.setYear(Calendar year) {
		this.year = year;
	}

	public Set<Team> League.getLeagueTeams() {
		return leagueTeams;
	}

	public void League.setLeagueTeams(Set<Team> leagueTeams) {
		this.leagueTeams = leagueTeams;
	}
}
