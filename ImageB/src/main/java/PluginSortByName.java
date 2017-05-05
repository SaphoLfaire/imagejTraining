
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
@Plugin(type = InterfaceActionPlugin.class, label = "Sort by Name")
public class PluginSortByName implements InterfaceActionPlugin{
    @Parameter
    DataService dataService;
    
    
    
    @Override
    public void actionButton(Item item){
        
    }
}
