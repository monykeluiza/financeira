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
import br.com.financeira.entities.ResetSenha;
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
	private Usuario usuarioRedefinirSenha;
	private ResetSenha resetSenha;
	private String emailEsqueci;
	
	@PostConstruct
	public void init() {
		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		usuarioLogado = (Usuario) request.getSession().getAttribute("usuarioLogado");
		listaUsuarios = new ArrayList<Usuario>();
		usuario = new Usuario();
		carregarLista();
	}
	
	public void carregarLista() {
		if (usuarioLogado != null) {
			if (usuarioLogado.getAdmin() || usuarioLogado.getPerfilId().getId().equals(Perfil.PERFIL_ADMIN)) {
				listaUsuarios = service.findAll();
			} else {
				listaUsuarios = new ArrayList<Usuario>();
				listaUsuarios.add(service.findById(usuarioLogado));
			}
		}
		
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
	
	public String redefinirSenha() {
		try {
			String msg = service.redefinirSenha(usuarioRedefinirSenha, resetSenha, usuarioLogado);
			if (msg == null) {
				JsfUtil.closeModal("senhaDialog");
				return "";
			} else {
				JsfUtil.addErrorMessage(msg);
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
			JsfUtil.addFatalMessage();
			return "";
		}
	}
	
	public void esqueciSenha() {
		try {
			 String msgErro = service.esqueciSenha(usuario);
			 if (msgErro != null) {
				 JsfUtil.addErrorMessage(msgErro);
			 } else {
				 JsfUtil.closeModal("esqueciSenhaDialog");
				 JsfUtil.addSuccessMessage("Sua senha foi redefinida. Verifique seu e-mail e siga as instruções para acesso.");
			 }
		} catch (Exception e) {
			e.printStackTrace();
			JsfUtil.addFatalMessage();
		}
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

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public Usuario getUsuarioRedefinirSenha() {
		return usuarioRedefinirSenha;
	}

	public void setUsuarioRedefinirSenha(Usuario usuarioRedefinirSenha) {
		this.usuarioRedefinirSenha = usuarioRedefinirSenha;
	}

	public ResetSenha getResetSenha() {
		return resetSenha;
	}

	public void setResetSenha(ResetSenha resetSenha) {
		this.resetSenha = resetSenha;
	}

	public String getEmailEsqueci() {
		return emailEsqueci;
	}

	public void setEmailEsqueci(String emailEsqueci) {
		this.emailEsqueci = emailEsqueci;
	}
}
