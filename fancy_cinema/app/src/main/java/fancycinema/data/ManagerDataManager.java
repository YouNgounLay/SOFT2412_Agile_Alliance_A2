package fancycinema.data;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

import fancycinema.user.Manager;

//ID,username,password
public class ManagerDataManager {
    private Manager manager;
    private final String path = "src/main/java/fancycinema/data/";

    public ManagerDataManager(){
        this.manager = readManager();
    }

    public Manager readManager() {

        this.manager = null;
        try {
            File f = new File(path + "Manager.txt");
            Scanner sc = new Scanner(f);

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] user = line.split(",");

                String ID = user[0];
                String username = user[1];
                String password = user[2];


                Manager manager = new Manager(ID, username, password);
                this.manager = manager;

            }

            sc.close();

            return this.manager;
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
            return null;
        }
    }

    public Manager writeManager() {
        try {
            FileWriter writer = new FileWriter(path + "Manager.txt", false);

            String ID = this.manager.getId();
            String username = this.manager.getName();
            String password = this.manager.getPassword();
            writer.write(ID + "," + username + "," + password + "\n");

            writer.close();

            return this.manager;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Manager getManager(String name, String password) {
        if (manager.getName().equals(name) && manager.getPassword().equals(password)) {
            return manager;
        }
        return null;
    }
    
    public void update() {
        this.writeManager();
    }
    
    public Manager loadManager() { 
        return this.manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public void removeManager(){
        this.manager = null;
    }
}