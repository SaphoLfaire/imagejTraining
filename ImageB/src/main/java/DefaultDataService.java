
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import javafx.beans.value.ChangeListener;
import org.scijava.event.EventService;
import org.scijava.plugin.Parameter;
import org.scijava.plugin.Plugin;
import org.scijava.service.AbstractService;
import org.scijava.service.SciJavaService;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sapho
 */

@Plugin(type = SciJavaService.class, priority = 10)
public class DefaultDataService extends AbstractService implements DataService{
    
    @Parameter
    EventService eventService;
       
    String path, way;
    List <Item> listItems = new ArrayList();
    
    File[] files;
    
    public void afficheItems(Item item){
        
        if (path == null){
            path = "C:\\Users\\Sapho\\Pictures";
        }
        else {
            path = item.getPath();    
        }

        File folder = new File(path);
        files = folder.listFiles();
        updateItems();
    }
    
    
    @Override
    public void updateItems(){
        listItems.clear();
        for (File file : files ){
            Item item = new ItemInterface(file.getName(), file.length(), file.isDirectory(), file.getPath(), file.getParent());
            listItems.add(item);

        }
        eventService.publish(new EventAfficheItems(listItems));
  
    }
    @Override
    public void filterbyName(){
        
    }
    
    @Override
    public void comeBack(Item item){
        String path = item.getParent();
        System.out.println("path get parent"+path);
        File folder = new File(path);
        files = folder.listFiles();
        updateItems();
        
    }
    
}
