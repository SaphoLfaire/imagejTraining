/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import org.scijava.plugin.Parameter;

/**
 * FXML Controller class
 *
 * @author sapho
 */
public class TilePaneController extends TilePane implements ViewController {
    
    @Parameter
    ViewService viewService;
    
    @FXML
    TilePane tilePane;
    
    ObservableList list;
    List<VBox> listTilePane;
    GridPane contactTilePane;

    @Override
    public void refresh() {
        tilePane.getChildren().clear();
        System.out.println("refresh tilePaneeuuuh !!!");
        List<Contact> contactList = viewService.getList();
        for (Contact contact : contactList){
            VBox contactVBox = createVBox(contact);
            tilePane.getChildren().add(contactVBox);
            
        }

    }
    
    public VBox createVBox(Contact item){
        VBox vBox = new VBox();
        ImageView imageIcon = new ImageView(item.getImage().toURI().toString()); 
        Label nameLabel = new Label(item.getName()); 
        Label telLabel = new Label(item.getTel()); 
        Label emailLabel = new Label(item.getEmail()); 
        
        imageIcon.setSmooth(true);
        imageIcon.setFitWidth(100);
        imageIcon.setPreserveRatio(true);
        
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(imageIcon, nameLabel, telLabel, emailLabel);
        
        return vBox;
    }

    @Override
    public void showView() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addContact() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setList(Contact contact) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeContact(Contact contact) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String Notify(String notify) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void init() {
    }

    @Override
    public void show() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public TilePaneController() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/TilePane.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();
        
        
        //Setting the orientation for the Tile Pane 
      tilePane.setOrientation(Orientation.HORIZONTAL); 
       
      //Setting the alignment for the Tile Pane 
      tilePane.setTileAlignment(Pos.CENTER_LEFT); 
       
      //Setting the preferred columns for the Tile Pane 
      tilePane.setPrefRows(4);  
      
      //Retrieving the observable list of the Tile Pane 
       
      
    }

    /**
     * Initializes the controller class.
     */
    
    
}
