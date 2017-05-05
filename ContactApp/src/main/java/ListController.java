/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import org.scijava.plugin.Parameter;

/**
 * FXML Controller class
 *
 * @author sapho
 */
public class ListController extends AnchorPane implements ViewController{
    
    @Parameter
    ViewService viewService;
    
    @FXML
    ListView listView;

    @Override
    public void refresh() {
        System.out.println("refresh !!!");
        List<Contact> contactList = viewService.getList();
        //File file = new File("file");
        //Contact e = new Contact("Sapho", "truc", "chouette", file);
        //contactList.add(e);
        listView.setCellFactory(lv -> new SimpleContactListCell());
        listView.getItems().clear();
        listView.getItems().setAll(contactList);    }

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

    public ListController() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/List.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();
    }

    /**
     * Initializes the controller class.
     */
    
    
}
