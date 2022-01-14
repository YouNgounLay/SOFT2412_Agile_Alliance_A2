package fancycinema.component;
public class Card {  
    private String cardName; 
    private String cardNumber; 

    public Card(String cardName, String cardNumber) { 
        this.cardName = cardName;
        this.cardNumber = cardNumber;
    }
    
    public String getCardName() { 
        return this.cardName; 
    }
    
    public String getCardNumber() { 
        return this.cardNumber;
    }

    public void setCardName(String cardName) { 
        this.cardName = cardName;
    }
    
    public void setCardNumber(String cardNumber) { 
        this.cardNumber = cardNumber;
    }

}

