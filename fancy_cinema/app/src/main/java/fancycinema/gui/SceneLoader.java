package fancycinema.gui;

import fancycinema.Fancy;
import fancycinema.gui.navbar.NavBar;
import fancycinema.gui.scene.BaseScene;
import fancycinema.gui.scene.MovieScene;
import javafx.scene.Scene; 
import javafx.stage.Stage;

public class SceneLoader {
    private BaseScene scene;
    private Stage stage;
    private Fancy userSystem;
    
    public SceneLoader(Stage stage, Fancy userSystem) { 
        this.userSystem = userSystem;
        this.scene = new MovieScene(this);
        this.stage = stage;
    }
    
    public Scene getScene() {
        return this.scene.getScene(); 
    }
     
    public void setScene(BaseScene scene) {
        this.scene = scene;  
        this.changeStage();
    }

    public NavBar getNavBar() { 
        return this.scene.getNavBar();
    }
    
    public boolean hasUser() { 
        return this.userSystem.hasUser();
    }
    
    public Fancy getSystem() { 
        return this.userSystem;
    }
    
    private void changeStage() { 
        this.stage.setScene(this.scene.getScene());
    }
    
} 
