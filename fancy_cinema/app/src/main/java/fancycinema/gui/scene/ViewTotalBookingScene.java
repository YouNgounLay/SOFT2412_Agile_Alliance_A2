package fancycinema.gui.scene;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fancycinema.component.Cinema;
import fancycinema.component.Session;
import fancycinema.gui.SceneLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class ViewTotalBookingScene extends StaffScene{
    private final String path = "src/main/java/fancycinema/data/";
    public List<Session> allSessions;

    public ViewTotalBookingScene(SceneLoader sceneLoader, Cinema cinema){
        super(sceneLoader);
        this.allSessions = cinema.getAllSessions();
        this.initialization();

    }

    public void initialization(){
        GridPane bookingGrid = new GridPane();
        //loginGrid.setAlignment(Pos.CENTER); 
        bookingGrid.setPadding(new Insets(25, 25, 25, 25));
        bookingGrid.setHgap(10);  
        bookingGrid.setVgap(8);

        Label movieTitle = new Label("Movie, ");
        movieTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 25));
        bookingGrid.add(movieTitle, 0, 0);

        Label bookingNumber = new Label("no. bookings, ");
        bookingNumber.setFont(Font.font("Tahoma", FontWeight.NORMAL, 25));
        bookingGrid.add(bookingNumber, 1, 0);

        Label seatTaken = new Label("seats taken, ");
        seatTaken.setFont(Font.font("Tahoma", FontWeight.NORMAL, 25));
        bookingGrid.add(seatTaken, 2, 0);

        Label seatVacancies = new Label("seats vacant: ");
        seatVacancies.setFont(Font.font("Tahoma", FontWeight.NORMAL, 25));
        bookingGrid.add(seatVacancies, 3, 0);

        
        try(BufferedReader br = new BufferedReader(new FileReader(path + "sessions.txt"))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
        
            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            String everything = sb.toString();
            System.out.println(everything);

            Text allBookings = new Text(everything); 
            allBookings.setFont(Font.font("Tahoma", FontWeight.NORMAL, 10));
            bookingGrid.add(allBookings,0, 3);

        } catch (IOException e) {
            e.printStackTrace();
        }

        this.canvas.setCenter(bookingGrid);
    }
}
