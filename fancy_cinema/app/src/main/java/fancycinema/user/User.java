package fancycinema.user;

import fancycinema.component.Movie;

public abstract class User {
    protected String name;

    public User(String name){
        this.name = name;
    }

    public abstract String getMovieDetails(Movie m);

    public String getName(){
        return this.name;
    }
    

}
