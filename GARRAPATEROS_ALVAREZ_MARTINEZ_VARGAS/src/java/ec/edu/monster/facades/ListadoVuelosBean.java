/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.monster.facades;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author sebas
 */
@Named(value = "listadoVuelosBean")
@RequestScoped
public class ListadoVuelosBean {

    /**
     * Creates a new instance of ListadoVuelosBean
     */
    @EJB
    private VuelosFacade vueFacade;
    private List<Object[]> listado;

    public List<Object[]> getListado() {
        return listado;
    }

    public void setListado(List<Object[]> listado) {
        this.listado = listado;
    }
     
    /**
     * Creates a new instance of ListadoVuelosBean
     */
    public ListadoVuelosBean() {
    }
    
    @PostConstruct
    public void llenarTabla()
    {
        //System.out.println("VALORRRRRRRRRR: " +listado.get(0));
        listado=vueFacade.obtenerInfoVuelos();
        
    }
    
    
    
}
