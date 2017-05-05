
import java.io.File;
import java.nio.file.Files;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sapho
 */
public class ItemInterface implements Item{
    String name;
    long size;
    Boolean type ;
    String path;
    String parent;
    
    
    
    public ItemInterface  (String name, long size, Boolean type, String path, String Parent){
        this.name = name;
        this.size = size;
        this.type = type;
        this.path = path;
        this.parent = parent;
        
    }
    
    @Override
    public String getName(){
        return this.name;
    }
   
    @Override
    public float getSize (){
        return this.size;
    }
   
    @Override
    public Boolean getBoolean (){
        return this.type;
    }
    
    @Override
    public String getPath(){
        return this.path;
        
    }
    
    @Override
    public String getParent(){
        return this.parent;
    }
    
}
