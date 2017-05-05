
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.scijava.SciJava;
import static javafx.application.Application.launch;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sapho
 */
public class Main {
    
    
    public Main() throws Exception {
        SciJava scijava = new SciJava();
        scijava.getContext().inject(this);
        scijava.get(ViewService.class).showView();
        
    }

    public static void main(String[] args) throws Exception {
        new Main();

    
    
    
        
    }
    
}
