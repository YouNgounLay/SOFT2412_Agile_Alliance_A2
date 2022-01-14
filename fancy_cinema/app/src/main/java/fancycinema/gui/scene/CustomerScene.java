package fancycinema.gui.scene;


import fancycinema.gui.SceneLoader;
import fancycinema.gui.navbar.LoggedInNavBar;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.layout.GridPane;

public class CustomerScene extends BaseScene {  

    public CustomerScene(SceneLoader sceneLoader) { 
        super(sceneLoader); 
        this.setNavBar(new LoggedInNavBar(this.sceneLoader));
        this.initialization();
    }
    
    private void initialization() {
        GridPane userGrid = new GridPane();
        userGrid.setAlignment(Pos.CENTER); 
        userGrid.setHgap(10);  
        userGrid.setVgap(10);
        userGrid.setPadding(new Insets(25, 25, 25, 25));

        Label headerText = new Label("Welcome User");
        headerText.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        userGrid.add(headerText, 0, 0);
        
        this.canvas.setCenter(userGrid);
    }
}
