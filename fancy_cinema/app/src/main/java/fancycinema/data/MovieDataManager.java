package fancycinema.data;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import fancycinema.component.Movie;

import java.util.ArrayList;
import java.io.File;



//movieName,synopsis,classification,releaseDate,director,cast,screen,upcomingTimes
public class MovieDataManager{
    private List<Movie> movies;
    private final String path = "src/main/java/fancycinema/data/";

    public MovieDataManager() {
        this.movies = this.readMovies(); 
    }

    public List<Movie> readMovies() {

        this.movies = new ArrayList<>();

        try {
            File f = new File(path + "Movies.txt");
            Scanner sc = new Scanner(f);

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] mov = line.split("#");

                String movieName = mov[0];
                String synopsis = mov[1];
                String classification = mov[2];
                String releaseDate = mov[3];
                int year = Integer.valueOf(releaseDate.split("-")[0]);
                int month = Integer.valueOf(releaseDate.split("-")[1]);
                int day = Integer.valueOf(releaseDate.split("-")[2].substring(0, 2));
                String director = mov[4];
                String cast = mov[5];
                String screen = mov[6];
                String upcomingTimes = mov[7];
                
                


                Movie movie = new Movie(movieName, 
                                        synopsis, 
                                        this.getClassification(classification), 
                                        LocalDate.of(year, month, day),
                                        director, 
                                        this.getCast(cast), 
                                        this.getScreen(screen), 
                                        this.getUpComingTime(upcomingTimes));
                this.movies.add(movie);

            }

            sc.close();

            return this.movies;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Movie> writeMovies() {
        try {
            FileWriter writer = new FileWriter(path + "Movies.txt", false);

            for (Movie movie : this.movies) {
                String movieName = movie.getMovieName();
                String synopsis = movie.getSynopsis();
                String classification = movie.getClassification().name();
                String releaseDate = movie.getReleaseDate().toString();
                String director = movie.getDirector();
                String cast = movie.getCast().toString();
                String screen = movie.getScreen().name();
                String upcomingTimes = movie.getUpcomingTimes().toString();
                writer.write(movieName + "#" + synopsis + "#" + classification + "#" + releaseDate + "#" + director + "#" + cast + "#" + screen + "#" + upcomingTimes + "\n");

            }

            writer.close();

            return this.movies;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
    
    public void update() { 
        this.writeMovies();
    }

    public Movie addMovie(Movie movie){
        if (movie == null) { 
            return null;
        }
        this.movies.add(movie);
        this.writeMovies();
        return movie;
    }

    public Movie removeMovie(Movie movie){
        if (movie == null) { 
            return null; 
        } else if (!movies.contains(movie)) { 
            return null;
        }
        int index = movies.indexOf(movie);
        Movie removedMovie = movies.get(index);
        this.movies.remove(index);
        this.writeMovies();
        return removedMovie;
    }
    
    private Movie.Classification getClassification(String name) {
        for (Movie.Classification classification : Movie.Classification.values()) {  
            if (classification.name().equals(name)) {
                return classification;
            }
        }
        return null;
    }
    
    private List<String> getCast(String data) { 
        String[] dataArray = data.substring(1, data.length() -1).split(", ");
        List<String> casts = new ArrayList<>();
        for (String cast : dataArray)
            casts.add(cast);
        return casts;
    }
    
    private Movie.Screen getScreen(String name) {
        for (Movie.Screen screen : Movie.Screen.values()) { 
            if (screen.name().equals(name)) 
                return screen;
        }
        return null;
    }
    
    
    private List<LocalDateTime> getUpComingTime(String value){ 
        List<LocalDateTime> dateTimes = new ArrayList<>();
        String formattedValue = value.substring(1, value.length()-1);
        for (String date : formattedValue.split(", ")) { 
            String[] dateArray = date.split("-");
            int year = Integer.valueOf(dateArray[0]);
            int month = Integer.valueOf(dateArray[1]);
            int day = Integer.valueOf(dateArray[2].substring(0, 2));
            int hour = Integer.valueOf(dateArray[2].substring(3, 5));
            int minute = Integer.valueOf(dateArray[2].substring(6));
            dateTimes.add(LocalDateTime.of(year, month, day, hour, minute));
        }
        return dateTimes;
   }
   
}

