package fancycinema.component;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CinemaTest {


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
    Session s4;
    Booking b1;
    Booking b2;
    Booking b3;
    Booking b4;
    List<Movie> allMovies = new ArrayList<>();
    List<Session> allSessions = new ArrayList<>();
    List<Booking> allBookings = new ArrayList<>();
    List<Booking> allBookingsChanged = new ArrayList<>();
    Cinema c1;
    Cinema c2;

    @BeforeEach
    void init() {
        releaseDate = LocalDate.of(2021, Month.DECEMBER, 25);
        timeOne = LocalDateTime.of(2022, Month.JANUARY, 1, 10, 30);
        timeTwo = LocalDateTime.of(2022, Month.JANUARY, 1, 13,00);
        timeThree = LocalDateTime.of(2022, Month.JANUARY, 2, 17,40);
        timeFour = LocalDateTime.of(2022, Month.JANUARY, 2, 14,20);
        castMembers.clear();
        castMembers.add("Actor 1");
        castMembers.add("Actor 2");
        castMembers.add("Actor 3");
        upcomingTimesOne.clear();
        upcomingTimesOne.add(timeOne);
        upcomingTimesOne.add(timeTwo);
        upcomingTimesTwo.clear();
        upcomingTimesTwo.add(timeThree);
        upcomingTimesTwo.add(timeFour);
        m1 = new Movie("Movie1", "Example1", Movie.Classification.G, releaseDate, "director",castMembers, Movie.Screen.Silver, upcomingTimesOne);
        m2 = new Movie("Movie2", "Example2", Movie.Classification.R, releaseDate, "director",castMembers, Movie.Screen.Gold, upcomingTimesTwo);
        m3 = new Movie("Movie3", "Example2", Movie.Classification.R, releaseDate, "director",castMembers, Movie.Screen.Bronze, upcomingTimesTwo);
        m4 = new Movie("Movie4", "Example2", Movie.Classification.R, releaseDate, "director",castMembers, Movie.Screen.Silver, upcomingTimesTwo);
        s1 = new Session(m1, timeOne);
        s2 = new Session(m1, timeTwo);
        s3 = new Session(m2, timeThree);
        s4 = new Session(m2, timeFour);
        b1 = new Booking(s1, Booking.PersonType.Adult, Booking.SeatType.Front, "Ray");
        b2 = new Booking(s2, Booking.PersonType.Student, Booking.SeatType.Rear, "Eleanor");
        b3 = new Booking(s3, Booking.PersonType.Senior, Booking.SeatType.Middle, "Jimmy");
        b4 = new Booking(s4, Booking.PersonType.Child, Booking.SeatType.Middle, "Mackenzie");
        allMovies.add(m1);
        allMovies.add(m2);
        allSessions.add(s1);
        allSessions.add(s2);
        allBookings.add(b1);
        allBookings.add(b2);
        allBookingsChanged.add(b3);
        allBookingsChanged.add(b4);
        c1 = new Cinema(999, allMovies, allSessions, allBookings);
        c2 = new Cinema(100);
    }

    @Test
    void testGetSetBookings() {
        assertEquals(c1.getBookings(), allBookings);
        c1.setBookings(null);
        assertEquals(c1.getBookings(), allBookings);
        c1.setBookings(allBookingsChanged);
        assertNotEquals(c1.getBookings(), allBookings);
        assertEquals(c1.getBookings(), allBookingsChanged);
    }

    @Test
    void testAddBookings() {
        List<Booking> bookings = new ArrayList<>();
        bookings.addAll(allBookings);
        bookings.add(b3);
        c1.addBooking(b3);
        assertEquals(c1.getBookings(), bookings);
    }

    @Test
    void testGetSetID() {
        assertEquals(c1.getId(), 999);
        c1.setId(888);
        assertNotEquals(c1.getId(), 999);
        assertEquals(c1.getId(), 888);
        assertEquals(c2.getId(), 100);
        c2.setId(300);
        assertNotEquals(c2.getId(), 100);
        assertEquals(c2.getId(), 300);
    }

    @Test
    void testGetSetMovies() {
        List<Movie> movies = new ArrayList<>();
        movies = c1.getAllMovies();
        assertTrue(movies.contains(m1));
        assertTrue(movies.contains(m2));
        movies.clear();
        movies.add(m3);
        movies.add(m4);
        c1.setAllMovies(null);
        c1.setAllMovies(movies);
        assertTrue(c1.getAllMovies().contains(m3));
        assertTrue(c1.getAllMovies().contains(m4));
        assertFalse(c1.getAllMovies().contains(m1));
    }

    @Test
    void testAddMovie() {
        assertFalse(c1.getAllMovies().contains(m3));
        c1.addMovie(m3);
        c1.addMovie(m2);
        assertTrue(c1.getAllMovies().contains(m2));
    }

    @Test
    void testGetSetSessions() {
        assertEquals(c1.getAllSessions(), allSessions);
        List<Session> sessions = new ArrayList<>();
        sessions.addAll(allSessions);
        sessions.add(s3);
        assertNotEquals(c1.getAllSessions(), sessions);
        c1.setAllSessions(null);
        c1.setAllSessions(sessions);
        assertEquals(c1.getAllSessions(), sessions);

    }

    @Test
    void testAddSessions() {
        assertFalse(c1.getAllSessions().contains(s3));
        c1.addSession(s3);
        assertTrue(c1.getAllSessions().contains(s3));
        c1.addSession(s3);
        assertTrue(c1.getAllSessions().contains(s3));
        assertFalse(c1.getAllSessions().contains(s4));
        c1.addSession(s4);
        assertTrue(c1.getAllSessions().contains(s4));
    }

    @Test
    void testCancelledBooking() {
        c1.cancelledBooking(null);
        assertTrue(c1.getBookings().contains(b1));
        c1.cancelledBooking(b1);
        assertFalse(c1.getBookings().contains(b1));
        c1.cancelledBooking(b4);
    }

    @Test
    void testMovieExists() {
        assertTrue(c1.movieExists(m1));
        assertTrue(c1.movieExists(m2));
        assertFalse(c1.movieExists(m3));
    }

    @Test
    void testGenerateSessions() {
        List<Session> sessions = new ArrayList<>();
        c1.generateSessions();
        assertTrue(c1.getAllSessions().size() == 4);
    }

    @Test
    void testRemoveMovieSessionBooking() {
        assertTrue(c1.movieExists(m1));
        assertTrue(c1.getBookings().contains(b1));
        assertTrue(c1.getBookings().contains(b2));
        assertTrue(c1.getAllSessions().contains(s1));
        assertTrue(c1.getAllSessions().contains(s2));
        c1.removeMovie(m1);
        assertFalse(c1.movieExists(m1));
        assertFalse(c1.getBookings().contains(b1));
        assertFalse(c1.getAllSessions().contains(s1));
        c1.removeSession(m1);
        assertFalse(c1.getAllSessions().contains(s2));
        c1.getAllSessions().remove(s1);
        c1.removeSession(m1);
        assertFalse(c1.getAllSessions().contains(s1));
    }

}
