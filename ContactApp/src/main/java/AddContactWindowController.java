
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.scijava.plugin.Parameter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sapho
 */
public class AddContactWindowController extends GridPane implements ViewController{
    
    @Parameter
    ViewService viewService;
    
    @FXML
    Button saveButton, wayButton;
    
    @FXML
    TextField nameText, emailText, telText, picText;
    
    @FXML
    ImageView imageView;
    
    DefaultViewController pane;
    List<Contact> listContact = new ArrayList();
    File picture;
    String name, email, tel;
    private static String EMPTY = "";
    
    private final ListProperty<Contact> contactListProperty = new SimpleListProperty<>();

    private final ObjectProperty<TextField> nameTextField = new SimpleObjectProperty<>(); // propriété pour les textfields -- les textfields sont liés au bouton save pour désactiver celui ci s'ils ne sont pas rempli.
    private final ObjectProperty<TextField> emailTextField = new SimpleObjectProperty<>();
    private final ObjectProperty<TextField> telTextField = new SimpleObjectProperty<>();
    private final ObjectProperty<TextField> picTextField = new SimpleObjectProperty<>(); // textefield qui est également lié au au file pour avoir le chemin
    private final ObjectProperty<File> fileProperty = new SimpleObjectProperty<>();

    
    
    private final ObservableValue<Boolean> saveButtonState = Bindings.createObjectBinding(this::getStateButton, nameTextField, emailTextField, telTextField, picTextField);
    private final ObservableValue<String> picTextFieldText = Bindings.createStringBinding(this::getPicTextFieldText, fileProperty); 
    
    
    
    public AddContactWindowController() throws IOException {
        
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("View.fxml"));
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/AddContactWindowView.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();

        
        /*
        saveButton.disableProperty().bind(saveButtonState);
        nameTextField.setValue(nameText);
        emailTextField.setValue(emailText);
        telTextField.setValue(telText);
        picTextField.setValue(picText);
*/

        
        
    }
    
    protected boolean getStateButton(){
        if (nameTextField.getValue().getText() != null){
            System.out.println("value" +nameTextField.getValue().getText());
            return true;
        }
        
        else if (emailTextField.getValue().getText() != null){
            return true;
        }
        else if (telTextField.getValue().getText() != null){
            return true;
        }
        else if (picTextField.getValue().getText() != null){
            return true;
        }
       
        else {
            return false;
        }
        
    }
    
    public void addPicture(){
        viewService.setPicture();
        picture = viewService.getPicture();
        fileProperty.setValue(picture);
        picText.textProperty().bind(picTextFieldText);
        if (picture != null){
            Image image = new Image (picture.toURI().toString());
            imageView.setImage(image); //file.toURI().toString()
        }
    }
    
    public String getPicTextFieldText() {
        if (fileProperty.getValue() == null) {
            System.out.println("vide !");
            return EMPTY;
        } else {
            System.out.println("reçu mon capitaine !");
            //textFieldProperty.setValue(textField); //apparemment .set et .setValue sont la même chose.
            return (fileProperty.getValue().getName()); //plus facile à lire avec getName
        }
    }
    
    public void done(){
        Platform.runLater( () ->{
            picText.textProperty().unbind();
            saveButton.getScene().getWindow().hide();
        });
        
    }
    
    public List<Contact> getListW(){
        return this.listContact;
    }
    
    public void setListW(List<Contact> list){
        this.listContact = list;
    }

    @Override
    public void refresh() {
        
    }

    @Override
    public void showView() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addContact() {
        name = nameText.getText();
        tel = telText.getText();
        email = emailText.getText();
        picture = viewService.getPicture();
        viewService.setContact(name, tel, email, picture);
        done();
        
        
    }

    @Override
    public void setList(Contact contact) {
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
