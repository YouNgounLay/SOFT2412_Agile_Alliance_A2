package fancycinema.gui.scene;

import fancycinema.component.Movie;
import fancycinema.gui.SceneLoader;
import fancycinema.gui.utility.MovieContainer;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;

public class MovieScene extends BaseScene {
    public MovieScene(SceneLoader sceneLoader) { 
        super(sceneLoader); 
        this.initialization();
    }
    
    private void initialization() { 
        ScrollPane moviesContainer = new ScrollPane(); 
        moviesContainer.setPadding(new Insets(25, 25, 75, 50));
        GridPane body = new GridPane();
        body.setHgap(25);
        body.setVgap(10);

        this.movieInit(body);
        moviesContainer.setPrefSize(120, 120);
        moviesContainer.setContent(body);
        this.canvas.setCenter(moviesContainer); 
    }
    
    private void movieInit(GridPane body) {
        int yAxis = 0;
        for (Movie movie : this.sceneLoader.getSystem().getMovies()) { 
            MovieContainer content = new MovieContainer(movie, sceneLoader);
            body.add(content.getContainer(), 0, yAxis++);
        }
    }
    
}
