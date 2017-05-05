/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todolist_mvc;
import com.mvc.tst.Model ;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author Sapho
 */
public class ToDoList_mvc extends Application {
    
    Stage primaryStage;
    TextField enter;
    Button remove, check, add;
    ListView <Model> task;
    ObservableList <Model> data;
    
    
    @Override
    public void start(Stage primaryStage) {
        
        createButton();
        createLayout();
        createListView();
        
        
        Scene scene = new Scene (root, 350,400);
       
       primaryStage.setScene(scene);
       this.primaryStage = primaryStage;
       primaryStage.setTitle("Ok go");
       primaryStage.show();
        
    }
    /**
     *
     */
    
    public void createButton (){
       enter = new TextField();
       add = new Button ("Add Task");
       remove = new Button ("Remove");
       check = new Button ("Check all");
       
       enter.setPrefWidth(90);
       remove.setPrefWidth(90);
       check.setPrefWidth(90);
       add.setPrefWidth(90);
       
       this.task = new ListView();
       task.setEditable(true);
       this.data = FXCollections.observableArrayList();
       task.setItems(data);
    }
       
    
    public Gridpane createLayout (){
       GridPane root = new GridPane ();
       root.setAlignment(Pos.CENTER);
       root.setHgap(15);
       root.setVgap(15);
       root.add(enter, 0,1);
       root.add(remove, 0,2);
       root.add(add, 1,1);
       root.add(check, 1,2);
       root.add(task, 0,3,2,1);
       
       
        
    }
    
    public void createListView(){
       this.task = new ListView();
       task.setEditable(true);
       this.data = FXCollections.observableArrayList();
       task.setItems(data);
    }
       
      
  
    public ObservableList getData (){
        return data;
    }
        
    
    
    public ObservableList setData(){
        data.add(new Model ("truc", false));
        data.add(new Model ("machin", true));
        return data;
    }
    
    /*
    public void setData (ActionEvent e){
        Model text = new Model (enter.getText(), false);
        data.add(text);
        
    }
    */
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
