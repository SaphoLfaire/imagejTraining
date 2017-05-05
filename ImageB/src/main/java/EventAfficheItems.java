
import java.util.List;
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
public class EventAfficheItems extends SciJavaEvent{
   private final List <Item> listItems;
   
   public EventAfficheItems (List <Item> listItems){
       this.listItems = listItems;
   }
   
   public List <Item> getArray(){
       return this.listItems;
   }
   
    
}
