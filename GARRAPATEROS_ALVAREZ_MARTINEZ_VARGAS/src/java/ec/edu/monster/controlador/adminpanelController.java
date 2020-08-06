/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.monster.controlador;

import javax.inject.Named;
import javax.enterprise.context.Dependent;
import org.primefaces.event.TabChangeEvent;

/**
 *
 * @author Ismalf
 */
@Named(value = "adminpanelController")
@Dependent
public class adminpanelController {

    /**
     * Creates a new instance of adminpanelController
     */
    public adminpanelController() {
    }
    public void onTabChange(TabChangeEvent event) {
        switch(event.getTab().getTitle()){
            case "Gestionar Persona": 
                break;
                
        }
        
    }
       
    
}
