package fancycinema.data;


import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;

import fancycinema.component.GiftCard;



//number,amount
public class GiftCardDataManager {
    private List<GiftCard> giftCards;
    private final String path = "src/main/java/fancycinema/data/";

    public GiftCardDataManager(){
        this.giftCards = readGiftCards(); 
    }

    public List<GiftCard> readGiftCards() {

        this.giftCards = new ArrayList<>();

        try {
            File f = new File(path + "GiftCards.txt");
            Scanner sc = new Scanner(f);

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] card = line.split(",");

                String number = card[0];
                String amount = card[1];

                GiftCard giftCard = new GiftCard(number, Integer.valueOf(amount));
                this.giftCards.add(giftCard);

            }

            sc.close();

            return this.giftCards;
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
            return null;
        }
    }

    public List<GiftCard> writeGiftCards() {
        try {
            FileWriter writer = new FileWriter(path + "GiftCards.txt", false);

            for(GiftCard giftCard : this.giftCards){
                String number = giftCard.getGCNumber().substring(0, 16);
                String amount = Integer.toString(giftCard.getAmount());
                writer.write(number + "," + amount + "\n");

            }

            writer.close();

            return this.giftCards;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<GiftCard> getGiftCards() {
        return giftCards;
    }

    public void update() { 
        this.writeGiftCards();
    }

    public void setGiftCards(List<GiftCard> giftCards) {
        this.giftCards = giftCards;
    }

    public GiftCard addGiftCard(GiftCard giftcard){
        this.giftCards.add(giftcard);
        this.writeGiftCards();
        return giftcard;
    }

    public GiftCard removeGiftCard(GiftCard giftCard){
        if (!this.giftCards.contains(giftCard)) {
            return null;
        }
        int index = this.giftCards.indexOf(giftCard);
        GiftCard removedGiftCard = giftCards.get(index);
        this.giftCards.remove(index);
        this.writeGiftCards();
        return removedGiftCard;
    }
}