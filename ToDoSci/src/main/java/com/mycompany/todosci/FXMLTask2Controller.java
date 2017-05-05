/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.todosci;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import org.scijava.event.EventHandler;
import org.scijava.plugin.Parameter;

/**
 * FXML Controller class
 *
 * @author Sapho
 */
public class FXMLTask2Controller extends GridPane {

    @FXML
    TextField textField;
    
    
    @FXML Button select ;
    
    @FXML
    ListView <Task2> listView;
    
    @Parameter
    CustomDataService customDataService;
    
    
    
    
    
    public FXMLTask2Controller() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/FXMLTask2.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();
        //CustomDataService truc = new DefaultDataService ();
        
    }
        
    
    @FXML
    private void addT() {
        
        customDataService.addT(textField.getText());
        
     
    }
    
    @FXML
    private void selectAll(){
        customDataService.selectAll(select.getText());
        
    }
    
    @FXML
    private void removeTask(){
        customDataService.removeTask();
    }
    
    @EventHandler
    public void onDataEventAdded (EventListView event){
        Platform.runLater( () ->
                listView.getItems().add(event.getTask()));
                listView.setCellFactory(this::createCell);
                textField.setText("");
    }
    
    @EventHandler
    public void selectallTask (EventSelectTask event){
        
        Platform.runLater( () ->{
                listView.getItems().forEach(task->task.setSelected(event.getState()));
                select.setText(event.getValue());
        });
    }
    
    @EventHandler
    public void removeTask (EventRemoveTask event){
        System.out.println("Pouet");
        Platform.runLater( () ->{
            List<Task2> list = listView
                .getItems()
                .stream()
                .filter(x -> x.isSelected())
                .collect(Collectors.toList());
        
        listView.getItems().removeAll(list);
        });
    }
   
    private ListCell<Task2> createCell (ListView<Task2> task){
    return new FXMLTask2Controller.Task2ListCell();    
    }
        
    public class Task2ListCell extends ListCell<Task2>{
        CheckBox checkbox = new CheckBox();
        public Task2ListCell() {
            super();
            itemProperty().addListener(this::onItemChanged);
            
        }
        
        private void onItemChanged (ObservableValue obs, Task2 oldValue, Task2 newValue) {
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
