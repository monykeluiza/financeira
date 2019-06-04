	package br.com.financeira.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import br.com.financeira.entities.Banco;
import br.com.financeira.entities.Usuario;
import br.com.financeira.services.BancoService;
import br.com.financeira.utils.JsfUtil;

@ManagedBean(name="bancoMb")
@ViewScoped
public class BancoMB implements Serializable {
	
	private static final long serialVersionUID = -5424473912544013194L;
	private Banco banco;
	private List<Banco> listaBancos;
	@Inject
	private BancoService service;
	private Usuario usuarioLogado;
	
	@PostConstruct
	public void init() {
		listaBancos = new ArrayList<Banco>();
		carregarLista();
		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		usuarioLogado = (Usuario) request.getSession().getAttribute("usuarioLogado");
		banco = new Banco();
		carregarLista();
	}
	
	public void carregarLista() {
		listaBancos = service.findAll();
	}
	
	public void saveOrUpdate() {
		try {
			if (banco.getId() == null) {
				banco = service.save(banco, usuarioLogado);
				JsfUtil.addSuccessMessage("Banco cadastrado com sucesso.");
				carregarLista();
			} else {
				banco = service.update(banco, usuarioLogado);
				JsfUtil.addSuccessMessage("Banco atualizado com sucesso.");
				carregarLista();
			}
			JsfUtil.closeModal("bancoDialog");
		} catch (Exception e) {
			JsfUtil.addErrorMessage(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void ativar(Banco banco) {
		banco.setAtivo(true);
		service.update(banco, usuarioLogado);
		JsfUtil.addSuccessMessage("Banco ativado com sucesso.");
	}
	
	public void desativar(Banco banco) {
		banco.setAtivo(false);
		service.update(banco, usuarioLogado);
		JsfUtil.addSuccessMessage("Banco desativado com sucesso. O banco não aparecerá mais nas listas de cadastro.");
	}
	
	public void prepararEditar(Banco banco) {
		this.banco = banco;
	}
	
	public void limpar() {
		banco = new Banco();
	}

	public List<Banco> getListaBancos() {
		return listaBancos;
	}

	public void setListaBancos(List<Banco> listaBancos) {
		this.listaBancos = listaBancos;
	}

	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

}
