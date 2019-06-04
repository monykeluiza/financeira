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

import br.com.financeira.entities.TipoLembrete;
import br.com.financeira.entities.Usuario;
import br.com.financeira.services.TipoLembreteService;
import br.com.financeira.utils.JsfUtil;

@ManagedBean(name="tipoLembreteMb")
@ViewScoped
public class TipoLembreteMB implements Serializable {
	
	private static final long serialVersionUID = -5424473912544013194L;
	private TipoLembrete tipoLembrete;
	private List<TipoLembrete> listaTipoLembretes;
	@Inject
	private TipoLembreteService service;
	private Usuario usuarioLogado;
	
	@PostConstruct
	public void init() {
		listaTipoLembretes = new ArrayList<TipoLembrete>();
		carregarLista();
		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		usuarioLogado = (Usuario) request.getSession().getAttribute("usuarioLogado");
		tipoLembrete = new TipoLembrete();
		carregarLista();
	}
	
	public void carregarLista() {
		listaTipoLembretes = service.findAll();
	}
	
	public void saveOrUpdate() {
		try {
			if (tipoLembrete.getId() == null) {
				tipoLembrete = service.save(tipoLembrete, usuarioLogado);
				JsfUtil.addSuccessMessage("TipoLembrete cadastrado com sucesso.");
				carregarLista();
			} else {
				tipoLembrete = service.update(tipoLembrete, usuarioLogado);
				JsfUtil.addSuccessMessage("TipoLembrete atualizado com sucesso.");
				carregarLista();
			}
			JsfUtil.closeModal("tipoLembreteDialog");
		} catch (Exception e) {
			JsfUtil.addErrorMessage(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void prepararEditar(TipoLembrete tipoLembrete) {
		this.tipoLembrete = tipoLembrete;
	}
	
	public void limpar() {
		tipoLembrete = new TipoLembrete();
	}

	public List<TipoLembrete> getListaTipoLembretes() {
		return listaTipoLembretes;
	}

	public void setListaTipoLembretes(List<TipoLembrete> listaTipoLembretes) {
		this.listaTipoLembretes = listaTipoLembretes;
	}

	public TipoLembrete getTipoLembrete() {
		return tipoLembrete;
	}

	public void setTipoLembrete(TipoLembrete tipoLembrete) {
		this.tipoLembrete = tipoLembrete;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

}
