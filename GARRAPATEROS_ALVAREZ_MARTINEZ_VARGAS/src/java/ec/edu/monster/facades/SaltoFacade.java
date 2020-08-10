/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.monster.facades;

import ec.edu.monster.modelo.Salto;
import java.util.ArrayList;
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
public class SaltoFacade extends AbstractFacade<Salto> {

    @PersistenceContext(unitName = "GARRAPATEROS_ALVAREZ_MARTINEZ_VARGASPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SaltoFacade() {
        super(Salto.class);
    }
    
    public List infoVuelos()
    {
        Query q = em.createNativeQuery("SELECT * FROM vuelos");
        List<Object[]> info = q.getResultList();
        return info;
    }
    public List infoSaltos(int id)
    {
        
      
        Query q= em.createNativeQuery("select  S.id_vuelo, count(S.tipo_salto),S.tipo_salto\n" +
        "from salto S\n" +
        "where S.id_vuelo = '"+id+"'\n"+
        "group by S.id_vuelo,S.tipo_salto");
        
        System.out.println("QUERY" +q);
        List<Object[]> infoSaltos=new ArrayList<>();
        
        if(q.getResultList()!=null)
        {
           infoSaltos = q.getResultList();
           
        }  
          return infoSaltos;
    }
    
}
