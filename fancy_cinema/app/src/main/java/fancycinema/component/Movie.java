package fancycinema.component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Movie {

    private String movieName;
    private String synopsis;
    private Classification classification;
    private LocalDate releaseDate;
    private String director;
    private List<String> cast = new ArrayList<String>();
    private Screen screen;
    private List<LocalDateTime> upcomingTimes = new ArrayList<LocalDateTime>();

    public enum Classification{
        G,
        PG,
        M,
        MA,
        R,
        Unknown;
    }

    public enum Screen{
        Gold,
        Silver,
        Bronze
    }

    public Movie(String movieName, String synopsis, Classification classification, LocalDate releaseDate, String director, List<String> cast, Screen screen, List<LocalDateTime> upcomingTimes) {
        this.movieName = movieName;
        this.synopsis = synopsis;
        this.classification = classification;
        this.releaseDate = releaseDate;
        this.director = director;
        this.cast = cast;
        this.screen = screen;
        this.upcomingTimes = upcomingTimes;
    }

    public String getMovieName() {
        return movieName;
    }

    public String getDirector() {
        return director;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public List<String> getCast() {
        return cast;
    }

    public Classification getClassification() {
        return this.classification;
    }

    public Screen getScreen() {
        return this.screen;
    }

    public List<LocalDateTime> getUpcomingTimes() {
        return upcomingTimes;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public void setClassification(Classification classification) {
        this.classification = classification;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setCast(List<String> cast) {
        this.cast = cast;
    }
    
    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public void setUpcomingTimes(List<LocalDateTime> upcomingTimes) {
        this.upcomingTimes = upcomingTimes;
    }

    public boolean addCastMember(String memberName) {
        if (!this.cast.contains(memberName)) {
            return this.cast.add(memberName);
        }
        return false;
    }

    public boolean removeCastMember(String memberName) {
        if (this.cast.contains(memberName)) {
            return this.cast.remove(memberName);
        }
        return false;
    }

    public boolean addUpcomingTime(LocalDateTime ldt) {
        if (!this.upcomingTimes.contains(ldt)) {
            return this.upcomingTimes.add(ldt);
        }
        return false;
    }

    public boolean removeUpcomingTime(LocalDateTime ldt) {
        if (this.upcomingTimes.contains(ldt)) {
            return this.upcomingTimes.remove(ldt);
        }
        return false;
    }


}
