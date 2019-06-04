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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Monique
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Log.findAll", query = "SELECT l FROM Log l")
    , @NamedQuery(name = "Log.findById", query = "SELECT l FROM Log l WHERE l.id = :id")
    , @NamedQuery(name = "Log.findByData", query = "SELECT l FROM Log l WHERE l.data = :data")
    , @NamedQuery(name = "Log.findByIdUsuario", query = "SELECT l FROM Log l WHERE l.idUsuario = :idUsuario")
    , @NamedQuery(name = "Log.findByLogin", query = "SELECT l FROM Log l WHERE l.login = :login")
    , @NamedQuery(name = "Log.findByNome", query = "SELECT l FROM Log l WHERE l.nome = :nome")
    , @NamedQuery(name = "Log.findByIdTabela", query = "SELECT l FROM Log l WHERE l.idTabela = :idTabela")
    , @NamedQuery(name = "Log.findByAcao", query = "SELECT l FROM Log l WHERE l.acao = :acao")
    , @NamedQuery(name = "Log.findByNomeTabela", query = "SELECT l FROM Log l WHERE l.nomeTabela = :nomeTabela")})
public class Log implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    @Basic(optional = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;
    @Basic(optional = false)
    @Column(name = "id_usuario")
    private int idUsuario;
    @Basic(optional = false)
    private String login;
    @Basic(optional = false)
    private String nome;
    @Basic(optional = false)
    @Column(name = "id_tabela")
    private int idTabela;
    @Basic(optional = false)
    private String acao;
    @Basic(optional = false)
    @Column(name = "nome_tabela")
    private String nomeTabela;

    public Log() {
    }

    public Log(Integer id) {
        this.id = id;
    }

    public Log(Integer id, Date data, int idUsuario, String login, String nome, int idTabela, String acao, String nomeTabela) {
        this.id = id;
        this.data = data;
        this.idUsuario = idUsuario;
        this.login = login;
        this.nome = nome;
        this.idTabela = idTabela;
        this.acao = acao;
        this.nomeTabela = nomeTabela;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdTabela() {
        return idTabela;
    }

    public void setIdTabela(int idTabela) {
        this.idTabela = idTabela;
    }

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public String getNomeTabela() {
        return nomeTabela;
    }

    public void setNomeTabela(String nomeTabela) {
        this.nomeTabela = nomeTabela;
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
        if (!(object instanceof Log)) {
            return false;
        }
        Log other = (Log) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades_crm.Log[ id=" + id + " ]";
    }
    
}
