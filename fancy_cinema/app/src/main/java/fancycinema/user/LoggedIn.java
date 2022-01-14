package fancycinema.user;
//inherits user

import java.time.LocalDateTime;
import java.util.*;


import fancycinema.component.Movie;
import fancycinema.component.Card;
import fancycinema.component.Booking;
import fancycinema.component.Session;
import fancycinema.component.Booking.PersonType;
import fancycinema.component.Booking.SeatType;
import fancycinema.component.Cinema;

public class LoggedIn extends Customer{
    private Card creditCard;
    ArrayList<Booking> bookings = new ArrayList<Booking>();
    //listOfBookings
    public LoggedIn(String name){
        super(name);
    }

    public LoggedIn(String id, String userName, String password) {
        super(id, userName, password); 
        this.id = id; 
        this.password = password;
    }
    public LoggedIn(String id, String userName, String password, String cardName, String cardNumber) {
        super(id, userName, password, cardName, cardNumber);
    }
    
    
    @Override
    public String getMovieDetails(Movie m) {
        StringBuilder str = new StringBuilder();
        str.append("\nMovie:\n");
        str.append(m.getMovieName());
        str.append("\nSynopsis:\n");
        str.append(m.getSynopsis());
        str.append("\nClassification:\n");
        str.append(m.getClassification());
        str.append("\nRelease date:\n");
        str.append(m.getReleaseDate().toString());
        str.append("\nDirected by:\n");
        str.append(m.getDirector());
        str.append("\nCast:\n");
        for (String x: m.getCast()){
            str.append(x);
            str.append(", ");
        }
        str.append(".\nShowtimes:\n");
        for (LocalDateTime t: m.getUpcomingTimes()){
            str.append(t.toString());
            str.append(", ");
        }
        str.append(".\nScreensize:\n");
        str.append(m.getScreen());
        str.append("\n");
        return str.toString();
    }

    public void addCreditCard(Card creditCard){
        this.creditCard = creditCard;
    }

    /**
     * Returns an ArrayList of all sessions compatible with that movie.
     * 
     * @param movie
     * @param cinema
     * @return
     */
    public ArrayList<Session> viewSessions(Movie movie, Cinema cinema){
        List<Session> allSessions = cinema.getAllSessions();
        ArrayList<Session> allSelectedSessions = new ArrayList<Session>();
        for (Session s: allSessions){
            if(s.getMovie().equals(movie)){
                allSelectedSessions.add(s);
            }
        }
        return allSelectedSessions;
    }

    public Booking bookMovie(SeatType seat, PersonType person,  Session session){
        Booking newBooking = new Booking(session, person, seat, name);
        bookings.add(newBooking);
        return newBooking;
    }

    public boolean cancelBooking(Booking b){
        if(bookings.contains(b)){
            bookings.remove(b);
            return true;
        }
        return false;
    }

    public ArrayList<Booking> getBookings(){
        return this.bookings;
    }







    //chooseMovie()
    //addNumberOfPeople()
    //chooseSeat()
    //cancel()
    //bookMovie()

}

