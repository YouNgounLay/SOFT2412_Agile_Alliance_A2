package fancycinema.data;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

import fancycinema.component.CancelledTransaction;


//ID,time,reason
public class CancelledTransactionDataManager{
    private List<CancelledTransaction> cancelledTransactions;
    private final String path = "src/main/java/fancycinema/data/";

    public CancelledTransactionDataManager(){
        this.cancelledTransactions =  this.readCancelledTransactions();
    }

    public List<CancelledTransaction> readCancelledTransactions() {

        this.cancelledTransactions = new ArrayList<>();

        try {
            File f = new File(path+ "CancelledTransactions.txt");
            Scanner sc = new Scanner(f);

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] transaction = line.split(",");

                String ID = transaction[0];
                String time = transaction[1];
                String[] timeFormat = time.split("-");
                int year = Integer.valueOf(timeFormat[0]); 
                int month = Integer.valueOf(timeFormat[1]);
                String[] timeFormat2 = timeFormat[3].split("T");
                int day = Integer.valueOf(timeFormat2[0]);
                String[] timeFormat3 = timeFormat2[1].split(":");
                int hour = Integer.valueOf(timeFormat3[0]);
                int minute = Integer.valueOf(timeFormat3[1]);
                
                LocalDateTime dateTime = LocalDateTime.of(year, month, day, hour, minute);
                String reason = transaction[2];


                CancelledTransaction cancelledTransaction = new CancelledTransaction(ID,dateTime, reason);
                this.cancelledTransactions.add(cancelledTransaction);

            }

            sc.close();

            return this.cancelledTransactions;
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
            return null;
        }
    }

    public List<CancelledTransaction> writeCancelledTransactions() {
        try {
            FileWriter writer = new FileWriter(path + "CancelledTransactions.txt", false);

            for(CancelledTransaction cancelledTransaction : this.cancelledTransactions){
                String ID = cancelledTransaction.getCustomerID();
                String time = cancelledTransaction.getTime().toString();
                String reason = cancelledTransaction.getReason();
                writer.write(ID + "," + time + "," + reason + "\n");
            }

            writer.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public CancelledTransaction addCancelledTransaction(CancelledTransaction cancelledTransaction){
        if (cancelledTransaction == null) { 
            return null;
        }
        this.cancelledTransactions.add(cancelledTransaction);
        return cancelledTransaction;
    }

    public CancelledTransaction removeCancelledTransaction(CancelledTransaction cancelledTransaction){
        if (cancelledTransaction == null) { 
            return null;
        } else if (!cancelledTransactions.contains(cancelledTransaction)) { 
            return null;
        }
        int index = cancelledTransactions.indexOf(cancelledTransaction); 
        CancelledTransaction removedTransaction = cancelledTransactions.get(index);
        this.cancelledTransactions.remove(cancelledTransaction);
        return removedTransaction;
    }

    public List<CancelledTransaction> getCancelledTransactions() {
        return cancelledTransactions;
    }

    public void setCancelledTransactions(List<CancelledTransaction> cancelledTransactions) {
        this.cancelledTransactions = cancelledTransactions;
    }
}