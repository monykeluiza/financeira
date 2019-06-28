/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.financeira.entities;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "meta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Meta.findAll", query = "SELECT m FROM Meta m order by m.id desc"),
    @NamedQuery(name = "Meta.findById", query = "SELECT m FROM Meta m WHERE m.id = :id"),
    @NamedQuery(name = "Meta.findByDescricao", query = "SELECT m FROM Meta m WHERE m.descricao = :descricao"),
    @NamedQuery(name = "Meta.findByValor", query = "SELECT m FROM Meta m WHERE m.valor = :valor"),
    @NamedQuery(name = "Meta.findByBatida", query = "SELECT m FROM Meta m WHERE m.batida = :batida"),
    @NamedQuery(name = "Meta.findByDataAlcance", query = "SELECT m FROM Meta m WHERE m.dataAlcance = :dataAlcance"),
    @NamedQuery(name = "Meta.findByFuncionario", query = "SELECT m FROM Meta m WHERE m.funcionarioId.id = :idFuncionario order by m.id desc"),
    @NamedQuery(name = "Meta.findByFuncionarioAtiva", query = "SELECT m FROM Meta m WHERE m.funcionarioId.id = :idFuncionario and m.batida = false order by m.id desc"),
    @NamedQuery(name = "Meta.findByDataVencimento", query = "SELECT m FROM Meta m WHERE m.dataVencimento = :dataVencimento")})
public class Meta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 500)
    @Column(name = "descricao")
    private String descricao;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor")
    private BigDecimal valor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "batida")
    private boolean batida;
    @Column(name = "data_alcance")
    @Temporal(TemporalType.DATE)
    private Date dataAlcance;
    @Column(name = "data_vencimento")
    @Temporal(TemporalType.DATE)
    private Date dataVencimento;
    @Column(name = "data_inicio")
    @Temporal(TemporalType.DATE)
    private Date dataInicio;
    @JoinColumn(name = "funcionario_id", referencedColumnName = "id")
    @ManyToOne
    private Funcionario funcionarioId;
    
    public Meta() {
    }

    public Meta(Integer id) {
        this.id = id;
    }

    public Meta(Integer id, boolean batida, Date dataVencimento) {
        this.id = id;
        this.batida = batida;
        this.dataVencimento = dataVencimento;
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

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public boolean getBatida() {
        return batida;
    }

    public void setBatida(boolean batida) {
        this.batida = batida;
    }

    public Date getDataAlcance() {
        return dataAlcance;
    }

    public void setDataAlcance(Date dataAlcance) {
        this.dataAlcance = dataAlcance;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
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
        if (!(object instanceof Meta)) {
            return false;
        }
        Meta other = (Meta) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades_financeira.Meta[ id=" + id + " ]";
    }

	public Funcionario getFuncionarioId() {
		if (funcionarioId == null) {
			funcionarioId = new Funcionario();
		}
		return funcionarioId;
	}

	public void setFuncionarioId(Funcionario funcionarioId) {
		this.funcionarioId = funcionarioId;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
    
}
