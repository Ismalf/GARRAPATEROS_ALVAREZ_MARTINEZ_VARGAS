/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.monster.controlador;

import ec.edu.monster.facades.PeperPersonFacade;
import ec.edu.monster.facades.XeusuUsuarFacade;
import ec.edu.monster.modelo.PeperPerson;
import ec.edu.monster.modelo.XeperPerfil;
import ec.edu.monster.modelo.XeusuUsuar;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import javax.ejb.EJB;

/**
 *
 * @author Ismalf
 */
@Named(value = "crearcuentaController")
@SessionScoped
public class crearcuentaController implements Serializable {

    private XeusuUsuar usuario;
    private PeperPerson persona;
    @EJB
    private XeusuUsuarFacade usuarioFacade;
    @EJB
    private PeperPersonFacade personaFacade;

    /**
     * Creates a new instance of crearcuentaController
     */
    public crearcuentaController() {
        usuario = new XeusuUsuar();
        persona = new PeperPerson();

    }

    public void saveData() {
        personaFacade.create(persona);
        persona.setPeperId(personaFacade.getLastId());
        usuario.setPeperId(persona);
        usuario.setXeperCodper(new XeperPerfil("U"));
        usuario.setXeusuTipo('N');
        usuario.setXeusuFeccad(new Date());
        usuario.setXeusuFecmod(new Date());
        usuario.setXeusuFeccre(new Date());
        usuario.setXeusuObserv("");
        usuario.setXeusuPiefir("");
        usuario.setXeusuUltpas(usuario.getXeusuPasswo());
        usuarioFacade.create(usuario);
        usuario = new XeusuUsuar();
        persona = new PeperPerson();
    }

    public XeusuUsuar getUsuario() {
        return usuario;
    }

    public void setUsuario(XeusuUsuar usuario) {
        this.usuario = usuario;
    }

    public PeperPerson getPersona() {
        return persona;
    }

    public void setPersona(PeperPerson persona) {
        this.persona = persona;
    }

    public XeusuUsuarFacade getUsuarioFacade() {
        return usuarioFacade;
    }

    public void setUsuarioFacade(XeusuUsuarFacade usuarioFacade) {
        this.usuarioFacade = usuarioFacade;
    }

    public PeperPersonFacade getPersonaFacade() {
        return personaFacade;
    }

    public void setPersonaFacade(PeperPersonFacade personaFacade) {
        this.personaFacade = personaFacade;
    }

}
