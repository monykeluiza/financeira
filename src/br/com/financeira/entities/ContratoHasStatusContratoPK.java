/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.financeira.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author moniqueluiza
 */
@Embeddable
public class ContratoHasStatusContratoPK implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 3337609769858458235L;
	@Basic(optional = false)
    @NotNull
    @Column(name = "contrato_id")
    private int contratoId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status_contrato_id")
    private int statusContratoId;

    public ContratoHasStatusContratoPK() {
    }

    public ContratoHasStatusContratoPK(int contratoId, int statusContratoId) {
        this.contratoId = contratoId;
        this.statusContratoId = statusContratoId;
    }

    public int getContratoId() {
        return contratoId;
    }

    public void setContratoId(int contratoId) {
        this.contratoId = contratoId;
    }

    public int getStatusContratoId() {
        return statusContratoId;
    }

    public void setStatusContratoId(int statusContratoId) {
        this.statusContratoId = statusContratoId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) contratoId;
        hash += (int) statusContratoId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContratoHasStatusContratoPK)) {
            return false;
        }
        ContratoHasStatusContratoPK other = (ContratoHasStatusContratoPK) object;
        if (this.contratoId != other.contratoId) {
            return false;
        }
        if (this.statusContratoId != other.statusContratoId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades_financeira.ContratoHasStatusContratoPK[ contratoId=" + contratoId + ", statusContratoId=" + statusContratoId + " ]";
    }
    
}
