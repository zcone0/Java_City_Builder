package domainpackage.view;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Write a description of class MainScreen here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class MainScreen extends BorderPane
{
    // instance variables - replace the example below with your own
    private final BorderPane root;
    
    public MainScreen(){
        this.root = new BorderPane();
        this.root.setPadding(new Insets(15));

    }
    public void updateDisplay(){
       // TODO
    }
    public BorderPane getRoot() { return this.root;}
    
    /**
     * Alert dialog boxes
     */
    private void displayAlert(Alert.AlertType type, String title, String content) {
        /* === STUDENT IMPLEMENTATION === */  
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();            
        
    }
    
    
}