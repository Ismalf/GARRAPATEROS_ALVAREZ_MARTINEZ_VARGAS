/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.monster.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Ismalf
 */
@FacesValidator("CIDValidator")
public class CIDValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String x = ((String) value);
        int suma = 0;
        try {
            Integer.parseInt(x);
        } catch (NumberFormatException excepcion) {
            throw new ValidatorException(new FacesMessage("La cédula debe contener solo caracteres númericos"));
        }
        if (x.length() <= 9) {
            throw new ValidatorException(new FacesMessage("La cédula debe contener 10 dígitos"));
        } else {
            int a[] = new int[x.length() / 2];
            int b[] = new int[(x.length() / 2)];
            int c = 0;
            int d = 1;
            for (int i = 0; i < x.length() / 2; i++) {
                a[i] = Integer.parseInt(String.valueOf(x.charAt(c)));
                c = c + 2;
                if (i < (x.length() / 2) - 1) {
                    b[i] = Integer.parseInt(String.valueOf(x.charAt(d)));
                    d = d + 2;
                }
            }

            for (int i = 0; i < a.length; i++) {
                a[i] = a[i] * 2;
                if (a[i] > 9) {
                    a[i] = a[i] - 9;
                }
                suma = suma + a[i] + b[i];
            }
            int aux = suma / 10;
            int dec = (aux + 1) * 10;
            if ((dec - suma) == Integer.parseInt(String.valueOf(x.charAt(x.length() - 1)))) {

            } else if (suma % 10 == 0 && x.charAt(x.length() - 1) == '0') {

            } else {
                throw new ValidatorException(new FacesMessage("La cédula es incorrecta "));
            }
        }
    }

}
