/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.monster.controlador;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

/**
 *
 * @author Ismalf
 */
@Named(value = "reportesController")
@ViewScoped
public class ReportesController implements Serializable {

    private Date reportDate;
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    private BarChartModel reporteTotalVentas;
    private BarChartModel ReporteTotalSaltos;
    private BarChartModel ReporteTotalParacaidista;
    @PersistenceContext(unitName = "GARRAPATEROS_ALVAREZ_MARTINEZ_VARGASPU")
    private EntityManager em;
    LocalDateTime myDateObj;
    private Boolean hasData = false;
    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("YYYY-MM-dd");

    /**
     * Creates a new instance of ReportesController
     */
    public ReportesController() {
    }

    @PostConstruct
    public void init() {
        reportDate = new Date();
        try {
            createAnimatedModels(format.format(reportDate));
            hasData = true;
        } catch (Exception e) {
            hasData = false;
        }

        //createAnimatedModels("2020-08-12");
    }

    private void getReportes(String v) {
        List<Object[]> Total_ganancia_por_dia = em.createNativeQuery("select R.feres_fecha as Fecha, Sum(S.monto_salto) as Total_vendido\n"
                + "from feres_reserva R\n"
                + "inner join salto S\n"
                + "on R.feres_codigo=S.feres_codigo\n"
                + "where R.feres_fecha LIKE '" + v + "'").getResultList();
        reporteTotalVentas = initBarModel(Total_ganancia_por_dia, "Ganancia " + v);
        List<Object[]> Total_saltos_por_dia = em.createNativeQuery("select S.tipo_salto , count(S.tipo_salto) as Saltos_Totales, R.feres_fecha\n"
                + "from salto S\n"
                + "inner join feres_reserva R\n"
                + "on R.feres_codigo=S.feres_codigo\n"
                + "where R.feres_fecha LIKE '" + v + "'\n"
                + "group by S.tipo_salto, R.feres_fecha").getResultList();
        ReporteTotalSaltos = initBarModel(Total_saltos_por_dia, "Cantidad de saltos " + v);
        List<Object[]> Total_por_paracaidista = em.createNativeQuery("select concat_ws(' ', P.peper_nombre,P.peper_apelli) as Paracaidista, sum(S.monto_salto) as Total_Pagar\n"
                + "from peper_person P\n"
                + "left join salto S\n"
                + "on P.peper_id=S.peper_id\n"
                + "inner join fecom_compro F\n"
                + "on F.peper_id=S.peper_id\n"
                + "where F.pecom_fecha like '" + v + "'").getResultList();
        ReporteTotalParacaidista = initBarModel(Total_por_paracaidista, "Total x paracaidista " + v);
    }

    private void createAnimatedModels(String v) {
        getReportes(v);
        //reporteTotalVentas = initBarModel();
        reporteTotalVentas.setTitle("INGRESO TOTAL FECHA " + v.toString());
        reporteTotalVentas.setAnimate(true);
        reporteTotalVentas.setLegendPosition("ne");
        Axis yAxis = reporteTotalVentas.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(1000);

        //ReporteTotalSaltos = initBarModel();
        ReporteTotalSaltos.setTitle("TOTAL DE SALTOS FECHA " + v.toString());
        ReporteTotalSaltos.setAnimate(true);
        ReporteTotalSaltos.setLegendPosition("ne");
        yAxis = ReporteTotalSaltos.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(20);

        //ReporteTotalParacaidista = initBarModel();
        ReporteTotalParacaidista.setTitle("TOTAL POR PARACAIDISTA " + v.toString());
        ReporteTotalParacaidista.setAnimate(true);
        ReporteTotalParacaidista.setLegendPosition("ne");
        yAxis = ReporteTotalParacaidista.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(10000);
    }

    private BarChartModel initBarModel(List<Object[]> data, String label) {
        BarChartModel model = new BarChartModel();

        data.forEach((t) -> {
            ChartSeries cs = new ChartSeries();
            cs.setLabel(t[0].toString());
            try {
                cs.set(t[0], Integer.parseInt(t[1].toString()));
                System.out.println(t[0] + " " + t[1]);
            } catch (NumberFormatException e) {
                cs.set(t[0], Float.parseFloat(t[1].toString()));
                System.out.println(t[0] + " f " + t[1]);
            }
            model.addSeries(cs);
        });

        return model;
    }

    public void onDateSelect(SelectEvent<Date> event) {
        try {
            System.out.println("Ok");
            System.out.println(format.format(event.getObject()));
            createAnimatedModels(format.format(event.getObject()));
            hasData = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            hasData = false;
        }
    }

    public Boolean getHasData() {
        return hasData;
    }

    public void setHasData(Boolean hasData) {
        this.hasData = hasData;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public BarChartModel getReporteTotalVentas() {
        return reporteTotalVentas;
    }

    public void setReporteTotalVentas(BarChartModel reporteTotalVentas) {
        this.reporteTotalVentas = reporteTotalVentas;
    }

    public BarChartModel getReporteTotalSaltos() {
        return ReporteTotalSaltos;
    }

    public void setReporteTotalSaltos(BarChartModel ReporteTotalSaltos) {
        this.ReporteTotalSaltos = ReporteTotalSaltos;
    }

    public BarChartModel getReporteTotalParacaidista() {
        return ReporteTotalParacaidista;
    }

    public void setReporteTotalParacaidista(BarChartModel ReporteTotalParacaidista) {
        this.ReporteTotalParacaidista = ReporteTotalParacaidista;
    }

    public LocalDateTime getMyDateObj() {
        return myDateObj;
    }

    public void setMyDateObj(LocalDateTime myDateObj) {
        this.myDateObj = myDateObj;
    }

}
