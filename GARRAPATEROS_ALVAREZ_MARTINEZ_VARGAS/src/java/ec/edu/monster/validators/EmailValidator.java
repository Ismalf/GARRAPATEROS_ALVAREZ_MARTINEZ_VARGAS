/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.monster.validators;

import ec.edu.monster.facades.XeusuUsuarFacade;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ismalf
 */
@FacesValidator("EmailValidator")
public class EmailValidator implements Validator {

    @PersistenceContext(unitName = "GARRAPATEROS_ALVAREZ_MARTINEZ_VARGASPU")
    private EntityManager em;

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String x = ((String) value);
        try {
            em.createNativeQuery("SELECT * FROM xeusu_usuar WHERE XEUSU_EMAIL LIKE '" + x + "'").getSingleResult();
        } catch (Exception e) {
            throw new ValidatorException(new FacesMessage("Correo ya registrado"));
        }
    }

}
