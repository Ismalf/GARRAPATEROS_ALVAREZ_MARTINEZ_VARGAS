package ec.edu.monster.facades;

import ec.edu.monster.modelo.FecomCompro;
import ec.edu.monster.modelo.FepagPago;
import ec.edu.monster.modelo.FeresReserva;
import ec.edu.monster.modelo.Paracaidistas;
import ec.edu.monster.modelo.PeperPerson;
import ec.edu.monster.modelo.Salto;
import ec.edu.monster.modelo.Vuelos;
import java.io.IOException;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

/**
 *
 * @author sebas
 */
@Named(value = "saltosBean")
@SessionScoped
public class SaltosBean implements Serializable {

    @ManagedProperty("#{VuelosBean}")
    private VuelosBean beanV;

    private Salto saltos;
    private FepagPago pagos;
    private FecomCompro comprobantes;
    private FeresReserva reservas;
    @EJB
    private SaltoFacade salFacade;
    private int acumVuelos;
    private float acumExtras;
    private List<String> Camarografo;
    private List<Object[]> infoSaltos;
    private List<Salto> listaSaltos;
    private String cam;
    private List<Salto> listaSalto;
    List<Integer> list = new ArrayList<Integer>();
    private int n;
    int aux;
    float v = 308.00f;
    int temp = 0;
    private float valorPagar;
    private int acumTandem;
    private int acumTotal;
    private int contTandem = 0;
    private int contLibres = 0;
    private int acumLibre;
    private int maxVuelos = 5;
    private String tipo;
    private Paracaidistas par;
    private PeperPerson per;
    private Salto saltosSelected;
    @EJB
    private FecomComproFacade comprobantesFacade;
    @EJB
    private FepagPagoFacade pagosFacade;
    @EJB
    private SaltoFacade saltosFacade;
    @EJB
    private FeresReservaFacade reservasFacade;

    public List<Salto> getListaSalto() {
        return listaSalto;
    }

    public void setListaSalto(List<Salto> listaSalto) {
        this.listaSalto = listaSalto;
    }

    private VuelosBean vb;

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    private int getHMS() {
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("HH:mm:ss");
        myDateObj = LocalDateTime.now();
        String[] val = myDateObj.format(myFormatObj).split(":");
        String tmp = "";
        for (int j = 0; j < val.length; j++) {
            tmp += val[j];
        }
        return Integer.parseInt(tmp) + new Random().nextInt(10000);
    }

    public void saveDataTables() {
        float aux = (float) acumVuelos;
        // valorPagar=precios(float x);
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("HH:mm:ss");

        comprobantes.setPecomCodigo(myDateObj.format(myFormatObj));
        comprobantes.setPecomDescri(tipo);
        comprobantes.setPecomFecha(new Date());
        comprobantes.setPeperId(new PeperPerson(1));
        comprobantesFacade.create(comprobantes);

        //----------------------------------
        for (int i = 1; i <= acumVuelos; i++) {

            reservas.setFeresCodigo(getHMS());
            reservas.setFeresDescri(tipo);
            reservas.setFeresEstado("R");
            reservas.setFeresFecha(new Date());
            reservas.setPecomCodigo(comprobantes);
            reservasFacade.create(reservas);
            listaSaltos.get(i - 1).getTipoSalto();
            pagos.setFeresCodigo(reservas);
            pagos.setFeticCantidad(aux);
            pagos.setFeticFecha(new Date());
            pagos.setFeticId(getHMS());
            pagos.setFeticTipopa(listaSaltos.get(i - 1).getTipoSalto());
            pagosFacade.create(pagos);
            saltos.setDescuentoSalto(0);
            saltos.setFeresCodigo(reservas);
            saltos.setIdSalto(getHMS());
            saltos.setIdVuelo(listaSaltos.get(i - 1).getIdVuelo());
            System.out.println("PAGAR" + valorPagar);
            if (listaSaltos.get(i - 1).equals("Libre")) {
                saltos.setMontoSalto(listaSaltos.get(i - 1).getMontoSalto() + 30.00f);
            } else {
                saltos.setMontoSalto(listaSaltos.get(i - 1).getMontoSalto());
            }
            saltos.setPeperId(new Paracaidistas(1));
            saltos.setTipoSalto(listaSaltos.get(i - 1).getTipoSalto());
            saltosFacade.create(saltos);
        }
        //listaSaltos.clear();

        //-------------------------------------
        //-------------------------------------
        LimpiarLista();
    }

