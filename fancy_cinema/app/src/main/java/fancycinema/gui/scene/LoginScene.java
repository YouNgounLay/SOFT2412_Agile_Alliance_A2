package fancycinema.gui.scene;

import fancycinema.gui.SceneLoader;
import fancycinema.gui.utility.LoginVerifier;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class LoginScene extends BaseScene { 
    private LoginVerifier loginVerifier; 
    private Label errorMessage = new Label("Invalid Login Information");
    public LoginScene(SceneLoader sceneLoader) {
        super(sceneLoader);
        this.initialization();
    } 
    
    private void initialization() {
        this.errorMessage.setVisible(false); 
        this.errorMessage.setTextFill(Color.RED);
        loginVerifier = new LoginVerifier(this.sceneLoader.getSystem().getCustomers(), 
                                         this.sceneLoader.getSystem().getEmployees(),
                                         this.sceneLoader.getSystem().loadManager());
        GridPane loginGrid = new GridPane();
        loginGrid.setAlignment(Pos.CENTER); 
        loginGrid.setHgap(10);  
        loginGrid.setVgap(10);
        loginGrid.setPadding(new Insets(25, 25, 25, 25));

        Text headerText = new Text("Login"); 
        headerText.setFont(Font.font("Tahoma", FontWeight.NORMAL, 25));
        loginGrid.add(headerText, 0, 0);
        
        Label userName = new Label("User Name: "); 
        loginGrid.add(userName, 0 , 1); 
        
        TextField userTextField = new TextField(); 
        loginGrid.add(userTextField, 1, 1); 

        Label pw = new Label("Password:");
        loginGrid.add(pw, 0, 2);

        PasswordField pwBox = new PasswordField();
        loginGrid.add(pwBox, 1, 2);
        
        Label submit = new Label("Login"); 
        submit.setFont(Font.font("Tahoma", FontWeight.NORMAL, 18));
        submit.setOnMouseClicked(mouseEvent -> {
            String name = userTextField.getText();
            String password = pwBox.getText();
            
            if (this.loginVerifier.hasCustomer(name, password)) {
                this.sceneLoader.getSystem().setUser(this.sceneLoader.getSystem().getCustomer(name, password));
                this.sceneLoader.setScene(new CustomerScene(sceneLoader));
            } else if (this.loginVerifier.hasStaff(name, password)) { 
                this.sceneLoader.getSystem().setStaff(this.sceneLoader.getSystem().getEmployee(name, password));
                this.sceneLoader.setScene(new StaffScene(sceneLoader));
            } else if (this.loginVerifier.hasManager(name, password)) { 
                this.sceneLoader.getSystem().setManager(this.sceneLoader.getSystem().getManager(name, password));
                this.sceneLoader.setScene(new ManagerScene(sceneLoader));
            }
            this.errorMessage.setVisible(true); 
        });
        loginGrid.add(this.errorMessage, 1, 3);
        Button submitButton = new Button("", submit);
        loginGrid.add(submitButton, 1, 4);
        this.canvas.setCenter(loginGrid);
    }
    
}