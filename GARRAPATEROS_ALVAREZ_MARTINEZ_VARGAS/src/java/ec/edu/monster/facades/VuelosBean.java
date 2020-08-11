package ec.edu.monster.facades;

import ec.edu.monster.modelo.Salto;
import ec.edu.monster.modelo.Vuelos;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.event.AjaxBehaviorEvent;

/**
 *
 * @author sebas
 */
@Named(value = "vuelosBean")
@RequestScoped
public class VuelosBean {

    /**
     * Creates a new instance of VuelosBean
     */
    
    @EJB
    private VuelosFacade vueFacade;
    private SaltoFacade salFacade;
    private List<Vuelos> info;
    private int acumVuelos=0;
    private List<Object[]> infoSaltos;
    private int n;
    private int acumTandem;
    private int acumLibre;
    private int maxVuelos=5;
    private String tipo;
    private Vuelos vueloSelected; 
    private Salto saltosSelected;

    public int getAcumVuelos() {
        return acumVuelos;
    }

    public int getMaxVuelos() {
        return maxVuelos;
    }

    public void setMaxVuelos(int maxVuelos) {
        this.maxVuelos = maxVuelos;
    }

    
    public void setAcumVuelos(int acumVuelos) {
        this.acumVuelos = acumVuelos;
    }

    
    public Vuelos getVueloSelected() {
        return vueloSelected;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setVueloSelected(Vuelos vueloSelected) {
        this.vueloSelected = vueloSelected;
    }

    public List<Object[]> getInfoSaltos() {
        return infoSaltos;
    }

    public void setInfoSaltos(List<Object[]> infoSaltos) {
        this.infoSaltos = infoSaltos;
    }
    
    

    public List<Vuelos> getInfo() {
        return info;
    }

    public void setInfo(List<Vuelos> info) {
        this.info = info;
    }

    
     
    public VuelosBean() {
    }

    
  public void cargarDatos()
    {
       
       n=vueloSelected.getIdVuelo();
       vueloSelected=info.get(n-1);
        System.out.println("VS" +vueloSelected);
     //System.out.println("ID" +n);
       //System.out.println("VALOR: " +salFacade.infoSaltos(vueloSelected.getIdVuelo()));
       //salFacade.infoSaltos(vueloSelected.getIdVuelo());     

    }
  
  
    
    @PostConstruct
    public void obtenerDatos()
    {   
        salFacade = new SaltoFacade();
        vueloSelected = new Vuelos();
         saltosSelected = new Salto();
        acumTandem=0;
        acumLibre=0;
        infoSaltos= new ArrayList<>();
        info=vueFacade.findAll();
        tipo="";
        
    }
    
}