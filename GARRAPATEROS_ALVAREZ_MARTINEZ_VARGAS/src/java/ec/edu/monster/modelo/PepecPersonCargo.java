/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.monster.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sebas
 */
@Entity
@Table(name = "pepec_person_cargo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PepecPersonCargo.findAll", query = "SELECT p FROM PepecPersonCargo p")
    , @NamedQuery(name = "PepecPersonCargo.findByPeperId", query = "SELECT p FROM PepecPersonCargo p WHERE p.pepecPersonCargoPK.peperId = :peperId")
    , @NamedQuery(name = "PepecPersonCargo.findByIdContrato", query = "SELECT p FROM PepecPersonCargo p WHERE p.pepecPersonCargoPK.idContrato = :idContrato")
    , @NamedQuery(name = "PepecPersonCargo.findByPepecFecini", query = "SELECT p FROM PepecPersonCargo p WHERE p.pepecFecini = :pepecFecini")
    , @NamedQuery(name = "PepecPersonCargo.findByPepecFecfin", query = "SELECT p FROM PepecPersonCargo p WHERE p.pepecFecfin = :pepecFecfin")})
public class PepecPersonCargo implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PepecPersonCargoPK pepecPersonCargoPK;
    @Column(name = "PEPEC_FECINI")
    @Temporal(TemporalType.DATE)
    private Date pepecFecini;
    @Column(name = "PEPEC_FECFIN")
    @Temporal(TemporalType.DATE)
    private Date pepecFecfin;
    @JoinColumn(name = "PECAR_CODIGO", referencedColumnName = "PECAR_CODIGO")
    @ManyToOne(optional = false)
    private PecarCargo pecarCodigo;
    @JoinColumn(name = "PEPER_ID", referencedColumnName = "PEPER_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private PeperPerson peperPerson;

    public PepecPersonCargo() {
    }

    public PepecPersonCargo(PepecPersonCargoPK pepecPersonCargoPK) {
        this.pepecPersonCargoPK = pepecPersonCargoPK;
    }

    public PepecPersonCargo(int peperId, int idContrato) {
        this.pepecPersonCargoPK = new PepecPersonCargoPK(peperId, idContrato);
    }

    public PepecPersonCargoPK getPepecPersonCargoPK() {
        return pepecPersonCargoPK;
    }

    public void setPepecPersonCargoPK(PepecPersonCargoPK pepecPersonCargoPK) {
        this.pepecPersonCargoPK = pepecPersonCargoPK;
    }

    public Date getPepecFecini() {
        return pepecFecini;
    }

    public void setPepecFecini(Date pepecFecini) {
        this.pepecFecini = pepecFecini;
    }

    public Date getPepecFecfin() {
        return pepecFecfin;
    }

    public void setPepecFecfin(Date pepecFecfin) {
        this.pepecFecfin = pepecFecfin;
    }

    public PecarCargo getPecarCodigo() {
        return pecarCodigo;
    }

    public void setPecarCodigo(PecarCargo pecarCodigo) {
        this.pecarCodigo = pecarCodigo;
    }

    public PeperPerson getPeperPerson() {
        return peperPerson;
    }

    public void setPeperPerson(PeperPerson peperPerson) {
        this.peperPerson = peperPerson;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pepecPersonCargoPK != null ? pepecPersonCargoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PepecPersonCargo)) {
            return false;
        }
        PepecPersonCargo other = (PepecPersonCargo) object;
        if ((this.pepecPersonCargoPK == null && other.pepecPersonCargoPK != null) || (this.pepecPersonCargoPK != null && !this.pepecPersonCargoPK.equals(other.pepecPersonCargoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.monster.modelo.PepecPersonCargo[ pepecPersonCargoPK=" + pepecPersonCargoPK + " ]";
    }
    
}
