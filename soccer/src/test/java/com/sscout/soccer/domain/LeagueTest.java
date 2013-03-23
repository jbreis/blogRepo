package com.sscout.soccer.domain;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.junit.Test;

/**
 * Unit tests for domain rules of entity League.
 * 
 * @author joao
 * 
 */
public class LeagueTest extends BaseTest<League> {

	private static final String LEAGUE_NAME = "ZON SAGRES";
	private static final Calendar calendar = Calendar.getInstance();

	@Test
	public final void assertLeagueNameIsNull() {

		League league = new League(null, calendar, getValidTeamSet());

		assertEmptyAttributes(league);
	}

	@Test
	public final void assertLeagueNameIsEmpty() {

		League league = new League("", calendar, getValidTeamSet());

		assertEmptyAttributes(league);
	}

	@Test
	public final void assertLeagueNameIsDefined() {

		League league = new League(LEAGUE_NAME, calendar, getValidTeamSet());

		assertEmptyConstraints(league);
	}

	@Test
	public final void assertLeagueCalendarIsNull() {

		League league = new League(LEAGUE_NAME, null, getValidTeamSet());

		assertNullAttribs(league);
	}

	@Test
	public final void assertLeagueCalendarIsNotNull() {

		assertLeagueNameIsDefined();
	}

	@Test
	public final void assertLeagueTeamsIsNull() {
		League league = new League(LEAGUE_NAME, calendar, null);

		assertNullAttribs(league);
	}

	@Test
	public final void assertLeagueTeamsSizeIsLowerThanTwo() {

		Set<Team> notValidSet = new HashSet<Team>();
		Team fcportoTeam = new Team();
		notValidSet.add(fcportoTeam);

		League league = new League(LEAGUE_NAME, calendar, notValidSet);

		Set<ConstraintViolation<League>> constraintValidations = getConstraintViolations(league);

		assertEquals(1, constraintValidations.size());
		assertEquals("size must be between 2 and 40", constraintValidations
				.iterator().next().getMessage());
	}

	@Test
	public final void assertLeagueTeamsSizeIsBiggerThanTwo() {
		League league = new League(LEAGUE_NAME, calendar, getValidTeamSet());

		assertEmptyConstraints(league);
	}

}
