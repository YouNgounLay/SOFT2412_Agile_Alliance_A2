package fancycinema.component;

import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.List;

public class Cinema{
    private int id;
    private List<Movie> allMovies = new ArrayList<Movie>();
    private List<Session> allSessions = new ArrayList<Session>();
    private List<Booking> bookings = new ArrayList<>();

    public Cinema(int id) { 
        this.id = id; 
    }
    
    public Cinema(int id, List<Movie> allMovies, 
                  List<Session> allSessions, List<Booking> bookings) {
        this.id = id;
        this.allMovies = allMovies;
        this.allSessions = allSessions; 
        this.bookings = bookings; 
    }
    

    public List<Booking> getBookings() { 
        return this.bookings;
    }
    
    public int getId() { 
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void addBooking(Booking booking) { 
        this.bookings.add(booking);
    }
    
    public void setBookings(List<Booking> bookings) { 
        if (bookings != null) { 
            this.bookings = bookings;
        }
    }
    
    public void addMovie(Movie movie) { 
        for (Movie tmp : allMovies) { 
            if (movie.getMovieName().equals(tmp.getMovieName())) {
                return;
            }
        }
        this.allMovies.add(movie);
    }
    
    public void addSession(Session session) { 
        for (Session tmp : allSessions) {
            if (session.equals(tmp)) { 
                return;
            }
        }
        this.allSessions.add(session);
    }

    public List<Movie> getAllMovies() {
        return allMovies;
    }

    public void setAllMovies(List<Movie> allMovies) {
        if (allMovies != null) {
            this.allMovies = allMovies;
        }
    }
    
    public void setAllSessions(List<Session> allSessions) { 
        if (allSessions != null) { 
            this.allSessions = allSessions;
        }
    }
    
    public void removeMovie(Movie movie) { 
        int index = -1; 
        for (int i = 0; i < allMovies.size(); i++) {
            if (movie.getMovieName().equals(allMovies.get(i).getMovieName())) {
                index = i; 
                break;
            }
        }
        
        if (index != -1) {
            allMovies.remove(index);
            this.removeSession(movie);
            this.removeBooking(movie);
        }
    }
    
    public void removeSession(Movie movie) { 
        int count = allSessions.size();
        for (int i = 0; i < allSessions.size(); i++) { 
            if (allSessions.get(i).getMovie().getMovieName().equals(movie.getMovieName())) {
                allSessions.remove(i);
                count--;
                break;
            }
        }
        if (count != allSessions.size()) {
            this.removeSession(movie);
        } else if (movieExists(movie)) { 
            int index = 0;
            for (int i = 0; i < allMovies.size(); i++){
                if (allMovies.get(i).getMovieName().equals(movie.getMovieName())) {
                    index = i;
                    break;
                }
            }
            this.allMovies.remove(index);
        }
    }
    
    public void removeBooking(Movie movie) {
        int count = bookings.size(); 
        for (int i = 0; i < bookings.size(); i++) { 
           if (bookings.get(i).getMovie().getMovieName().equals(movie.getMovieName())) {
                bookings.remove(i);
                count--;
                break;
           } 
        }
        if (count != bookings.size()) { 
            this.removeBooking(movie);
        } 
    }
    
    public void cancelledBooking(Booking booking) { 
        if (booking == null) { 
            return;
        } else if (!bookings.contains(booking)) { 
            return;
        }
        bookings.remove(booking);
    }

    public List<Session> getAllSessions() {
        return allSessions;
    }

    public void generateSessions() {
        this.allSessions.clear();
        for (Movie m: allMovies) {
            for (LocalDateTime sessionTime: m.getUpcomingTimes()) {
                Session movieSession = new Session(m, sessionTime);
                this.allSessions.add(movieSession);
            }
        }
    }

    public boolean movieExists(Movie movie) {
        for (Movie tmpMovie : allMovies) { 
            if (tmpMovie.getMovieName().equals(movie.getMovieName())) {
                return true;
            }
        }
        return false;
    }
}
