/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.todoplugins;

import javafx.scene.control.Button;
import org.scijava.plugin.SciJavaPlugin;

/**
 *
 * @author Sapho
 */
public interface PluginSelectAll extends SciJavaPlugin {
    public void selectAll(String btnText, boolean state, Button select);
    
}
