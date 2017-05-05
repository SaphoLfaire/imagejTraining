/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.todosci;

import org.scijava.event.SciJavaEvent;

/**
 *
 * @author Sapho
 */
public class EventSelectTask extends SciJavaEvent {
    private String value ;
    private boolean state;
    
    public EventSelectTask (String value, boolean state){
        this.value = value;
        this.state = state;
    }
    
    public String getValue(){
        return this.value;
    }
    
    public boolean getState(){
        return this.state;
    }
    
}
