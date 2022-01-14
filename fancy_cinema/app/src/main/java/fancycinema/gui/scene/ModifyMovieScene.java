package fancycinema.gui.scene;

import fancycinema.gui.SceneLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.time.LocalDate;
import java.time.LocalDateTime;

import fancycinema.component.Cinema;
import fancycinema.component.Movie;
import fancycinema.component.Session;

public class ModifyMovieScene extends BaseScene {
    private Movie movie; 
    private TextField movieNameField; 
    private TextField synopsisField; 
    private DatePicker releaseDateField; 
    private Movie.Classification classificationField;
    private TextField directorField;
    private TextField addCastField, removeCastField;
    private Movie.Screen screenField;
    private DatePicker addUpComingTimeField;
    private TextField hourField, minuteField; 
    private Cinema addCinema;
    private int yAxis;
    
    public ModifyMovieScene(SceneLoader sceneLoader, Movie movie) { 
        super(sceneLoader); 
        this.movie = movie;
        this.initialization();
    }

    private void initialization() { 
        GridPane body = new GridPane();
        body.setVgap(10); 
        body.setHgap(50);
        this.movieNameInit(body); 
        this.synopsisInit(body);
        this.classificationInit(body); 
        this.releaseDateInit(body);
        this.directorInit(body);
        this.castInit(body);
        this.screenInit(body);
        this.upcomingTimeInit(body);  
        this.cinemaInit(body);
        
        Label done = new Label("Done"); 
        done.setOnMouseClicked(mouseEvent -> { this.operate(); }); 
        body.add(new Button("", done), 0, yAxis);
        this.canvas.setCenter(body);
    }
    
    private void movieNameInit(GridPane body) {     
        Text header = new Text(movie.getMovieName());
        header.setFont(Font.font("Tahoma", FontWeight.NORMAL, 10));
        
        movieNameField = new TextField();
        movieNameField.setPromptText("Enter Movie Name: ");
        body.add(header, 0, yAxis);
        body.add(movieNameField, 2, yAxis++);
    }
    
    private void synopsisInit(GridPane body) { 
        Text header = new Text(movie.getSynopsis());
        header.setFont(Font.font("Tahoma", FontWeight.NORMAL, 10));
        
        synopsisField = new TextField(); 
        synopsisField.setPromptText("Enter Synopsis: ");
        body.add(header, 0, yAxis);
        body.add(synopsisField, 2, yAxis++); 
    }
    
    private void classificationInit(GridPane body) { 
        Text header = new Text(movie.getClassification().name());
        header.setFont(Font.font("Tahoma", FontWeight.NORMAL, 10));
        
        final ComboBox<Label> comboBox = new ComboBox<>();   
        comboBox.setPromptText("Classification");
        // Adds in all available classifications
        for (Movie.Classification classification : Movie.Classification.values()) {
            comboBox.getItems().add(new Label(classification.name()));
        }
        // Implement event handling for combobox
        comboBox.setOnAction(event -> { 
            for (Movie.Classification classification : Movie.Classification.values()) { 
                if (comboBox.getValue().equals(classification.name())) {
                    this.classificationField = classification; 
                    break;
                }
            }
        });
        body.add(header, 0, yAxis);
        body.add(new VBox(comboBox), 2, yAxis++);
    }

    private void releaseDateInit(GridPane body) { 
        Text header = new Text(movie.getMovieName());
        header.setFont(Font.font("Tahoma", FontWeight.NORMAL, 10));
        
        releaseDateField = new DatePicker();
        body.add(header, 0, yAxis); 
        body.add(releaseDateField, 2, yAxis++);
    }
    
    private void directorInit(GridPane body) { 
        Text header = new Text(movie.getMovieName());
        header.setFont(Font.font("Tahoma", FontWeight.NORMAL, 10));
        
        directorField = new TextField();
        directorField.setPromptText("Enter Director: ");
        body.add(header, 0, yAxis);
        body.add(directorField, 2, yAxis++);
    }

    private void castInit(GridPane body) { 
        Text header = new Text(movie.getCast().toString());
        header.setFont(Font.font("Tahoma", FontWeight.NORMAL, 10));
        
        addCastField = new TextField(); 
        addCastField.setPromptText("Add Cast");
        
        removeCastField = new TextField();
        removeCastField.setPromptText("Remove Cast");
        
        body.add(header, 0, yAxis);
        body.add(addCastField, 2, yAxis);
        body.add(removeCastField, 3, yAxis++);
    }

