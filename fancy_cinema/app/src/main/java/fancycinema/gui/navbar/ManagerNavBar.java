package fancycinema.gui.navbar;

import fancycinema.gui.SceneLoader;
import fancycinema.gui.scene.CancelledTransactionScene;
import fancycinema.gui.scene.ManageStaffScene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;

public class ManagerNavBar extends StaffNavBar {
    public ManagerNavBar(SceneLoader sceneLoader) {
        super(sceneLoader);
        this.updateLeftBar();
    }

    private void updateLeftBar() { 
        Label manageStaff = new Label("Manage Staff");  
        manageStaff.setOnMouseClicked(mouseEvent -> {
            this.sceneLoader.setScene(new ManageStaffScene(sceneLoader));
        });
        Label cancelledReport = new Label("Cancelled Report");
        cancelledReport.setOnMouseClicked(mouseEvent -> { 
            System.out.println("hello");
            this.sceneLoader.setScene(new CancelledTransactionScene(sceneLoader)); 
        });
        this.leftBar.getMenus().addAll(
            new Menu("", manageStaff),
            new Menu("", cancelledReport)
        );
    }
}
