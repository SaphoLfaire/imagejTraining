
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.scijava.SciJava;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sapho
 */
public class Main extends Application{
    
    @Override
    public void start (Stage stage) throws Exception {
        
        SciJava scijava = new SciJava();
	ViewController controll = new ViewController();	
	scijava.context().inject(controll);
        
        
               
        Scene scene = new Scene (controll);                
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) throws IOException{
        launch(args);
    }
    
}
