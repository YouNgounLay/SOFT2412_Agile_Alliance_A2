package fancycinema.gui.scene;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fancycinema.component.Booking;
import fancycinema.component.Cinema;
import fancycinema.component.Movie;
import fancycinema.component.Session;
import fancycinema.gui.SceneLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class MovieDetailScene extends BaseScene {
    private Movie movie;
    private Movie.Screen screen;
    private Booking.SeatType seat;
    private Booking.PersonType personType;
    private List<Cinema> cinemas; 
    private Map<Session, Cinema> sessions;
    private Session bookedSession;
    private Label errorMessage;
    
    public MovieDetailScene(SceneLoader sceneLoader, Movie movie) {
        super(sceneLoader);
        this.movie = movie;
        this.cinemaInit();
        this.sessionInit();
        this.initialization();
    }
    
    private void initialization() {
        GridPane body = new GridPane();
        body.setVgap(15);
        body.setHgap(100);
        body.setPadding(new Insets(50, 50, 50, 50));
        body.setStyle("-fx-text-fill: white;" +
        "-fx-background-color: lightgray;" + "-fx-border-color:     black;");
        int yAxis = 0;
        
        // Adding title
        body.add(this.generateTitle(), 0, yAxis); 
        // Generate screen type  
        body.add(this.generateScreenType(), 5, yAxis++);
        // Generate classification
        body.add(this.generateClassification(), 0, yAxis);
        // Generate Seat Type
        body.add(this.generateSeatType(), 5, yAxis++);
        // Generate Person Type 
        body.add(this.generatePersonType(), 5, yAxis++);
        // Generate Session
        body.add(this.generateSessionTime(), 5, yAxis);
        // Generate Director
        body.add(new Text(movie.getDirector()), 0, yAxis++); 
        // Generate Casts
        body.add(this.generateCasts(), 0, yAxis++);
        // Generate Error message 
        errorMessage = new Label("Invalid Booking Request"); 
        errorMessage.setTextFill(Color.RED);
        errorMessage.setFont(Font.font("Tahoma", 20));
        errorMessage.setVisible(false); 
        body.add(errorMessage, 0, yAxis);
        
        // Generate Booking button
        Label booking = new Label("Booking"); 
        booking.setOnMouseClicked(mouseEvent -> {
            if (this.sceneLoader.hasUser()) { 
                this.operate();
            } else {
                this.sceneLoader.setScene(new SignUpScene(sceneLoader));
            }
        });
        body.add(new Button("", booking), 5, yAxis++);

        this.canvas.setCenter(new ScrollPane(body));
    }
    
    private void cinemaInit() {
        cinemas = new ArrayList<>(); 
        for (Cinema cinema : this.sceneLoader.getSystem().getCinemas().values()) {
            for (Movie movie : cinema.getAllMovies()) { 
                if (movie.getMovieName().equals(this.movie.getMovieName())){
                    cinemas.add(cinema);
                }
            }
        }
    }

    private void sessionInit() { 
        sessions = new HashMap<>(); 
        for (Cinema cinema : this.cinemas) { 
            for (Session session : cinema.getAllSessions()) { 
                sessions.put(session, cinema);
            }
        }
    }
    
    private Text generateTitle() { 
        Text title = new Text(movie.getMovieName());
        title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 50));
        return title;
    }
    
    private Text generateClassification() {
        Text classification = new Text(movie.getClassification().name());
        classification.setFont(Font.font("Tahoma", FontWeight.BOLD, 25));
        return classification;
    }   
    
    private VBox generateScreenType() { 
        final ComboBox<Label> comboBox = new ComboBox<>();
        comboBox.setPromptText("Screen Types");
        Label tmp = new Label(movie.getScreen().toString());
        tmp.setTextFill(Color.BLACK);
        comboBox.getItems().add(tmp);
        comboBox.setOnAction(event -> {
            for (Movie.Screen screenType : Movie.Screen.values()) {
                if (comboBox.getValue().getText().equals(screenType.name())) {
                    this.screen = screenType; 
                    break;
                }
            }
        });
        return new VBox(comboBox);
    }

    private VBox generateSeatType() {
        final ComboBox<Label> comboBox = new ComboBox<>();
        comboBox.setPromptText("Seat Type");
        for (Booking.SeatType seatType : Booking.SeatType.values()) {
            Label tmpSeat = new Label(seatType.name()); 
            tmpSeat.setTextFill(Color.BLACK);
            comboBox.getItems().add(tmpSeat);
        }
        comboBox.setOnAction(event -> {
            for (Booking.SeatType seatType : Booking.SeatType.values()) {
                if (comboBox.getValue().getText().equals(seatType.name())) {
                    this.seat = seatType; 
                    break;
                }
            }
        });
        return new VBox(comboBox);
    }

    private VBox generatePersonType() {
        final ComboBox<Label> comboBox = new ComboBox<>();
        comboBox.setPromptText("Person Type");
        for (Booking.PersonType personType : Booking.PersonType.values()) {
            Label tmpPersonType = new Label(personType.name()); 
            tmpPersonType.setTextFill(Color.BLACK);
            comboBox.getItems().add(tmpPersonType);
        }
        comboBox.setOnAction(event -> {
            for (Booking.PersonType personType : Booking.PersonType.values()) {
                if (comboBox.getValue().getText().equals(personType.name())) {
                    this.personType = personType; 
                    break;
                }
            }
        });
        return new VBox(comboBox);
    }
    
    private Text generateCasts() {
        Text casts = new Text("");
        for (String cast : movie.getCast()) { 
            casts.setText(casts.getText() + " " + cast);
        }
        return casts;
    }
    

    
    private VBox generateSessionTime() { 
        final ComboBox<Label> comboBox = new ComboBox<>();
        comboBox.setPromptText("Session");
        for (Session session : this.sessions.keySet()) {
            comboBox.getItems().add(
                new Label(session.getSessionTime().toString())
            );
        }
        
        comboBox.setOnAction(event -> { 
            for (Session session : this.sessions.keySet()) { 
                if (session.getSessionTime().toString().equals(comboBox.getValue().getText())) {
                    this.bookedSession = session; 
                    break;     
                }
            } 
        });
        
        return new VBox(comboBox);
    }
    
    private void operate() {
        if (this.personType != null && this.seat != null 
        &&  this.screen != null && this.bookedSession != null) {
            // this.sessions.get(bookedSession).addBooking(
            //     new Booking(this.bookedSession, this.personType, this.seat, this.sceneLoader.getSystem().getUser().getName())
            // );
            this.sceneLoader.getSystem().addBooking(
                this.sessions.get(bookedSession).getId(), 
                new Booking(this.bookedSession, this.personType, this.seat, this.sceneLoader.getSystem().getUser().getName()));
            BaseScene newScene = new MovieDetailScene(sceneLoader, movie);
            newScene.setNavBar(sceneLoader.getNavBar());
            this.sceneLoader.setScene(newScene);

        } else {
            this.errorMessage.setVisible(true);
        }
    }
    
}
