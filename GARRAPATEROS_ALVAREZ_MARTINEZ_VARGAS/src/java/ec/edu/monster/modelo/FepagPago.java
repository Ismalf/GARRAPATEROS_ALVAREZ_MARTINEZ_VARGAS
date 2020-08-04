/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.monster.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sebas
 */
@Entity
@Table(name = "fepag_pago")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FepagPago.findAll", query = "SELECT f FROM FepagPago f")
    , @NamedQuery(name = "FepagPago.findByFeticTipopa", query = "SELECT f FROM FepagPago f WHERE f.feticTipopa = :feticTipopa")
    , @NamedQuery(name = "FepagPago.findByFeticFecha", query = "SELECT f FROM FepagPago f WHERE f.feticFecha = :feticFecha")
    , @NamedQuery(name = "FepagPago.findByFeticCantidad", query = "SELECT f FROM FepagPago f WHERE f.feticCantidad = :feticCantidad")
    , @NamedQuery(name = "FepagPago.findByFeticId", query = "SELECT f FROM FepagPago f WHERE f.feticId = :feticId")})
public class FepagPago implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 20)
    @Column(name = "FETIC_TIPOPA")
    private String feticTipopa;
    @Column(name = "FETIC_FECHA")
    @Temporal(TemporalType.DATE)
    private Date feticFecha;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "FETIC_CANTIDAD")
    private Float feticCantidad;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "FETIC_ID")
    private Integer feticId;
    @JoinColumn(name = "FERES_CODIGO", referencedColumnName = "FERES_CODIGO")
    @ManyToOne(optional = false)
    private FeresReserva feresCodigo;

    public FepagPago() {
    }

    public FepagPago(Integer feticId) {
        this.feticId = feticId;
    }

    public String getFeticTipopa() {
        return feticTipopa;
    }

    public void setFeticTipopa(String feticTipopa) {
        this.feticTipopa = feticTipopa;
    }

    public Date getFeticFecha() {
        return feticFecha;
    }

    public void setFeticFecha(Date feticFecha) {
        this.feticFecha = feticFecha;
    }

    public Float getFeticCantidad() {
        return feticCantidad;
    }

    public void setFeticCantidad(Float feticCantidad) {
        this.feticCantidad = feticCantidad;
    }

    public Integer getFeticId() {
        return feticId;
    }

    public void setFeticId(Integer feticId) {
        this.feticId = feticId;
    }

    public FeresReserva getFeresCodigo() {
        return feresCodigo;
    }

    public void setFeresCodigo(FeresReserva feresCodigo) {
        this.feresCodigo = feresCodigo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (feticId != null ? feticId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FepagPago)) {
            return false;
        }
        FepagPago other = (FepagPago) object;
        if ((this.feticId == null && other.feticId != null) || (this.feticId != null && !this.feticId.equals(other.feticId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.monster.modelo.FepagPago[ feticId=" + feticId + " ]";
    }
    
}
