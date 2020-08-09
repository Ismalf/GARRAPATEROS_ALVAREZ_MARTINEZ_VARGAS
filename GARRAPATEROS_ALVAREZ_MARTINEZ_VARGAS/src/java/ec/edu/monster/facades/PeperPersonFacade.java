/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.monster.facades;

import ec.edu.monster.modelo.PeperPerson;
import java.math.BigInteger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NamedNativeQuery;
import javax.persistence.PersistenceContext;

/**
 *
 * @author sebas
 */

@Stateless
public class PeperPersonFacade extends AbstractFacade<PeperPerson> {

    @PersistenceContext(unitName = "GARRAPATEROS_ALVAREZ_MARTINEZ_VARGASPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PeperPersonFacade() {
        super(PeperPerson.class);
    }
    
    public Integer getLastId(){
        return ((BigInteger) em.createNativeQuery("SELECT LAST_INSERT_ID()").getSingleResult()).intValue();
    }
    
}
