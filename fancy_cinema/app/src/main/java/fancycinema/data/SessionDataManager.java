package fancycinema.data;

import fancycinema.component.Movie;
import fancycinema.component.Session;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

public class SessionDataManager {
    private final String path = "src/main/java/fancycinema/data/"; 
    private final String file = "Sessions.txt";
    private Map<Integer, List<Session>> cinemaSessions;
    private Map<Integer, List<Movie>> cinemaMovies;
    private List<Session> sessions; // Just iterate through this would be enough
    private List<Movie> movies; // No need to be deleted

    public SessionDataManager(List<Movie> movies) {
        this.movies = movies;
        this.readFile(); 
    }
    
    public void addSession(int id, Session session) { 
        if (!cinemaSessions.containsKey(id)) { 
            cinemaSessions.put(id, new ArrayList<>());
        }
        
        if (!cinemaSessions.get(id).contains(session)) { 
            cinemaSessions.get(id).add(session);
        }
        this.writeFile();
    }
    
    public void removeSession(int id, Session session) { 
        if (!cinemaSessions.containsKey(id)) {
            return; 
        } 
        
        if (!cinemaSessions.get(id).contains(session)) { 
            return; 
        }
        
        cinemaSessions.get(id).remove(session);  
        if (cinemaSessions.get(id).size() == 0) { 
            cinemaSessions.remove(id);
        }
        this.writeFile();
    }
    
    public List<Session> getSessions(int id) { 
        return cinemaSessions.get(id);
    }

    public List<Session> getAllSessions() { 
        return sessions;
    }
    
    public void update() { 
        this.writeFile();
    }

    private void readFile() { 
        this.sessions = new ArrayList<>();
        this.cinemaSessions = new HashMap<>();
        this.cinemaMovies = new HashMap<>();
        File filename = new File(path + file); 
        try {
            Scanner lineScanner = new Scanner(filename);
            while(lineScanner.hasNextLine()) { 
                String[] data = lineScanner.nextLine().split("#");
                int cinemaId = Integer.parseInt(data[0]);
                Movie movie = this.getMovie(data[1]);
                LocalDateTime sessionTime = LocalDateTime.parse(data[2]); 
                //int unavailableSeats = Integer.parseInt(data[3]);
                //int vacantSeats = Integer.parseInt(data[4]);
                
                if (movie == null) { 
                    continue;
                }

                Session session  = new Session(movie, sessionTime);
                if (!this.cinemaSessions.containsKey(cinemaId)) {
                    this.cinemaSessions.put(cinemaId, new ArrayList<>());
                } 
                
                if (!this.cinemaMovies.containsKey(cinemaId)) { 
                    this.cinemaMovies.put(cinemaId, new ArrayList<>());
                }
                this.cinemaSessions.get(cinemaId).add(session);
                
                if (!cinemaMovies.get(cinemaId).contains(movie)) { 
                    cinemaMovies.get(cinemaId).add(movie);
                }

                this.sessions.add(session);
                
            }
            lineScanner.close();
        } catch (IOException e) { 
            System.err.println("Invalid File");
        }
    }
     
    private void writeFile() { 
        File filename = new File(path + file); 
        try { 
            FileWriter writer = new FileWriter(filename, false);
            String msgFormat = "%s#%s#%s#s#s\n";
            for (int key : this.cinemaSessions.keySet()) {
                String id = Integer.toString(key);
                for (Session session : this.cinemaSessions.get(key)) { 
                    String movieName = session.getMovie().getMovieName();
                    String sessionTime = session.getSessionTime().toString(); 
                    String unavailableSeats = String.valueOf(session.getVacant());
                    String vacantSeats = String.valueOf(session.getUnavailable());
                    writer.write(String.format(msgFormat, id, movieName, sessionTime, unavailableSeats, vacantSeats));
                } 
            }
            writer.flush(); 
            writer.close(); 
        } catch (IOException e) {
            System.err.println("Invaid File");
        }
    }

    private Movie getMovie(String name) { 
        for (Movie movie : this.movies) { 
            if (name.equals(movie.getMovieName())) { 
                return movie;
            }
        }
        return null;
    }
    
    public List<Movie> getMovies(int id) { 
        return this.cinemaMovies.get(id);
    }

    /**
     * Recursively delete all the sessions that contains the movie
     * @param: movie: Movie
     **/
    public void removeMovie(Movie movie) { 
        int count = this.sessions.size();
        for (Session session : this.sessions) {
            if (session.getMovie().getMovieName().equals(movie.getMovieName())) {
                this.sessions.remove(session);
                count--; 
                break;
            }
        }
        if (count != this.sessions.size()) { 
            this.removeMovie(movie);
        } else { 
            this.writeFile();
        }

        }
    }

