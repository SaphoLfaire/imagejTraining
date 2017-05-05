/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import org.scijava.event.EventHandler;
import org.scijava.plugin.Parameter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToolBar;
import org.scijava.Context;
import org.scijava.InstantiableException;
import org.scijava.SciJava;
import org.scijava.plugin.PluginInfo;
import org.scijava.plugin.PluginService;


/**
 * FXML Controller class
 *
 * @author Sapho
 */
public class ViewController extends GridPane {
    
    @Parameter
    DataService dataService;
    
    @Parameter
    Context context;
    
    @FXML
    ListView <Item> listView;
    
    @FXML
    ToolBar toolBar;
    
    @FXML
    Label label;
    
    @FXML
    Button back;
    
    Item item;
    
    
    public ViewController() throws IOException {
        
        SciJava scijava = new SciJava();
        scijava.context().inject(this);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/View.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();
        afficheItems(item);
        ToolBarPlugin toolBarPlugin = new ToolBarPlugin(context);
    }
    
   
    
    
    private void afficheItems(Item item){
        dataService.afficheItems(item);
    }
    
    
    @FXML
    private void comeBack (){
        dataService.comeBack(item);
    }

    
    @EventHandler
    @SuppressWarnings("empty-statement")
    public void addAfficheItems(EventAfficheItems event){
        Platform.runLater( () ->{
             listView.getItems().clear();
             List <Item> listItems = event.getArray();
             listItems.forEach((item) -> {
                 listView.getItems().add(item);
                 listView.setCellFactory(this::call);
                
            });
             
            listView.getSelectionModel()
                    .selectedItemProperty()
                    .addListener(observable -> {
                            if (listView.getSelectionModel().getSelectedItem().getBoolean())
                            {
                                Item item = listView.getSelectionModel().getSelectedItem();
                                afficheItems(item);
                                //comeBack(item);
                            }
                                 
                                    });
         });         
        
    }
    
    @EventHandler
    public void addActionDetails (EventActionDetails event){
        Platform.runLater( () ->{
            label.setText(event.setDetails());
        });
    }
    
    
    @EventHandler
    public void addActionSortBySize (EventAfficheItems event){
        final List<Item> finalListItem = event.getArray()
                .stream()
                .sorted (this::compareByName)
                .collect(Collectors.toList());
        
        Platform.runLater(() ->{
            listView.getItems().addAll(finalListItem);
            
    });     
    }
    
    private int compareByName (Item left, Item right){
        return left.getName().compareTo(right.getName());
    }
 
    
    public ListCell<Item> call (ListView<Item> item){
    return new ViewController.ItemListCell();
}
    
    public class ItemListCell extends ListCell<Item> {
        
        @Override
        protected void updateItem (Item item, boolean empty){
            super.updateItem(item, empty);
            setText(null);
            if (!empty && item!=null){
                final String text = String.format("%s", item.getName());
                setText(text);
            }
 
    }
    }
    
    
    
    
    
    
    
    public class ToolBarPlugin extends ToolBar{
    @Parameter
    PluginService pluginService;
    
       
    public ToolBarPlugin (Context context){
        context.inject(this);
        
        pluginService.getPluginsOfType(InterfaceActionPlugin.class)
                .forEach(this::addPlugin);
    }
    
    public void addPlugin(PluginInfo<InterfaceActionPlugin> pluginInfo){
        Button button = new Button(pluginInfo.getLabel());
        
        InterfaceActionPlugin plugin;
        toolBar.getItems().add(button);
        try {
            
            plugin = pluginInfo.createInstance();
            context.inject(plugin);
            button.setOnAction(actionEvent ->applyPlugin(plugin));
            
            
        } catch (InstantiableException ex) {
            Logger.getLogger(ViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }
    
     public void applyPlugin(InterfaceActionPlugin plugin){
        Item item = listView.getSelectionModel().getSelectedItem();       
        plugin.actionButton(item);
                            
        
    }
    
    
    
}
    
    
    
     
}
