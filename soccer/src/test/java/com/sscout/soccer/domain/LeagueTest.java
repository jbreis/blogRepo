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
public class LeagueTest extends BaseTest {

	private static final String LEAGUE_NAME = "ZON SAGRES";
	private static final Calendar calendar = Calendar.getInstance();

	@Test
	public final void assertLeagueNameIsNull() {

		League league = new League(null, calendar, getValidTeamSet());

		Set<ConstraintViolation<League>> constraintViolations = validator
				.validate(league);

		assertEquals(1, constraintViolations.size());
		assertEquals("may not be empty", constraintViolations.iterator().next()
				.getMessage());
	}

	@Test
	public final void assertLeagueNameIsEmpty() {

		League league = new League("", calendar, getValidTeamSet());

		Set<ConstraintViolation<League>> constraintViolations = validator
				.validate(league);

		assertEquals(1, constraintViolations.size());
		assertEquals("may not be empty", constraintViolations.iterator().next()
				.getMessage());
	}

	@Test
	public final void assertLeagueNameIsDefined() {

		League league = new League(LEAGUE_NAME, calendar, getValidTeamSet());

		Set<ConstraintViolation<League>> constraintViolations = validator
				.validate(league);

		assertEquals(0, constraintViolations.size());
	}

	@Test
	public final void assertLeagueCalendarIsNull() {

		League league = new League(LEAGUE_NAME, null, getValidTeamSet());

		Set<ConstraintViolation<League>> constraintViolations = validator
				.validate(league);

		assertEquals(1, constraintViolations.size());
		assertEquals("may not be null", constraintViolations.iterator().next()
				.getMessage());
	}

	@Test
	public final void assertLeagueCalendarIsNotNull() {

		assertLeagueNameIsDefined();
	}

	@Test
	public final void assertLeagueTeamsIsNull() {
		League league = new League(LEAGUE_NAME, calendar, null);

		Set<ConstraintViolation<League>> constraintViolations = validator
				.validate(league);

		assertEquals(1, constraintViolations.size());
		assertEquals("may not be null", constraintViolations.iterator().next()
				.getMessage());
	}

	@Test
	public final void assertLeagueTeamsSizeIsLowerThanTwo() {

		Set<Team> notValidSet = new HashSet<Team>();
		Team fcportoTeam = new Team();
		notValidSet.add(fcportoTeam);

		League league = new League(LEAGUE_NAME, calendar, notValidSet);

		Set<ConstraintViolation<League>> constraintViolations = validator
				.validate(league);

		assertEquals(1, constraintViolations.size());
		assertEquals("size must be between 2 and 40", constraintViolations
				.iterator().next().getMessage());
	}

	@Test
	public final void assertLeagueTeamsSizeIsBiggerThanTwo() {
		League league = new League(LEAGUE_NAME, calendar, getValidTeamSet());

		Set<ConstraintViolation<League>> constraintViolations = validator
				.validate(league);

		assertEquals(0, constraintViolations.size());
	}

}
