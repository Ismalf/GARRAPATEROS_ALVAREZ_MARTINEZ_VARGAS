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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author sebas
 */
@Entity
@Table(name = "vuelos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vuelos.findAll", query = "SELECT v FROM Vuelos v")
    , @NamedQuery(name = "Vuelos.findByIdVuelo", query = "SELECT v FROM Vuelos v WHERE v.idVuelo = :idVuelo")
    , @NamedQuery(name = "Vuelos.findByHoraSalida", query = "SELECT v FROM Vuelos v WHERE v.horaSalida = :horaSalida")
    , @NamedQuery(name = "Vuelos.findByHoraLlegada", query = "SELECT v FROM Vuelos v WHERE v.horaLlegada = :horaLlegada")
    , @NamedQuery(name = "Vuelos.findByFechaVuelo", query = "SELECT v FROM Vuelos v WHERE v.fechaVuelo = :fechaVuelo")})
public class Vuelos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_VUELO")
    private Integer idVuelo;
    @Column(name = "HORA_SALIDA")
    @Temporal(TemporalType.TIME)
    private Date horaSalida;
    @Column(name = "HORA_LLEGADA")
    @Temporal(TemporalType.TIME)
    private Date horaLlegada;
    @Column(name = "FECHA_VUELO")
    @Temporal(TemporalType.DATE)
    private Date fechaVuelo;
    @JoinTable(name = "vuelos_por_reserva", joinColumns = {
        @JoinColumn(name = "ID_VUELO", referencedColumnName = "ID_VUELO")}, inverseJoinColumns = {
        @JoinColumn(name = "FERES_CODIGO", referencedColumnName = "FERES_CODIGO")})
    @ManyToMany
    private Collection<FeresReserva> feresReservaCollection;
    @JoinColumn(name = "ID_AVIONETA", referencedColumnName = "ID_AVIONETA")
    @ManyToOne(optional = false)
    private Avioneta idAvioneta;
    @JoinColumn(name = "PEPER_ID", referencedColumnName = "PEPER_ID")
    @ManyToOne(optional = false)
    private Piloto peperId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idVuelo")
    private Collection<Salto> saltoCollection;

    public Vuelos() {
    }

    public Vuelos(Integer idVuelo) {
        this.idVuelo = idVuelo;
    }

    public Integer getIdVuelo() {
        return idVuelo;
    }

    public void setIdVuelo(Integer idVuelo) {
        this.idVuelo = idVuelo;
    }

    public Date getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(Date horaSalida) {
        this.horaSalida = horaSalida;
    }

    public Date getHoraLlegada() {
        return horaLlegada;
    }

    public void setHoraLlegada(Date horaLlegada) {
        this.horaLlegada = horaLlegada;
    }

    public Date getFechaVuelo() {
        return fechaVuelo;
    }

    public void setFechaVuelo(Date fechaVuelo) {
        this.fechaVuelo = fechaVuelo;
    }

    @XmlTransient
    public Collection<FeresReserva> getFeresReservaCollection() {
        return feresReservaCollection;
    }

    public void setFeresReservaCollection(Collection<FeresReserva> feresReservaCollection) {
        this.feresReservaCollection = feresReservaCollection;
    }

    public Avioneta getIdAvioneta() {
        return idAvioneta;
    }

    public void setIdAvioneta(Avioneta idAvioneta) {
        this.idAvioneta = idAvioneta;
    }

    public Piloto getPeperId() {
        return peperId;
    }

    public void setPeperId(Piloto peperId) {
        this.peperId = peperId;
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
        hash += (idVuelo != null ? idVuelo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vuelos)) {
            return false;
        }
        Vuelos other = (Vuelos) object;
        if ((this.idVuelo == null && other.idVuelo != null) || (this.idVuelo != null && !this.idVuelo.equals(other.idVuelo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.monster.modelo.Vuelos[ idVuelo=" + idVuelo + " ]";
    }
    
}
