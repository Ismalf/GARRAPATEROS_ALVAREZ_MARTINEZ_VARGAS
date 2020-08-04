/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.monster.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sebas
 */
@Entity
@Table(name = "xeaut_autent")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "XeautAutent.findAll", query = "SELECT x FROM XeautAutent x")
    , @NamedQuery(name = "XeautAutent.findByXeautCodigo", query = "SELECT x FROM XeautAutent x WHERE x.xeautCodigo = :xeautCodigo")
    , @NamedQuery(name = "XeautAutent.findByXeautFechai", query = "SELECT x FROM XeautAutent x WHERE x.xeautFechai = :xeautFechai")
    , @NamedQuery(name = "XeautAutent.findByXeautDescri", query = "SELECT x FROM XeautAutent x WHERE x.xeautDescri = :xeautDescri")})
public class XeautAutent implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "XEAUT_CODIGO")
    private String xeautCodigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "XEAUT_FECHAI")
    @Temporal(TemporalType.TIMESTAMP)
    private Date xeautFechai;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "XEAUT_DESCRI")
    private String xeautDescri;
    @JoinColumn(name = "XEUSU_EMAIL", referencedColumnName = "XEUSU_EMAIL")
    @ManyToOne(optional = false)
    private XeusuUsuar xeusuEmail;

    public XeautAutent() {
    }

    public XeautAutent(String xeautCodigo) {
        this.xeautCodigo = xeautCodigo;
    }

    public XeautAutent(String xeautCodigo, Date xeautFechai, String xeautDescri) {
        this.xeautCodigo = xeautCodigo;
        this.xeautFechai = xeautFechai;
        this.xeautDescri = xeautDescri;
    }

    public String getXeautCodigo() {
        return xeautCodigo;
    }

    public void setXeautCodigo(String xeautCodigo) {
        this.xeautCodigo = xeautCodigo;
    }

    public Date getXeautFechai() {
        return xeautFechai;
    }

    public void setXeautFechai(Date xeautFechai) {
        this.xeautFechai = xeautFechai;
    }

    public String getXeautDescri() {
        return xeautDescri;
    }

    public void setXeautDescri(String xeautDescri) {
        this.xeautDescri = xeautDescri;
    }

    public XeusuUsuar getXeusuEmail() {
        return xeusuEmail;
    }

    public void setXeusuEmail(XeusuUsuar xeusuEmail) {
        this.xeusuEmail = xeusuEmail;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (xeautCodigo != null ? xeautCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof XeautAutent)) {
            return false;
        }
        XeautAutent other = (XeautAutent) object;
        if ((this.xeautCodigo == null && other.xeautCodigo != null) || (this.xeautCodigo != null && !this.xeautCodigo.equals(other.xeautCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.monster.modelo.XeautAutent[ xeautCodigo=" + xeautCodigo + " ]";
    }
    
}
