package ec.edu.monster.facades;

import ec.edu.monster.modelo.FeresReserva;
import ec.edu.monster.modelo.Paracaidistas;
import ec.edu.monster.modelo.Salto;
import ec.edu.monster.modelo.Vuelos;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedProperty;

/**
 *
 * @author sebas
 */
@Named(value = "saltosBean")
@SessionScoped
public class SaltosBean implements Serializable  {
    
    @ManagedProperty("#{VuelosBean}")
    private VuelosBean beanV;
   
    
    
    @EJB
    private SaltoFacade salFacade;
    private int acumVuelos;
    private List<Object[]> infoSaltos;
    private List<Salto> listaSaltos;
    List<Integer> list = new ArrayList<Integer>();
    private int n;
     int aux=0;
    float v=308.00f;
    private float valorPagar=0.00f;
    private int acumTandem;
    private int acumTotal;
    private int contTandem=0;
    private int contLibres=0;
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

    public List<Salto> getListaSaltos() {
        return listaSaltos;
    }

    public void setListaSaltos(List<Salto> listaSaltos) {
        this.listaSaltos = listaSaltos;
    }

    public VuelosBean getBeanV() {
        return beanV;
    }

    public void setBeanV(VuelosBean beanV) {
        this.beanV = beanV;
    }

    public float getValorPagar() {
        return valorPagar;
    }

    public void setValorPagar(float valorPagar) {
        this.valorPagar = valorPagar;
    }
    
    
    
    
    public void guardarSaltos(int id)
    {
       
        //System.out.println("GUARDARRR\n");
        //System.out.println("ID:\n " +id);
        //System.out.println("ACUM" +acumVuelos);
//        System.out.println("ACUM 11" +beanV.getAcumVuelos());
         Vuelos objVuelos = new Vuelos();
        objVuelos.setIdVuelo(id);
         //System.out.println("ID:\n " +id);
     if(acumVuelos<=5)
     {
      for(int i=0;i<acumVuelos;i++)
        {
            
            Salto objSaltos = new Salto();
            objSaltos.setIdVuelo(objVuelos);
            objSaltos.setTipoSalto(tipo);
            objSaltos.setFeresCodigo(new FeresReserva());
            objSaltos.setPeperId(new Paracaidistas());
            validarLibres(objSaltos,acumVuelos);
           /* if(objSaltos.getTipoSalto().equals("Libre"))
            {
                  objSaltos.setDescuentoSalto(30);
                
            }*/
          
           /* if(objSaltos.getTipoSalto().equals("Tandem"))
            {
                v*=acumVuelos;
            }*/
            //objSaltos.setMontoSalto(valorPagar);
            listaSaltos.add(objSaltos);
            //System.out.println("VALORR" +listaSaltos.get(id));
        }   
     }      
    }
    
    public void validarLibres(Salto obj, int acum)
    {
        if(obj.getTipoSalto().equals("Libre") && acum==2)
        {
            obj.setDescuentoSalto(30);
            obj.setMontoSalto(40.00f);
        }
        else if(obj.getTipoSalto().equals("Tandem"))
        {
             obj.setDescuentoSalto(0);
             obj.setMontoSalto(154.00f);
        }
        else if(obj.getTipoSalto().equals("Libre") && acum==3)
        {
              obj.setDescuentoSalto(30);
            obj.setMontoSalto(50.00f);
        }
        else if(obj.getTipoSalto().equals("Libre") && acum==5)
        
        {
             obj.setDescuentoSalto(30);
            obj.setMontoSalto(60.00f);
        }
        else
        {
              obj.setDescuentoSalto(0);
              obj.setMontoSalto(0.00f);
        }
        
    }
    
    public void precios()
    {
        System.out.println("INGRESAAA.");
    if(acumVuelos<=5)
    {
        for(int i=0;i<listaSaltos.size();i++)
        {
            if(listaSaltos.get(i).getTipoSalto().equals("Tandem"))
            {
                contTandem++;
            }
            else if(listaSaltos.get(i).getTipoSalto().equals("Libre"))
            {
                contLibres++;
            }
            else
            {
                System.out.println("Campo vacio");
            }
            
        }
        valorPagar=promociones(contTandem,contLibres);
    }
        
         System.out.println("VALOR: " +valorPagar);
    
        
        
    }
    
        
     public int promociones(int contTandem , int contLibres)
     {
        
         System.out.println("contL" +contLibres);
         System.out.println("contT" +contTandem);
         if(contLibres==5)
         {
             aux=contLibres*60;
         }
         else if(contLibres==3 && contTandem==2)
         {
             aux=(contLibres*50)+(contTandem*154);
         }
         else
         {
             aux=(contLibres*40)+(contTandem*154);
         }
         
         System.out.println("\nAUX" +aux);
         return aux;
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
        acumVuelos=0;
        acumLibre=0;
        infoSaltos= new ArrayList<>();
        listaSaltos=new ArrayList<>(); 
        tipo="";
        
        
    }
           
    
}