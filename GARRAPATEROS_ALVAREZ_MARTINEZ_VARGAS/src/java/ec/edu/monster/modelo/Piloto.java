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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author sebas
 */
@Entity
@Table(name = "piloto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Piloto.findAll", query = "SELECT p FROM Piloto p")
    , @NamedQuery(name = "Piloto.findByPeperId", query = "SELECT p FROM Piloto p WHERE p.peperId = :peperId")
    , @NamedQuery(name = "Piloto.findByLicenciaPiloto", query = "SELECT p FROM Piloto p WHERE p.licenciaPiloto = :licenciaPiloto")
    , @NamedQuery(name = "Piloto.findByHorasVuelo", query = "SELECT p FROM Piloto p WHERE p.horasVuelo = :horasVuelo")})
public class Piloto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PEPER_ID")
    private Integer peperId;
    @Size(max = 8)
    @Column(name = "LICENCIA_PILOTO")
    private String licenciaPiloto;
    @Column(name = "HORAS_VUELO")
    private Integer horasVuelo;
    @JoinColumn(name = "PEPER_ID", referencedColumnName = "PEPER_ID", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private PeperPerson peperPerson;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "peperId")
    private Collection<Vuelos> vuelosCollection;

    public Piloto() {
    }

    public Piloto(Integer peperId) {
        this.peperId = peperId;
    }

    public Integer getPeperId() {
        return peperId;
    }

    public void setPeperId(Integer peperId) {
        this.peperId = peperId;
    }

    public String getLicenciaPiloto() {
        return licenciaPiloto;
    }

    public void setLicenciaPiloto(String licenciaPiloto) {
        this.licenciaPiloto = licenciaPiloto;
    }

    public Integer getHorasVuelo() {
        return horasVuelo;
    }

    public void setHorasVuelo(Integer horasVuelo) {
        this.horasVuelo = horasVuelo;
    }

    public PeperPerson getPeperPerson() {
        return peperPerson;
    }

    public void setPeperPerson(PeperPerson peperPerson) {
        this.peperPerson = peperPerson;
    }

    @XmlTransient
    public Collection<Vuelos> getVuelosCollection() {
        return vuelosCollection;
    }

    public void setVuelosCollection(Collection<Vuelos> vuelosCollection) {
        this.vuelosCollection = vuelosCollection;
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
        if (!(object instanceof Piloto)) {
            return false;
        }
        Piloto other = (Piloto) object;
        if ((this.peperId == null && other.peperId != null) || (this.peperId != null && !this.peperId.equals(other.peperId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.monster.modelo.Piloto[ peperId=" + peperId + " ]";
    }
    
}
