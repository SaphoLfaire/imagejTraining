
import org.scijava.event.EventService;
import org.scijava.plugin.Parameter;
import org.scijava.plugin.Plugin;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sapho
 */
@Plugin(type = InterfaceActionPlugin.class, label = "Details")
public class PluginDetails implements InterfaceActionPlugin{
    @Parameter
    DataService dataService;
    
    @Parameter
    EventService eventService;
    
    
    
    @Override
    public void actionButton(Item item){
        eventService.publish(new EventActionDetails (item));
    }
}
