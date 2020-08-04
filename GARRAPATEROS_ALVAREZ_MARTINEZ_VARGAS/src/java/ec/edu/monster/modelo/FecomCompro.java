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
@Table(name = "fecom_compro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FecomCompro.findAll", query = "SELECT f FROM FecomCompro f")
    , @NamedQuery(name = "FecomCompro.findByPecomCodigo", query = "SELECT f FROM FecomCompro f WHERE f.pecomCodigo = :pecomCodigo")
    , @NamedQuery(name = "FecomCompro.findByPecomDescri", query = "SELECT f FROM FecomCompro f WHERE f.pecomDescri = :pecomDescri")
    , @NamedQuery(name = "FecomCompro.findByPecomFecha", query = "SELECT f FROM FecomCompro f WHERE f.pecomFecha = :pecomFecha")})
public class FecomCompro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "PECOM_CODIGO")
    private String pecomCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "PECOM_DESCRI")
    private String pecomDescri;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PECOM_FECHA")
    @Temporal(TemporalType.DATE)
    private Date pecomFecha;
    @JoinColumn(name = "PEPER_ID", referencedColumnName = "PEPER_ID")
    @ManyToOne(optional = false)
    private PeperPerson peperId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pecomCodigo")
    private Collection<FeresReserva> feresReservaCollection;

    public FecomCompro() {
    }

    public FecomCompro(String pecomCodigo) {
        this.pecomCodigo = pecomCodigo;
    }

    public FecomCompro(String pecomCodigo, String pecomDescri, Date pecomFecha) {
        this.pecomCodigo = pecomCodigo;
        this.pecomDescri = pecomDescri;
        this.pecomFecha = pecomFecha;
    }

    public String getPecomCodigo() {
        return pecomCodigo;
    }

    public void setPecomCodigo(String pecomCodigo) {
        this.pecomCodigo = pecomCodigo;
    }

    public String getPecomDescri() {
        return pecomDescri;
    }

    public void setPecomDescri(String pecomDescri) {
        this.pecomDescri = pecomDescri;
    }

    public Date getPecomFecha() {
        return pecomFecha;
    }

    public void setPecomFecha(Date pecomFecha) {
        this.pecomFecha = pecomFecha;
    }

    public PeperPerson getPeperId() {
        return peperId;
    }

    public void setPeperId(PeperPerson peperId) {
        this.peperId = peperId;
    }

    @XmlTransient
    public Collection<FeresReserva> getFeresReservaCollection() {
        return feresReservaCollection;
    }

    public void setFeresReservaCollection(Collection<FeresReserva> feresReservaCollection) {
        this.feresReservaCollection = feresReservaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pecomCodigo != null ? pecomCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FecomCompro)) {
            return false;
        }
        FecomCompro other = (FecomCompro) object;
        if ((this.pecomCodigo == null && other.pecomCodigo != null) || (this.pecomCodigo != null && !this.pecomCodigo.equals(other.pecomCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.monster.modelo.FecomCompro[ pecomCodigo=" + pecomCodigo + " ]";
    }
    
}
