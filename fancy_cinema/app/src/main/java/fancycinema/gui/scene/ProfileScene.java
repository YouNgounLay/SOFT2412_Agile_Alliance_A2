package fancycinema.gui.scene;

import fancycinema.user.Customer;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import fancycinema.component.Booking;
import fancycinema.component.Card;
import fancycinema.gui.SceneLoader;
import fancycinema.gui.utility.BookingContainer;
import fancycinema.gui.utility.CreditCardVerifier;

public class ProfileScene extends CustomerScene {          
    private Customer user;
    private CreditCardVerifier verifier;
    private Label errorMessage;

    public ProfileScene(SceneLoader sceneLoader) {
        super(sceneLoader);
        this.user = sceneLoader.getSystem().getUser(); 
        this.navbar = sceneLoader.getNavBar();
        this.verifier = new CreditCardVerifier(user, sceneLoader.getSystem().getCreditCards());
        this.centerInit();
        this.leftInit();
    }
    
    private void centerInit() { 
        ScrollPane container = new ScrollPane();
        int yAxis = 0;
        GridPane body = new GridPane(); 
        body.setHgap(100); 
        body.setVgap(10); 
        body.setPadding(new Insets(25, 75, 25, 50));

        Text header = new Text("Welcome " + user.getName());  
        header.setFont(Font.font("Tahoma", 18));
        body.add(header, 0, yAxis++);
        bookingInit(body);
        
        container.setContent(body);
        this.canvas.setCenter(container);
    }
    
    private void leftInit() { 
        GridPane body = new GridPane();
        int yAxis = 0; 
        body.setVgap(10);
        
        Text header = new Text("Card Information");
        header.setFont(Font.font("Tahoma", 18)); 

        Text cardInfo = user.hasCard() 
            ? new Text("Card Number: " + user.getCardNumber()) 
            : new Text("Card Number: No Card");

        Text setCardHeader = new Text("Set New Card"); 
        setCardHeader.setFont(Font.font("Tahoma", 18)); 
        
        TextField cardNumber = new TextField();
        cardNumber.setPromptText("Card Number");
        TextField cardName = new TextField();
        cardName.setPromptText("Card Name");
        
        Label setCard = new Label("Set Card"); 
        errorMessage = new Label("");
        errorMessage.setTextFill(Color.RED);
        errorMessage.setFont(Font.font("Tahoma", 18)); 
        setCard.setOnMouseClicked(mouseEvent -> {
            
            Card card = new Card(cardName.getText(), cardNumber.getText());
            if (verifier.cardExists(card)) { 
                this.sceneLoader.getSystem().linkCreditCard(card);
                this.sceneLoader.setScene(new ProfileScene(sceneLoader));
            } else if (verifier.isValidCard(card)) { 
                this.sceneLoader.getSystem().addCreditCard(card);
                this.sceneLoader.setScene(new ProfileScene(sceneLoader));
            } else {
                errorMessage.setText("Invalid Card Info");
            }
        });
        
    
        body.add(header, 0, yAxis++);
        body.add(cardInfo, 0, yAxis++);
        body.add(setCardHeader, 0, yAxis++);
        body.add(cardName, 0, yAxis++);
        body.add(cardNumber, 0, yAxis++);
        body.add(errorMessage, 0, yAxis++);
        body.add(new Button("", setCard), 0, yAxis++);

        this.canvas.setLeft(body);
    }

    private void bookingInit(GridPane body) {  
        int yAxis = 1; 
        for (Booking booking : sceneLoader.getSystem().getBookings(user.getName())) {
            if (booking.isPaid()) { 
                BookingContainer container = new BookingContainer(booking, sceneLoader);
                body.add(container.getContainer(), 0, yAxis++);
            }
        }
    }
    
}

    
