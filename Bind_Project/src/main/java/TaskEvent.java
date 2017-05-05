
import javafx.concurrent.Task;
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
public class TaskEvent extends SciJavaEvent{
    private final Task task;

    public TaskEvent(Task task) {
        this.task = task;
    }
    
    
    public Task getTask(){
        return this.task;
    }
    
}
