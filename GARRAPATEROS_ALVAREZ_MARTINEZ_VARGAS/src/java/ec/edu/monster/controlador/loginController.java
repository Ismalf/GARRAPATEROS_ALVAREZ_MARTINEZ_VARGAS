/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.monster.controlador;

import ec.edu.monster.facades.XeusuUsuarFacade;
import ec.edu.monster.modelo.XeusuUsuar;
import java.io.IOException;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Ismalf
 */
@Named(value = "loginController")
@ViewScoped
public class loginController implements Serializable {

    private XeusuUsuar usuario;
    @EJB
    private XeusuUsuarFacade usuarioFacade;

    public XeusuUsuar getUsuario() {
        return usuario;
    }

    @PostConstruct
    public void init() {
        XeusuUsuar x = (XeusuUsuar) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        if (x != null) {
            try {
                // Logged in - redirect
                switch (x.getXeperCodper().getXeperCodper()) {
                    case "A":
                        FacesContext.getCurrentInstance().getExternalContext().redirect("/GARRAPATEROS_ALVAREZ_MARTINEZ_VARGAS/faces/adminpanel.xhtml");
                        break;
                    case "U":
                        FacesContext.getCurrentInstance().getExternalContext().redirect("/GARRAPATEROS_ALVAREZ_MARTINEZ_VARGAS/faces/formulario.xhtml");
                        break;
                    case "E":
                        FacesContext.getCurrentInstance().getExternalContext().redirect("/GARRAPATEROS_ALVAREZ_MARTINEZ_VARGAS/faces/adminpanel.xhtml");
                        break;
                }

            } catch (IOException ex) {
                Logger.getLogger(loginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("No bro");
        }
    }

    /**
     * Creates a new instance of loginController
     */
    public loginController() {
        usuario = new XeusuUsuar();
    }

    public void setUsuario(XeusuUsuar usuario) {
        this.usuario = usuario;
    }

    public void doLogin() throws NoSuchAlgorithmException, IOException {
        MessageDigest sha = MessageDigest.getInstance("MD5");
        System.out.println(usuario);
        sha.update(usuario.getXeusuPasswo().getBytes());
        if (usuarioFacade.doLogin(usuario.getXeusuEmail(), new String(sha.digest()))) {

            XeusuUsuar x = (XeusuUsuar) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            if (x != null) {
                try {
                    // Logged in - redirect
                    switch (x.getXeperCodper().getXeperCodper()) {
                        case "A":
                            FacesContext.getCurrentInstance().getExternalContext().redirect("/GARRAPATEROS_ALVAREZ_MARTINEZ_VARGAS/faces/adminpanel.xhtml");
                            break;
                        case "U":
                            FacesContext.getCurrentInstance().getExternalContext().redirect("/GARRAPATEROS_ALVAREZ_MARTINEZ_VARGAS/faces/formulario.xhtml");
                            break;
                        case "E":
                            FacesContext.getCurrentInstance().getExternalContext().redirect("/GARRAPATEROS_ALVAREZ_MARTINEZ_VARGAS/faces/adminpanel.xhtml");
                            break;
                    }

                } catch (IOException ex) {
                    Logger.getLogger(loginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                System.out.println("No bro");
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal!", "Credenciales incorrectas"));
        }
    }
}
