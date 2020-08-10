/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.monster.facades;

import ec.edu.monster.modelo.Salto;
import ec.edu.monster.modelo.Vuelos;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author sebas
 */
@Named(value = "saltosBean")
@RequestScoped
public class SaltosBean {
    
    @EJB
    private SaltoFacade salFacade;
    private int acumVuelos=0;
    private List<Object[]> infoSaltos;
    List<Integer> list = new ArrayList<Integer>();
    private int n;
    private int acumTandem;
    private int acumTotal;
    private int acumLibre;
    private int maxVuelos=5;
    private String tipo;
    private Salto saltosSelected;
    private VuelosBean vb;
    /**
     * Creates a new instance of SaltosBean
     */
    public SaltosBean() {
    }

    public int getAcumTotal() {
        return acumTotal;
    }

    public void setAcumTotal(int acumTotal) {
        this.acumTotal = acumTotal;
    }
    
    
    

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getAcumVuelos() {
        return acumVuelos;
    }

    public void setAcumVuelos(int acumVuelos) {
        this.acumVuelos = acumVuelos;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int getAcumTandem() {
        return acumTandem;
    }

    public void setAcumTandem(int acumTandem) {
        this.acumTandem = acumTandem;
    }

    public int getAcumLibre() {
        return acumLibre;
    }

    public void setAcumLibre(int acumLibre) {
        this.acumLibre = acumLibre;
    }
    
    
    
      public void cargarDatosSaltos()
    {

       salFacade.infoSaltos(vb.getVueloSelected().getIdVuelo());     
       contarDisponibles();
    }

    public int getMaxVuelos() {
        return maxVuelos;
    }

    public void setMaxVuelos(int maxVuelos) {
        this.maxVuelos = maxVuelos;
    }

    public List<Integer> getList() {
        return list;
    }

    public void setList(List<Integer> list) {
        this.list = list;
    }
  
    
    
    
     public void contarDisponibles()
  {
     for(int i=0;i<infoSaltos.size();i++)
     {
         if(infoSaltos.get(i)[2].equals("Tandem"))
         {
            acumTandem++;
         }
         else
         {
             acumLibre++;
         }        
     }
     acumTotal=(2*acumTandem)+acumLibre;
    // list.add(acumTotal);
     //acumTotal=0;
     
      System.out.println("ACUMT" +acumTotal);
     
  }
  public void obtenerDisponibles()
  {
      switch(tipo){
      
          case "Tandem": maxVuelos=5-(2*acumTandem); 
              System.out.println("CONTADOR" +maxVuelos);
          break;
                        
          
          case "Libre": maxVuelos= 5-acumLibre-(2*acumTandem);
              System.out.println("CONTADOR" +maxVuelos);
          break;
                         
          default:
                maxVuelos=5;
                break;
      
      }
     
  }
  
  
  
   @PostConstruct
    public void obtenerDatos()
    {   
        salFacade = new SaltoFacade();
        saltosSelected = new Salto();
        acumTandem=0;
        acumTotal=0;
        acumLibre=0;
        infoSaltos= new ArrayList<>();
        tipo="";
        
    }
           
    
}
