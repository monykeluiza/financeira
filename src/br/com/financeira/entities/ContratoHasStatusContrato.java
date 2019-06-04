/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.financeira.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author moniqueluiza
 */
@Entity
@Table(name = "contrato_has_status_contrato")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ContratoHasStatusContrato.findAll", query = "SELECT c FROM ContratoHasStatusContrato c"),
    @NamedQuery(name = "ContratoHasStatusContrato.findByContratoId", query = "SELECT c FROM ContratoHasStatusContrato c WHERE c.contratoHasStatusContratoPK.contratoId = :contratoId"),
    @NamedQuery(name = "ContratoHasStatusContrato.findByStatusContratoId", query = "SELECT c FROM ContratoHasStatusContrato c WHERE c.contratoHasStatusContratoPK.statusContratoId = :statusContratoId"),
    @NamedQuery(name = "ContratoHasStatusContrato.findByData", query = "SELECT c FROM ContratoHasStatusContrato c WHERE c.data = :data")})
public class ContratoHasStatusContrato implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ContratoHasStatusContratoPK contratoHasStatusContratoPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data")
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;
    @JoinColumn(name = "contrato_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Contrato contrato;
    @JoinColumn(name = "status_contrato_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private StatusContrato statusContrato;
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Usuario usuarioId;

    public ContratoHasStatusContrato() {
    }

    public ContratoHasStatusContrato(ContratoHasStatusContratoPK contratoHasStatusContratoPK) {
        this.contratoHasStatusContratoPK = contratoHasStatusContratoPK;
    }

    public ContratoHasStatusContrato(ContratoHasStatusContratoPK contratoHasStatusContratoPK, Date data) {
        this.contratoHasStatusContratoPK = contratoHasStatusContratoPK;
        this.data = data;
    }

    public ContratoHasStatusContrato(int contratoId, int statusContratoId) {
        this.contratoHasStatusContratoPK = new ContratoHasStatusContratoPK(contratoId, statusContratoId);
    }

    public ContratoHasStatusContratoPK getContratoHasStatusContratoPK() {
        return contratoHasStatusContratoPK;
    }

    public void setContratoHasStatusContratoPK(ContratoHasStatusContratoPK contratoHasStatusContratoPK) {
        this.contratoHasStatusContratoPK = contratoHasStatusContratoPK;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }

    public StatusContrato getStatusContrato() {
        return statusContrato;
    }

    public void setStatusContrato(StatusContrato statusContrato) {
        this.statusContrato = statusContrato;
    }

    public Usuario getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Usuario usuarioId) {
        this.usuarioId = usuarioId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (contratoHasStatusContratoPK != null ? contratoHasStatusContratoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContratoHasStatusContrato)) {
            return false;
        }
        ContratoHasStatusContrato other = (ContratoHasStatusContrato) object;
        if ((this.contratoHasStatusContratoPK == null && other.contratoHasStatusContratoPK != null) || (this.contratoHasStatusContratoPK != null && !this.contratoHasStatusContratoPK.equals(other.contratoHasStatusContratoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades_financeira.ContratoHasStatusContrato[ contratoHasStatusContratoPK=" + contratoHasStatusContratoPK + " ]";
    }
    
}
