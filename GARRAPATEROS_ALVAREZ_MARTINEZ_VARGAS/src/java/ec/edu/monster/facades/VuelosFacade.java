/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.monster.facades;

import ec.edu.monster.modelo.Vuelos;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author sebas
 */
@Stateless
public class VuelosFacade extends AbstractFacade<Vuelos> {

    @PersistenceContext(unitName = "GARRAPATEROS_ALVAREZ_MARTINEZ_VARGASPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VuelosFacade() {
        super(Vuelos.class);
    }

    public List obtenerInfoVuelos() {
        Query q = em.createNativeQuery("SELECT V.ID_VUELO,V.ID_AVIONETA, concat_ws(' ', P.PEPER_NOMBRE,P.PEPER_APELLI) as Nombre,V.HORA_SALIDA,V.HORA_LLEGADA,V.FECHA_VUELO\n"
                + "FROM vuelos V\n"
                + "INNER JOIN  PEPER_PERSON P\n"
                + "ON V.PEPER_ID=P.PEPER_ID");
        // Query q=em.createNativeQuery("select * from vuelos");
        List<Object[]> listado = q.getResultList();
        return listado;
    }

}
