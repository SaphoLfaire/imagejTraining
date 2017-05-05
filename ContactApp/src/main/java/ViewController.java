
import org.scijava.plugin.SciJavaPlugin;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sapho
 */
public interface ViewController extends SciJavaPlugin{
    void refresh();
    void showView();
    void addContact();
    void setList(Contact contact);
    void removeContact(Contact contact);
    String Notify(String notify);
    void init();
    void show();
}
