package fancycinema.user;

import fancycinema.component.Card;

public abstract class Customer extends User{
    protected String id;
    protected String password; 
    protected String cardName; 
    protected String cardNumber;
    protected Card card;
    
    public Customer(String name){
        super(name);
    }
    
    public Customer(String id, String userName, String password, String cardName, String cardNumber) {
        super(userName); 
        this.id = id; 
        this.password = password; 
        this.cardName = cardName; 
        this.cardNumber = cardNumber;     
        if ((cardName != null && cardNumber != null)) {
            this.card = new Card(cardName, cardNumber);
        }
    }

    public Customer(String id, String userName, String password) {
        super(userName); 
        this.id = id; 
        this.password = password;     
    }
    
    public String getId() { 
        return this.id;
    }
    
    public String getPassword() { 
        return this.password;
    }
    
    public String getCardName() { 
        return this.card.getCardName();
    }
    
    public String getCardNumber() { 
        return this.card.getCardNumber();
    }
    
    public void linkCard(Card card) {
        this.card = card;
    }

    public boolean hasCard() { 
        return card != null; 
    }
    
}
