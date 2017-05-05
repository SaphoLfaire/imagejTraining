
import java.io.File;
import javafx.concurrent.Task;
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
public interface DataService extends SciJavaService{
    void setCurrentFile(File file);
    void setCurrentTextField();
    Task<Void> runTask(RunThread task);
    Task<Void> cancelTask (RunThread task);
}
