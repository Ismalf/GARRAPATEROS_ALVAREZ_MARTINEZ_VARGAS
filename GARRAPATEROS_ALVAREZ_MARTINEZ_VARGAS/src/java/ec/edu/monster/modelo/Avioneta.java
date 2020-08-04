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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author sebas
 */
@Entity
@Table(name = "avioneta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Avioneta.findAll", query = "SELECT a FROM Avioneta a")
    , @NamedQuery(name = "Avioneta.findByIdAvioneta", query = "SELECT a FROM Avioneta a WHERE a.idAvioneta = :idAvioneta")
    , @NamedQuery(name = "Avioneta.findByMatricula", query = "SELECT a FROM Avioneta a WHERE a.matricula = :matricula")
    , @NamedQuery(name = "Avioneta.findByModelo", query = "SELECT a FROM Avioneta a WHERE a.modelo = :modelo")
    , @NamedQuery(name = "Avioneta.findByAnio", query = "SELECT a FROM Avioneta a WHERE a.anio = :anio")})
public class Avioneta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_AVIONETA")
    private Integer idAvioneta;
    @Size(max = 20)
    @Column(name = "MATRICULA")
    private String matricula;
    @Size(max = 40)
    @Column(name = "MODELO")
    private String modelo;
    @Column(name = "ANIO")
    private Integer anio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAvioneta")
    private Collection<Vuelos> vuelosCollection;

    public Avioneta() {
    }

    public Avioneta(Integer idAvioneta) {
        this.idAvioneta = idAvioneta;
    }

    public Integer getIdAvioneta() {
        return idAvioneta;
    }

    public void setIdAvioneta(Integer idAvioneta) {
        this.idAvioneta = idAvioneta;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
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
        hash += (idAvioneta != null ? idAvioneta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Avioneta)) {
            return false;
        }
        Avioneta other = (Avioneta) object;
        if ((this.idAvioneta == null && other.idAvioneta != null) || (this.idAvioneta != null && !this.idAvioneta.equals(other.idAvioneta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.monster.modelo.Avioneta[ idAvioneta=" + idAvioneta + " ]";
    }
    
}
