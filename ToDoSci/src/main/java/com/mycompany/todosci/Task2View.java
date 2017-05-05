/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.todosci;

import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.scijava.SciJava;

/**
 *
 * @author Sapho
 */
public class Task2View extends Application{
    
    @Override
    public void start (Stage stage) throws Exception {
        
        SciJava scijava = new SciJava();
	FXMLTask2Controller controll = new FXMLTask2Controller();	
	scijava.context().inject(controll);
        
        
               
        Scene scene = new Scene (controll);                
        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String[] args) throws IOException{
        launch(args);
        
    }
    
}
