/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.financeira.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
 * @author moniqueluiza
 */
@Entity
@Table(name = "status_contrato")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StatusContrato.findAll", query = "SELECT s FROM StatusContrato s"),
    @NamedQuery(name = "StatusContrato.findById", query = "SELECT s FROM StatusContrato s WHERE s.id = :id"),
    @NamedQuery(name = "StatusContrato.findByNome", query = "SELECT s FROM StatusContrato s WHERE s.nome = :nome")})
public class StatusContrato implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nome")
    private String nome;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "statusContrato")
    private List<ContratoHasStatusContrato> contratoHasStatusContratoList;

    public StatusContrato() {
    }

    public StatusContrato(Integer id) {
        this.id = id;
    }

    public StatusContrato(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @XmlTransient
    public List<ContratoHasStatusContrato> getContratoHasStatusContratoList() {
        return contratoHasStatusContratoList;
    }

    public void setContratoHasStatusContratoList(List<ContratoHasStatusContrato> contratoHasStatusContratoList) {
        this.contratoHasStatusContratoList = contratoHasStatusContratoList;
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
        if (!(object instanceof StatusContrato)) {
            return false;
        }
        StatusContrato other = (StatusContrato) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades_financeira.StatusContrato[ id=" + id + " ]";
    }
    
}
