
import java.io.File;
import java.util.List;
import javafx.beans.property.ListProperty;
import org.scijava.plugin.SciJavaPlugin;
import org.scijava.service.Service;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sapho
 */

public interface ViewService extends Service{
    
    void showView();
    void setContact(String name, String gemail, String tel, File picture);
    void setSelected();
    void setPicture();
    public File getPicture();
    public List<Contact> getList();
    
}
