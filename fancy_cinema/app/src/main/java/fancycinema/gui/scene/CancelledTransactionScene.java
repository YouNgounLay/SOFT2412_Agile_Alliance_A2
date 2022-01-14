package fancycinema.gui.scene;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import fancycinema.gui.SceneLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Philip Task
 */
public class CancelledTransactionScene extends ManagerScene {
    private final String path = "src/main/java/fancycinema/data/";

    public CancelledTransactionScene(SceneLoader sceneLoader) { 
        super(sceneLoader);
        this.initialization(); 
    }
    

    public void initialization() {
        GridPane transactionGrid = new GridPane();
        //loginGrid.setAlignment(Pos.CENTER); 
        transactionGrid.setPadding(new Insets(25, 25, 25, 25));
        transactionGrid.setHgap(10);  
        transactionGrid.setVgap(8);

        Label transactionTitle = new Label("Transaction, ");
        transactionTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 25));
        transactionGrid.add(transactionTitle, 0, 0);

        Label transactionTime = new Label("Date Time, ");
        transactionTime.setFont(Font.font("Tahoma", FontWeight.NORMAL, 25));
        transactionGrid.add(transactionTime, 1, 0);

        Label transactionReason = new Label("Reason");
        transactionReason.setFont(Font.font("Tahoma", FontWeight.NORMAL, 25));
        transactionGrid.add(transactionReason, 2, 0);

        
        try(BufferedReader br = new BufferedReader(new FileReader(path + "CancelledTransactions.txt"))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
        
            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            String everything = sb.toString();
            System.out.println(everything);

            Text cancelledTransactions = new Text(everything); 
            cancelledTransactions.setFont(Font.font("Tahoma", FontWeight.NORMAL, 10));
            transactionGrid.add(cancelledTransactions,0, 3);

        } catch (IOException e) {
            e.printStackTrace();
        }

        this.canvas.setCenter(transactionGrid);
    }
}
