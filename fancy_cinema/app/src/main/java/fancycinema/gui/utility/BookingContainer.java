package fancycinema.gui.utility;

import fancycinema.component.Booking;
import fancycinema.gui.SceneLoader;
import fancycinema.gui.scene.CartScene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class BookingContainer {
    private SceneLoader sceneLoader;
    private Booking booking; 
    private GridPane container;
    
    public BookingContainer(Booking booking, SceneLoader sceneLoader) { 
       this.booking = booking; 
       this.sceneLoader = sceneLoader; 
       this.initialization();
    }
    
    private void initialization() {
        container = new GridPane();
        int yAxis = 0;
        container.setHgap(100);
        container.setVgap(10);
        
        Text movieName = new Text(booking.getMovie().getMovieName());
        movieName.setFont(Font.font("Tahoma", 15));
        
        Text personType = new Text(booking.getPersonType().name());
        personType.setFont(Font.font("Tahoma", 15));
        
        Text seatType = new Text(booking.getSeatType().name()); 
        seatType.setFont(Font.font("Tahoma", 15));
        
        Text sessionTime = new Text(booking.getSession().getSessionTime().toString()); 
        sessionTime.setFont(Font.font("Tahoma", 15));
        
        Label cancelled = new Label("Cancelled"); 
        cancelled.setOnMouseClicked(mouseEvent -> { 
            this.sceneLoader.getSystem().cancelBooking(booking);
            this.sceneLoader.setScene(new CartScene(sceneLoader));
        });
        Button cancelledButton = new Button("", cancelled);
        cancelledButton.setVisible(!booking.isPaid());
        
        container.add(movieName, 0, yAxis++);
        container.add(personType, 0, yAxis++);
        container.add(seatType, 0, yAxis++);
        container.add(sessionTime, 0, yAxis++);
        container.add(cancelledButton, 0, yAxis++);
    }
    
    public GridPane getContainer() { 
        return this.container;
    }
}
    
