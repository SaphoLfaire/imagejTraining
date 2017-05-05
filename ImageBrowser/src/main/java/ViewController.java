
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sapho
 */
public class ViewController extends GridPane {
    
    @FXML 
    TextField textField;
    
    
    
    
     public ViewController() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/View.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();
        
        
        
        
    }
    
}
