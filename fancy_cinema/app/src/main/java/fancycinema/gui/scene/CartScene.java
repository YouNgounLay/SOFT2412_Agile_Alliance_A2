package fancycinema.gui.scene;

import java.util.ArrayList;
import java.util.List;

import fancycinema.component.Booking;
import fancycinema.component.GiftCard;
import fancycinema.gui.SceneLoader;
import fancycinema.gui.utility.BookingContainer;
import fancycinema.user.Customer;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.geometry.Insets;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import javafx.scene.paint.Color;

import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class CartScene extends BaseScene { 
    private Customer user;
    private Label cardErrorMessage;
    private Label giftCardErrorMessage;
    private List<Booking> userBookings;

    public CartScene(SceneLoader sceneLoader) { 
        super(sceneLoader);
        this.user = sceneLoader.getSystem().getUser();
        this.setNavBar(sceneLoader.getNavBar());
        this.initialization();
        this.sideBarInit();
    }
    
    private void initialization() { 
        ScrollPane container = new ScrollPane(); 
        GridPane body = new GridPane();
        int yAxis = 0; 
        body.setHgap(150);
        body.setVgap(100);
        body.setPadding(new Insets(25, 75, 25, 50));

        Text header = new Text("This is your current cart:");  
        header.setFont(Font.font("Tahoma", 18));
        
        this.bookingInit(body); 

        body.add(header, 0, yAxis++);
        container.setContent(body); 
        this.canvas.setCenter(container);
    }
    
    private void sideBarInit() {
        GridPane body = new GridPane();
        int yAxis = 0;
        body.setVgap(10);
        
        Text header = new Text("Payment Method"); 
        header.setFont(Font.font("Tahoma", 15));
        
        Label cardPayment = new Label("Pay By Card");
        this.cardErrorMessage = new Label("");
        this.cardErrorMessage.setTextFill(Color.RED);
        cardPayment.setOnMouseClicked(mouseEvent -> { 
            if (this.user.hasCard()) { 
                this.payBooking(); 
                this.sceneLoader.setScene(new CartScene(sceneLoader));
            } else {
                this.cardErrorMessage.setText("No Card Information");
            }
        });
        
        TextField giftCardNumber = new TextField();
        giftCardNumber.setPromptText("Gift Card Number");
        
        Label giftCardPayment = new Label("Pay By GiftCard"); 
        this.giftCardErrorMessage = new Label("");
        this.giftCardErrorMessage.setTextFill(Color.RED);
        giftCardPayment.setOnMouseClicked(mouseEvent -> { 
            GiftCard giftCard = this.getGiftCard(giftCardNumber.getText());
            if (giftCard != null) { 
                giftCard.redeemGC(this.userBookings.size());
                this.payBooking(); 
                this.sceneLoader.setScene(new CartScene(sceneLoader));
            } else {
                this.giftCardErrorMessage.setText("Invalid GiftCard");
            }
        });
    
        body.add(header, 0, yAxis++);
        body.add(cardErrorMessage, 0, yAxis++);
        body.add(new Button("", cardPayment), 0, yAxis++);
        body.add(giftCardNumber, 0, yAxis++);
        body.add(giftCardErrorMessage, 0, yAxis++);
        body.add(new Button("",giftCardPayment), 0, yAxis++);

        this.canvas.setRight(body);
    }
    
    private void bookingInit(GridPane body) { 
       int yAxis = 1;
       userBookings = new ArrayList<>();
       for (Booking booking : sceneLoader.getSystem().getBookings(user.getName())) {
            if (!booking.isPaid()) { 
                userBookings.add(booking);
                BookingContainer container = new BookingContainer(booking, sceneLoader);
                body.add(container.getContainer(), 0, yAxis++);
            }
       }
    }
    
    private void payBooking() { 
        for (Booking booking : this.userBookings) { 
            booking.pay();
        }
    }
    
    private GiftCard getGiftCard(String cardNumber) {
        for (GiftCard giftCard : this.sceneLoader.getSystem().getGiftCards()) {
            if (giftCard.getGCNumber().equals(cardNumber) && giftCard.isRedeemable()) { 
                return giftCard;
            }
        }
        return null;
    }
    
}
