package fancycinema.user;
//inherits user

import java.time.LocalDateTime;
import fancycinema.component.Movie;


public class NotLoggedIn extends Customer{

    public NotLoggedIn(){
        super("Guest");
    }

    @Override
    public String getMovieDetails(Movie m){

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

    public String getName(){
        return this.name;
    }
}

