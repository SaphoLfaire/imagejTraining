/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package essai1;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 *
 * @author Sapho
 */
public class Task {
    String name;
    BooleanProperty selectedProperty = new SimpleBooleanProperty();
    public Behaviour comportement ;
    
    
    public Task (String name, boolean isSelected){
        comportement = new Red();
        comportement.color();
        this.name = name;
        selectedProperty.setValue (isSelected);
        //selectedProperty.addListener((obs, oldValue, newValue)->{
          //  System.out.println("Prout ");
        //});
      
    }
    
        
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public boolean isSelected (){
        return selectedProperty.getValue();
    }
    
    public void setSelected (boolean selected){
        selectedProperty.setValue(selected);
    }
    
    public BooleanProperty selectedProperty (){
        return selectedProperty;
    }
}
