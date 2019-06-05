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

import br.com.financeira.entities.Perfil;
import br.com.financeira.entities.Usuario;
import br.com.financeira.services.UsuarioService;
import br.com.financeira.utils.JsfUtil;

@ManagedBean(name="usuarioMb")
@ViewScoped
public class UsuarioMB implements Serializable{
	
	private static final long serialVersionUID = -8451286990814492929L;
	private Usuario usuario;
	private List<Usuario> listaUsuarios;
	@Inject
	private UsuarioService service;
	private Usuario usuarioLogado;
	
	@PostConstruct
	public void init() {
		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		usuarioLogado = (Usuario) request.getSession().getAttribute("usuarioLogado");
		listaUsuarios = new ArrayList<Usuario>();
		usuario = new Usuario();
		carregarLista();
	}
	
	public void carregarLista() {
		listaUsuarios = service.findAll();
//		PrimeFaces.current().ajax().update("usuariopesqform:grid");
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
	
	public void mudarPerfil(Usuario user, Integer idPerfil) {
		Perfil perfil = new Perfil(idPerfil);
		user.setPerfilId(perfil);
		user = service.update(user, usuarioLogado);
		carregarLista();
		JsfUtil.addSuccessMessage("Modificado o perfil do usuário com sucesso!");
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
