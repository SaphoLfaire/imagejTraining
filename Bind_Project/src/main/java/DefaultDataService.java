
import java.io.File;
import javafx.concurrent.Task;
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
 * @author sapho
 */

@Plugin(type = SciJavaService.class, priority = 10)
public class DefaultDataService extends AbstractService implements DataService{
    
    
    @Parameter
    EventService eventService;
    
    private File file;
    
    
    public void setCurrentFile(File file) {

        if (file.isDirectory() != false) {
            throw new IllegalArgumentException("The input must be a folder !");
        } else {
            this.file = file;
            eventService.publish(new FileEvent(file));
        }

}
    
    public void setCurrentTextField(){
        eventService.publish(new TextFieldEvent());
    }
    
    public Task<Void> runTask(RunThread task){
        
        eventService.publish(new TaskEvent(task));
        new Thread(task).start();
        return task;
    }
    
    public Task<Void> cancelTask(RunThread task){
        task.cancel();
        return task;
    }
    
    

}
