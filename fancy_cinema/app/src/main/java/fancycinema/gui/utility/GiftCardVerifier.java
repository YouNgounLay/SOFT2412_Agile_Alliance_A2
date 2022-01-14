package fancycinema.gui.utility;

import java.util.List;

import fancycinema.component.GiftCard;

public class GiftCardVerifier {
    private List<GiftCard> giftCards;
    private Integer[] validAmounts = { 0, 10, 20, 50, 100 };
    
    public GiftCardVerifier(List<GiftCard> giftCards) { 
        this.giftCards = giftCards;
    }
    
    public boolean isValidCard(String cardNumber, String amount) {
        if (cardNumber.length() != 16) {
            return false;
        }  
        
        try { 
            Long.valueOf(cardNumber);
            Integer.valueOf(amount);
        } catch (NumberFormatException e) { 
            return false; 
        }
         
        int count = validAmounts.length;
        for (Integer value : validAmounts) {
            if (value != Integer.valueOf(amount) ) 
                count--;
        }
        if (count == 0) 
            return false;
        
        for (GiftCard giftCard : giftCards) { 
            System.out.println(giftCard.getGCNumber());
            if (cardNumber.equals(giftCard.getGCNumber().substring(0, 16))) 
                return false;
        }

        return true; 
    }
    
}
