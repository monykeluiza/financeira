/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.financeira.entities;

import java.io.Serializable;
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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import br.com.financeira.utils.Util;

/**
 *
 * @author moniqueluiza
 */
@Entity
@Table(name = "cliente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c order by c.id desc"),
    @NamedQuery(name = "Cliente.findById", query = "SELECT c FROM Cliente c WHERE c.id = :id"),
    @NamedQuery(name = "Cliente.findByFuncionario", query = "SELECT c FROM Cliente c WHERE c.funcionarioId.id = :idFuncionario order by c.id desc"),
    @NamedQuery(name = "Cliente.findByNome", query = "SELECT c FROM Cliente c WHERE c.nome = :nome"),
    @NamedQuery(name = "Cliente.findByCpf", query = "SELECT c FROM Cliente c WHERE c.cpf = :cpf"),
    @NamedQuery(name = "Cliente.findByDataNasc", query = "SELECT c FROM Cliente c WHERE function('month',c.dataNasc) = :mes"),
    @NamedQuery(name = "Cliente.findByEmail", query = "SELECT c FROM Cliente c WHERE c.email = :email"),
    @NamedQuery(name = "Cliente.findByTelefones", query = "SELECT c FROM Cliente c WHERE c.telefones = :telefones"),
    @NamedQuery(name = "Cliente.findByWhatsapp", query = "SELECT c FROM Cliente c WHERE c.whatsapp = :whatsapp"),
    @NamedQuery(name = "Cliente.findByEndereco", query = "SELECT c FROM Cliente c WHERE c.endereco = :endereco"),
    @NamedQuery(name = "Cliente.findBySiape", query = "SELECT c FROM Cliente c WHERE c.siape = :siape"),
    @NamedQuery(name = "Cliente.findByRg", query = "SELECT c FROM Cliente c WHERE c.rg = :rg"),
    @NamedQuery(name = "Cliente.findByOrgao", query = "SELECT c FROM Cliente c WHERE c.orgao = :orgao")})
public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "nome")
    private String nome;
    @Size(max = 15)
    @Column(name = "cpf")
    private String cpf;
    @Column(name = "data_nasc")
    @Temporal(TemporalType.DATE)
    private Date dataNasc;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="E-mail inválido")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 200)
    @Column(name = "email")
    private String email;
    @Size(max = 200)
    @Column(name = "telefones")
    private String telefones;
    @Size(max = 100)
    @Column(name = "whatsapp")
    private String whatsapp;
    @Size(max = 500)
    @Column(name = "endereco")
    private String endereco;
    @Size(max = 10)
    @Column(name = "siape")
    private String siape;
    @Size(max = 45)
    @Column(name = "rg")
    private String rg;
    @Size(max = 400)
    @Column(name = "orgao")
    private String orgao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clienteId")
    private List<Contrato> contratoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clienteId")
    private List<Documento> documentoList;
    @JoinColumn(name = "funcionario_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Funcionario funcionarioId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clienteId")
    private List<Contato> contatoList;
    @Column(name = "beneficio")
    private Integer beneficio;
    @Transient
    private String rowStyleClass;

    public Cliente() {
    }

    public Cliente(Integer id) {
        this.id = id;
    }

    public Cliente(Integer id, String nome) {
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefones() {
        return telefones;
    }

    public void setTelefones(String telefones) {
        this.telefones = telefones;
    }

    public String getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getSiape() {
        return siape;
    }

    public void setSiape(String siape) {
        this.siape = siape;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getOrgao() {
        return orgao;
    }

    public void setOrgao(String orgao) {
        this.orgao = orgao;
    }

    @XmlTransient
    public List<Contrato> getContratoList() {
        return contratoList;
    }

    public void setContratoList(List<Contrato> contratoList) {
        this.contratoList = contratoList;
    }

    @XmlTransient
    public List<Documento> getDocumentoList() {
        return documentoList;
    }

    public void setDocumentoList(List<Documento> documentoList) {
        this.documentoList = documentoList;
    }

    public Funcionario getFuncionarioId() {
        return funcionarioId;
    }

    public void setFuncionarioId(Funcionario funcionarioId) {
        this.funcionarioId = funcionarioId;
    }

    @XmlTransient
    public List<Contato> getContatoList() {
        return contatoList;
    }

    public void setContatoList(List<Contato> contatoList) {
        this.contatoList = contatoList;
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
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades_financeira.Cliente[ id=" + id + " ]";
    }

	public Integer getBeneficio() {
		return beneficio;
	}

	public void setBeneficio(Integer beneficio) {
		this.beneficio = beneficio;
	}

	public String getRowStyleClass() {
		if (Util.getDayFromDate(new Date()).equals(Util.getDayFromDate(getDataNasc()))) {
			return "row_blue";
		}
		return rowStyleClass;
	}

	public void setRowStyleClass(String rowStyleClass) {
		this.rowStyleClass = rowStyleClass;
	}
    
}
