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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "xeopc_opcion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "XeopcOpcion.findAll", query = "SELECT x FROM XeopcOpcion x")
    , @NamedQuery(name = "XeopcOpcion.findByXeopcCodopc", query = "SELECT x FROM XeopcOpcion x WHERE x.xeopcCodopc = :xeopcCodopc")
    , @NamedQuery(name = "XeopcOpcion.findByXeopcDescri", query = "SELECT x FROM XeopcOpcion x WHERE x.xeopcDescri = :xeopcDescri")})
public class XeopcOpcion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "XEOPC_CODOPC")
    private String xeopcCodopc;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "XEOPC_DESCRI")
    private String xeopcDescri;
    @JoinTable(name = "opciones_perfil", joinColumns = {
        @JoinColumn(name = "XEOPC_CODOPC", referencedColumnName = "XEOPC_CODOPC")}, inverseJoinColumns = {
        @JoinColumn(name = "XEPER_CODPER", referencedColumnName = "XEPER_CODPER")})
    @ManyToMany
    private Collection<XeperPerfil> xeperPerfilCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "xeopcCodopc")
    private Collection<XevenVentan> xevenVentanCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "xeopcOpcion")
    private XerolRol xerolRol;
    @JoinColumn(name = "XESIS_CODSIS", referencedColumnName = "XESIS_CODSIS")
    @ManyToOne(optional = false)
    private XesisSistem xesisCodsis;

    public XeopcOpcion() {
    }

    public XeopcOpcion(String xeopcCodopc) {
        this.xeopcCodopc = xeopcCodopc;
    }

    public XeopcOpcion(String xeopcCodopc, String xeopcDescri) {
        this.xeopcCodopc = xeopcCodopc;
        this.xeopcDescri = xeopcDescri;
    }

    public String getXeopcCodopc() {
        return xeopcCodopc;
    }

    public void setXeopcCodopc(String xeopcCodopc) {
        this.xeopcCodopc = xeopcCodopc;
    }

    public String getXeopcDescri() {
        return xeopcDescri;
    }

    public void setXeopcDescri(String xeopcDescri) {
        this.xeopcDescri = xeopcDescri;
    }

    @XmlTransient
    public Collection<XeperPerfil> getXeperPerfilCollection() {
        return xeperPerfilCollection;
    }

    public void setXeperPerfilCollection(Collection<XeperPerfil> xeperPerfilCollection) {
        this.xeperPerfilCollection = xeperPerfilCollection;
    }

    @XmlTransient
    public Collection<XevenVentan> getXevenVentanCollection() {
        return xevenVentanCollection;
    }

    public void setXevenVentanCollection(Collection<XevenVentan> xevenVentanCollection) {
        this.xevenVentanCollection = xevenVentanCollection;
    }

    public XerolRol getXerolRol() {
        return xerolRol;
    }

    public void setXerolRol(XerolRol xerolRol) {
        this.xerolRol = xerolRol;
    }

    public XesisSistem getXesisCodsis() {
        return xesisCodsis;
    }

    public void setXesisCodsis(XesisSistem xesisCodsis) {
        this.xesisCodsis = xesisCodsis;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (xeopcCodopc != null ? xeopcCodopc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof XeopcOpcion)) {
            return false;
        }
        XeopcOpcion other = (XeopcOpcion) object;
        if ((this.xeopcCodopc == null && other.xeopcCodopc != null) || (this.xeopcCodopc != null && !this.xeopcCodopc.equals(other.xeopcCodopc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.monster.modelo.XeopcOpcion[ xeopcCodopc=" + xeopcCodopc + " ]";
    }
    
}
