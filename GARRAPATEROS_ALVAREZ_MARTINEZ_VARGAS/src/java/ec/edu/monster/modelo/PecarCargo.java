/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.monster.modelo;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author sebas
 */
@Entity
@Table(name = "pecar_cargo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PecarCargo.findAll", query = "SELECT p FROM PecarCargo p")
    , @NamedQuery(name = "PecarCargo.findByPecarCodigo", query = "SELECT p FROM PecarCargo p WHERE p.pecarCodigo = :pecarCodigo")
    , @NamedQuery(name = "PecarCargo.findByPecarDescri", query = "SELECT p FROM PecarCargo p WHERE p.pecarDescri = :pecarDescri")})
public class PecarCargo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "PECAR_CODIGO")
    private String pecarCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "PECAR_DESCRI")
    private String pecarDescri;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pecarCodigo")
    private Collection<PepecPersonCargo> pepecPersonCargoCollection;

    public PecarCargo() {
    }

    public PecarCargo(String pecarCodigo) {
        this.pecarCodigo = pecarCodigo;
    }

    public PecarCargo(String pecarCodigo, String pecarDescri) {
        this.pecarCodigo = pecarCodigo;
        this.pecarDescri = pecarDescri;
    }

    public String getPecarCodigo() {
        return pecarCodigo;
    }

    public void setPecarCodigo(String pecarCodigo) {
        this.pecarCodigo = pecarCodigo;
    }

    public String getPecarDescri() {
        return pecarDescri;
    }

    public void setPecarDescri(String pecarDescri) {
        this.pecarDescri = pecarDescri;
    }

    @XmlTransient
    public Collection<PepecPersonCargo> getPepecPersonCargoCollection() {
        return pepecPersonCargoCollection;
    }

    public void setPepecPersonCargoCollection(Collection<PepecPersonCargo> pepecPersonCargoCollection) {
        this.pepecPersonCargoCollection = pepecPersonCargoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pecarCodigo != null ? pecarCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PecarCargo)) {
            return false;
        }
        PecarCargo other = (PecarCargo) object;
        if ((this.pecarCodigo == null && other.pecarCodigo != null) || (this.pecarCodigo != null && !this.pecarCodigo.equals(other.pecarCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.monster.modelo.PecarCargo[ pecarCodigo=" + pecarCodigo + " ]";
    }
    
}
