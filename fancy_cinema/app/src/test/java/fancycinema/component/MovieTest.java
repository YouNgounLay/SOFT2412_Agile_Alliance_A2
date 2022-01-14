package fancycinema.component;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MovieTest {

    LocalDateTime timeOne;
    LocalDateTime timeTwo;
    LocalDateTime timeThree;
    LocalDateTime timeFour;
    LocalDateTime timeFive;
    LocalDateTime timeSix;
    LocalDate releaseDate;
    LocalDate releaseDateChanged;
    List<LocalDateTime> upcomingTimesOne = new ArrayList<>();
    List<LocalDateTime> upcomingTimesTwo = new ArrayList<>();
    List<LocalDateTime> upcomingTimesThree = new ArrayList<>();
    List<String> castMembers = new ArrayList<>();
    List<String> castMembersChanged = new ArrayList<>();
    Movie m1;
    Movie m2;
    Movie m3;
    Movie m4;

    @BeforeEach
    void init() {
        releaseDate = LocalDate.of(2021, Month.DECEMBER, 25);
        releaseDateChanged = LocalDate.of(2021, Month.DECEMBER, 30);
        timeOne = LocalDateTime.of(2022, Month.JANUARY, 1, 10, 30);
        timeTwo = LocalDateTime.of(2022, Month.JANUARY, 1, 13,00);
        timeThree = LocalDateTime.of(2022, Month.JANUARY, 2, 17,40);
        timeFour = LocalDateTime.of(2022, Month.JANUARY, 2, 14,20);
        timeFive = LocalDateTime.of(2022, Month.JANUARY, 2, 9, 30);
        timeSix = LocalDateTime.of(2022, Month.JANUARY, 3, 12,00);
        castMembers.clear();
        castMembers.add("Actor 1");
        castMembers.add("Actor 2");
        castMembers.add("Actor 3");
        castMembersChanged.clear();
        castMembersChanged.add("Actor 4");
        castMembersChanged.add("Actor 5");
        castMembersChanged.add("Actor 6");
        upcomingTimesOne.clear();
        upcomingTimesOne.add(timeOne);
        upcomingTimesOne.add(timeTwo);
        upcomingTimesOne.add(timeThree);
        upcomingTimesOne.add(timeFour);
        upcomingTimesTwo.clear();
        upcomingTimesTwo.add(timeFour);
        upcomingTimesTwo.add(timeFive);
        upcomingTimesTwo.add(timeSix);
        upcomingTimesThree.clear();
        upcomingTimesThree.add(timeTwo);
        upcomingTimesThree.add(timeFour);
        upcomingTimesThree.add(timeSix);
        m1 = new Movie("Movie1", "Example1", Movie.Classification.G, releaseDate, "director",castMembers, Movie.Screen.Silver, upcomingTimesOne);
        m2 = new Movie("Movie1", "Example1 but different screen", Movie.Classification.G, releaseDate, "director",castMembers, Movie.Screen.Gold, upcomingTimesOne);
        m3 = new Movie("Movie3", "Example3", Movie.Classification.MA, releaseDate, "director",castMembers, Movie.Screen.Bronze, upcomingTimesTwo);
        m4 = new Movie("Movie4", "Example4", Movie.Classification.R, releaseDate, "director",castMembers, Movie.Screen.Gold, upcomingTimesThree);
    }

    @Test
    void testGetSetMovieName() {
        assertEquals(m1.getMovieName(), "Movie1");
        assertNotEquals(m2.getMovieName(), "Movie2");
        m2.setMovieName("Movie1 but different screen");
        assertEquals(m2.getMovieName(), "Movie1 but different screen");
        assertNotEquals(m2.getMovieName(), "Movie1");
        m3.setMovieName("Movie3 changed");
        assertNotEquals(m3.getMovieName(), "Movie3");
        assertEquals(m3.getMovieName(), "Movie3 changed");
    }

    @Test
    void testGetSetSynopsis() {
        assertEquals(m1.getSynopsis(), "Example1");
        assertNotEquals(m2.getSynopsis(), "Example1");
        m1.setSynopsis("Example1 changed");
        assertEquals(m1.getSynopsis(), "Example1 changed");
        assertNotEquals(m1.getSynopsis(), "Example1");
        m3.setSynopsis("Example3 changed");
        assertEquals(m3.getSynopsis(), "Example3 changed");
        assertNotEquals(m3.getSynopsis(), "Example3");
    }

    @Test
    void testGetSetClassification() {
        assertEquals(m4.getClassification(), Movie.Classification.R);
        assertNotEquals(m3.getClassification(), Movie.Classification.G);
        m4.setClassification(Movie.Classification.PG);
        assertEquals(m4.getClassification(), Movie.Classification.PG);
        assertNotEquals(m4.getClassification(), Movie.Classification.R);
        m3.setClassification(Movie.Classification.G);
        assertEquals(m3.getClassification(), Movie.Classification.G);
    }

    @Test
    void testGetSetReleaseDate() {
        assertEquals(m1.getReleaseDate(), releaseDate);
        assertNotEquals(m2.getReleaseDate(), releaseDateChanged);
        m1.setReleaseDate(releaseDateChanged);
        m2.setReleaseDate(releaseDateChanged);
        assertNotEquals(m1.getReleaseDate(), releaseDate);
        assertEquals(m2.getReleaseDate(), releaseDateChanged);
    }

    @Test
    void testGetSetDirector(){
        assertEquals(m1.getDirector(), "director");
        assertNotEquals(m2.getDirector(), "not director");
        m1.setDirector("not director");
        m2.setDirector("not director");
        assertNotEquals(m1.getDirector(), "director");
        assertEquals(m2.getDirector(), "not director");
    }

    @Test
    void testGetSetCast() {
        assertEquals(m1.getCast(), castMembers);
        assertNotEquals(m2.getCast(), castMembersChanged);
        m1.setCast(castMembersChanged);
        m2.setCast(castMembersChanged);
        assertNotEquals(m1.getCast(), castMembers);
        assertEquals(m2.getCast(), castMembersChanged);
    }

    @Test
    void testGetSetScreen(){
        assertEquals(m1.getScreen(), Movie.Screen.Silver);
        assertNotEquals(m2.getScreen(), Movie.Screen.Silver);
        m1.setScreen(Movie.Screen.Gold);
        m2.setScreen(Movie.Screen.Silver);
        assertNotEquals(m1.getScreen(), Movie.Screen.Silver);
        assertEquals(m2.getScreen(), Movie.Screen.Silver);
    }

    @Test
    void testGetSetUpcomingTimes(){
        assertEquals(m1.getUpcomingTimes(), upcomingTimesOne);
        assertEquals(m2.getUpcomingTimes(), upcomingTimesOne);
        assertNotEquals(m2.getUpcomingTimes(), upcomingTimesTwo);
        m3.setUpcomingTimes(upcomingTimesThree);
        m4.setUpcomingTimes(upcomingTimesTwo);
        assertEquals(m3.getUpcomingTimes(), upcomingTimesThree);
        assertEquals(m4.getUpcomingTimes(), upcomingTimesTwo);
        assertNotEquals(m4.getUpcomingTimes(), upcomingTimesThree);
        assertNotEquals(m3.getUpcomingTimes(), upcomingTimesTwo);
    }
    @Test
    void testAddCast() {
        assertTrue(m1.addCastMember("Actor 7"));
        assertFalse(m1.addCastMember("Actor 1"));
        m2.addCastMember("Actor 7");
        assertTrue(m2.getCast().contains("Actor 7"));
        m3.addCastMember("Harry Potter");
        assertFalse(m3.getCast().contains("Ron Weasley"));
    }

    @Test
    void testRemoveCast() {
        assertFalse(m1.removeCastMember("Actor 99"));
        assertTrue(m1.removeCastMember("Actor 1"));
        m3.removeCastMember("Actor 3");
        assertFalse(m3.getCast().contains("Actor 3"));
        assertTrue(m3.getCast().contains("Actor 2"));
    }

    @Test
    void testAddUpcomingTime() {
        assertFalse(m1.addUpcomingTime(timeOne));
        assertTrue(m1.addUpcomingTime(timeSix));
        m2.addUpcomingTime(timeFive);
        assertTrue(m2.getUpcomingTimes().contains(timeFive));
        assertFalse(m2.addUpcomingTime(timeTwo));
        assertFalse(m2.addUpcomingTime(timeThree));
    }

    @Test
    void testRemoveUpcomingTime() {
        assertFalse(m1.removeUpcomingTime(timeSix));
        assertTrue(m1.removeUpcomingTime(timeOne));
        m2.removeUpcomingTime(timeOne);
        assertFalse(m2.getUpcomingTimes().contains(timeOne));
        m2.removeUpcomingTime(timeTwo);
        assertFalse(m2.getUpcomingTimes().contains(timeTwo));
        assertFalse(m2.removeUpcomingTime(timeTwo));

    }




}
