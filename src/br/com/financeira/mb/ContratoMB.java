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

import br.com.financeira.entities.Contrato;
import br.com.financeira.entities.Perfil;
import br.com.financeira.entities.Usuario;
import br.com.financeira.services.ContratoService;
import br.com.financeira.utils.JsfUtil;

@ManagedBean(name="contratoMb")
@ViewScoped
public class ContratoMB implements Serializable {
	
	private static final long serialVersionUID = -7230516231380620750L;

	private List<Contrato> listaContratos;
	
	private Contrato contrato;
	
	private Usuario usuarioLogado;
	
	private boolean isFuncionario;
	
	@Inject
	private ContratoService service;
	
	@PostConstruct
	public void init() {
		try {
			if (usuarioLogado == null) {
				HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
				usuarioLogado = (Usuario) request.getSession().getAttribute("usuarioLogado");
			}
			listaContratos = new ArrayList<Contrato>();
			contrato = new Contrato();
			if (usuarioLogado.getPerfilId().getId().equals(Perfil.PERFIL_FUNCIONARIO)) {
				contrato.setFuncionarioId(usuarioLogado.getFuncionarioList().get(0));
				isFuncionario = true;
			}
			carregarLista();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void carregarLista() {
		if (usuarioLogado.getAdmin() || usuarioLogado.getPerfilId().getId().equals(Perfil.PERFIL_ADMIN)) {
			listaContratos = service.findAll();
		} else {
			if (usuarioLogado.getPerfilId().getId().equals(Perfil.PERFIL_GERENTE) || usuarioLogado.getPerfilId().getId().equals(Perfil.PERFIL_SUPERVISOR)) {
				listaContratos = service.findByFuncionario(usuarioLogado.getFuncionarioList().get(0));
				listaContratos.addAll(service.findByFuncionarios(usuarioLogado.getFuncionarioList().get(0).getFuncionarioList()));
			} else if (usuarioLogado.getPerfilId().getId().equals(Perfil.PERFIL_FUNCIONARIO)) {
				listaContratos = service.findByFuncionario(usuarioLogado.getFuncionarioList().get(0));
			} 
		}
	}
	
	public void saveOrUpdate() {
		try {
			if (contrato.getId() == null) {
				contrato = service.save(contrato, usuarioLogado);
				JsfUtil.addSuccessMessage("Contrato cadastrado com sucesso.");
				carregarLista();
			} else {
				contrato = service.update(contrato, usuarioLogado);
				JsfUtil.addSuccessMessage("Contrato atualizado com sucesso.");
				carregarLista();
			}
			JsfUtil.closeModal("contratoDialog");
		} catch (Exception e) {
			JsfUtil.addErrorMessage(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void prepararEditar(Contrato contrato) {
		this.contrato = contrato;
		if (usuarioLogado.getPerfilId().getId().equals(Perfil.PERFIL_FUNCIONARIO)) {
			contrato.setFuncionarioId(usuarioLogado.getFuncionarioList().get(0));
			isFuncionario = true;
		}
	}
	
	public void limpar() {
		contrato = new Contrato();
		if (usuarioLogado.getPerfilId().getId().equals(Perfil.PERFIL_FUNCIONARIO)) {
			contrato.setFuncionarioId(usuarioLogado.getFuncionarioList().get(0));
			isFuncionario = true;
		}
	}

	public List<Contrato> getListaContratos() {
		return listaContratos;
	}

	public void setListaContratos(List<Contrato> listaContratos) {
		this.listaContratos = listaContratos;
	}

	public Contrato getContrato() {
		return contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public boolean getIsFuncionario() {
		return isFuncionario;
	}

	public void setFuncionario(boolean isFuncionario) {
		this.isFuncionario = isFuncionario;
	}

}
