package fancycinema.component;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BookingTest {

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
    Session s3;
    Booking b1;
    Booking b2;
    Booking b3;
    Booking b4;

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
        s3 = new Session(m3, timeFour);
        b1 = new Booking(s1, Booking.PersonType.Adult, Booking.SeatType.Front, "Ray");
        b2 = new Booking(s2, Booking.PersonType.Student, Booking.SeatType.Rear, "Eleanor");
        b3 = new Booking(s2, Booking.PersonType.Senior, Booking.SeatType.Middle, "Jimmy");
        b4 = new Booking(s1, Booking.PersonType.Child, Booking.SeatType.Middle, "Mackenzie");
    }

    @Test
    void testGetMovie(){
        assertEquals(b1.getMovie(), m1);
        assertEquals(b2.getMovie(), m2);
        assertNotEquals(b1.getMovie(), m2);
    }

    @Test
    void testGetPersonType() {
        assertEquals(b1.getPersonType(), Booking.PersonType.Adult);
        assertEquals(b2.getPersonType(), Booking.PersonType.Student);
        assertEquals(b3.getPersonType(), Booking.PersonType.Senior);
        assertEquals(b4.getPersonType(), Booking.PersonType.Child);
    }

    @Test
    void testGetSeatType() {
        assertEquals(b1.getSeatType(), Booking.SeatType.Front);
        assertEquals(b2.getSeatType(), Booking.SeatType.Rear);
        assertEquals(b3.getSeatType(), Booking.SeatType.Middle);
    }

    @Test
    void testGetCustomerName() {
        assertEquals(b1.getCustomerName(), "Ray");
        assertEquals(b2.getCustomerName(), "Eleanor");
        assertNotEquals(b3.getCustomerName(), "Bill");
    }

    @Test
    void testGetSession() {
        assertEquals(b1.getSession(), s1);
        assertNotEquals(b1.getSession(), s2);
        assertNotEquals(b2.getSession(), s1);
        assertEquals(b2.getSession(), s2);
    }

    @Test
    void testPayAndPaid() {
        assertFalse(b1.isPaid());
        b1.pay();
        assertTrue(b1.isPaid());
        assertFalse(b2.isPaid());
    }

    @Test
    void testGetMovieTimes() {
        List<LocalDateTime> times = new ArrayList<>();
        times = b1.getMovieTime();
        assertTrue(times.contains(timeOne));
        assertEquals(b1.getMovieTime(), upcomingTimesOne);
    }

    @Test
    void testCreation(){
        s3.rmRearCapacity(25);
        Booking test1 = new Booking(s3, Booking.PersonType.Student, Booking.SeatType.Rear, "Nancy");
        s2.rmFrontCapacity(25);
        Booking test2 = new Booking(s2, Booking.PersonType.Student, Booking.SeatType.Front, "Derek");
        s1.rmMiddleCapacity(25);
        Booking test3 = new Booking(s1, Booking.PersonType.Student, Booking.SeatType.Middle, "Taylor");
        Booking test4 = new Booking(s1, Booking.PersonType.Student, null, "Halsey");
        s1.addMiddleCapacity(10);
        Booking test5 = new Booking(s1, Booking.PersonType.Senior, Booking.SeatType.Middle, "Idk");
    }

}
