/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sapho
 */
public class ItemImp implements ItemInt{
    String name;
    Float size;
    Boolean type ;
    
    
    public ItemImp  (String name, Float size, Boolean type){
        this.name = name;
        this.size = size;
        this.type = type;
    }
    
    @Override
    public String getName(){
        return name;
    }
   
    @Override
    public float getSize (){
        return size;
    }
   
    @Override
    public Boolean getBoolean (){
        return type;
    }
    
    @Override
    public void openFolder(){
        /*
        String command;
        String path;
        command = "C:\\Users\\Sapho\\Pictures\\";
        path = command.join(this.name);
        Runtime runtime = Runtime.getRuntime();
        Process process = null;
        
        try {
            process = runtime.exec(path);
            }
        catch(Exception err){;}
*/
        
    }
}
