/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.GridPane;

import org.scijava.Context;
import org.scijava.SciJava;
import org.scijava.event.EventHandler;
import org.scijava.plugin.Parameter;

/**
 * FXML Controller class
 *
 * @author sapho
 */
public class FXMLController extends GridPane {

    @FXML 
    Label label;
    
    @FXML
    Button vButton, startButton;
    
    @FXML
    ProgressBar progressBar;
    
    @FXML
    Context context;
    
    @Parameter
    DataService dataService;
    
    RunThread task = new RunThread();
    
    PartViewController target = new PartViewController(FILETARGET); //pour utiliser le même service, passer context en paramètre.
    PartViewController source = new PartViewController(FILESOURCE);
    
            
     
    private final Property<Worker.State> stateProperty = new SimpleObjectProperty<>(Worker.State.READY);
        
    private final ObservableValue<Boolean> selectButtonState = Bindings.createObjectBinding(this::getStateButton, source.getFileProperty());
    private final ObservableValue<Boolean> startAndVButtonState = Bindings.createObjectBinding(this::getStartAndVButtonState, source.getFileProperty(), target.getFileProperty());
    private final ObservableValue<String> labelText = Bindings.createStringBinding(this::getLabel, source.getFileProperty(), target.getFileProperty()); 
    private final ObservableValue<String> startButtonText = Bindings.createStringBinding(this::getStartButtonText,stateProperty); //taskProperty,
    private final ObservableValue<String> labelTextTask = Bindings.createStringBinding(this::getLabelTask, stateProperty);
    
    
    
    private static String SELECTSOURCE = "Please select source";
    private static String SELECTTARGET = "Please select target";
    private static String RUN = "Comparing ";
    private static String CANCELLED = "CANCELLED !";
    private static String WAIT = "Waiting something...";
    private static String PLEASERUN = "Please clic on Start !";
    private static String START = "Start !";
    private static String CANCEL = "Cancel";
    private static String DONE = "Done !";
    private static String FILESOURCE = "Source";
    private static String FILETARGET = "Target";
    
    public FXMLController() throws IOException {
        
        SciJava scijava = new SciJava();
        scijava.context().inject(this);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/FXML.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();
        
        
 
        this.add(target, 1,2);
        this.add(source,1,0 );
        init();
        
        
    }
    
    private void init(){ 
        
        vButton.setText("↕");
        target.selectButton.disableProperty().bind(selectButtonState);
        startButton.disableProperty().bind(startAndVButtonState);
        vButton.disableProperty().bind(startAndVButtonState);
        label.textProperty().bind(labelText);
        startButton.setOnAction(this::onStartButtonClicked);
        startButton.textProperty().bind(startButtonText);
        vButton.setOnAction(this::onVButtonClicked);
       
    }
    
    protected boolean getStateButton(){
        if (source.getFileProperty().getValue() == null) {
            return true;
        } else {
            return false;
        }
        
    }
    
    protected boolean getStartAndVButtonState(){
        if (target.getFileProperty().getValue() == null){
            return true;
        }
        else{
            return false;
        }
    }
    
    protected String getLabel(){
        if (target.getFileProperty().getValue() != null && source.getFileProperty().getValue() != null) {
            return PLEASERUN;
        } 
        if (target.getFileProperty().getValue() == null && source.getFileProperty().getValue() != null) {
            return SELECTTARGET;
        }
                       
        else {
            return SELECTSOURCE;
        }
    }
    
  
    
    protected void onStartButtonClicked(ActionEvent event){
        label.textProperty().unbind();
        RunThread newTask = new RunThread();
        dataService.runTask(newTask);
        stateProperty.bind(newTask.stateProperty());
        label.textProperty().bind(labelTextTask);
        progressBar.progressProperty().bind(newTask.progressProperty());

    }
    
    protected void onVButtonClicked(ActionEvent event){
        System.out.println("prout");
        File sourceValue = source.getFileProperty().getValue();
        source.setTextFieldText(target.getFileProperty().getValue());
        target.setTextFieldText(sourceValue);
    }
    
    protected String getStartButtonText(){
       
        if (stateProperty.getValue() == Worker.State.RUNNING) {
            return CANCEL;
        }        
        else{
            return START;
        }
    }
    
    protected String getLabelTask(){
        if (stateProperty.getValue() == Worker.State.RUNNING) {
            String sourcePath = source.getFileProperty().getValue().getName();
            String targetPath = target.getFileProperty().getValue().getName();
            String AND = " and ";
            String compare = RUN.concat(sourcePath.concat(AND.concat(targetPath)));
            return compare;
        }
        
        else if (stateProperty.getValue() == Worker.State.SUCCEEDED) {
            return DONE;
        }
        
        else if (stateProperty.getValue() == Worker.State.CANCELLED){
            return CANCELLED;
        }
        
        else{
            return WAIT;
        }
    }
  
    
    public void restart(){
        label.textProperty().unbind();
        label.textProperty().bind(labelText);
    }
    
    @EventHandler
     public void addTaskEvent(TaskEvent event){
         
         Platform.runLater(() ->{
            Task task = event.getTask();
        
         });
     
     }
    
    
    
}
