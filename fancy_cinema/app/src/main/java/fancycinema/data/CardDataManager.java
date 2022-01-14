package fancycinema.data;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import fancycinema.component.Card;
import javafx.css.CssParser.ParseError;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class CardDataManager {
    private final String path = "src/main/java/fancycinema/data/";
    private Map<String, Card> validCards = new HashMap<String, Card>();
    
    public CardDataManager() { 
        try  {
            this.readCardFile();
        } catch( IOException i) { 
            System.err.println("IOError");
        } catch (ParseException p) {
            System.err.println("Error");
        }
    }
    
    public void addCard(Card card) { 
        if (validCards.containsKey(card.getCardName())) {
            this.validCards.get(card.getCardName()).setCardNumber(card.getCardNumber());
            return;
        }
        this.validCards.put(card.getCardName(), card);
        try { 
            this.writeCardFile();
        } catch (IOException io) {
            System.err.println("Io Error");
        } catch (ParseException e) { 
            System.err.println("Parse Error");
        }


    }

    public void readCardFile() throws IOException, ParseException {

        JSONArray creditCards = (JSONArray) new JSONParser().parse(new FileReader(path + "credit_cards.json"));

        for (Object card : creditCards) {
            JSONObject person = (JSONObject) card;
        
            String name = (String) person.get("name");
            String number = (String) person.get("number");

            this.validCards.put(name, new Card(name, number));
        }

    }

    public void writeCardFile() throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();

        Object obj = jsonParser.parse(new FileReader(path + "credit_cards.json"));
        JSONArray jsonArray = (JSONArray)obj;

        for (String key : validCards.keySet()) { 
            String name = validCards.get(key).getCardName();
            String number = validCards.get(key).getCardNumber();
            JSONObject newCard = new JSONObject();
            newCard.put("name", name);
            newCard.put("number", number);

            jsonArray.add(newCard);

        }
            FileWriter fw = new FileWriter(path + "credit_cards.json");
            fw.write(jsonArray.toJSONString());
            fw.flush();
            fw.close();
    }

    public boolean isValidCard(String cardNumber, String cardName) {
        if (cardNumber == null || cardName == null) {
            return false;
        }
        for (String number: this.validCards.keySet()) {
            if (cardNumber.equals(number)) {
                if (this.validCards.get(number).equals(cardName)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public Map<String, Card> getCards() { 
        return this.validCards;
    }
    
    public Card getCard(String cardName) {
        return this.validCards.get(cardName);
    }

    public void setValidCards(Map<String, Card> validCards) {
        this.validCards = validCards;
    }
}

