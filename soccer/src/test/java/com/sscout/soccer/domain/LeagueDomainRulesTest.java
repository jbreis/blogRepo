package com.sscout.soccer.domain;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Unit tests for domain rules of entity League.
 * 
 * @author joao
 * 
 */
public class LeagueDomainRulesTest {

	private static Validator validator;
	private static final String LEAGUE_NAME = "ZON SAGRES";
	private static final Calendar calendar = Calendar.getInstance();
	private Set<Team> leagueTeams = null;

	@BeforeClass
	public static void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public final void assertLeagueNameIsNull() {

		League league = new League();
		league.setYear(calendar);
		league.setLeagueTeams(getValidTeamSet());

		Set<ConstraintViolation<League>> constraintViolations = validator
				.validate(league);

		assertEquals(1, constraintViolations.size());
		assertEquals("may not be empty", constraintViolations.iterator().next()
				.getMessage());
	}

	@Test
	public final void assertLeagueNameIsEmpty() {

		League league = new League();
		league.setName("");
		league.setYear(calendar);
		league.setLeagueTeams(getValidTeamSet());

		Set<ConstraintViolation<League>> constraintViolations = validator
				.validate(league);

		assertEquals(1, constraintViolations.size());
		assertEquals("may not be empty", constraintViolations.iterator().next()
				.getMessage());
	}

	@Test
	public final void assertLeagueNameIsDefined() {

		League league = new League();
		league.setName(LEAGUE_NAME);
		league.setYear(calendar);
		league.setLeagueTeams(getValidTeamSet());

		Set<ConstraintViolation<League>> constraintViolations = validator
				.validate(league);

		assertEquals(0, constraintViolations.size());
	}

	@Test
	public final void assertLeagueCalendarIsNull() {

		League league = new League();
		league.setName(LEAGUE_NAME);
		league.setLeagueTeams(getValidTeamSet());

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
		League league = new League();
		league.setName(LEAGUE_NAME);
		league.setYear(calendar);

		Set<ConstraintViolation<League>> constraintViolations = validator
				.validate(league);

		assertEquals(1, constraintViolations.size());
		assertEquals("may not be null", constraintViolations.iterator().next()
				.getMessage());
	}

	@Test
	public final void assertLeagueTeamsSizeIsLowerThanTwo() {
		League league = new League();
		league.setName(LEAGUE_NAME);
		league.setYear(calendar);

		Set<Team> notValidSet = new HashSet<Team>();
		Team fcportoTeam = new Team();
		notValidSet.add(fcportoTeam);

		league.setLeagueTeams(notValidSet);

		Set<ConstraintViolation<League>> constraintViolations = validator
				.validate(league);

		assertEquals(1, constraintViolations.size());
		assertEquals("size must be between 2 and 40", constraintViolations
				.iterator().next().getMessage());
	}

	@Test
	public final void assertLeagueTeamsSizeIsBiggerThanTwo() {
		League league = new League();
		league.setName(LEAGUE_NAME);
		league.setYear(calendar);

		league.setLeagueTeams(getValidTeamSet());

		Set<ConstraintViolation<League>> constraintViolations = validator
				.validate(league);

		assertEquals(0, constraintViolations.size());
	}

	private Set<Team> getValidTeamSet() {

		if (leagueTeams == null) {
			leagueTeams = new HashSet<Team>();

			Team fcportoTeam = new Team();
			Team slbTeam = new Team();
			leagueTeams.add(fcportoTeam);
			leagueTeams.add(slbTeam);
		}
		return leagueTeams;
	}
}
