
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.scijava.Context;
import org.scijava.plugin.Parameter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sapho
 */
public class DefaultViewController extends GridPane implements ViewController{
    
    
    
    
    @Parameter
    static private Context context;
    
    @Parameter
    ViewService viewService;
    
    @FXML
    ListView listView;


    public DefaultViewController() throws IOException {
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/View.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();
        
        
        
    }
    
    @Override
    public void show() {

    }

    @Override
    public void showView() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public void refresh() {
        

       
      
    }


    @Override
    @FXML
    public void addContact() {
        
        Platform.runLater( () ->{
            Stage newStage = new Stage();
            try {
                AddContactWindowController addContactWindow = new AddContactWindowController();
                context.inject(addContactWindow);
                addContactWindow.init();
                Scene newScene = new Scene(addContactWindow);
                newStage.setScene(newScene);
                newStage.show();

            } catch (IOException ex) {
                Logger.getLogger(SuperViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
        
        
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
        /*
        List<Contact> contactList = viewService.getList();
        contactListProperty.set(FXCollections.observableArrayList(contactList));
        listView.itemsProperty().bind(contactListProperty);
*/
        //Apparemment c'est inutile mais il le faut quand même...

    }
    
}
