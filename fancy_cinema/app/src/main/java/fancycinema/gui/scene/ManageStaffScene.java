package fancycinema.gui.scene;

import fancycinema.gui.SceneLoader;
import fancycinema.gui.utility.StaffContainer;
import fancycinema.gui.utility.StaffVerifier;
import fancycinema.user.Employee;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class ManageStaffScene extends ManagerScene {
    private StaffVerifier staffVerifier; 
    private Label errorMessage;

    public ManageStaffScene(SceneLoader sceneLoader) {
        super(sceneLoader);
        this.staffVerifier = new StaffVerifier(sceneLoader.getSystem().getUsers());
        this.init();
        this.sideBarInit();
    }
    
    private void init() { 
        // Initialze the scrolling panel
        ScrollPane staffsContainer = new ScrollPane();
        staffsContainer.setPadding(new Insets(25, 25, 75, 50));
         
        //Initialize Error Message
        this.errorMessage = new Label("Invalid User Name"); 
        this.errorMessage.setTextFill(Color.RED);
        this.errorMessage.setVisible(false);
        
        // Initialize the body
        GridPane body = new GridPane(); 
        body.setHgap(25);
        body.setVgap(10);
        
        this.staffInit(body);
        staffsContainer.setPrefSize(120, 120);
        staffsContainer.setContent(body);
        staffsContainer.setContent(body); 

        this.canvas.setCenter(staffsContainer);
    }

    private void staffInit(GridPane body) { 
        int yAxis = 0;
        for (Employee staff : this.sceneLoader.getSystem().getEmployees()) {
            StaffContainer staffContainer = new StaffContainer(staff, sceneLoader);
            body.add(staffContainer.getContainer(), 0, yAxis++);
        }
    }
    
    private void sideBarInit() {
        GridPane body = new GridPane();
        body.setVgap(15);

        TextField userName = new TextField(); 
        userName.setPromptText("Username");
        
        TextField passwordBox = new TextField();
        passwordBox.setPromptText("Password");
        
        Label addStaff = new Label("Add Staff"); 
        addStaff.setOnMouseClicked(mouseEvent -> {  
            String name = userName.getText();
            String password = passwordBox.getText();
            if (!staffVerifier.isValidStaff(name)) {
                this.errorMessage.setVisible(true);
            } else { 
                this.sceneLoader.getSystem().createEmployee(name, password); 
                this.sceneLoader.setScene(new ManageStaffScene(sceneLoader));
            }
        });

        body.add(userName, 0, 0); 
        body.add(passwordBox, 0, 1);  
        body.add(errorMessage, 0, 2);
        body.add(new Button("", addStaff), 0, 3);

        this.canvas.setRight(body);
    }
    
}
