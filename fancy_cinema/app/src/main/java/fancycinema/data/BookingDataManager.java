package fancycinema.data;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import fancycinema.component.Booking;
import fancycinema.component.CancelledTransaction;
import fancycinema.component.Movie;
import fancycinema.component.Session;

public class BookingDataManager {
    private final String path = "src/main/java/fancycinema/data/"; 
    private final String file = "Bookings.txt";
    private Map<Integer, List<Booking>> cinemaBookings;
    private List<Session> sessions; //Automatically updated by sessions
    private List<Booking> bookings; 
    private List<Movie> movies; // Automatically updated by movie data manage 

    private CancelledTransactionDataManager cancelledTransactionDataManager; 
    
    public BookingDataManager(List<Movie> movies, List<Session> sessions) {
        this.movies = movies; 
        this.sessions = sessions;
        this.cancelledTransactionDataManager = new CancelledTransactionDataManager();
        this.readFile();
    }

    private void readFile() { 
        cinemaBookings = new HashMap<>();
        bookings = new ArrayList<>();
        File filename = new File(path + file); 
        
        try {
            Scanner lineScanner = new Scanner(filename);  
            while (lineScanner.hasNextLine()) {
                String[] data = lineScanner.nextLine().split("#");
                int cinemaId = Integer.parseInt(data[0]);
                Booking.PersonType personType = this.getPersonType(data[1]);
                Booking.SeatType seatType = this.getSeatType(data[2]);
                LocalDateTime dateTime = LocalDateTime.parse(data[3]); 
                Movie movie = this.getMovie(data[4]); 
                
                if (movie == null) { 
                    continue; 
                }

                String username = data[5];
                Boolean paid = Boolean.valueOf(data[6]);

                Session session = this.getSession(movie, dateTime); 
                Booking booking = new Booking(session, personType, seatType, username); 
                
                if (paid) 
                    booking.pay();
                
                if (!cinemaBookings.containsKey(cinemaId)) {  
                    cinemaBookings.put(cinemaId, new ArrayList<>());
                }
                
                cinemaBookings.get(cinemaId).add(booking);
                bookings.add(booking);
            }
        } catch (IOException e) { 
            System.err.println("IO Exception");
        }
    }
    
    private void writeFile() { 
        File filename = new File(path + file);
        try { 
            FileWriter writer = new FileWriter(filename, false);
            String format = "%s#%s#%s#%s#%s#%s#%s\n";
            for (int key : cinemaBookings.keySet()) { 
                String id = Integer.toString(key); 
                for (Booking booking : cinemaBookings.get(key)) {   
                    String personType = booking.getPersonType().name();
                    String seatType = booking.getSeatType().name();
                    String dateTime = booking.getSession().getSessionTime().toString();
                    String movieName = booking.getSession().getMovie().getMovieName();
                    String username = booking.getCustomerName();
                    String paid = Boolean.toString(booking.isPaid());
                    writer.write(String.format(format, id, personType, seatType, dateTime,
                                                movieName, username, paid)
                                );
                }
            }
            writer.flush(); 
            writer.close();
        } catch (IOException e) { 
            System.err.println("IO Exception");
        }
    }
    
    public void addBooking(int id, Booking booking) { 
        if (this.cinemaBookings.get(id) == null) { 
            this.cinemaBookings.put(id, new ArrayList<>());
        }
        this.cinemaBookings.get(id).add(booking);
        this.update();
    }
    
    public List<Booking> getBookings(int id) {
        return this.cinemaBookings.get(id); 
    }

    public List<Session> getAllSessions() { 
        return sessions;
    }
    
    public void update() { 
        this.writeFile();
    }
     
    private Booking.PersonType getPersonType(String value) {
        for (Booking.PersonType type : Booking.PersonType.values()) { 
            if (value.equals(type.name())) {
                return type;
            }
        }
        return null;
    }
    
    private Booking.SeatType getSeatType(String value) {
        for (Booking.SeatType type : Booking.SeatType.values()) { 
            if (value.equals(type.name())) {
                return type;
            }
        }
        return null;
    }

    private Movie getMovie(String name) { 
        for (Movie movie : this.movies) { 
            if (name.equals(movie.getMovieName())) { 
                return movie;
            }
        }
        return null;
    }
    
    private Session getSession(Movie name, LocalDateTime dateTime) { 
        for (Session session : sessions) { 
            if (name.getMovieName().equals(session.getMovie().getMovieName()) 
              && dateTime.equals(session.getSessionTime())) {
                  return session;
              }
        }
        return null; 
    }
    
    public void removeMovie(Movie movie) {
        int count = this.bookings.size();
        for (Booking booking : this.bookings) { 
            Movie sessionMovie = booking.getSession().getMovie(); 
            if (sessionMovie.getMovieName().equals(movie.getMovieName())) {
                this.bookings.remove(booking);
                if (!booking.isPaid()) {
                    this.createCancelledTransaction(booking);
                }
                count--; 
                break;
            }
        }
        
        if (count != this.bookings.size()) { 
            // Implies that there are still things to delete    
            this.removeMovie(movie);
        } else { 
            this.writeFile();
        }
    }
    
    private void createCancelledTransaction(Booking booking) {
        String username = booking.getCustomerName();
        LocalDateTime dateTime = booking.getSession().getSessionTime();
        String reason = "Super User Operation"; 
        CancelledTransaction cancelledTransaction = new CancelledTransaction(username, dateTime, reason);
        cancelledTransactionDataManager.addCancelledTransaction(cancelledTransaction);
    }
    
    public void removeBooking(Booking booking) { 
        if (booking == null) { 
            return;
        }
        for (List<Booking> tmpBooking : cinemaBookings.values()) {
            if (tmpBooking.contains(booking)) {
                tmpBooking.remove(booking);
                break; 
            }
        }
    }

}
