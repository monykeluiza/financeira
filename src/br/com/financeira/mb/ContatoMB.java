package br.com.financeira.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import br.com.financeira.entities.Cliente;
import br.com.financeira.entities.Contato;
import br.com.financeira.entities.Perfil;
import br.com.financeira.entities.Usuario;
import br.com.financeira.services.ContatoService;
import br.com.financeira.utils.JsfUtil;

@ManagedBean(name="contatoMb")
@ViewScoped
public class ContatoMB implements Serializable {
	
	private static final long serialVersionUID = -7230516231380620750L;

	private List<Contato> listaContatos;
	
	private Contato contato;
	
	private Usuario usuarioLogado;
	
	@Inject
	private ContatoService service;
	
	@ManagedProperty(value="#{clienteMb}") 
	ClienteMB clienteMb;
	
	@PostConstruct
	public void init() {
		try {
			if (usuarioLogado == null) {
				HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
				usuarioLogado = (Usuario) request.getSession().getAttribute("usuarioLogado");
			}
			listaContatos = new ArrayList<Contato>();
			contato = new Contato();
			carregarLista();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void carregarLista() {
		if (usuarioLogado.getAdmin() || usuarioLogado.getPerfilId().getId().equals(Perfil.PERFIL_ADMIN)) {
			listaContatos = service.findAll();
		} else {
			if (usuarioLogado.getPerfilId().getId().equals(Perfil.PERFIL_GERENTE) || usuarioLogado.getPerfilId().getId().equals(Perfil.PERFIL_SUPERVISOR)) {
				listaContatos = service.findByFuncionarios(usuarioLogado.getFuncionarioList().get(0).getFuncionarioList());
			} else if (usuarioLogado.getPerfilId().getId().equals(Perfil.PERFIL_FUNCIONARIO)) {
				listaContatos = service.findByFuncionario(usuarioLogado.getFuncionarioList().get(0));
			} 
		}
	}
	
	public void saveOrUpdate() {
		try {
			if (contato.getId() == null) {
				contato = service.save(contato, usuarioLogado);
				JsfUtil.addSuccessMessage("Contato cadastrado com sucesso.");
				carregarLista();
			} else {
				contato = service.update(contato, usuarioLogado);
				JsfUtil.addSuccessMessage("Contato atualizado com sucesso.");
				carregarLista();
			}
			clienteMb.carregarLista();
			JsfUtil.closeModal("contatoDialog");
		} catch (Exception e) {
			JsfUtil.addErrorMessage(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void prepararEditar(Contato contato) {
		this.contato = contato;
	}
	
	public void limpar(Cliente cliente) {
		contato = new Contato();
		contato.setClienteId(cliente);
		contato.setData(new Date());
	}

	public List<Contato> getListaContatos() {
		return listaContatos;
	}

	public void setListaContatos(List<Contato> listaContatos) {
		this.listaContatos = listaContatos;
	}

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public ClienteMB getClienteMb() {
		return clienteMb;
	}

	public void setClienteMb(ClienteMB clienteMb) {
		this.clienteMb = clienteMb;
	}

}
