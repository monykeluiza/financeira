package br.com.financeira.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import br.com.financeira.entities.Lembrete;
import br.com.financeira.entities.Perfil;
import br.com.financeira.entities.Usuario;
import br.com.financeira.services.LembreteService;
import br.com.financeira.utils.JsfUtil;

@ManagedBean(name="lembreteMb")
@ViewScoped
public class LembreteMB implements Serializable {
	
	private static final long serialVersionUID = -7230516231380620750L;

	private List<Lembrete> listaLembretes;
	
	private Lembrete lembrete;
	
	private Usuario usuarioLogado;
	
	private Date dataAtual;
	
	@Inject
	private LembreteService service;
	
	@PostConstruct
	public void init() {
		try {
			if (usuarioLogado == null) {
				HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
				usuarioLogado = (Usuario) request.getSession().getAttribute("usuarioLogado");
			}
			dataAtual = new Date();
			listaLembretes = new ArrayList<Lembrete>();
			lembrete = new Lembrete();
			carregarLista();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void carregarLista() {
		if (usuarioLogado.getAdmin() || usuarioLogado.getPerfilId().getId().equals(Perfil.PERFIL_ADMIN)) {
			listaLembretes = service.findAll();
		} else {
			if (usuarioLogado.getPerfilId().getId().equals(Perfil.PERFIL_GERENTE) || usuarioLogado.getPerfilId().getId().equals(Perfil.PERFIL_SUPERVISOR)) {
				listaLembretes = service.findByFuncionarios(usuarioLogado.getFuncionarioList().get(0).getFuncionarioList());
				listaLembretes.addAll(service.findByFuncionario(usuarioLogado.getFuncionarioList().get(0)));
			} else if (usuarioLogado.getPerfilId().getId().equals(Perfil.PERFIL_FUNCIONARIO)) {
				listaLembretes = service.findByFuncionario(usuarioLogado.getFuncionarioList().get(0));
			} 
		}
	}
	
	public void saveOrUpdate() {
		try {
			if (lembrete.getId() == null) {
				lembrete = service.save(lembrete, usuarioLogado);
				JsfUtil.addSuccessMessage("Lembrete cadastrado com sucesso.");
				carregarLista();
			} else {
				lembrete = service.update(lembrete, usuarioLogado);
				JsfUtil.addSuccessMessage("Lembrete atualizado com sucesso.");
				carregarLista();
			}
			JsfUtil.closeModal("lembreteDialog");
		} catch (Exception e) {
			JsfUtil.addErrorMessage(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void executado(Lembrete lembrete) {
		lembrete.setExecutado(true);
		service.update(lembrete, usuarioLogado);
		JsfUtil.addSuccessMessage("Lembrete atualizado com sucesso.");
	}
	
	public void prepararEditar(Lembrete lembrete) {
		this.lembrete = lembrete;
	}
	
	public void limpar() {
		lembrete = new Lembrete();
	}

	public List<Lembrete> getListaLembretes() {
		return listaLembretes;
	}

	public void setListaLembretes(List<Lembrete> listaLembretes) {
		this.listaLembretes = listaLembretes;
	}

	public Lembrete getLembrete() {
		return lembrete;
	}

	public void setLembrete(Lembrete lembrete) {
		this.lembrete = lembrete;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public Date getDataAtual() {
		return dataAtual;
	}

	public void setDataAtual(Date dataAtual) {
		this.dataAtual = dataAtual;
	}

}
