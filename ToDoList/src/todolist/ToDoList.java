/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todolist;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView; 
import javafx.geometry.HPos;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;



/**
 *
 * @author Sapho
 */
public class ToDoList extends Application {
   /**
    * La variable enter permet de récupérer 
    * le texte du champs associé.
    * 
    * Les boutons remove, check et add permettent
    * respectivement de supprimer des taches, de 
    * cocher toutes les taches d'un coup, et d'ajouter
    * des taches.
    */
    TextField enter;
    Button remove, check, add; 
    
    /**
     * Methode permettant le traitement de
     * la création et de la mise à jour de 
     * l'affichage. 
     * 
     * En premier lieu les variables visuelles
     * sont définies comme telles (boutons et 
     * champs de texte).
     * 
     * Next, create an observer for 
     * check the ListView, its name is
     * data. Data stock all task and 
     * permit the data uptade in the view.
     * 
     *  
     * @param primaryStage 
     */
    @Override
    public void start(Stage primaryStage) {
    enter = new TextField();
    add = new Button("Add Task");
    remove = new Button("Remove");
    check = new Button("Check all");
    ObservableList <CheckBox> data =
            FXCollections.observableArrayList();
    
    /**
     * Create the Listview with possibilities
     * to editable it.
     */
    ListView <CheckBox> task = new ListView ();
    task.setEditable(true);
    

    /**
     * Determine each place of each button on 
     * the view. It uses a GridPane model. 
     */
    GridPane root = new GridPane();
    root.setAlignment(Pos.CENTER);
    Label toDoList = new Label("To Do List");
    root.add(toDoList, 0, 0);
    GridPane.setHalignment(toDoList, HPos.CENTER);
    root.setHgap(10);
    root.setVgap(10);
    root.add(enter, 0,1);
    root.add(remove, 0,2);
    root.add(add, 1,1);
    root.add(check, 1,2);
    root.add(task, 0,3,2,1);
    
    /**
     * @see setWiths()
     */
    setWidths();
    
    
    add.setOnAction((ActionEvent event) -> {
        CheckBox cb = new CheckBox();
        String toDo = enter.getText();
        cb.setText(toDo);
        data.addAll(cb);
        task.setItems(data);
        enter.setText("");
        
        
    });
    
    remove.setOnAction((ActionEvent event) -> {
         for (CheckBox i : data ){
            if (i.isSelected()) 
                data.remove(i);      

         }
      
      
    });

    check.setOnAction((ActionEvent event) -> {
        for (int i = 0; i < data.size(); i++ ){
            if (data.get(i).isSelected() == false)
                data.get(i).setSelected(true);
        }
       
    });
    
    
    Scene scene = new Scene(root, 350,400);
    primaryStage.setTitle ("TODOLIST");
    primaryStage.setScene(scene);
    primaryStage.show();
            
            
       
    }
    /**
     * A method for fix all width preferencies
     * for all buttons and field. 
     */
    public void setWidths(){
        enter.setPrefWidth(90);
        remove.setPrefWidth(90);
        check.setPrefWidth(90);
        add.setPrefWidth(90);
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
