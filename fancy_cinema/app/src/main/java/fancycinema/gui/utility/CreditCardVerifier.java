package fancycinema.gui.utility;

import java.util.List;


import fancycinema.component.Card;
import fancycinema.user.Customer;

public class CreditCardVerifier {   
    private Customer user;
    private List<Card> cards; 
    
    public CreditCardVerifier(Customer user, List<Card> cards) { 
        this.user = user;
        this.cards = cards;
    }

    public boolean isValidCard(Card card) {
        
        String name = card.getCardName(); 
        String cardNumber = card.getCardNumber();
        
        // Check if the card name matched the user
        if (!name.equals(user.getName())) { 
            return false;
        } 
        
        
        // Check if the provided number is valid
        int number;
        try {
            number = Integer.parseInt(cardNumber);
        } catch (NumberFormatException e) {
            return false;
        }
        
        if (number < 10000 || number > 99999){
            return false;
        }

        // Check if the card number belongs to another user
        for (Card c: cards) { 
            if (c.getCardNumber().equals(cardNumber) && !c.getCardName().equals(name)) {
                return false;
            }
        }
        
        return true;
    }

    public boolean cardExists(Card card) { 
        String name = card.getCardName(); 
        String cardNumber = card.getCardNumber();
        for (Card c: cards) { 
            if (c.getCardNumber().equals(cardNumber) && c.getCardName().equals(name)) {
                return true;
            }
        }
        return false;  
    }
}
