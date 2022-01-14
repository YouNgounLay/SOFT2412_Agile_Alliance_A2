package fancycinema.gui.scene;

import fancycinema.component.GiftCard;
import fancycinema.gui.SceneLoader;
import fancycinema.gui.utility.GiftCardContainer;
import fancycinema.gui.utility.GiftCardVerifier;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class GiftCardScene extends BaseScene {
    private GiftCardVerifier giftCardVerifier; 
    private Label errorMessage;
    public GiftCardScene(SceneLoader sceneLoader) { 
        super(sceneLoader);
        this.navbar = sceneLoader.getNavBar();
        this.canvas.setTop(navbar.getTopContainer());  
        this.initialization(); 
        this.sideBarInit();
    }

    private void initialization() {
        this.giftCardVerifier = new GiftCardVerifier(sceneLoader.getSystem().getGiftCards());
        this.errorMessage = new Label("Invalid Information"); 
        this.errorMessage.setTextFill(Color.RED);
        this.errorMessage.setVisible(false);
        ScrollPane giftCardsContainer = new ScrollPane();
        giftCardsContainer.setPadding(new Insets(25, 25, 75, 50));
        GridPane body = new GridPane();
        body.setHgap(25);
        body.setVgap(10);

        this.giftCardInit(body);
        giftCardsContainer.setPrefSize(120, 120);
        giftCardsContainer.setContent(body);
        this.canvas.setCenter(giftCardsContainer); 
    }
    
    private void giftCardInit(GridPane body) {
        int yAxis = 0;
        for (GiftCard giftCard : this.sceneLoader.getSystem().getGiftCards()) {
            GiftCardContainer content = new GiftCardContainer(giftCard, sceneLoader);  
            body.add(content.getContainer(), 0, yAxis++);
        }
    }

    private void sideBarInit() { 
        GridPane body = new GridPane(); 
        body.setVgap(15);
         
        TextField cardNumberBox = new TextField();
        cardNumberBox.setPromptText("Card Number");
        body.add(cardNumberBox, 0, 0);
        
        TextField amountBox = new TextField();
        amountBox.setPromptText("Amount");
        body.add(amountBox, 0, 1);
        
        Label submitButton = new Label("Add New Card");
        submitButton.setOnMouseClicked(mouseEvent -> {   
            String cardNumber = cardNumberBox.getText();
            String amount = amountBox.getText();
            if (!giftCardVerifier.isValidCard(cardNumber, amount)) { 
                this.errorMessage.setVisible(true);
            } else {
                this.sceneLoader.getSystem().addGiftCard(new GiftCard(cardNumber, Integer.valueOf(amount)));
                this.sceneLoader.setScene(new GiftCardScene(sceneLoader));
            }
        });
        body.add(errorMessage, 0, 2);
        body.add(new Button("", submitButton), 0, 3);
        
        this.canvas.setRight(body);
    }
    
}
