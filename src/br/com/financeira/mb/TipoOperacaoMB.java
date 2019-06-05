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

import br.com.financeira.entities.TipoOperacao;
import br.com.financeira.entities.Usuario;
import br.com.financeira.services.TipoOperacaoService;
import br.com.financeira.utils.JsfUtil;

@ManagedBean(name="tipoOperacaoMb")
@ViewScoped
public class TipoOperacaoMB implements Serializable {
	
	private static final long serialVersionUID = -5424473912544013194L;
	private TipoOperacao tipoOperacao;
	private List<TipoOperacao> listaTipoOperacao;
	@Inject
	private TipoOperacaoService service;
	private Usuario usuarioLogado;
	
	@PostConstruct
	public void init() {
		listaTipoOperacao = new ArrayList<TipoOperacao>();
		carregarLista();
		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		usuarioLogado = (Usuario) request.getSession().getAttribute("usuarioLogado");
		tipoOperacao = new TipoOperacao();
		carregarLista();
	}
	
	public void carregarLista() {
		listaTipoOperacao = service.findAll();
	}
	
	public void saveOrUpdate() {
		try {
			if (tipoOperacao.getId() == null) {
				tipoOperacao = service.save(tipoOperacao, usuarioLogado);
				JsfUtil.addSuccessMessage("TipoOperacao cadastrado com sucesso.");
				carregarLista();
			} else {
				tipoOperacao = service.update(tipoOperacao, usuarioLogado);
				JsfUtil.addSuccessMessage("TipoOperacao atualizado com sucesso.");
				carregarLista();
			}
			JsfUtil.closeModal("tipooperacaoDialog");
		} catch (Exception e) {
			JsfUtil.addErrorMessage(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void ativar(TipoOperacao tipo) {
		tipo.setAtivo(true);
		service.update(tipo, usuarioLogado);
		JsfUtil.addSuccessMessage("Tipo Operação ativado com sucesso.");
	}
	
	public void desativar(TipoOperacao tipo) {
		tipo.setAtivo(false);
		service.update(tipo, usuarioLogado);
		JsfUtil.addSuccessMessage("Tipo Operação desativado com sucesso. O Tipo não aparecerá mais nas listas de cadastro.");
	}
	
	public void prepararEditar(TipoOperacao tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}
	
	public void limpar() {
		tipoOperacao = new TipoOperacao();
	}

	public List<TipoOperacao> getListaTipoOperacao() {
		return listaTipoOperacao;
	}

	public void setListaTipoOperacao(List<TipoOperacao> listaTipoOperacao) {
		this.listaTipoOperacao = listaTipoOperacao;
	}

	public TipoOperacao getTipoOperacao() {
		return tipoOperacao;
	}

	public void setTipoOperacao(TipoOperacao tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

}
