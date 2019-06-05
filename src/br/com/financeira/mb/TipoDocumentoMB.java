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

import br.com.financeira.entities.TipoDocumento;
import br.com.financeira.entities.Usuario;
import br.com.financeira.services.TipoDocumentoService;
import br.com.financeira.utils.JsfUtil;

@ManagedBean(name="tipoDocumentoMb")
@ViewScoped
public class TipoDocumentoMB implements Serializable {
	
	private static final long serialVersionUID = 6113886826049360540L;
	private TipoDocumento tipoDocumento;
	private List<TipoDocumento> listaTipoDocumento;
	@Inject
	private TipoDocumentoService service;
	private Usuario usuarioLogado;
	
	@PostConstruct
	public void init() {
		listaTipoDocumento = new ArrayList<TipoDocumento>();
		carregarLista();
		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		usuarioLogado = (Usuario) request.getSession().getAttribute("usuarioLogado");
		tipoDocumento = new TipoDocumento();
		carregarLista();
	}
	
	public void carregarLista() {
		listaTipoDocumento = service.findAll();
	}
	
	public void saveOrUpdate() {
		try {
			if (tipoDocumento.getId() == null) {
				tipoDocumento = service.save(tipoDocumento, usuarioLogado);
				JsfUtil.addSuccessMessage("TipoDocumento cadastrado com sucesso.");
				carregarLista();
			} else {
				tipoDocumento = service.update(tipoDocumento, usuarioLogado);
				JsfUtil.addSuccessMessage("TipoDocumento atualizado com sucesso.");
				carregarLista();
			}
			JsfUtil.closeModal("tipoDocumentoDialog");
		} catch (Exception e) {
			JsfUtil.addErrorMessage(e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	public void prepararEditar(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	
	public void limpar() {
		tipoDocumento = new TipoDocumento();
	}

	public List<TipoDocumento> getListaTipoDocumento() {
		return listaTipoDocumento;
	}

	public void setListaTipoDocumento(List<TipoDocumento> listaTipoDocumento) {
		this.listaTipoDocumento = listaTipoDocumento;
	}

	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

}
