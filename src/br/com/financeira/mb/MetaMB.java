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

import br.com.financeira.entities.Meta;
import br.com.financeira.entities.Perfil;
import br.com.financeira.entities.Usuario;
import br.com.financeira.services.MetaService;
import br.com.financeira.utils.JsfUtil;

@ManagedBean(name="metaMb")
@ViewScoped
public class MetaMB implements Serializable {
	
	private static final long serialVersionUID = -7230516231380620750L;

	private List<Meta> listaMetas;
	
	private Meta meta;
	
	private Usuario usuarioLogado;
	
	@Inject
	private MetaService service;
	
	@PostConstruct
	public void init() {
		try {
			if (usuarioLogado == null) {
				HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
				usuarioLogado = (Usuario) request.getSession().getAttribute("usuarioLogado");
			}
			listaMetas = new ArrayList<Meta>();
			meta = new Meta();
			carregarLista();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void carregarLista() {
		if (usuarioLogado.getAdmin() || usuarioLogado.getPerfilId().getId().equals(Perfil.PERFIL_ADMIN)) {
			listaMetas = service.findAll();
		} else {
			if (usuarioLogado.getPerfilId().getId().equals(Perfil.PERFIL_GERENTE) || usuarioLogado.getPerfilId().getId().equals(Perfil.PERFIL_SUPERVISOR)) {
				listaMetas = service.findByFuncionarios(usuarioLogado.getFuncionarioList().get(0).getFuncionarioList());
			} else if (usuarioLogado.getPerfilId().getId().equals(Perfil.PERFIL_FUNCIONARIO)) {
				listaMetas = service.findByFuncionario(usuarioLogado.getFuncionarioList().get(0));
			} 
		}
	}
	
	public void saveOrUpdate() {
		try {
			if (meta.getId() == null) {
				meta = service.save(meta, usuarioLogado);
				JsfUtil.addSuccessMessage("Meta cadastrado com sucesso.");
				carregarLista();
			} else {
				meta = service.update(meta, usuarioLogado);
				JsfUtil.addSuccessMessage("Meta atualizado com sucesso.");
				carregarLista();
			}
			JsfUtil.closeModal("metaDialog");
		} catch (Exception e) {
			JsfUtil.addErrorMessage(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void prepararEditar(Meta meta) {
		this.meta = meta;
	}
	
	public void limpar() {
		meta = new Meta();
	}

	public List<Meta> getListaMetas() {
		return listaMetas;
	}

	public void setListaMetas(List<Meta> listaMetas) {
		this.listaMetas = listaMetas;
	}

	public Meta getMeta() {
		return meta;
	}

	public void setMeta(Meta meta) {
		this.meta = meta;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

}
