/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.financeira.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
 * @author moniqueluiza
 */
@Entity
@Table(name = "contrato")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contrato.findAll", query = "SELECT c FROM Contrato c"),
    @NamedQuery(name = "Contrato.findById", query = "SELECT c FROM Contrato c WHERE c.id = :id"),
    @NamedQuery(name = "Contrato.findByValorCliente", query = "SELECT c FROM Contrato c WHERE c.valorCliente = :valorCliente"),
    @NamedQuery(name = "Contrato.findByValorPago", query = "SELECT c FROM Contrato c WHERE c.valorPago = :valorPago"),
    @NamedQuery(name = "Contrato.findByNumero", query = "SELECT c FROM Contrato c WHERE c.numero = :numero"),
    @NamedQuery(name = "Contrato.findByValorContrato", query = "SELECT c FROM Contrato c WHERE c.valorContrato = :valorContrato"),
    @NamedQuery(name = "Contrato.findByQtdParcelas", query = "SELECT c FROM Contrato c WHERE c.qtdParcelas = :qtdParcelas"),
    @NamedQuery(name = "Contrato.findByDataPgto", query = "SELECT c FROM Contrato c WHERE c.dataPgto = :dataPgto")})
public class Contrato implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_cliente")
    private BigDecimal valorCliente;
    @Column(name = "valor_pago")
    private BigDecimal valorPago;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "numero")
    private String numero;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_contrato")
    private BigDecimal valorContrato;
    @Basic(optional = false)
    @NotNull
    @Column(name = "qtd_parcelas")
    private int qtdParcelas;
    @Column(name = "data_pgto")
    @Temporal(TemporalType.DATE)
    private Date dataPgto;
    @JoinColumn(name = "banco_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Banco bancoId;
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Cliente clienteId;
    @JoinColumn(name = "funcionario_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Funcionario funcionarioId;
    @JoinColumn(name = "tipo_operacao_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TipoOperacao tipoOperacaoId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contrato")
    private List<ContratoHasStatusContrato> contratoHasStatusContratoList;

    public Contrato() {
    }

    public Contrato(Integer id) {
        this.id = id;
    }

    public Contrato(Integer id, BigDecimal valorCliente, String numero, BigDecimal valorContrato, int qtdParcelas) {
        this.id = id;
        this.valorCliente = valorCliente;
        this.numero = numero;
        this.valorContrato = valorContrato;
        this.qtdParcelas = qtdParcelas;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getValorCliente() {
        return valorCliente;
    }

    public void setValorCliente(BigDecimal valorCliente) {
        this.valorCliente = valorCliente;
    }

    public BigDecimal getValorPago() {
        return valorPago;
    }

    public void setValorPago(BigDecimal valorPago) {
        this.valorPago = valorPago;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public BigDecimal getValorContrato() {
        return valorContrato;
    }

    public void setValorContrato(BigDecimal valorContrato) {
        this.valorContrato = valorContrato;
    }

    public int getQtdParcelas() {
        return qtdParcelas;
    }

    public void setQtdParcelas(int qtdParcelas) {
        this.qtdParcelas = qtdParcelas;
    }

    public Date getDataPgto() {
        return dataPgto;
    }

    public void setDataPgto(Date dataPgto) {
        this.dataPgto = dataPgto;
    }

    public Banco getBancoId() {
        return bancoId;
    }

    public void setBancoId(Banco bancoId) {
        this.bancoId = bancoId;
    }

    public Cliente getClienteId() {
        return clienteId;
    }

    public void setClienteId(Cliente clienteId) {
        this.clienteId = clienteId;
    }

    public Funcionario getFuncionarioId() {
        return funcionarioId;
    }

    public void setFuncionarioId(Funcionario funcionarioId) {
        this.funcionarioId = funcionarioId;
    }

    public TipoOperacao getTipoOperacaoId() {
        return tipoOperacaoId;
    }

    public void setTipoOperacaoId(TipoOperacao tipoOperacaoId) {
        this.tipoOperacaoId = tipoOperacaoId;
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
        if (!(object instanceof Contrato)) {
            return false;
        }
        Contrato other = (Contrato) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades_financeira.Contrato[ id=" + id + " ]";
    }
    
}
