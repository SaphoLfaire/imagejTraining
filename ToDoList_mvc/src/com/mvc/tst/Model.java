/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mvc.tst;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 *
 * @author Sapho
 */
public class Model {
    String name;
    BooleanProperty selectedProperty = new SimpleBooleanProperty(); 
    
    public Model (String name, boolean isSelected){
        this.name = name;
        selectedProperty.setValue (isSelected);
      
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
