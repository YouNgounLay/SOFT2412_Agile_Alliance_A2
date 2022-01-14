package fancycinema.gui.scene;

import fancycinema.gui.SceneLoader;
import fancycinema.gui.navbar.ManagerNavBar;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.layout.GridPane;


public class ManagerScene extends StaffScene {
    
    public ManagerScene(SceneLoader sceneLoader) {
        super(sceneLoader);
        this.setNavBar(new ManagerNavBar(sceneLoader));
        this.initialization();
    }
    
    private void initialization() { 
        GridPane userGrid = new GridPane();
        userGrid.setAlignment(Pos.CENTER); 
        userGrid.setHgap(10);  
        userGrid.setVgap(10);
        userGrid.setPadding(new Insets(25, 25, 25, 25));

        Label headerText = new Label("This is manager");
        headerText.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        userGrid.add(headerText, 0, 0);
        
        this.canvas.setCenter(userGrid);
    }
}
