/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.FileChooser;
import org.scijava.SciJava;
import org.scijava.plugin.Parameter;
import org.scijava.plugin.Plugin;
import org.scijava.plugin.PluginService;
import org.scijava.service.AbstractService;
import org.scijava.service.SciJavaService;
import org.scijava.service.Service;

/**
 * FXML Controller class
 *
 * @author sapho
 */
@Plugin(type = Service.class)
public class DefaultViewService extends AbstractService implements ViewService {
    
    List<ViewController> listControll;
    
    ViewController currentView;
    
    File picture;
    
    String name, email, tel;
    
    

    
    @Parameter
    PluginService pluginService;
    
    List<Contact> listContact = new ArrayList();
    
    /*
    ObservableList<Priority> observableList = FXCollections.observableArrayList(list)
    this.choice = new SimpleListProperty<Priority>(observableList);
*/

    @Override
    public void showView() {
        if (listControll == null) {
            listControll = pluginService.createInstancesOfType(ViewController.class);
            
            
            if (listControll.size() == 0) {
                System.out.println("No view found");
            } else {
                
                ViewController view = listControll.get(0);
                currentView = view;

                view.init();
                view.show();
                

            }

        }
    }

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    

    @Override
    public void setContact(String name, String email, String tel, File picture) {
        Contact contact = new Contact (name, email, tel, picture);
        this.listContact.add(contact);
        
        System.out.println("Contact enregistré avec succès =) ");
        System.out.println(name + email + tel);
        currentView.refresh();
        
        
    }

    @Override
    public void setSelected() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void setPicture(){
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Select a file");
        File file = chooser.showOpenDialog(null);
        this.picture = file;

    }
    
    public File getPicture(){
        return picture;
    }

    @Override
    public List<Contact> getList(){
        return this.listContact;
    }
    
    

    
}

