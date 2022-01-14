package fancycinema.data;
 
import fancycinema.component.Booking;
import fancycinema.component.Cinema;
import fancycinema.component.Movie;
import fancycinema.component.Session;

import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List; 

import java.io.File; 
import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;


public class CinemaDataManager {
    private final String path = "src/main/java/fancycinema/data/";
    private final String file = "Cinemas.txt";
    private int cinemaId;
    private Map<Integer, Cinema> cinemas;
    private SessionDataManager sessionDataManager; 
    private BookingDataManager bookingDataManager;
     
    public CinemaDataManager(List<Movie> movies) {
        this.sessionDataManager = new SessionDataManager(movies);
        this.bookingDataManager = new BookingDataManager(movies, sessionDataManager.getAllSessions());
        this.readFile();
    }
    
    private void readFile() { 
        this.cinemas = new HashMap<>();
        File filename = new File(path + file);
        try { 
            Scanner lineScanner = new Scanner(filename);
            while (lineScanner.hasNextLine()) {
                int id = Integer.parseInt(lineScanner.nextLine()); 
                cinemaId = id;
                cinemas.put(id, new Cinema(id));
                cinemas.get(id).setAllMovies(
                    sessionDataManager.getMovies(id)
                );
                cinemas.get(id).setAllSessions(
                    sessionDataManager.getSessions(id)
                ); 
                cinemas.get(id).setBookings(
                    bookingDataManager.getBookings(id)
                );
            }
            lineScanner.close();
        } catch (IOException e) { 
            return;
        }
    }
 
    private void writeFile(){ 
        File filename = new File(path + file);
        FileWriter writer; 
        
        try {
            writer = new FileWriter(filename, false);
        
            // How do I write to file lmao 
            String msg_format = "%s\n";
            for (int key : this.cinemas.keySet()) {
                Cinema cinema = cinemas.get(key); 
                String id = Integer.toString(cinema.getId()); 
                writer.write(String.format(msg_format, id));
            }
            writer.flush(); 
            writer.close();
        } catch (IOException e) { 
            return;
        }
    }
    
    private void incrementId() { 
        this.cinemaId++; 
    }

    public int addCinema() {
        this.incrementId();
        this.cinemas.put(cinemaId, new Cinema(cinemaId));
        this.writeFile();
        return this.cinemaId;
    }
    
    public void update() { 
        this.writeFile();
        this.bookingDataManager.update();
        this.sessionDataManager.update();
    }

    public List<Booking> getBookings(String userName) { 
        List<Booking> bookings = new ArrayList<>(); 
        for (Integer id : cinemas.keySet()) {
            for (Booking booking : cinemas.get(id).getBookings()) {
                if (booking.getCustomerName().equals(userName))
                    bookings.add(booking);
            }
        }
        return bookings;
    }

    public Map<Integer, Cinema> getCinemas() {
        return this.cinemas;
    }
    
    public void removeMovie(Movie movie) { 
        for (Cinema cinema : cinemas.values()) { 
            cinema.removeMovie(movie);
        }
        this.sessionDataManager.removeMovie(movie);
        this.bookingDataManager.removeMovie(movie);
    }
    
    public void addSession(Cinema cinema, Session session) { 
        this.cinemas.get(cinema.getId()).addSession(session);
        this.sessionDataManager.addSession(cinema.getId(), session);
        this.update();
    }

    public void addBooking(int cinemaId, Booking booking) { 
        this.cinemas.get(cinemaId).addBooking(booking);
        this.bookingDataManager.addBooking(cinemaId, booking); 
    }
    
    public void removeBooking(Booking booking) { 
        for (Cinema cinema : this.cinemas.values()) { 
            cinema.cancelledBooking(booking);
        }
        this.bookingDataManager.removeBooking(booking);
        this.update(); 
    }
}
