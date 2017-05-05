/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todolist_mvc;
import com.mvc.tst.Model;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

/**
 *
 * @author Sapho
 */
public class Control {
    
    
    
    public Control() {
        
        
        //ToDoList_mvc wind = new ToDoList_mvc() ;
        
        
    }
    /*
    public void addTask (){
        task.getItems().add(new Model("truc", true));
        System.out.println("coucou");
    }
    */
    private ListCell<Model> createCell(ListView<Model> task){
        return new ItemCell();
    }
    
    public class ItemCell extends ListCell {
        
        public ItemCell(){ }
        
        public void updateItem (String item, boolean empty){
        super.updateItem(item, empty);
        //setText (item == null ? "" : task.getName());
        
    }
    }
    
    
    public void removeTask (){
        
    }
    
}
