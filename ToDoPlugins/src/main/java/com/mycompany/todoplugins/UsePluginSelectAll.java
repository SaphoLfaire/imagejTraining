/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.todoplugins;

import javafx.scene.control.Button;
import org.scijava.plugin.Plugin;

/**
 *
 * @author Sapho
 */

@Plugin(type=PluginSelectAll.class, label = "")
public class UsePluginSelectAll implements PluginSelectAll {
    private String btnText;
    private boolean state;
    private Button select;

    @Override
    public void selectAll(String btnText, boolean state, Button select) {
        String oldValue = "Select all";
        String newValue = "Deselect all";
        
        
        if (btnText.equals(oldValue)){
            listView.getItems().forEach(task->task.setSelected(true));
            select.setText(newValue);
            
        }
        
        else if (btnText.equals(newValue)) {
            select.setText(oldValue);
            listView.getItems().forEach(task->task.setSelected(false));

                }
    }
    
}
