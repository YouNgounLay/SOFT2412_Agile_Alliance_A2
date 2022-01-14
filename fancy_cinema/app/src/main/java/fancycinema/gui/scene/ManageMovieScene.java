package fancycinema.gui.scene;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import fancycinema.component.Movie;
import fancycinema.gui.SceneLoader;
import fancycinema.gui.utility.MovieContainer;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class ManageMovieScene extends BaseScene {

    public ManageMovieScene(SceneLoader sceneLoader) {
        super(sceneLoader);
        this.initialization();
        this.sideBarInit();
    }
    
    private void initialization() { 
        ScrollPane container = new ScrollPane(); 
        GridPane body = new GridPane();
        body.setVgap(10);
        body.setHgap(150);
        
        this.movieInit(body); 
        container.setPrefSize(120, 120);
        container.setContent(body);
        this.canvas.setCenter(container); 
        
    }

    private void movieInit(GridPane body) {
        int yAxis = 0;
        for (Movie movie : this.sceneLoader.getSystem().getMovies()) { 
            MovieContainer content = new MovieContainer(movie, sceneLoader);
            content.isSuperUser(true);
            body.add(content.getContainer(), 0, yAxis++);
        }
    }
    

    private void sideBarInit() {
        GridPane body = new GridPane();
        body.setVgap(10);
        Text header = new Text("Adding Movie");

        // MovieName, synopsis, classification, 
        // releaseDate: yyyy-mm-dd 
        // Director 
        // Casts
        // Screen 
        // Upcoming time: yyyy-mm-dd hh-mm-ss
        TextField movieName = new TextField(); 
        movieName.setPromptText("Movie Name");
        // Getting the value of the input field
        // movieName.getText();
        TextField synopsis = new TextField();
        synopsis.setPromptText("Synopsis");

        TextField classification = new TextField(); 
        classification.setPromptText("Classification");

        DatePicker releaseDate = new DatePicker();
        releaseDate.setPromptText("releaseDate");

        TextField director = new TextField();
        director.setPromptText("Director");
        
        TextField cast = new TextField();
        cast.setPromptText("Cast");

        TextField screen = new TextField();
        screen.setPromptText("Screen");

        DatePicker upcomingTime = new DatePicker();
        upcomingTime.setPromptText("Upcoming Time");
        
        Label submitButton = new Label("Create New Movie");     
        submitButton.setOnMouseClicked(mouseEvent -> { 
            // Philip to uncomment
            this.createMovie(movieName.getText(), synopsis.getText(), classification.getText(), releaseDate.getValue().toString(), 
                                director.getText(), cast.getText(), screen.getText(), upcomingTime.getValue().toString());
            
            // To refreash the page do not remove
            BaseScene manageMovieScene = new ManageMovieScene(sceneLoader);
            manageMovieScene.setNavBar(sceneLoader.getNavBar());
            this.sceneLoader.setScene(manageMovieScene);
        });
        
        int yAxis = 0;
        body.add(header, 0, yAxis++);
        body.add(movieName, 0, yAxis++);  
        body.add(synopsis, 0, yAxis++);  
        body.add(classification, 0, yAxis++);  
        body.add(releaseDate, 0, yAxis++);
        body.add(director, 0, yAxis++);  
        body.add(cast, 0, yAxis++);  
        body.add(screen, 0, yAxis++);
        body.add(upcomingTime, 0, yAxis++);  
        body.add(new Button("", submitButton), 0, yAxis++);

        this.canvas.setRight(body);
    }
    
    /** 
     * Philip Tasks
    */
    private void createMovie(String name, String synopsis,
                             String classification, String releaseDate,
                             String director, String cast, 
                             String screen, String upcomingTime) {

        Movie.Classification classificationUpdated = getClassification(classification);
        System.out.println(classificationUpdated);
        LocalDate releaseDateUpdated = LocalDate.parse(releaseDate); //"YYYY-MM-DD"
        String upcomingDateTime = upcomingTime + " 00:00:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); 
        LocalDateTime dateTime = LocalDateTime.parse(upcomingDateTime, formatter);
        List<String> casts = new ArrayList<>();
        casts.add(cast);
        List<LocalDateTime> dateTimes = new ArrayList<>();
        dateTimes.add(dateTime);
        Movie newMovie = new Movie(name, synopsis, classificationUpdated, 
                                   releaseDateUpdated, director, 
                                   casts, this.getScreen(screen), dateTimes);

        this.sceneLoader.getSystem().addMovie(newMovie);

    }
    
    private Movie.Classification getClassification(String value) {  
        for (Movie.Classification name : Movie.Classification.values()) { 
            if (name.name().equals(value)) { 
                return name;
            }
        }
        return null;
    }
    
    private Movie.Screen getScreen(String value) { 
        for (Movie.Screen name : Movie.Screen.values()) { 
            if (name.name().equals(value)) 
                return name;
        }
        return null;
    }
}

