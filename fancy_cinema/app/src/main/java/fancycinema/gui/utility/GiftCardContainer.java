package fancycinema.gui.utility;

import fancycinema.component.GiftCard; 
import fancycinema.gui.SceneLoader;
import fancycinema.gui.scene.GiftCardScene;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.control.Button;

public class GiftCardContainer {
    private GiftCard giftCard;
    private SceneLoader sceneLoader;
    private GridPane container;
    private Paint textColor; 
    
    public GiftCardContainer(GiftCard giftCard, SceneLoader sceneLoader) { 
        this.giftCard = giftCard;
        this.sceneLoader = sceneLoader;
        this.textColor = giftCard.isRedeemable() ?  Color.BLACK : Color.GRAY;
        this.init();
    } 
    
    private void init() { 
        this.container = new GridPane();
        this.container.setVgap(10); 
        this.container.setHgap(150);
        this.container.setPadding(new Insets(25, 50, 25, 50));
        this.container.setStyle("-fx-text-fill: white;" +
        "-fx-background-color: lightgray;" + "-fx-border-color:     black;");
        
        Label giftCardNumber = new Label(giftCard.getGCNumber()); 
        giftCardNumber.setTextFill(this.textColor);
        Label giftCardAmount = new Label(Integer.toString(giftCard.getAmount()));
        giftCardAmount.setTextFill(this.textColor);
        
        Label removeCard = new Label("Remove Card"); 
        removeCard.setOnMouseClicked(mouseEvent -> { 
            this.sceneLoader.getSystem().removeGiftCard(this.giftCard); 
            this.sceneLoader.setScene(new GiftCardScene(sceneLoader));
        });
        Button removeButton = new Button("", removeCard);
        
        this.container.add(giftCardNumber, 0, 0);
        this.container.add(giftCardAmount, 0, 1);
        this.container.add(removeButton, 2, 0);

    }

    public GridPane getContainer() {
        return this.container; 
    }
}
