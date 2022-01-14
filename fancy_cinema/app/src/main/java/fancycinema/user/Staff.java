package fancycinema.user;
//inherits user

//inherits user
import fancycinema.component.Movie;


public class Staff extends Employee{

    public Staff(String name){
        super(name);
    }
    
    public Staff(String id, String name, String password) { 
        super(id, name, password);
    }

    @Override
    public String getMovieDetails(Movie m) {
        return null;
    }
}

