/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.monster.modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sebas
 */
@Entity
@Table(name = "salto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Salto.findAll", query = "SELECT s FROM Salto s")
    , @NamedQuery(name = "Salto.findByIdSalto", query = "SELECT s FROM Salto s WHERE s.idSalto = :idSalto")
    , @NamedQuery(name = "Salto.findByTipoSalto", query = "SELECT s FROM Salto s WHERE s.tipoSalto = :tipoSalto")
    , @NamedQuery(name = "Salto.findByMontoSalto", query = "SELECT s FROM Salto s WHERE s.montoSalto = :montoSalto")
    , @NamedQuery(name = "Salto.findByDescuentoSalto", query = "SELECT s FROM Salto s WHERE s.descuentoSalto = :descuentoSalto")})
public class Salto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_SALTO")
    private Integer idSalto;
    @Size(max = 64)
    @Column(name = "TIPO_SALTO")
    private String tipoSalto;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "MONTO_SALTO")
    private Float montoSalto;
    @Column(name = "DESCUENTO_SALTO")
    private Integer descuentoSalto;
    @JoinColumn(name = "PEPER_ID", referencedColumnName = "PEPER_ID")
    @ManyToOne(optional = false)
    private Paracaidistas peperId;
    @JoinColumn(name = "FERES_CODIGO", referencedColumnName = "FERES_CODIGO")
    @ManyToOne(optional = false)
    private FeresReserva feresCodigo;
    @JoinColumn(name = "ID_VUELO", referencedColumnName = "ID_VUELO")
    @ManyToOne(optional = false)
    private Vuelos idVuelo;

    public Salto() {
    }

    public Salto(Integer idSalto) {
        this.idSalto = idSalto;
    }

    public Integer getIdSalto() {
        return idSalto;
    }

    public void setIdSalto(Integer idSalto) {
        this.idSalto = idSalto;
    }

    public String getTipoSalto() {
        return tipoSalto;
    }

    public void setTipoSalto(String tipoSalto) {
        this.tipoSalto = tipoSalto;
    }

    public Float getMontoSalto() {
        return montoSalto;
    }

    public void setMontoSalto(Float montoSalto) {
        this.montoSalto = montoSalto;
    }

    public Integer getDescuentoSalto() {
        return descuentoSalto;
    }

    public void setDescuentoSalto(Integer descuentoSalto) {
        this.descuentoSalto = descuentoSalto;
    }

    public Paracaidistas getPeperId() {
        return peperId;
    }

    public void setPeperId(Paracaidistas peperId) {
        this.peperId = peperId;
    }

    public FeresReserva getFeresCodigo() {
        return feresCodigo;
    }

    public void setFeresCodigo(FeresReserva feresCodigo) {
        this.feresCodigo = feresCodigo;
    }

    public Vuelos getIdVuelo() {
        return idVuelo;
    }

    public void setIdVuelo(Vuelos idVuelo) {
        this.idVuelo = idVuelo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSalto != null ? idSalto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Salto)) {
            return false;
        }
        Salto other = (Salto) object;
        if ((this.idSalto == null && other.idSalto != null) || (this.idSalto != null && !this.idSalto.equals(other.idSalto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.monster.modelo.Salto[ idSalto=" + idSalto + " ]";
    }
    
}
