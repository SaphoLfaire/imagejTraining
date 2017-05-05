
import java.io.File;
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
public class FileEvent extends SciJavaEvent{
    
    private final File file;

    public FileEvent(File file) {
        this.file = file;
    }
    
    public File getFile(){
        return this.file;
    }
}
