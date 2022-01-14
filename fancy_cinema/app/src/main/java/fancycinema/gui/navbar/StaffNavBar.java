package fancycinema.gui.navbar;


import fancycinema.component.Cinema;
import fancycinema.gui.SceneLoader;
import fancycinema.gui.scene.BaseScene;
import fancycinema.gui.scene.GiftCardScene;
import fancycinema.gui.scene.ManageMovieScene;
import fancycinema.gui.scene.ViewTotalBookingScene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;


public class StaffNavBar extends LoggedInNavBar{
    
    public StaffNavBar(SceneLoader sceneLoader) {
        super(sceneLoader);
        this.updateLeftBar();
    }

    private void updateLeftBar() { 
        Label giftCard = new Label("Gift Card"); 
        giftCard.setOnMouseClicked(mouseEvent -> {
            BaseScene giftCardScene = new GiftCardScene(sceneLoader);
            giftCardScene.setNavBar(this.sceneLoader.getNavBar());
            this.sceneLoader.setScene(giftCardScene);
        }); 
        
        Label reports = new Label("Reports"); 
        reports.setOnMouseClicked(mouseEvent -> {
            Cinema cinema = this.sceneLoader.getSystem().getCinemas().get(1);
            BaseScene newScene = new ViewTotalBookingScene(sceneLoader, cinema);
            newScene.setNavBar(this.sceneLoader.getNavBar());
            this.sceneLoader.setScene(newScene);
        });
        
        Label manageMovies = new Label("Manage Movies"); 
        manageMovies.setOnMouseClicked(mouseEvent -> {
            BaseScene manageMovieScene = new ManageMovieScene(sceneLoader);
            manageMovieScene.setNavBar(this.sceneLoader.getNavBar());
            this.sceneLoader.setScene(manageMovieScene);
        });
        
        this.leftBar.getMenus().addAll(
            new Menu("", giftCard), 
            new Menu("", reports),
            new Menu("", manageMovies)
        ); 
    }
}
