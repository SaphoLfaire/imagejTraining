
import org.scijava.service.SciJavaService;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sapho
 */
public interface DataService extends SciJavaService{
    void afficheItems(Item item);
    void updateItems();
    void filterbyName();
    void comeBack(Item item);
    
}
