package fancycinema.gui.navbar;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.control.MenuBar;
import fancycinema.gui.SceneLoader;

public abstract class NavBar {
    protected HBox container; 
    protected MenuBar leftBar; 
    protected MenuBar rightBar; 
    protected Region filler;
    protected SceneLoader sceneLoader;    

    public NavBar(SceneLoader sceneLoader) {
        this.container = new HBox();  
        this.leftBar = new MenuBar(); 
        this.rightBar = new MenuBar(); 
        this.filler = new Region();
        HBox.setHgrow(this.filler, Priority.ALWAYS); 
        this.container.getChildren().addAll(leftBar, filler, rightBar);
        this.sceneLoader = sceneLoader; 
        this.initialization();        
    }
    
    public abstract void initialization();
    
    public HBox getTopContainer() {
        return this.container;
    }
}     
