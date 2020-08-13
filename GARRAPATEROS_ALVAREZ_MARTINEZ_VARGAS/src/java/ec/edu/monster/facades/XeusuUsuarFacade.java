/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.monster.facades;

import ec.edu.monster.modelo.XeusuUsuar;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author sebas
 */
@Stateless
public class XeusuUsuarFacade extends AbstractFacade<XeusuUsuar> {

    @PersistenceContext(unitName = "GARRAPATEROS_ALVAREZ_MARTINEZ_VARGASPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public XeusuUsuarFacade() {
        super(XeusuUsuar.class);
    }

    public Boolean doLogin(String correo, String pass) {

        try {
            XeusuUsuar obj = (XeusuUsuar) em.createNativeQuery("SELECT * FROM xeusu_usuar WHERE XEUSU_EMAIL LIKE '" + correo + "' AND XEUSU_PASSWO LIKE '" + pass + "'", XeusuUsuar.class).getSingleResult();
            System.out.println(obj.getXeusuEmail());
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", obj);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public Boolean searchEmail(String correo){
        try {
            em.createNativeQuery("SELECT * FROM xeusu_usuar WHERE XEUSU_EMAIL LIKE '" + correo + "'").getSingleResult();
            System.out.println("existe");
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
