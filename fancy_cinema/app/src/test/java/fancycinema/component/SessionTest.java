package fancycinema.component;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SessionTest {

    LocalDateTime timeOne;
    LocalDateTime timeTwo;
    LocalDateTime timeThree;
    LocalDateTime timeFour;
    LocalDateTime timeFive;
    LocalDateTime timeSix;
    LocalDate releaseDate;
    List<LocalDateTime> upcomingTimesOne = new ArrayList<>();
    List<LocalDateTime> upcomingTimesTwo = new ArrayList<>();
    List<String> castMembers = new ArrayList<>();
    Movie m1;
    Movie m2;
    Movie m3;
    Movie m4;
    Session s1;
    Session s2;

    @BeforeEach
    void init() {
        releaseDate = LocalDate.of(2021, Month.DECEMBER, 25);
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
        upcomingTimesOne.clear();
        upcomingTimesOne.add(timeOne);
        upcomingTimesOne.add(timeTwo);
        upcomingTimesOne.add(timeThree);
        upcomingTimesTwo.clear();
        upcomingTimesTwo.add(timeFour);
        upcomingTimesTwo.add(timeFive);
        upcomingTimesTwo.add(timeSix);
        m1 = new Movie("Movie1", "Example1", Movie.Classification.G, releaseDate, "director",castMembers, Movie.Screen.Silver, upcomingTimesOne);
        m2 = new Movie("Movie2", "Example2", Movie.Classification.R, releaseDate, "director",castMembers, Movie.Screen.Gold, upcomingTimesTwo);
        m3 = new Movie("Movie3", "Example3", Movie.Classification.MA, releaseDate, "director",castMembers, Movie.Screen.Bronze, upcomingTimesTwo);
        m4 = new Movie("Movie4", "Example4", Movie.Classification.R, releaseDate, "director",castMembers, Movie.Screen.Gold, upcomingTimesOne);
        s1 = new Session(m1, timeOne);
        s2 = new Session(m2, timeFour);
    }

    @Test
    void testGetSetMovie() {
        assertEquals(s1.getMovie(), m1);
        s1.setMovie(m4);
        assertNotEquals(s1.getMovie(), m1);
        assertEquals(s1.getMovie(), m4);
    }

    @Test
    void testGetSetSessionTime() {
        assertEquals(s1.getSessionTime(), timeOne);
        s1.setSessionTime(timeTwo);
        assertNotEquals(s1.getSessionTime(), timeOne);
        assertEquals(s1.getSessionTime(), timeTwo);
    }

    @Test
    void testGetSetSeatCapacity() {
        assertEquals(s1.getFrontCapacity(), 25);
        assertEquals(s1.getMiddleCapacity(), 25);
        assertEquals(s1.getRearCapacity(), 25);
        assertEquals(s1.getLimit(), 25);
        s1.setLimit(50);
        assertNotEquals(s1.getLimit(), 25);
        assertEquals(s1.getLimit(), 50);
        assertTrue(s1.addFrontCapacity(25));
        assertFalse(s1.addFrontCapacity(1));
        assertFalse(s1.addMiddleCapacity(100));
        assertTrue(s1.addMiddleCapacity(5));
        assertTrue(s1.addRearCapacity(25));
        assertFalse(s1.addRearCapacity(1));
        assertTrue(s1.rmFrontCapacity(50));
        assertTrue(s1.rmMiddleCapacity(10));
        assertTrue(s1.rmRearCapacity(50));
        assertFalse(s1.rmRearCapacity(10));
        assertFalse(s1.rmFrontCapacity(1000));
        assertFalse(s1.rmMiddleCapacity(1000));
    }

    @Test
    void testIsFull() {
        assertFalse(s1.isFull());
        s1.rmFrontCapacity(25);
        assertFalse(s1.isFull());
        s1.rmMiddleCapacity(25);
        assertFalse(s1.isFull());
        s1.rmRearCapacity(25);
        assertTrue(s1.isFull());
    }

    @Test
    void testGetVacant() {
        assertEquals(s1.getVacant(), 75);
        assertNotEquals(s1.getVacant(), 25);
        s1.rmFrontCapacity(25);
        s1.rmMiddleCapacity(25);
        s1.rmRearCapacity(25);
        assertEquals(s1.getVacant(), 0);
    }

    @Test
    void testGetUnavailable() {
        assertEquals(s1.getUnavailable(), 0);
        s1.rmFrontCapacity(10);
        s1.rmMiddleCapacity(10);
        s1.rmRearCapacity(10);
        assertEquals(s1.getUnavailable(), 30);
    }


}
