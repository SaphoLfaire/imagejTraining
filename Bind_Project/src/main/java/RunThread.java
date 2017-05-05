
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.concurrent.Task;
import org.scijava.event.EventService;
import org.scijava.plugin.Parameter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sapho
 */
public class RunThread extends Task<Void>{
    
    @Parameter
    EventService eventService;
    
    
    public RunThread (){
        super();
    }
    
    @Override 
        public Void call() throws InterruptedException {
            int num = 0;
            
        for ( num =0; num <= 10; num++){
            
            Thread.sleep(150);
            
            num++;
            this.updateMessage("Running ");
            this.updateProgress(num, 10);
            
        }
        this.updateMessage("Done !");
            
         
        return null;
        
        }
        
        
    
}
