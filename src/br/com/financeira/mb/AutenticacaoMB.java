package br.com.financeira.mb;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.financeira.entities.Usuario;
import br.com.financeira.services.AutenticacaoService;
import br.com.financeira.utils.JsfUtil;
import javax.faces.bean.SessionScoped;



@ManagedBean(name="autenticacaoMb") 
@SessionScoped
public class AutenticacaoMB implements Serializable {
	
	private static final long serialVersionUID = -8414938967269220264L;
	private Usuario usuario;
	@Inject
	private AutenticacaoService autenticacaoService;
	
	private boolean renderUsuarioLogado = false;
	private boolean renderUsuarioLogadoAdmin = false;
	private Date dataAtual = new Date();
	
	public String autenticar() {
		try {
			usuario = autenticacaoService.autenticar(usuario);
			renderUsuarioLogadoAdmin = (usuario.getAdmin());
			return doLogin();
		} catch (Exception e) {
			renderUsuarioLogadoAdmin = false;
			JsfUtil.addErrorMessage(e.getMessage());
			e.printStackTrace();
			return "";
		}
	}
	
	public String doLogin() { 
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		session.setAttribute("usuarioLogado", usuario);
		renderUsuarioLogado = true;
		try {
			HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
			FacesContext.getCurrentInstance().getExternalContext().redirect(request.getContextPath() + "/pages/welcome.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "/index.xhtml";
	}
	
	public String doLogout() { 
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		session.setAttribute("usuarioLogado", null);
		renderUsuarioLogado = false;
		try {
			HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
			FacesContext.getCurrentInstance().getExternalContext().redirect(request.getContextPath() + "/index.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "/index.xhtml";
	}
		
	public Usuario getUsuario() {
		if (usuario == null) {
			usuario = new Usuario();
		}
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public boolean isRenderUsuarioLogado() {
		return renderUsuarioLogado;
	}

	public void setRenderUsuarioLogado(boolean renderUsuarioLogado) {
		this.renderUsuarioLogado = renderUsuarioLogado;
	}

	public Date getDataAtual() {
		return dataAtual;
	}

	public void setDataAtual(Date dataAtual) {
		this.dataAtual = dataAtual;
	}

	public boolean isRenderUsuarioLogadoAdmin() {
		return renderUsuarioLogadoAdmin;
	}

	public void setRenderUsuarioLogadoAdmin(boolean renderUsuarioLogadoAdmin) {
		this.renderUsuarioLogadoAdmin = renderUsuarioLogadoAdmin;
	} 
	
	
	
	
	
	
	

}
