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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
 * @author moniqueluiza
 */
@Entity
@Table(name = "lembrete")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Lembrete.findAll", query = "SELECT l FROM Lembrete l"),
    @NamedQuery(name = "Lembrete.findById", query = "SELECT l FROM Lembrete l WHERE l.id = :id"),
    @NamedQuery(name = "Lembrete.findByDescricao", query = "SELECT l FROM Lembrete l WHERE l.descricao = :descricao"),
    @NamedQuery(name = "Lembrete.findByData", query = "SELECT l FROM Lembrete l WHERE l.data = :data"),
    @NamedQuery(name = "Lembrete.findByExecutado", query = "SELECT l FROM Lembrete l WHERE l.executado = :executado"),
    @NamedQuery(name = "Lembrete.findByBeneficio", query = "SELECT l FROM Lembrete l WHERE l.beneficio = :beneficio")})
public class Lembrete implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "descricao")
    private String descricao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data")
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;
    @Basic(optional = false)
    @NotNull
    @Column(name = "executado")
    private boolean executado;
    @Column(name = "beneficio")
    private Integer beneficio;
    @JoinColumn(name = "tipo_lembrete_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TipoLembrete tipoLembreteId;
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Usuario usuarioId;

    public Lembrete() {
    }

    public Lembrete(Integer id) {
        this.id = id;
    }

    public Lembrete(Integer id, String descricao, Date data, boolean executado) {
        this.id = id;
        this.descricao = descricao;
        this.data = data;
        this.executado = executado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public boolean getExecutado() {
        return executado;
    }

    public void setExecutado(boolean executado) {
        this.executado = executado;
    }

    public Integer getBeneficio() {
        return beneficio;
    }

    public void setBeneficio(Integer beneficio) {
        this.beneficio = beneficio;
    }

    public TipoLembrete getTipoLembreteId() {
        return tipoLembreteId;
    }

    public void setTipoLembreteId(TipoLembrete tipoLembreteId) {
        this.tipoLembreteId = tipoLembreteId;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lembrete)) {
            return false;
        }
        Lembrete other = (Lembrete) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades_financeira.Lembrete[ id=" + id + " ]";
    }
    
}
