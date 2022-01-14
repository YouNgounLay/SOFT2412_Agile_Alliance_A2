package fancycinema.gui.utility;

import fancycinema.gui.SceneLoader;
import fancycinema.gui.scene.ManageStaffScene;
import fancycinema.user.Employee;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class StaffContainer {

    private Employee staff; 
    private GridPane container;
    private SceneLoader sceneLoader; 

    public StaffContainer(Employee staff, SceneLoader sceneLoader) {  
        this.staff = staff; 
        this.sceneLoader = sceneLoader;
        this.init(); 
    }

    public GridPane getContainer() {
        return this.container;
    }

    private void init() {
        // Initialize Container
        this.container = new GridPane();
        this.container.setHgap(150); 
        this.container.setVgap(10);
        this.container.setPadding(new Insets(25, 50, 25, 50));
        this.container.setStyle("-fx-text-fill: white;" +
        "-fx-background-color: lightgray;" + "-fx-border-color:     black;");
        
        // Initialize Container components  
        // Staff Information 
        Label userName = new Label(this.staff.getName() + " | " + this.staff.getPassword()); 
        Label id = new Label(this.staff.getId());

        // Button to remove staff 
        Label removeButton = new Label("Remove Staff");
        removeButton.setOnMouseClicked(mouseEvent -> {
            this.sceneLoader.getSystem().removeEmployee(staff);
            this.sceneLoader.setScene(new ManageStaffScene(sceneLoader));
        });
        
        // Adds all componenets into the container 
        this.container.add(userName, 0, 0);  
        this.container.add(new Button("", removeButton), 3, 0); 
        this.container.add(id, 0, 1);
    }

    
}
