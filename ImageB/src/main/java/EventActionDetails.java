
import org.scijava.event.SciJavaEvent;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sapho
 */
public class EventActionDetails extends SciJavaEvent {
    
    
    private final Item item;
    
    
    public EventActionDetails (Item item){
        this.item = item;
    }
    
    public String setDetails() {
        String cast = Float.toString(item.getSize());
        String[] array;
        array = new String[] {item.getName(), item.getPath(), cast};
        String text = String.join("\n", array);
        return text;
    }
    
}
