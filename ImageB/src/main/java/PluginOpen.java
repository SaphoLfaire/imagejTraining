
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@Plugin(type = InterfaceActionPlugin.class, label = "Open")
public class PluginOpen implements InterfaceActionPlugin{
    @Parameter
    DataService dataService;
    
    Item item;
    
    
    
    @Override
    public void actionButton(Item item){
        
        Runtime runtime = Runtime.getRuntime();
        String[] args = { "cmd.exe", "/C", item.getPath()};

        try {
            final Process process = runtime.exec(args);
            
        } catch (IOException ex) {
            Logger.getLogger(PluginOpen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