    public List<String> getCamarografo() {
        return Camarografo;
    }

    public void setCamarografo(List<String> Camarografo) {
        this.Camarografo = Camarografo;
    }

    public String getCam() {
        return cam;
    }

    public void setCam(String cam) {
        this.cam = cam;
    }

    public void LimpiarLista() {
        listaSaltos.clear();
        Camarografo.clear();
        Camarografo = new ArrayList<>();
        listaSaltos = new ArrayList<>();
        acumTandem = 0;
        acumTotal = 0;
        acumVuelos = 0;
        acumLibre = 0;
    }

    public FecomCompro getComprobantes() {
        return comprobantes;
    }

    /**
     * Creates a new instance of SaltosBean
     */
    public void setComprobantes(FecomCompro comprobantes) {
        this.comprobantes = comprobantes;
    }

    public SaltosBean() {
    }

    public FeresReserva getReservas() {
        return reservas;
    }

    public void setReservas(FeresReserva reservas) {
        this.reservas = reservas;
    }

    public Salto getSaltos() {
        return saltos;
    }

    public void setSaltos(Salto saltos) {
        this.saltos = saltos;
    }

    public FepagPago getPagos() {
        return pagos;
    }

    public void setPagos(FepagPago pagos) {
        this.pagos = pagos;
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

    public void guardarSaltos(int id) {

        Vuelos objVuelos = new Vuelos();
        int ban = 0;
        int temp1 = 0;
        objVuelos.setIdVuelo(id);
        System.out.println("ACUMV" + acumVuelos);
        //  if (acumVuelos <= 5) {
        Salto objSaltos = new Salto();
        for (int i = temp1; i < acumVuelos; i++) {
            temp++;

            objSaltos.setIdVuelo(objVuelos);
            System.out.println("TIPOV: " + tipo);
            objSaltos.setTipoSalto(tipo);
            if (tipo.equals("Tandem")) {
                acumTandem++;
            } else {
                acumLibre++;
            }
            objSaltos.setFeresCodigo(new FeresReserva());
            objSaltos.setPeperId(new Paracaidistas());

            validarLibres(objSaltos, acumVuelos);

            //objSaltos.setMontoSalto(valorPagar);
            Camarografo.add(cam);
            listaSaltos.add(objSaltos);

            //System.out.println("VALORR" +listaSaltos.get(id));
        }

        temp1 = temp;
        acumVuelos = temp;
        System.out.println("ACUMVUELOS: " + acumVuelos);
        //acumVuelos+=temp;
        float aux1 = 0, aux2 = 0, aux3 = 0, aux4 = 0;
        for (int i = 0; i < listaSaltos.size(); i++) {
            if (listaSaltos.get(i).getTipoSalto().equals("Tandem")) {
                aux1 += listaSaltos.get(i).getMontoSalto();
            } else if (listaSaltos.get(i).getTipoSalto().equals("Libre")) {
                aux2 += listaSaltos.get(i).getMontoSalto() + listaSaltos.get(i).getDescuentoSalto();
            }
        }
        aux3 = aux1 + aux2;

        for (int i = 0; i < Camarografo.size(); i++) {
            if (Camarografo.get(i).equals("Si")) {
                aux4 += 70;
            }
        }

        valorPagar = aux3 + aux4;
        System.out.println("AUX3" + aux3);
        System.out.println("AUX3" + aux4);
        System.out.println("VALOR TOTAL " + valorPagar);

    }

    public void validarLibres(Salto obj, int acum) {
        int contTandem = 0;
        int contLibres = 0;

        if (obj.getTipoSalto().equals("Libre") && acum == 2) {
            obj.setDescuentoSalto(30);
            acumExtras = 60.00f;
            obj.setMontoSalto(40.00f);
        } else if (obj.getTipoSalto().equals("Libre") && acum == 3) {
            obj.setDescuentoSalto(30);
            acumExtras = 90.00f;
            obj.setMontoSalto(50.00f);
        } else if (obj.getTipoSalto().equals("Libre") && acum == 5) {
            obj.setDescuentoSalto(30);
            acumExtras = 150.00f;
            obj.setMontoSalto(60.00f);
        } else if (obj.getTipoSalto().equals("Libre") && acum == 1) {
            obj.setDescuentoSalto(30);
            acumExtras = 60.00f;
            obj.setMontoSalto(40.00f);
        } else if (obj.getTipoSalto().equals("Tandem")) {
            obj.setDescuentoSalto(0);
            obj.setMontoSalto(308.00f);
        } else {
            obj.setDescuentoSalto(0);
            obj.setMontoSalto(0.00f);
        }
    }

    public void precios() {
        System.out.println("INGRESAAA.");
        if (acumVuelos <= 5) {
            for (int i = 0; i < listaSaltos.size(); i++) {
                if (listaSaltos.get(i).getTipoSalto().equals("Tandem")) {
                    contTandem++;
                } else if (listaSaltos.get(i).getTipoSalto().equals("Libre")) {
                    contLibres++;
                } else {
                    System.out.println("Campo vacio");
                }

            }
            valorPagar = promociones(contTandem, contLibres);
        }

    }

    public int promociones(int contTandem, int contLibres) {

        System.out.println("contL" + contLibres);
        System.out.println("contT" + contTandem);
        if (contLibres == 5) {
            aux = contLibres * 60;
        } else if (contLibres == 3 && contTandem == 2) {
            aux = (contLibres * 50) + (contTandem * 154);
        } else {
            aux = (contLibres * 40) + (contTandem * 154);
        }
        // valorPagar=aux;
        System.out.println("\nAUX" + aux);
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

    public void cargarDatosSaltos(int id) {

        acumVuelos = 0;
        infoSaltos = salFacade.infoSaltos(id);
        contarDisponibles();
        obtenerDisponibles();
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

    public void contarDisponibles() {
        acumTandem = 0;
        acumTotal = 0;
        acumLibre = 0;
        for (int i = 0; i < infoSaltos.size(); i++) {
            if (infoSaltos.get(i)[2].equals("Tandem")) {
                acumTandem += Integer.parseInt(infoSaltos.get(i)[1].toString());
            } else {
                acumLibre += Integer.parseInt(infoSaltos.get(i)[1].toString());
                //acumLibre++;
            }
        }

        acumTotal = (2 * acumTandem) + acumLibre;
        // list.add(acumTotal);
        //acumTotal=0;

        System.out.println("ACUMT" + acumTotal);

    }

    public void obtenerDisponibles() {

        //  cargarDatosSaltos();
        switch (tipo) {

            case "Tandem":
                maxVuelos = 2 - (2 * acumTandem);
                System.out.println("CONTADOR" + maxVuelos);
                break;

            case "Libre":
                maxVuelos = 5 - acumLibre - (2 * acumTandem);
                System.out.println("CONTADOR" + maxVuelos);
                break;

            default:
                maxVuelos = 5;
                break;

        }

    }

    @PostConstruct
    public void obtenerDatos() {
        comprobantes = new FecomCompro();
        comprobantes.setPeperId(new PeperPerson(1));
        reservas = new FeresReserva();
        saltos = new Salto();
        pagos = new FepagPago();
        saltosSelected = new Salto();
        valorPagar = 0;
        acumExtras = 0.00f;
        cam = "";
        aux = 0;
        acumTandem = 0;
        acumTotal = 0;
        acumVuelos = 0;
        acumLibre = 0;

        Camarografo = new ArrayList<>();
        infoSaltos = new ArrayList<>();
        listaSaltos = new ArrayList<>();
        tipo = "";

    }

    public void doLogout() throws NoSuchAlgorithmException, IOException {
        System.out.println("logout");
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();

        FacesContext.getCurrentInstance().getExternalContext().redirect("/GARRAPATEROS_ALVAREZ_MARTINEZ_VARGAS/faces/login.xhtml");

    }

}
