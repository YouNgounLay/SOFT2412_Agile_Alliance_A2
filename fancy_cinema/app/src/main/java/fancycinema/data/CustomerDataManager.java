package fancycinema.data;

import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;

import fancycinema.component.Card;
import fancycinema.user.Customer;
import fancycinema.user.LoggedIn;

//ID,username,password,cardname,cardnumber
public class CustomerDataManager {
    private List<Customer> customers;
    private int customerID;
    private final String path = "src/main/java/fancycinema/data/";
    private CardDataManager cardDataManager; 

    public CustomerDataManager(){
        this.cardDataManager = new CardDataManager();
        this.customers = this.readCustomers();
    }

    public List<Customer> readCustomers() {

        this.customers = new ArrayList<>();
        try {
            File f = new File(path + "Customers.txt");
            Scanner sc = new Scanner(f);

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] user = line.split(",");

                String ID = user[0];
                this.customerID = Integer.valueOf(ID);
                String username = user[1];
                String password = user[2];
                Card card = cardDataManager.getCard(username); 
                String cardName, cardNumber; 
                if (card == null) { 
                    cardName = null; 
                    cardNumber = null;
                } else { 
                    cardName = card.getCardName();
                    cardNumber = card.getCardNumber();
                }
                
                Customer customer = new LoggedIn(ID, username, password, cardName, cardNumber);
                this.customers.add(customer);

            }

            sc.close();

            return this.customers;
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
            return null;
        }
    }

    public List<Customer> writeCustomers() {
        try {
            FileWriter writer = new FileWriter(path +"Customers.txt", false);

            for(Customer user : this.customers){
                String ID = user.getId();
                String username = user.getName();
                String password = user.getPassword();
                writer.write(ID + "," + username + "," + password + "\n");
            }

            writer.close();
            return null;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public void update() { 
        this.writeCustomers();
        this.cardDataManager.update(); 
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public Customer addCustomer(Customer customer){
        if (customer == null) { 
            return null;
        } else if (customers.contains(customer)) { 
            return null;
        }
        this.incrementID();
        this.customers.add(customer);
        this.writeCustomers();
        return customer;
    }
    
    public Customer getCustomer(String name, String password) {
        for (Customer customer : this.customers) {
            if (name.equals(customer.getName()) && password.equals(customer.getPassword())) {
                return customer;
            }
        }
        return null;
    }

    public Customer removeCustomer(Customer customer){
        if (customer == null) {
            return null;
        } else if (!customers.contains(customer)) {
            return null;
        }
        int index = customers.indexOf(customer);
        Customer removedCustomer = customers.get(index);
        this.customers.remove(customer);
        this.writeCustomers();
        return removedCustomer;
    }
    
    public int getID() { 
        return this.customerID; 
    }
    
    public void incrementID() {
        this.customerID++;
    }
    
    public void linkCard(String name, Card card) {
        for (Customer customer : customers) {
            if (name.equals(customer.getName())) {
                customer.linkCard(card);
                this.cardDataManager.getCards().put(name, card); 
                break;
            }
        }  
    }
    
    public void createCard(Customer user, Card card) { 
        user.linkCard(card); 
        if (this.cardDataManager.getCard(card.getCardName()) == null) {
            this.cardDataManager.addCard(card);
        }
        this.update(); 
    }
    
    public List<Card> getCreditCards() { 
        return this.cardDataManager.getListCards();
    }
    
}