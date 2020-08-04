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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "xeusu_usuar")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "XeusuUsuar.findAll", query = "SELECT x FROM XeusuUsuar x")
    , @NamedQuery(name = "XeusuUsuar.findByXeusuPasswo", query = "SELECT x FROM XeusuUsuar x WHERE x.xeusuPasswo = :xeusuPasswo")
    , @NamedQuery(name = "XeusuUsuar.findByXeusuUltpas", query = "SELECT x FROM XeusuUsuar x WHERE x.xeusuUltpas = :xeusuUltpas")
    , @NamedQuery(name = "XeusuUsuar.findByXeusuFeccre", query = "SELECT x FROM XeusuUsuar x WHERE x.xeusuFeccre = :xeusuFeccre")
    , @NamedQuery(name = "XeusuUsuar.findByXeusuFecmod", query = "SELECT x FROM XeusuUsuar x WHERE x.xeusuFecmod = :xeusuFecmod")
    , @NamedQuery(name = "XeusuUsuar.findByXeusuFeccad", query = "SELECT x FROM XeusuUsuar x WHERE x.xeusuFeccad = :xeusuFeccad")
    , @NamedQuery(name = "XeusuUsuar.findByXeusuPiefir", query = "SELECT x FROM XeusuUsuar x WHERE x.xeusuPiefir = :xeusuPiefir")
    , @NamedQuery(name = "XeusuUsuar.findByXeusuObserv", query = "SELECT x FROM XeusuUsuar x WHERE x.xeusuObserv = :xeusuObserv")
    , @NamedQuery(name = "XeusuUsuar.findByXeusuEmail", query = "SELECT x FROM XeusuUsuar x WHERE x.xeusuEmail = :xeusuEmail")
    , @NamedQuery(name = "XeusuUsuar.findByXeusuTipo", query = "SELECT x FROM XeusuUsuar x WHERE x.xeusuTipo = :xeusuTipo")})
public class XeusuUsuar implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "XEUSU_PASSWO")
    private String xeusuPasswo;
    @Size(max = 40)
    @Column(name = "XEUSU_ULTPAS")
    private String xeusuUltpas;
    @Column(name = "XEUSU_FECCRE")
    @Temporal(TemporalType.DATE)
    private Date xeusuFeccre;
    @Column(name = "XEUSU_FECMOD")
    @Temporal(TemporalType.DATE)
    private Date xeusuFecmod;
    @Column(name = "XEUSU_FECCAD")
    @Temporal(TemporalType.DATE)
    private Date xeusuFeccad;
    @Size(max = 100)
    @Column(name = "XEUSU_PIEFIR")
    private String xeusuPiefir;
    @Size(max = 500)
    @Column(name = "XEUSU_OBSERV")
    private String xeusuObserv;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "XEUSU_EMAIL")
    private String xeusuEmail;
    @Column(name = "XEUSU_TIPO")
    private Character xeusuTipo;
    @JoinColumn(name = "XEPER_CODPER", referencedColumnName = "XEPER_CODPER")
    @ManyToOne(optional = false)
    private XeperPerfil xeperCodper;
    @JoinColumn(name = "PEPER_ID", referencedColumnName = "PEPER_ID")
    @ManyToOne(optional = false)
    private PeperPerson peperId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "xeusuEmail")
    private Collection<XeautAutent> xeautAutentCollection;

    public XeusuUsuar() {
    }

    public XeusuUsuar(String xeusuEmail) {
        this.xeusuEmail = xeusuEmail;
    }

    public XeusuUsuar(String xeusuEmail, String xeusuPasswo) {
        this.xeusuEmail = xeusuEmail;
        this.xeusuPasswo = xeusuPasswo;
    }

    public String getXeusuPasswo() {
        return xeusuPasswo;
    }

    public void setXeusuPasswo(String xeusuPasswo) {
        this.xeusuPasswo = xeusuPasswo;
    }

    public String getXeusuUltpas() {
        return xeusuUltpas;
    }

    public void setXeusuUltpas(String xeusuUltpas) {
        this.xeusuUltpas = xeusuUltpas;
    }

    public Date getXeusuFeccre() {
        return xeusuFeccre;
    }

    public void setXeusuFeccre(Date xeusuFeccre) {
        this.xeusuFeccre = xeusuFeccre;
    }

    public Date getXeusuFecmod() {
        return xeusuFecmod;
    }

    public void setXeusuFecmod(Date xeusuFecmod) {
        this.xeusuFecmod = xeusuFecmod;
    }

    public Date getXeusuFeccad() {
        return xeusuFeccad;
    }

    public void setXeusuFeccad(Date xeusuFeccad) {
        this.xeusuFeccad = xeusuFeccad;
    }

    public String getXeusuPiefir() {
        return xeusuPiefir;
    }

    public void setXeusuPiefir(String xeusuPiefir) {
        this.xeusuPiefir = xeusuPiefir;
    }

    public String getXeusuObserv() {
        return xeusuObserv;
    }

    public void setXeusuObserv(String xeusuObserv) {
        this.xeusuObserv = xeusuObserv;
    }

    public String getXeusuEmail() {
        return xeusuEmail;
    }

    public void setXeusuEmail(String xeusuEmail) {
        this.xeusuEmail = xeusuEmail;
    }

    public Character getXeusuTipo() {
        return xeusuTipo;
    }

    public void setXeusuTipo(Character xeusuTipo) {
        this.xeusuTipo = xeusuTipo;
    }

    public XeperPerfil getXeperCodper() {
        return xeperCodper;
    }

    public void setXeperCodper(XeperPerfil xeperCodper) {
        this.xeperCodper = xeperCodper;
    }

    public PeperPerson getPeperId() {
        return peperId;
    }

    public void setPeperId(PeperPerson peperId) {
        this.peperId = peperId;
    }

    @XmlTransient
    public Collection<XeautAutent> getXeautAutentCollection() {
        return xeautAutentCollection;
    }

    public void setXeautAutentCollection(Collection<XeautAutent> xeautAutentCollection) {
        this.xeautAutentCollection = xeautAutentCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (xeusuEmail != null ? xeusuEmail.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof XeusuUsuar)) {
            return false;
        }
        XeusuUsuar other = (XeusuUsuar) object;
        if ((this.xeusuEmail == null && other.xeusuEmail != null) || (this.xeusuEmail != null && !this.xeusuEmail.equals(other.xeusuEmail))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.monster.modelo.XeusuUsuar[ xeusuEmail=" + xeusuEmail + " ]";
    }
    
}
