package fancycinema.gui.navbar;

import fancycinema.gui.SceneLoader;
import fancycinema.gui.scene.BaseScene;
import fancycinema.gui.scene.CartScene;
import fancycinema.gui.scene.MovieScene;
import fancycinema.gui.scene.ProfileScene;
import fancycinema.user.NotLoggedIn;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;


public class LoggedInNavBar extends NavBar{
    private String userName;
    public LoggedInNavBar(SceneLoader sceneLoader) {
        super(sceneLoader);
    }

    @Override
    public void initialization() {
        this.userName = sceneLoader.getSystem().getUserName();
        this.initLeftBar(); 
        this.initRightBar();
    }
    
    private void initLeftBar() {
        Label movies = new Label("Movies");
        movies.setOnMouseClicked(mouseEvent -> {
            BaseScene movieScene = new MovieScene(sceneLoader); 
            movieScene.setNavBar(sceneLoader.getNavBar());
            this.sceneLoader.setScene(movieScene);
        });
        this.leftBar.getMenus().add(new Menu("", movies));
    }

    private void initRightBar() { 
        Menu profile = new Menu(userName);
        MenuItem user = new MenuItem("Profile");
        user.setOnAction(event -> { 
            this.sceneLoader.setScene(new ProfileScene(sceneLoader));
        }); 
        
        MenuItem cart = new MenuItem("Cart");
        cart.setOnAction(event -> { 
            BaseScene newScene = new CartScene(sceneLoader); 
            newScene.setNavBar(sceneLoader.getNavBar()); 
            this.sceneLoader.setScene(newScene);
        });

        
        profile.getItems().addAll(
            user,
            cart
            );
        
        Label signOut = new Label("Sign Out");
        signOut.setOnMouseClicked(mouseEvent -> {
            this.sceneLoader.getSystem().setUser(new NotLoggedIn());
            this.sceneLoader.setScene(new MovieScene(this.sceneLoader));
        }); 
        
        this.rightBar.getMenus().addAll(
            profile,
            new Menu("", signOut)     
        );
    } 
}
