/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.monster.modelo;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author sebas
 */
@Entity
@Table(name = "feres_reserva")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FeresReserva.findAll", query = "SELECT f FROM FeresReserva f")
    , @NamedQuery(name = "FeresReserva.findByFeresCodigo", query = "SELECT f FROM FeresReserva f WHERE f.feresCodigo = :feresCodigo")
    , @NamedQuery(name = "FeresReserva.findByFeresFecha", query = "SELECT f FROM FeresReserva f WHERE f.feresFecha = :feresFecha")
    , @NamedQuery(name = "FeresReserva.findByFeresDescri", query = "SELECT f FROM FeresReserva f WHERE f.feresDescri = :feresDescri")
    , @NamedQuery(name = "FeresReserva.findByFeresEstado", query = "SELECT f FROM FeresReserva f WHERE f.feresEstado = :feresEstado")})
public class FeresReserva implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "FERES_CODIGO")
    private Integer feresCodigo;
    @Column(name = "FERES_FECHA")
    @Temporal(TemporalType.DATE)
    private Date feresFecha;
    @Size(max = 64)
    @Column(name = "FERES_DESCRI")
    private String feresDescri;
    @Size(max = 1)
    @Column(name = "FERES_ESTADO")
    private String feresEstado;
    @JoinTable(name = "servicios_por_reserva", joinColumns = {
        @JoinColumn(name = "FERES_CODIGO", referencedColumnName = "FERES_CODIGO")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_PRECIO", referencedColumnName = "ID_PRECIO")})
    @ManyToMany
    private Collection<Servicios> serviciosCollection;
    @ManyToMany(mappedBy = "feresReservaCollection")
    private Collection<Vuelos> vuelosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "feresCodigo")
    private Collection<FepagPago> fepagPagoCollection;
    @JoinColumn(name = "PECOM_CODIGO", referencedColumnName = "PECOM_CODIGO")
    @ManyToOne(optional = false)
    private FecomCompro pecomCodigo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "feresCodigo")
    private Collection<Salto> saltoCollection;

    public FeresReserva() {
    }

    public FeresReserva(Integer feresCodigo) {
        this.feresCodigo = feresCodigo;
    }

    public Integer getFeresCodigo() {
        return feresCodigo;
    }

    public void setFeresCodigo(Integer feresCodigo) {
        this.feresCodigo = feresCodigo;
    }

    public Date getFeresFecha() {
        return feresFecha;
    }

    public void setFeresFecha(Date feresFecha) {
        this.feresFecha = feresFecha;
    }

    public String getFeresDescri() {
        return feresDescri;
    }

    public void setFeresDescri(String feresDescri) {
        this.feresDescri = feresDescri;
    }

    public String getFeresEstado() {
        return feresEstado;
    }

    public void setFeresEstado(String feresEstado) {
        this.feresEstado = feresEstado;
    }

    @XmlTransient
    public Collection<Servicios> getServiciosCollection() {
        return serviciosCollection;
    }

    public void setServiciosCollection(Collection<Servicios> serviciosCollection) {
        this.serviciosCollection = serviciosCollection;
    }

    @XmlTransient
    public Collection<Vuelos> getVuelosCollection() {
        return vuelosCollection;
    }

    public void setVuelosCollection(Collection<Vuelos> vuelosCollection) {
        this.vuelosCollection = vuelosCollection;
    }

    @XmlTransient
    public Collection<FepagPago> getFepagPagoCollection() {
        return fepagPagoCollection;
    }

    public void setFepagPagoCollection(Collection<FepagPago> fepagPagoCollection) {
        this.fepagPagoCollection = fepagPagoCollection;
    }

    public FecomCompro getPecomCodigo() {
        return pecomCodigo;
    }

    public void setPecomCodigo(FecomCompro pecomCodigo) {
        this.pecomCodigo = pecomCodigo;
    }

    @XmlTransient
    public Collection<Salto> getSaltoCollection() {
        return saltoCollection;
    }

    public void setSaltoCollection(Collection<Salto> saltoCollection) {
        this.saltoCollection = saltoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (feresCodigo != null ? feresCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FeresReserva)) {
            return false;
        }
        FeresReserva other = (FeresReserva) object;
        if ((this.feresCodigo == null && other.feresCodigo != null) || (this.feresCodigo != null && !this.feresCodigo.equals(other.feresCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.monster.modelo.FeresReserva[ feresCodigo=" + feresCodigo + " ]";
    }
    
}
