
import org.scijava.event.SciJavaEvent;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sapho
 */
public class TextFieldEvent extends SciJavaEvent{

    public TextFieldEvent() {
    }
    
    public boolean getState(){
        return true;
    }
}
