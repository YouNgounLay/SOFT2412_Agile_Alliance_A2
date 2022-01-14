package fancycinema.gui.utility;


import fancycinema.component.Movie;
import fancycinema.gui.SceneLoader;
import fancycinema.gui.scene.BaseScene;
import fancycinema.gui.scene.ManageMovieScene;
import fancycinema.gui.scene.ModifyMovieScene;
import fancycinema.gui.scene.MovieDetailScene;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class MovieContainer {
    private Movie movie;
    private SceneLoader sceneLoader;
    private GridPane container;
    private Button removeButton;
    private Button modifyButton;
    
    public MovieContainer(Movie movie, SceneLoader sceneLoader) { 
        this.movie = movie; 
        this.sceneLoader = sceneLoader;
        this.init();
    }
     
    private void init() { 
        this.container = new GridPane();
        this.container.setHgap(150);
        this.container.setVgap(10);
        this.container.setPadding(new Insets(25, 50, 25, 50));
        this.container.setStyle("-fx-text-fill: white;" +
        "-fx-background-color: lightgray;" + "-fx-border-color:     black;");
        
        Label movieTitle = new Label(this.movie.getMovieName());
        Label classification = new Label(this.movie.getClassification().name());
        Label movieDetail = new Label("Movie Detail"); 
        movieDetail.setOnMouseClicked( mouseEvent -> {
            BaseScene movieDetailScene = new MovieDetailScene(sceneLoader, movie);
            movieDetailScene.setNavBar(sceneLoader.getNavBar()); 
            this.sceneLoader.setScene(movieDetailScene);
            
        });
        Label removeLabel = new Label("Remove Movie"); 
        removeLabel.setOnMouseClicked(mouseEvent -> { 
            this.sceneLoader.getSystem().removeMovie(this.movie);
            BaseScene newScene = new ManageMovieScene(sceneLoader);
            this.sceneLoader.setScene(newScene);
        });
        removeButton = new Button("", removeLabel);
        removeButton.setVisible(false);
        
        Label modifyLabel = new Label("Modify Movie"); 
        modifyLabel.setOnMouseClicked(mouseEvent -> { 
            BaseScene newScene = new ModifyMovieScene(this.sceneLoader, this.movie); 
            newScene.setNavBar(sceneLoader.getNavBar());
            this.sceneLoader.setScene(newScene);
        });
        modifyButton = new Button("", modifyLabel);
        modifyButton.setVisible(false);

        this.container.add(movieTitle, 0, 0);  
        this.container.add(new Button("", movieDetail), 4 , 0);
        this.container.add(modifyButton , 4, 1);
        this.container.add(removeButton , 4, 2);
        this.container.add(movieDetail, 1, 0);
        this.container.add(classification, 0, 1);
    } 
    
    public void isSuperUser(Boolean value) { 
        this.removeButton.setVisible(value);
        this.modifyButton.setVisible(value);
        this.container.setHgap(100);
    }
    
    public GridPane getContainer() { 
        return this.container;
    }
    
}
