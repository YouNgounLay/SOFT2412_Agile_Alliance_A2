package fancycinema.component;
import java.util.*;

public class GiftCard {

    private String GCNumber;
    private boolean redeemable = true;
    private static List<GiftCard> GCList = new ArrayList<GiftCard>();
    private int amount = 10;


    public GiftCard(String GCNumber, int amount) {
        if (GCNumber == null || GCNumber.length() != 16) {
            System.out.println("Error, this is not a valid 16-digit number.");
            return;
        }
        this.GCNumber = GCNumber + "GC";
        GiftCard.GCList.add(this);
        if (amount == 10 || amount == 20 || amount == 50 || amount == 100) {
            this.amount = amount;
        }

    }

    public String getGCNumber() {
        return GCNumber;
    }

    public static List<GiftCard> getGCList() {
        return GCList;
    }

    public int getAmount() {
        return amount;
    }

    public int setAmount(int amount) {
        if (amount == 10 || amount == 20 || amount == 50 || amount == 100) {
            this.amount = amount;
            return this.amount;
        }
        this.amount = 10;
        return this.amount;
    }

    public boolean isRedeemable(){
        return this.redeemable;
    }

    public void checkRedeemable() {
        if (amount <= 0) {
            this.redeemable = false;
        }
        return;
    }

    public boolean redeemGC(int cost) {
        if (cost > this.amount) {
            return false;
        } else if (this.amount >= cost) {
            this.amount -= cost;
            this.checkRedeemable();
            return true;
        }
        return false;
    }




}