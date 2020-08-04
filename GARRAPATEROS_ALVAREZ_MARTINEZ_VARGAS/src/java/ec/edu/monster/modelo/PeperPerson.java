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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "peper_person")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PeperPerson.findAll", query = "SELECT p FROM PeperPerson p")
    , @NamedQuery(name = "PeperPerson.findByPeperId", query = "SELECT p FROM PeperPerson p WHERE p.peperId = :peperId")
    , @NamedQuery(name = "PeperPerson.findByPeperNombre", query = "SELECT p FROM PeperPerson p WHERE p.peperNombre = :peperNombre")
    , @NamedQuery(name = "PeperPerson.findByPeperApelli", query = "SELECT p FROM PeperPerson p WHERE p.peperApelli = :peperApelli")
    , @NamedQuery(name = "PeperPerson.findByPeperCedula", query = "SELECT p FROM PeperPerson p WHERE p.peperCedula = :peperCedula")
    , @NamedQuery(name = "PeperPerson.findByPeperNacimi", query = "SELECT p FROM PeperPerson p WHERE p.peperNacimi = :peperNacimi")
    , @NamedQuery(name = "PeperPerson.findByPeperTelf", query = "SELECT p FROM PeperPerson p WHERE p.peperTelf = :peperTelf")
    , @NamedQuery(name = "PeperPerson.findByPeperDir", query = "SELECT p FROM PeperPerson p WHERE p.peperDir = :peperDir")})
public class PeperPerson implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PEPER_ID")
    private Integer peperId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "PEPER_NOMBRE")
    private String peperNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "PEPER_APELLI")
    private String peperApelli;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "PEPER_CEDULA")
    private String peperCedula;
    @Column(name = "PEPER_NACIMI")
    @Temporal(TemporalType.DATE)
    private Date peperNacimi;
    @Size(max = 15)
    @Column(name = "PEPER_TELF")
    private String peperTelf;
    @Size(max = 128)
    @Column(name = "PEPER_DIR")
    private String peperDir;
    @JoinColumn(name = "PESEX_CODIGO", referencedColumnName = "PESEX_CODIGO")
    @ManyToOne(optional = false)
    private PesexSexo pesexCodigo;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "peperPerson")
    private Paracaidistas paracaidistas;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "peperId")
    private Collection<XeusuUsuar> xeusuUsuarCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "peperPerson")
    private Collection<PepecPersonCargo> pepecPersonCargoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "peperId")
    private Collection<FecomCompro> fecomComproCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "peperPerson")
    private Piloto piloto;

    public PeperPerson() {
    }

    public PeperPerson(Integer peperId) {
        this.peperId = peperId;
    }

    public PeperPerson(Integer peperId, String peperNombre, String peperApelli, String peperCedula) {
        this.peperId = peperId;
        this.peperNombre = peperNombre;
        this.peperApelli = peperApelli;
        this.peperCedula = peperCedula;
    }

    public Integer getPeperId() {
        return peperId;
    }

    public void setPeperId(Integer peperId) {
        this.peperId = peperId;
    }

    public String getPeperNombre() {
        return peperNombre;
    }

    public void setPeperNombre(String peperNombre) {
        this.peperNombre = peperNombre;
    }

    public String getPeperApelli() {
        return peperApelli;
    }

    public void setPeperApelli(String peperApelli) {
        this.peperApelli = peperApelli;
    }

    public String getPeperCedula() {
        return peperCedula;
    }

    public void setPeperCedula(String peperCedula) {
        this.peperCedula = peperCedula;
    }

    public Date getPeperNacimi() {
        return peperNacimi;
    }

    public void setPeperNacimi(Date peperNacimi) {
        this.peperNacimi = peperNacimi;
    }

    public String getPeperTelf() {
        return peperTelf;
    }

    public void setPeperTelf(String peperTelf) {
        this.peperTelf = peperTelf;
    }

    public String getPeperDir() {
        return peperDir;
    }

    public void setPeperDir(String peperDir) {
        this.peperDir = peperDir;
    }

    public PesexSexo getPesexCodigo() {
        return pesexCodigo;
    }

    public void setPesexCodigo(PesexSexo pesexCodigo) {
        this.pesexCodigo = pesexCodigo;
    }

    public Paracaidistas getParacaidistas() {
        return paracaidistas;
    }

    public void setParacaidistas(Paracaidistas paracaidistas) {
        this.paracaidistas = paracaidistas;
    }

    @XmlTransient
    public Collection<XeusuUsuar> getXeusuUsuarCollection() {
        return xeusuUsuarCollection;
    }

    public void setXeusuUsuarCollection(Collection<XeusuUsuar> xeusuUsuarCollection) {
        this.xeusuUsuarCollection = xeusuUsuarCollection;
    }

    @XmlTransient
    public Collection<PepecPersonCargo> getPepecPersonCargoCollection() {
        return pepecPersonCargoCollection;
    }

    public void setPepecPersonCargoCollection(Collection<PepecPersonCargo> pepecPersonCargoCollection) {
        this.pepecPersonCargoCollection = pepecPersonCargoCollection;
    }

    @XmlTransient
    public Collection<FecomCompro> getFecomComproCollection() {
        return fecomComproCollection;
    }

    public void setFecomComproCollection(Collection<FecomCompro> fecomComproCollection) {
        this.fecomComproCollection = fecomComproCollection;
    }

    public Piloto getPiloto() {
        return piloto;
    }

    public void setPiloto(Piloto piloto) {
        this.piloto = piloto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (peperId != null ? peperId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PeperPerson)) {
            return false;
        }
        PeperPerson other = (PeperPerson) object;
        if ((this.peperId == null && other.peperId != null) || (this.peperId != null && !this.peperId.equals(other.peperId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.monster.modelo.PeperPerson[ peperId=" + peperId + " ]";
    }
    
}
