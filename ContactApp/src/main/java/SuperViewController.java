
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.adapter.JavaBeanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableDoubleValue;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.scijava.Context;
import org.scijava.plugin.Parameter;
import org.scijava.plugin.Plugin;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sapho
 */
@Plugin(type = ViewController.class)
public class SuperViewController extends Application implements ViewController{

    
    
    static private DefaultViewController pane;
    static private ListController list;
    static private TilePaneController tilePane;
    
    @Parameter
    ViewService viewService;
    
    
    
    @FXML
    Button addButton, deleteButton, modButton;
    
    private final double MAX = 900.0; //largeur à partir de laquelle la sous fenêtre change
    double width;
    ViewController currentView;

    
    @Parameter
    static private Context context;
    
    @FXML
    Stage primaryStage;


   
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        pane = new DefaultViewController(); //oui la gridpane s'appelle pane
        list = new ListController();
        tilePane = new TilePaneController();
        
        context.inject(pane);
        context.inject(list);
        context.inject(tilePane);
        
        pane.init();
        Scene scene = new Scene(pane);

        primaryStage.setScene(scene);
        
        // Par défaut la list est insérée
        //comme yoda je parle aujourd'hui
            pane.add(list, 0,3);
        
        //Permet de changer de sous fenêtre en fonction de la taille de la fenêtre principale
        primaryStage.widthProperty().addListener((ObservableValue<? extends Number > observable, Number oldValue, Number newValue) -> {
            
            //un listener permettant de surveiller la taille de la fenêtre principale



            if (newValue.doubleValue() > MAX){
                setWidth(newValue.doubleValue());
                pane.getChildren().remove(tilePane);//les deux sous fenêtres sont systématiquement supprimées avant d'en ajouter une nouvelle
                pane.getChildren().remove(list);
                pane.add(tilePane, 0, 3);//emplacement dans le gridpane
                //refresh();
                if (newValue.doubleValue()> MAX && oldValue.doubleValue()<MAX){
                    tilePane.refresh();
                }
            }
            
            
            
            
            else {
                setWidth(newValue.doubleValue());
                pane.getChildren().remove(list);
                pane.getChildren().remove(tilePane);
                pane.add(list, 0, 3);
                //refresh();
                
                if (oldValue.doubleValue()> MAX && newValue.doubleValue()<MAX){
                    list.refresh();
                }
            }
                       
            
  
            });
        primaryStage.show();
        // <
   

    }
    
    public void setWidth(double width){
        this.width = width;
    }
    
    public double getWidth(){
        
        return this.width;
    }
    
    @Override
    public void showView(){
        
    }

    @Override
    public void refresh() {
        Platform.runLater(() ->{
            System.out.println("les refresh " +this.width);
  
                if (this.width > MAX){
                    System.out.println("tilepane refresh après if width");
                    tilePane.refresh();
                }
                else {
                    list.refresh();
                }
            });

    }

    @Override
    @FXML
    public void addContact() {
                
    }

    @Override
    public void setList(Contact contact) {
        //listContact.add(contact);
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeContact(Contact contact) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String Notify(String notify) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void init() {
        

    }
    
    
   

    @Override
    public void show() {
        launch();
    }

    
    
    
}
