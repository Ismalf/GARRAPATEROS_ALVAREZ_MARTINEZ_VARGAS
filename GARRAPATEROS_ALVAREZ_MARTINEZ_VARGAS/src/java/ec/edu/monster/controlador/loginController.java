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
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Ismalf
 */
@Named(value = "loginController")
@SessionScoped
public class loginController implements Serializable {

    private XeusuUsuar usuario;
    @EJB
    private XeusuUsuarFacade usuarioFacade;

    public XeusuUsuar getUsuario() {
        return usuario;
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
            
            FacesContext.getCurrentInstance().getExternalContext().redirect("/GARRAPATEROS_ALVAREZ_MARTINEZ_VARGAS/faces/adminpanel.xhtml");
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal!", "Credenciales incorrectas"));
        }
    }
}
