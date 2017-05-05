/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package essai1;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Sapho
 */
public class FXMLEssai1Controller extends GridPane {
    
    
    @FXML
    TextField textField;
    
    @FXML
    ListView <Task> listView;
    
    @FXML
    Label label;
    
    @FXML
    BooleanProperty  state = new SimpleBooleanProperty();
    
    @FXML
    Image img ;
    
    @FXML Button select ;
    
    
    
   
    
    public FXMLEssai1Controller() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("FXMLEssai1.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();
        
    }
        
      

   
   
    
    
    private ListCell<Task> createCell (ListView<Task> task){
    return new TaskListCell();    
    }
    
   
   
    @FXML
    /*
    add method is use for add a new task.
    It build with a condition if for check if the fieldtext is not empty,
    and as the textfield.getText() return a string, and a String is an object,
    the if conditon check if the getText is empty of string and empty of object. 
    */
    private void add(){
        if (textField.getText() != null && ! textField.getText().trim().isEmpty()){
            listView.getItems().add(new Task (textField.getText(), false));
            listView.setCellFactory(this::createCell);
            
            textField.setText("");
           
        }
                
    }
    
    @FXML
    private void selectAll() {
        String oldValue = "Select all";
        String newValue = "Deselect all";
        
        if (select.getText().equals(oldValue)){
            listView.getItems().forEach(task->task.setSelected(true));
            select.setText(newValue);
        }
        
        else if (select.getText().equals(newValue)) {
            select.setText(oldValue);
            listView.getItems().forEach(task->task.setSelected(false));

                }
    }
    
   
    
    @FXML
    /*
    removeTask permit to remove a task in the listView. For remove all 
    the selected task, a stream is use, with a filter and a collectors,
    for get only the task object selected. theses objects are stock in a
    list, and remove to the listView. 
    */
    private void removeTask(){
        List<Task> list = listView
                .getItems()
                .stream()
                .filter(x -> x.isSelected())
                .collect(Collectors.toList());
        
        listView.getItems().removeAll(list);        
    }

    
    private class TaskListCell extends ListCell<Task>{
        CheckBox checkbox = new CheckBox();
        public TaskListCell() {
            super();
            itemProperty().addListener(this::onItemChanged);
            
        }
        
        private void onItemChanged (ObservableValue obs, Task oldValue, Task newValue) {
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
