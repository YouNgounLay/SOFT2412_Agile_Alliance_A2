package fancycinema.gui.scene;

import fancycinema.gui.navbar.GuestNavBar;
import fancycinema.gui.navbar.NavBar;
import fancycinema.gui.SceneLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class BaseScene {
    protected Scene scene;
    protected BorderPane canvas;
    protected NavBar navbar;
    protected SceneLoader sceneLoader;
    protected final double width = 960; 
    protected final double height = 800;
 
    public BaseScene(SceneLoader sceneLoader) {
        canvas = new BorderPane(); 
        this.sceneLoader = sceneLoader;
        this.navbar = new GuestNavBar(this.sceneLoader);
        canvas.setTop(navbar.getTopContainer());

        scene = new Scene(canvas, width, height);
    }
    
    public Scene getScene() {
        return this.scene;
    }
    
    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void setCanvas(BorderPane canvas) { 
        this.canvas = canvas;
    }

    public BorderPane getCanvas() { 
        return this.canvas;
    }
    
    public NavBar getNavBar() { 
        return this.navbar;
    }
     
    public void setNavBar(NavBar navbar) { 
        this.navbar = navbar;
        this.canvas.setTop(navbar.getTopContainer());
    }
}