    private void screenInit(GridPane body) { 
        Text header = new Text(movie.getScreen().name());
        header.setFont(Font.font("Tahoma", FontWeight.NORMAL, 10));
        
        final ComboBox<Label> comboBox = new ComboBox<>();
        comboBox.setPromptText("Screen");
        for (Movie.Screen screen : Movie.Screen.values()) { 
            comboBox.getItems().add(new Label(screen.name()));
        }
        comboBox.setOnAction(event -> {
            for (Movie.Screen screen : Movie.Screen.values()) { 
                if (screen.name().equals(comboBox.getValue().getText())) { 
                    this.screenField = screen;
                    break;
                }
            }
        });
        
        body.add(header, 0, yAxis);
        body.add(new VBox(comboBox), 2, yAxis++);
    }

    private void upcomingTimeInit(GridPane body) { 
        Text header = new Text("Upcoming Time");
        header.setFont(Font.font("Tahoma", FontWeight.NORMAL, 10));
    
        addUpComingTimeField = new DatePicker();
        addUpComingTimeField.setPromptText("Set Upcoming Times");
        hourField = new TextField();  
        hourField.setPromptText("Enter Hour: ");
        minuteField = new TextField();
        minuteField.setPromptText("Enter Minute: ");
        
        body.add(header, 0, yAxis); 
        body.add(addUpComingTimeField, 2, yAxis++);
        body.add(hourField, 2, yAxis++);
        body.add(minuteField, 2, yAxis++);
    }

    private void cinemaInit(GridPane body) {
        final ComboBox<Label> comboBox = new ComboBox<>();
        comboBox.setPromptText("Add Cinema");   
        for (Cinema cinema : sceneLoader.getSystem().getCinemas().values()) { 
            comboBox.getItems().add(new Label(Integer.toString(cinema.getId())));
        }
        comboBox.setOnAction(event -> { 
            this.addCinema = sceneLoader.getSystem().getCinemas().get(Integer.valueOf(comboBox.getValue().getText()));
        });
        body.add(new VBox(comboBox), 2, yAxis);
    }
    
    private void operate() { 
        if (!movieNameField.getText().equals("")) { 
            this.movie.setMovieName(movieNameField.getText());
        }
        
        if (!synopsisField.getText().equals("")) { 
            this.movie.setSynopsis(synopsisField.getText());
        }
        
        if (classificationField != null) {
            this.movie.setClassification(classificationField);
        }
         
        if (releaseDateField.getValue() != null) {
            this.movie.setReleaseDate(releaseDateField.getValue());
        }
        
        if (!directorField.equals("")) {
            this.movie.setDirector(directorField.getText());
        }
        
        if (!addCastField.equals("")) {
            this.movie.addCastMember(addCastField.getText());
        }
        
        if (!removeCastField.equals("")) {
            this.movie.removeCastMember(removeCastField.getText());
        }
        
        if (screenField != null) {
            this.movie.setScreen(screenField);
        }
        
        LocalDateTime dateTime; 
        if (addUpComingTimeField.getValue() != null && !hourField.getText().equals("") 
        && !minuteField.getText().equals("") && addCinema != null) {
            System.out.println("Yes");
            LocalDate date = addUpComingTimeField.getValue();
            int year = date.getYear(); 
            int month = date.getMonthValue();
            int day = date.getDayOfMonth();
            
            int hour = 0;
            if (isNumeric(hourField.getText())) { 
                hour = Integer.parseInt(hourField.getText());
            }
            
            int minute = 0; 
            if (isNumeric(minuteField.getText())) {
                minute = Integer.parseInt(minuteField.getText());
            }

            this.movie.addUpcomingTime(dateTime = LocalDateTime.of(year, month, day, hour, minute));
            this.addCinema.addMovie(this.movie);
            this.sceneLoader.getSystem().addSession(addCinema, new Session(this.movie, dateTime));
        }

        
        BaseScene newScene = new ModifyMovieScene(sceneLoader, movie);
        newScene.setNavBar(navbar);
        this.sceneLoader.getSystem().update();
        this.sceneLoader.setScene(newScene);
    }
    
    private boolean isNumeric(String line) {
        try {
            Integer.parseInt(line);
        } catch (NumberFormatException e) { 
            return false; 
        }
        return true;
    }
}
