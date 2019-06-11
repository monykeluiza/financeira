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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author moniqueluiza
 */
@Entity
@Table(name = "funcionario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Funcionario.findAll", query = "SELECT f FROM Funcionario f"),
    @NamedQuery(name = "Funcionario.findById", query = "SELECT f FROM Funcionario f WHERE f.id = :id"),
    @NamedQuery(name = "Funcionario.findByNome", query = "SELECT f FROM Funcionario f WHERE f.nome = :nome"),
    @NamedQuery(name = "Funcionario.findByCpf", query = "SELECT f FROM Funcionario f WHERE f.cpf = :cpf"),
    @NamedQuery(name = "Funcionario.findByRg", query = "SELECT f FROM Funcionario f WHERE f.rg = :rg"),
    @NamedQuery(name = "Funcionario.findByCtps", query = "SELECT f FROM Funcionario f WHERE f.ctps = :ctps"),
    @NamedQuery(name = "Funcionario.findByPispasep", query = "SELECT f FROM Funcionario f WHERE f.pispasep = :pispasep"),
    @NamedQuery(name = "Funcionario.findByEmail", query = "SELECT f FROM Funcionario f WHERE f.email = :email"),
    @NamedQuery(name = "Funcionario.findByTelefone", query = "SELECT f FROM Funcionario f WHERE f.telefone = :telefone"),
    @NamedQuery(name = "Funcionario.findByWhatsapp", query = "SELECT f FROM Funcionario f WHERE f.whatsapp = :whatsapp"),
    @NamedQuery(name = "Funcionario.findByEndereco", query = "SELECT f FROM Funcionario f WHERE f.endereco = :endereco"),
    @NamedQuery(name = "Funcionario.findByDataNascimento", query = "SELECT f FROM Funcionario f WHERE f.dataNascimento = :dataNascimento"),
    @NamedQuery(name = "Funcionario.findByDataInicio", query = "SELECT f FROM Funcionario f WHERE f.dataInicio = :dataInicio"),
    @NamedQuery(name = "Funcionario.findByChefe", query = "SELECT f FROM Funcionario f WHERE f.chefiaId.id = :chefeId"),
    @NamedQuery(name = "Funcionario.findChefes", query = "SELECT f FROM Funcionario f WHERE f.chefiaId = null"),
    @NamedQuery(name = "Funcionario.findByUsuario", query = "SELECT f FROM Funcionario f WHERE f.usuarioId.id = :usuarioId"),
    @NamedQuery(name = "Funcionario.findByDataSaida", query = "SELECT f FROM Funcionario f WHERE f.dataSaida = :dataSaida")})
public class Funcionario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "cpf")
    private String cpf;
    @Size(max = 45)
    @Column(name = "rg")
    private String rg;
    @Size(max = 100)
    @Column(name = "ctps")
    private String ctps;
    @Size(max = 45)
    @Column(name = "pispasep")
    private String pispasep;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="E-mail inválido")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 100)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "telefone")
    private String telefone;
    @Size(max = 45)
    @Column(name = "whatsapp")
    private String whatsapp;
    @Size(max = 500)
    @Column(name = "endereco")
    private String endereco;
    @Column(name = "data_nascimento")
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data_inicio")
    @Temporal(TemporalType.DATE)
    private Date dataInicio;
    @Column(name = "data_saida")
    @Temporal(TemporalType.DATE)
    private Date dataSaida;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "funcionarioId")
    private List<Contrato> contratoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "funcionarioId")
    private List<Cliente> clienteList;
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Usuario usuarioId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "funcionarioId")
    private List<Contato> contatoList;
    @OneToMany(mappedBy = "chefiaId")
    private List<Funcionario> funcionarioList;
    @OneToMany(mappedBy = "funcionarioId")
    private List<Meta> metaList;
    @JoinColumn(name = "chefia_id", referencedColumnName = "id")
    @ManyToOne
    private Funcionario chefiaId;
    @Transient
    private BigDecimal totalPago;
    @Transient
    private BigDecimal totalDigitado;
   
    public Funcionario() {
    }

    public Funcionario(Integer id) {
        this.id = id;
    }

    public Funcionario(Integer id, String nome, String cpf, String telefone, Date dataInicio) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.dataInicio = dataInicio;
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

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCtps() {
        return ctps;
    }

    public void setCtps(String ctps) {
        this.ctps = ctps;
    }

    public String getPispasep() {
        return pispasep;
    }

    public void setPispasep(String pispasep) {
        this.pispasep = pispasep;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
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

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(Date dataSaida) {
        this.dataSaida = dataSaida;
    }

    @XmlTransient
    public List<Contrato> getContratoList() {
        return contratoList;
    }

    public void setContratoList(List<Contrato> contratoList) {
        this.contratoList = contratoList;
    }

    @XmlTransient
    public List<Cliente> getClienteList() {
        return clienteList;
    }

    public void setClienteList(List<Cliente> clienteList) {
        this.clienteList = clienteList;
    }

    public Usuario getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Usuario usuarioId) {
        this.usuarioId = usuarioId;
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
        if (!(object instanceof Funcionario)) {
            return false;
        }
        Funcionario other = (Funcionario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades_financeira.Funcionario[ id=" + id + " ]";
    }

	public List<Funcionario> getFuncionarioList() {
		return funcionarioList;
	}

	public void setFuncionarioList(List<Funcionario> funcionarioList) {
		this.funcionarioList = funcionarioList;
	}

	public List<Meta> getMetaList() {
		return metaList;
	}

	public void setMetaList(List<Meta> metaList) {
		this.metaList = metaList;
	}

	public Funcionario getChefiaId() {
		return chefiaId;
	}

	public void setChefiaId(Funcionario chefiaId) {
		this.chefiaId = chefiaId;
	}

	public BigDecimal getTotalPago() {
		totalPago = new BigDecimal(0);
		for (Contrato contrato : contratoList) {
//			if (contrato.getContratoHasStatusContratoList()))
		}
		return totalPago;
	}

	public void setTotalPago(BigDecimal totalPago) {
		this.totalPago = totalPago;
	}

	public BigDecimal getTotalDigitado() {
		totalPago = new BigDecimal(0);
		for (Contrato contrato : contratoList) {
//			if (contrato.getContratoHasStatusContratoList()))
		}
		return totalDigitado;
	}

	public void setTotalDigitado(BigDecimal totalDigitado) {
		this.totalDigitado = totalDigitado;
	}
    
}
