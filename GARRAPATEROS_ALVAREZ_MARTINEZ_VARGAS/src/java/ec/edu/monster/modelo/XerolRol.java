/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.monster.modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sebas
 */
@Entity
@Table(name = "xerol_rol")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "XerolRol.findAll", query = "SELECT x FROM XerolRol x")
    , @NamedQuery(name = "XerolRol.findByXeopcCodopc", query = "SELECT x FROM XerolRol x WHERE x.xeopcCodopc = :xeopcCodopc")
    , @NamedQuery(name = "XerolRol.findByXerolInserc", query = "SELECT x FROM XerolRol x WHERE x.xerolInserc = :xerolInserc")
    , @NamedQuery(name = "XerolRol.findByXerolActual", query = "SELECT x FROM XerolRol x WHERE x.xerolActual = :xerolActual")
    , @NamedQuery(name = "XerolRol.findByXerolElimin", query = "SELECT x FROM XerolRol x WHERE x.xerolElimin = :xerolElimin")
    , @NamedQuery(name = "XerolRol.findByXerolConsul", query = "SELECT x FROM XerolRol x WHERE x.xerolConsul = :xerolConsul")})
public class XerolRol implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "XEOPC_CODOPC")
    private String xeopcCodopc;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "XEROL_INSERC")
    private String xerolInserc;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "XEROL_ACTUAL")
    private String xerolActual;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "XEROL_ELIMIN")
    private String xerolElimin;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "XEROL_CONSUL")
    private String xerolConsul;
    @JoinColumn(name = "XEOPC_CODOPC", referencedColumnName = "XEOPC_CODOPC", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private XeopcOpcion xeopcOpcion;

    public XerolRol() {
    }

    public XerolRol(String xeopcCodopc) {
        this.xeopcCodopc = xeopcCodopc;
    }

    public XerolRol(String xeopcCodopc, String xerolInserc, String xerolActual, String xerolElimin, String xerolConsul) {
        this.xeopcCodopc = xeopcCodopc;
        this.xerolInserc = xerolInserc;
        this.xerolActual = xerolActual;
        this.xerolElimin = xerolElimin;
        this.xerolConsul = xerolConsul;
    }

    public String getXeopcCodopc() {
        return xeopcCodopc;
    }

    public void setXeopcCodopc(String xeopcCodopc) {
        this.xeopcCodopc = xeopcCodopc;
    }

    public String getXerolInserc() {
        return xerolInserc;
    }

    public void setXerolInserc(String xerolInserc) {
        this.xerolInserc = xerolInserc;
    }

    public String getXerolActual() {
        return xerolActual;
    }

    public void setXerolActual(String xerolActual) {
        this.xerolActual = xerolActual;
    }

    public String getXerolElimin() {
        return xerolElimin;
    }

    public void setXerolElimin(String xerolElimin) {
        this.xerolElimin = xerolElimin;
    }

    public String getXerolConsul() {
        return xerolConsul;
    }

    public void setXerolConsul(String xerolConsul) {
        this.xerolConsul = xerolConsul;
    }

    public XeopcOpcion getXeopcOpcion() {
        return xeopcOpcion;
    }

    public void setXeopcOpcion(XeopcOpcion xeopcOpcion) {
        this.xeopcOpcion = xeopcOpcion;
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
        if (!(object instanceof XerolRol)) {
            return false;
        }
        XerolRol other = (XerolRol) object;
        if ((this.xeopcCodopc == null && other.xeopcCodopc != null) || (this.xeopcCodopc != null && !this.xeopcCodopc.equals(other.xeopcCodopc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.monster.modelo.XerolRol[ xeopcCodopc=" + xeopcCodopc + " ]";
    }
    
}
