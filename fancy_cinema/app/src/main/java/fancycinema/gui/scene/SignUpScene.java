package fancycinema.gui.scene;


import fancycinema.gui.SceneLoader;
import fancycinema.gui.navbar.GuestNavBar;
import fancycinema.gui.utility.SignUpVerifier;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class SignUpScene extends BaseScene {    
    private Label errorMessage; 
    private SignUpVerifier signUpVerifier;
    public SignUpScene(SceneLoader sceneLoader) {
        super(sceneLoader); 
        this.setNavBar(new GuestNavBar(sceneLoader));
        this.initialization();
    }
    
    private void initialization() {

        this.signUpVerifier = new SignUpVerifier(sceneLoader.getSystem().getUsers());
        this.errorMessage = new Label("Invalid Information");
        this.errorMessage.setTextFill(Color.RED); 
        this.errorMessage.setVisible(false);

        GridPane signUpGrid = new GridPane(); 
        signUpGrid.setAlignment(Pos.CENTER);
        signUpGrid.setHgap(10);
        signUpGrid.setVgap(10);
        signUpGrid.setPadding(new Insets(25, 25, 25, 25));
        
        Text headerText = new Text("Sign Up"); 
        headerText.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        signUpGrid.add(headerText, 0, 0);
         
        Label userName = new Label("User Name: "); 
        signUpGrid.add(userName, 0, 1);
        
        TextField userTextField = new TextField();
        signUpGrid.add(userTextField, 1, 1);
        
        Label pw = new Label("Password: ");
        signUpGrid.add(pw, 0, 2);
        
        PasswordField pwBox = new PasswordField();
        signUpGrid.add(pwBox, 1, 2); 

        Label confirmedPw = new Label("Confirmed Password: ");
        signUpGrid.add(confirmedPw, 0, 3); 
        
        TextField confirmedPwBox = new TextField(); 
        signUpGrid.add(confirmedPwBox, 1, 3);

        signUpGrid.add(this.errorMessage, 1, 4);
        Label submit = new Label("Sign Up");  
        submit.setOnMouseClicked(mouseEvent -> { 
            String name = userTextField.getText(); 
            String password = pwBox.getText();
            if (!signUpVerifier.verifyUserName(name)) { 
                this.errorMessage.setVisible(true);
            } else if (!signUpVerifier.verifyPassword(password, confirmedPwBox.getText())) {
                this.errorMessage.setVisible(true);
            } else { 
                this.sceneLoader.getSystem().createCustomer(name, password);
                this.sceneLoader.setScene(new CustomerScene(sceneLoader));
            }
        });
        Button submitButton = new Button("", submit);
        signUpGrid.add(submitButton, 1, 5); 
        this.canvas.setCenter(signUpGrid);
    }

    
}
