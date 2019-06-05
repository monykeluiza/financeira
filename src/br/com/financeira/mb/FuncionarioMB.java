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

import br.com.financeira.entities.Funcionario;
import br.com.financeira.entities.Perfil;
import br.com.financeira.entities.Usuario;
import br.com.financeira.services.FuncionarioService;
import br.com.financeira.utils.JsfUtil;

@ManagedBean(name="funcionarioMb")
@ViewScoped
public class FuncionarioMB implements Serializable {
	
	private static final long serialVersionUID = 7973007643725141823L;
	private Funcionario funcionario;
	private List<Funcionario> listaFuncionarios;
	@Inject
	private FuncionarioService service;
	private Usuario usuarioLogado;
	
	@PostConstruct
	public void init() {
		listaFuncionarios = new ArrayList<Funcionario>();
		carregarLista();
		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		usuarioLogado = (Usuario) request.getSession().getAttribute("usuarioLogado");
		funcionario = new Funcionario();
	}
	
	public void carregarLista() {
		if (usuarioLogado.getAdmin() || usuarioLogado.getPerfilId().getId().equals(Perfil.PERFIL_ADMIN)) {
			listaFuncionarios = service.findAll();
		} else {
			if (usuarioLogado.getPerfilId().getId().equals(Perfil.PERFIL_GERENTE) || usuarioLogado.getPerfilId().getId().equals(Perfil.PERFIL_SUPERVISOR)) {
				listaFuncionarios = service.findSubordinados(usuarioLogado.getFuncionarioList().get(0));
			} 
		}
	}
	
	public void cadastrar() {
		try {
			funcionario = service.save(funcionario, usuarioLogado);
			JsfUtil.addSuccessMessage("Funcionário cadastrado com sucesso.");
			carregarLista();
			JsfUtil.closeModal("funcionarioDialog");
		} catch (Exception e) {
			JsfUtil.addErrorMessage(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void limpar() {
		funcionario = new Funcionario();
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public List<Funcionario> getListaFuncionarios() {
		return listaFuncionarios;
	}

	public void setListaFuncionarios(List<Funcionario> listaFuncionarios) {
		this.listaFuncionarios = listaFuncionarios;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

}
