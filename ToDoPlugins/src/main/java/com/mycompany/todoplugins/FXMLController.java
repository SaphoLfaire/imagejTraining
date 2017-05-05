package com.mycompany.todoplugins;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.io.IOException;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import org.scijava.Context;
import org.scijava.plugin.Parameter;

/**
 * FXML Controller class
 *
 * @author Sapho
 */
public class FXMLController extends GridPane {
    
    @FXML
    TextField textField;
    
    @FXML
    ListView <Task3> listView;
    
    @FXML
    Image img ;
    
    @FXML 
    Button select ;
    
    @Parameter
    UsePluginSelectAll pluginSelectAll;
    
    public PluginSelectAllGridPane(Context context){
        context.inject(this);
        pluginSelectAll.getPluginsOfTpe()
        
    }


    
     public FXMLController() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/FXML.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();
   
    }    
     
     
    private ListCell<Task3> createCell (ListView<Task3> task){
    return new TaskListCell();    
    }
    
     
     @FXML
    private void addT() {
        if (textField.getText() != null && ! textField.getText().trim().isEmpty()){
            listView.getItems().add(new Task3 (textField.getText(), false));
            listView.setCellFactory(this::createCell);
            
            textField.setText("");
        }
    }
    
    
    private class TaskListCell extends ListCell<Task3>{
        CheckBox checkbox = new CheckBox();
        public TaskListCell() {
            super();
            itemProperty().addListener(this::onItemChanged);
            
        }
        
        private void onItemChanged (ObservableValue obs, Task3 oldValue, Task3 newValue) {
            if (oldValue != null){
                oldValue.selectedProperty().unbindBidirectional(checkbox.selectedProperty());
            }
            
            if (newValue == null){
                setGraphic(null);
            }
            else {
                setGraphic(checkbox);
                newValue.selectedProperty().bindBidirectional(checkbox.selectedProperty());
                checkbox.setText(newValue.getName());
            }
            
            
        }
    }
}
