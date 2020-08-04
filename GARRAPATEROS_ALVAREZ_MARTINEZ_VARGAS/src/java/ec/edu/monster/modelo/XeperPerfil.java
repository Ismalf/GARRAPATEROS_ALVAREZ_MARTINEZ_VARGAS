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
import javax.persistence.ManyToMany;
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
@Table(name = "xeper_perfil")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "XeperPerfil.findAll", query = "SELECT x FROM XeperPerfil x")
    , @NamedQuery(name = "XeperPerfil.findByXeperCodper", query = "SELECT x FROM XeperPerfil x WHERE x.xeperCodper = :xeperCodper")
    , @NamedQuery(name = "XeperPerfil.findByXeperDescri", query = "SELECT x FROM XeperPerfil x WHERE x.xeperDescri = :xeperDescri")
    , @NamedQuery(name = "XeperPerfil.findByXeperObserv", query = "SELECT x FROM XeperPerfil x WHERE x.xeperObserv = :xeperObserv")})
public class XeperPerfil implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "XEPER_CODPER")
    private String xeperCodper;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "XEPER_DESCRI")
    private String xeperDescri;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "XEPER_OBSERV")
    private String xeperObserv;
    @ManyToMany(mappedBy = "xeperPerfilCollection")
    private Collection<XeopcOpcion> xeopcOpcionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "xeperCodper")
    private Collection<XeusuUsuar> xeusuUsuarCollection;

    public XeperPerfil() {
    }

    public XeperPerfil(String xeperCodper) {
        this.xeperCodper = xeperCodper;
    }

    public XeperPerfil(String xeperCodper, String xeperDescri, String xeperObserv) {
        this.xeperCodper = xeperCodper;
        this.xeperDescri = xeperDescri;
        this.xeperObserv = xeperObserv;
    }

    public String getXeperCodper() {
        return xeperCodper;
    }

    public void setXeperCodper(String xeperCodper) {
        this.xeperCodper = xeperCodper;
    }

    public String getXeperDescri() {
        return xeperDescri;
    }

    public void setXeperDescri(String xeperDescri) {
        this.xeperDescri = xeperDescri;
    }

    public String getXeperObserv() {
        return xeperObserv;
    }

    public void setXeperObserv(String xeperObserv) {
        this.xeperObserv = xeperObserv;
    }

    @XmlTransient
    public Collection<XeopcOpcion> getXeopcOpcionCollection() {
        return xeopcOpcionCollection;
    }

    public void setXeopcOpcionCollection(Collection<XeopcOpcion> xeopcOpcionCollection) {
        this.xeopcOpcionCollection = xeopcOpcionCollection;
    }

    @XmlTransient
    public Collection<XeusuUsuar> getXeusuUsuarCollection() {
        return xeusuUsuarCollection;
    }

    public void setXeusuUsuarCollection(Collection<XeusuUsuar> xeusuUsuarCollection) {
        this.xeusuUsuarCollection = xeusuUsuarCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (xeperCodper != null ? xeperCodper.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof XeperPerfil)) {
            return false;
        }
        XeperPerfil other = (XeperPerfil) object;
        if ((this.xeperCodper == null && other.xeperCodper != null) || (this.xeperCodper != null && !this.xeperCodper.equals(other.xeperCodper))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.monster.modelo.XeperPerfil[ xeperCodper=" + xeperCodper + " ]";
    }
    
}
