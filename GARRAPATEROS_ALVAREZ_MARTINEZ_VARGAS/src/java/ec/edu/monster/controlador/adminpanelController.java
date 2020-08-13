/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.monster.controlador;

import java.io.IOException;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.TabChangeEvent;

/**
 *
 * @author Ismalf
 */
@Named(value = "adminpanelController")
@SessionScoped
public class adminpanelController implements Serializable {

    /**
     * Creates a new instance of adminpanelController
     */
    public adminpanelController() {
    }

    public void onTabChange(TabChangeEvent event) {
        switch (event.getTab().getTitle()) {
            case "Gestionar Persona":
                break;

        }

    }

    public void doLogout() throws NoSuchAlgorithmException, IOException {
        System.out.println("logout");
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();

        FacesContext.getCurrentInstance().getExternalContext().redirect("/GARRAPATEROS_ALVAREZ_MARTINEZ_VARGAS/faces/login.xhtml");

    }

}
