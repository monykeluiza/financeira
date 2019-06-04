package br.com.financeira.mb;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.PrimeFaces;

import br.com.financeira.entities.Usuario;
import br.com.financeira.services.UsuarioService;
import br.com.financeira.utils.JsfUtil;

@ManagedBean(name="clienteMB")
@ViewScoped
public class UsuarioMB {
	
	private Usuario usuario;
	private List<Usuario> listaUsuarios;
	@Inject
	private UsuarioService service;
	private Usuario usuarioLogado;
	
	@PostConstruct
	public void init() {
		listaUsuarios = new ArrayList<Usuario>();
		carregarLista();
		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		usuarioLogado = (Usuario) request.getSession().getAttribute("usuarioLogado");
		usuario = new Usuario();
	}
	
	public void carregarLista() {
		listaUsuarios = service.findAll();
		PrimeFaces.current().ajax().update("usuariopesqform:grid");
	}
	
	public void ativar(Usuario usuario) {
		usuario.setAtivo(true);
		usuario = service.update(usuario, usuarioLogado);
		JsfUtil.addSuccessMessage("Usuário ativado com sucesso.");
	}
	
	public void desativar(Usuario usuario) {
		usuario.setAtivo(false);
		usuario = service.update(usuario, usuarioLogado);
		JsfUtil.addSuccessMessage("Usuário desativado com sucesso.");
	}
	
	public void tornarAdmin(Usuario usuario) {
		usuario.setAdmin(true);
		usuario = service.update(usuario, usuarioLogado);
		JsfUtil.addSuccessMessage("Usuário configurado como administrador com sucesso!");
	}
	
	public void retirarAdmin(Usuario usuario) {
		usuario.setAdmin(false);
		usuario = service.update(usuario, usuarioLogado);
		JsfUtil.addSuccessMessage("Usuário retirado de administrador com sucesso!");
	}
	
	public void cadastrar() {
		usuario.setAtivo(false);
		try {
			usuario = service.save(usuario, usuarioLogado);
			JsfUtil.addSuccessMessage("Usuário cadastrado com sucesso.");
			carregarLista();
			JsfUtil.closeModal("usuarioDialog");
		} catch (Exception e) {
			JsfUtil.addErrorMessage(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void limpar() {
		usuario = new Usuario();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}
}
