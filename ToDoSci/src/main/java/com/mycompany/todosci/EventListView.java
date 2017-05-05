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
public class EventListView extends SciJavaEvent {
    private final String getText ;
    private final Task2 task;
    
    
    public EventListView (String getText, Task2 task){
        this.getText = getText;
        this.task = task;
        
        
    }
    
    public String getData(){
        return this.getText;
    }
    
    public Task2 getTask (){
        return task;
    }

    
}
