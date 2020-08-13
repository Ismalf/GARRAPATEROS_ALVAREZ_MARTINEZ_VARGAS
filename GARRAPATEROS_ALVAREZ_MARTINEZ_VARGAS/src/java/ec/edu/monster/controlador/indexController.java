/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.monster.controlador;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Ismalf
 */
@Named(value = "indexController")
@ViewScoped
public class indexController implements Serializable{

    /**
     * Creates a new instance of indexController
     */
    public indexController() {

    }

    @PostConstruct
    public void init() {
        
    }
    
    public void redirect(Object event){
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/GARRAPATEROS_ALVAREZ_MARTINEZ_VARGAS/faces/login.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(indexController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
