/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.todosci;


import java.util.List;
import java.util.stream.Collectors;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import org.scijava.event.EventService;
import org.scijava.plugin.Parameter;
import org.scijava.plugin.Plugin;
import org.scijava.service.AbstractService;
import org.scijava.service.SciJavaService;

/**
 *
 * @author Sapho
 */

@Plugin(type = SciJavaService.class, priority = 10)
public class DefaultDataService extends AbstractService implements CustomDataService  {
    
    
    @FXML
    ListView <Task2> listView;
    
    @FXML
    Label label;

    @FXML
    Image img ;
    
    
    
    @Parameter
    EventService eventService;
    
    
    public DefaultDataService() {
    super();
}
    
    
    
    @Override
    public void addT(String getText){
        
        if (getText != null && ! getText.trim().isEmpty()){
            Task2 task = new Task2(getText, false);
            eventService.publish(new EventListView (getText, task));
           
        }

    }
    
    
    @Override
    public void selectAll(String btnTxt) {
        String oldValue = "Select all";
        String newValue = "Deselect all";
        boolean state;
        
        if (btnTxt.equals(oldValue)){
            state = true;
            eventService.publish(new EventSelectTask (newValue, state));
            
        }
        
        else if (btnTxt.equals(newValue)) {
            state = false;
            eventService.publish(new EventSelectTask (oldValue, state));

                }
    }
    
    
    @FXML
    public void removeTask(){
          
        eventService.publish(new EventRemoveTask());
    }
}
