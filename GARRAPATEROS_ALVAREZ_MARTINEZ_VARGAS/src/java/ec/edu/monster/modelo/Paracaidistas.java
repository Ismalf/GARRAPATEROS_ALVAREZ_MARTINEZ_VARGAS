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
@Table(name = "paracaidistas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Paracaidistas.findAll", query = "SELECT p FROM Paracaidistas p")
    , @NamedQuery(name = "Paracaidistas.findByPeperId", query = "SELECT p FROM Paracaidistas p WHERE p.peperId = :peperId")
    , @NamedQuery(name = "Paracaidistas.findByTipoParacaidista", query = "SELECT p FROM Paracaidistas p WHERE p.tipoParacaidista = :tipoParacaidista")
    , @NamedQuery(name = "Paracaidistas.findByEscuelaParacaidista", query = "SELECT p FROM Paracaidistas p WHERE p.escuelaParacaidista = :escuelaParacaidista")
    , @NamedQuery(name = "Paracaidistas.findBySaltosParacaidista", query = "SELECT p FROM Paracaidistas p WHERE p.saltosParacaidista = :saltosParacaidista")
    , @NamedQuery(name = "Paracaidistas.findByFechaParacaidista", query = "SELECT p FROM Paracaidistas p WHERE p.fechaParacaidista = :fechaParacaidista")
    , @NamedQuery(name = "Paracaidistas.findByLicenciaParacaidista", query = "SELECT p FROM Paracaidistas p WHERE p.licenciaParacaidista = :licenciaParacaidista")
    , @NamedQuery(name = "Paracaidistas.findByCupulaParacaidista", query = "SELECT p FROM Paracaidistas p WHERE p.cupulaParacaidista = :cupulaParacaidista")
    , @NamedQuery(name = "Paracaidistas.findByCupdirParacaidista", query = "SELECT p FROM Paracaidistas p WHERE p.cupdirParacaidista = :cupdirParacaidista")
    , @NamedQuery(name = "Paracaidistas.findByAguaParacaidista", query = "SELECT p FROM Paracaidistas p WHERE p.aguaParacaidista = :aguaParacaidista")
    , @NamedQuery(name = "Paracaidistas.findByAguadirParacaidista", query = "SELECT p FROM Paracaidistas p WHERE p.aguadirParacaidista = :aguadirParacaidista")
    , @NamedQuery(name = "Paracaidistas.findByDisciplinasParacaidista", query = "SELECT p FROM Paracaidistas p WHERE p.disciplinasParacaidista = :disciplinasParacaidista")})
public class Paracaidistas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PEPER_ID")
    private Integer peperId;
    @Size(max = 3)
    @Column(name = "TIPO_PARACAIDISTA")
    private String tipoParacaidista;
    @Size(max = 40)
    @Column(name = "ESCUELA_PARACAIDISTA")
    private String escuelaParacaidista;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SALTOS_PARACAIDISTA")
    private int saltosParacaidista;
    @Column(name = "FECHA_PARACAIDISTA")
    @Temporal(TemporalType.DATE)
    private Date fechaParacaidista;
    @Size(max = 1)
    @Column(name = "LICENCIA_PARACAIDISTA")
    private String licenciaParacaidista;
    @Size(max = 1024)
    @Column(name = "CUPULA_PARACAIDISTA")
    private String cupulaParacaidista;
    @Size(max = 64)
    @Column(name = "CUPDIR_PARACAIDISTA")
    private String cupdirParacaidista;
    @Size(max = 64)
    @Column(name = "AGUA_PARACAIDISTA")
    private String aguaParacaidista;
    @Size(max = 50)
    @Column(name = "AGUADIR_PARACAIDISTA")
    private String aguadirParacaidista;
    @Size(max = 20)
    @Column(name = "DISCIPLINAS_PARACAIDISTA")
    private String disciplinasParacaidista;
    @JoinColumn(name = "PEPER_ID", referencedColumnName = "PEPER_ID", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private PeperPerson peperPerson;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "peperId")
    private Collection<Salto> saltoCollection;

    public Paracaidistas() {
    }

    public Paracaidistas(Integer peperId) {
        this.peperId = peperId;
    }

    public Paracaidistas(Integer peperId, int saltosParacaidista) {
        this.peperId = peperId;
        this.saltosParacaidista = saltosParacaidista;
    }

    public Integer getPeperId() {
        return peperId;
    }

    public void setPeperId(Integer peperId) {
        this.peperId = peperId;
    }

    public String getTipoParacaidista() {
        return tipoParacaidista;
    }

    public void setTipoParacaidista(String tipoParacaidista) {
        this.tipoParacaidista = tipoParacaidista;
    }

    public String getEscuelaParacaidista() {
        return escuelaParacaidista;
    }

    public void setEscuelaParacaidista(String escuelaParacaidista) {
        this.escuelaParacaidista = escuelaParacaidista;
    }

    public int getSaltosParacaidista() {
        return saltosParacaidista;
    }

    public void setSaltosParacaidista(int saltosParacaidista) {
        this.saltosParacaidista = saltosParacaidista;
    }

    public Date getFechaParacaidista() {
        return fechaParacaidista;
    }

    public void setFechaParacaidista(Date fechaParacaidista) {
        this.fechaParacaidista = fechaParacaidista;
    }

    public String getLicenciaParacaidista() {
        return licenciaParacaidista;
    }

    public void setLicenciaParacaidista(String licenciaParacaidista) {
        this.licenciaParacaidista = licenciaParacaidista;
    }

    public String getCupulaParacaidista() {
        return cupulaParacaidista;
    }

    public void setCupulaParacaidista(String cupulaParacaidista) {
        this.cupulaParacaidista = cupulaParacaidista;
    }

    public String getCupdirParacaidista() {
        return cupdirParacaidista;
    }

    public void setCupdirParacaidista(String cupdirParacaidista) {
        this.cupdirParacaidista = cupdirParacaidista;
    }

    public String getAguaParacaidista() {
        return aguaParacaidista;
    }

    public void setAguaParacaidista(String aguaParacaidista) {
        this.aguaParacaidista = aguaParacaidista;
    }

    public String getAguadirParacaidista() {
        return aguadirParacaidista;
    }

    public void setAguadirParacaidista(String aguadirParacaidista) {
        this.aguadirParacaidista = aguadirParacaidista;
    }

    public String getDisciplinasParacaidista() {
        return disciplinasParacaidista;
    }

    public void setDisciplinasParacaidista(String disciplinasParacaidista) {
        this.disciplinasParacaidista = disciplinasParacaidista;
    }

    public PeperPerson getPeperPerson() {
        return peperPerson;
    }

    public void setPeperPerson(PeperPerson peperPerson) {
        this.peperPerson = peperPerson;
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
        hash += (peperId != null ? peperId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Paracaidistas)) {
            return false;
        }
        Paracaidistas other = (Paracaidistas) object;
        if ((this.peperId == null && other.peperId != null) || (this.peperId != null && !this.peperId.equals(other.peperId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.monster.modelo.Paracaidistas[ peperId=" + peperId + " ]";
    }
    
}
