package fancycinema.component;//cinema,movie,time

import java.time.LocalDateTime;
import java.util.List;

public class Booking {

    private Session session;
    private PersonType personType;
    private SeatType seatType;
    private Movie movie;
    private String customerName; 
    private boolean paid;

    public enum PersonType {
        Child,
        Student,
        Adult,
        Senior;
    }

    public enum SeatType {
        Front,
        Middle,
        Rear;
    }
    
    public Movie getMovie() { 
        return this.session.getMovie();
    }
    
    public List<LocalDateTime> getMovieTime() {
        return this.movie.getUpcomingTimes();
    }
    
    public PersonType getPersonType() { 
        return this.personType;
    }

    public SeatType getSeatType() {
        return this.seatType;
    }
    
    public String getCustomerName() { 
        return this.customerName; 
    }
    
    public Session getSession() { 
        return this.session;
    }
    
    public boolean isPaid() { 
        return paid; 
    }
    
    public void pay() { 
        this.paid = true;
    }

    public Booking(Session session, PersonType personType, SeatType seatType, String name) {
        this.session = session;
        this.movie = this.session.getMovie();
        this.personType = personType;
        this.seatType = seatType;
        if (this.seatType == SeatType.Front) {
            if (this.session.getFrontCapacity() >= 1) {
                this.session.rmFrontCapacity(1);
            }
        } else if (this.seatType == SeatType.Middle) {
            if (this.session.getMiddleCapacity() >= 1) {
                this.session.rmMiddleCapacity(1);
            }
        } else if (this.seatType == SeatType.Rear) {
            if (this.session.getRearCapacity() >= 1) {
                this.session.rmRearCapacity(1);
            }
        } else {
            System.out.println("Out of seats!");
            return;
        }
        this.customerName = name;
    }











}