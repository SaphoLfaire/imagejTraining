/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.scijava.Context;
import org.scijava.SciJava;
import org.scijava.event.EventHandler;
import org.scijava.plugin.Parameter;

/**
 * FXML Controller class
 *
 * @author sapho
 */
public class PartViewController extends AnchorPane {
    
    @Parameter
    DataService dataService;
    
    @Parameter
    Context context;
    
    @FXML
    Button selectButton;
    
    @FXML
    TextField textField;
    
    @FXML
    Label label;
    
    private static String EMPTY = "";
    private static String CHANGE = "Change";
    private static String CHOOSE = "Choose";
    
    private final ObjectProperty<File> fileProperty = new SimpleObjectProperty<>();
    private final ObjectProperty<Button> buttonProperty = new SimpleObjectProperty<>();
    
    private final ObservableValue<String> selectButtonText = Bindings.createStringBinding(this::getFileButtonText, fileProperty);
    private final ObservableValue<String> textFieldText = Bindings.createStringBinding(this::getTextFieldText, fileProperty);
    
    //private final StringBinding startButtonText = Bindings.createStringBinding(this::getStartButtonText, fileProperty, taskProperty);
    
    //private final StringBinding selectButtonText = Bindings.createStringBinding(this::getStartButtonText, fileProperty, taskProperty);
    
    //private final StringBinding selectButtonText = Bindings.createStringBinding(func, dependencies)

    public PartViewController(String labelName) throws IOException { // pour utiliser le même service, rajouter context dans les paramètres.
        
        SciJava scijava = new SciJava();
        scijava.context().inject(this); // pour utiliser le même service, ne pas supprimer cette ligne

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/PartView.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();  
        this.label.setText(labelName);
        init();
    }
    
    private void init(){
        selectButton.setOnAction(this::onFileButtonClicked);
        selectButton.textProperty().bind(selectButtonText);
        
        buttonProperty.setValue(selectButton); //liaison du bouton et de la buttonProperty;
        
    }
    
    
    protected void onFileButtonClicked(ActionEvent event) {
        textField.textProperty().bind(textFieldText);
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Select a file");
        File file = chooser.showOpenDialog(null);

        if (file != null) {
            dataService.setCurrentFile(file);
        }


}
   
    
    protected String getFileButtonText() {

        // if the file property is empty
        if (fileProperty.getValue() == null) {
            return CHOOSE;
        } else {
            // returns the name of the file
            return CHANGE;//(fileProperty.getValue().getName());
        }
        
}
    
    protected String getTextFieldText(){
        if (fileProperty.getValue() == null) {
            return EMPTY;
        } else {
            //textFieldProperty.setValue(textField); //apparemment .set et .setValue sont la même chose.
            return (fileProperty.getValue().getName()); //plus facile à lire avec getName
        }
    }
    public String setTextFieldText(File newFile){
        textField.textProperty().unbind();
        fileProperty.setValue(newFile);
        textField.textProperty().bind(textFieldText);
        return (fileProperty.getValue().getName());
    }
    
    public ObjectProperty<File> getFileProperty(){
        return fileProperty;
    }
    
    @EventHandler
    public void onFolderChanged(FileEvent event) {
        Platform.runLater(()->{
            fileProperty.setValue(event.getFile()); //replissage de fileProperty
        });
    }
    
    

}
