package fancycinema.gui.navbar;

import fancycinema.gui.SceneLoader; 
import fancycinema.gui.scene.LoginScene;
import fancycinema.gui.scene.SignUpScene;
import fancycinema.gui.scene.MovieScene; 


import javafx.scene.control.Label;
import javafx.scene.control.Menu;

public class GuestNavBar extends NavBar {   
    public GuestNavBar(SceneLoader sceneLoader) {
        super(sceneLoader);
    }

    @Override
    public void initialization() {
        // Left Menu Bar 
        this.initLeftBar();
        // Rigth Menu Bar
        this.initRightBar();
    }
    
    private void initLeftBar() {
        Label movies = new Label("Movies");
        movies.setOnMouseClicked(mouseEvent -> {
            this.sceneLoader.setScene(new MovieScene(this.sceneLoader));
        });
        this.leftBar.getMenus().add(new Menu("", movies));
    }
    
    private void initRightBar() { 
        Label login = new Label("Login"); 
        login.setOnMouseClicked(mouseEvent -> {
            this.sceneLoader.setScene(new LoginScene(this.sceneLoader));
        });
        
        Label signUp = new Label("Sign Up");
        signUp.setOnMouseClicked(mouseEvent -> {
            this.sceneLoader.setScene(new SignUpScene(this.sceneLoader));
        }); 
        
        this.rightBar.getMenus().addAll(
            new Menu("", login), 
            new Menu("", signUp)     
        );
    } 
    

}

