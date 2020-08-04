/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.monster.modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author sebas
 */
@Embeddable
public class PepecPersonCargoPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "PEPER_ID")
    private int peperId;
    @Basic(optional = false)
    @Column(name = "ID_CONTRATO")
    private int idContrato;

    public PepecPersonCargoPK() {
    }

    public PepecPersonCargoPK(int peperId, int idContrato) {
        this.peperId = peperId;
        this.idContrato = idContrato;
    }

    public int getPeperId() {
        return peperId;
    }

    public void setPeperId(int peperId) {
        this.peperId = peperId;
    }

    public int getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(int idContrato) {
        this.idContrato = idContrato;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) peperId;
        hash += (int) idContrato;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PepecPersonCargoPK)) {
            return false;
        }
        PepecPersonCargoPK other = (PepecPersonCargoPK) object;
        if (this.peperId != other.peperId) {
            return false;
        }
        if (this.idContrato != other.idContrato) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.monster.modelo.PepecPersonCargoPK[ peperId=" + peperId + ", idContrato=" + idContrato + " ]";
    }
    
}
