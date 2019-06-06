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

import br.com.financeira.entities.Cliente;
import br.com.financeira.entities.Perfil;
import br.com.financeira.entities.Usuario;
import br.com.financeira.services.ClienteService;
import br.com.financeira.utils.JsfUtil;

@ManagedBean(name="clienteMb")
@ViewScoped
public class ClienteMB implements Serializable {
	
	private static final long serialVersionUID = -7230516231380620750L;

	private List<Cliente> listaClientes;
	
	private Cliente cliente;
	
	private Usuario usuarioLogado;
	
	@Inject
	private ClienteService service;
	
	@PostConstruct
	public void init() {
		try {
			if (usuarioLogado == null) {
				HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
				usuarioLogado = (Usuario) request.getSession().getAttribute("usuarioLogado");
			}
			listaClientes = new ArrayList<Cliente>();
			cliente = new Cliente();
			carregarLista();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void carregarLista() {
		if (usuarioLogado.getAdmin() || usuarioLogado.getPerfilId().getId().equals(Perfil.PERFIL_ADMIN)) {
			listaClientes = service.findAll();
		} else {
			if (usuarioLogado.getPerfilId().getId().equals(Perfil.PERFIL_GERENTE) || usuarioLogado.getPerfilId().getId().equals(Perfil.PERFIL_SUPERVISOR)) {
				listaClientes = service.findByFuncionarios(usuarioLogado.getFuncionarioList().get(0).getFuncionarioList());
			} else if (usuarioLogado.getPerfilId().getId().equals(Perfil.PERFIL_FUNCIONARIO)) {
				listaClientes = service.findByFuncionario(usuarioLogado.getFuncionarioList().get(0));
			} 
		}
	}
	
	public void saveOrUpdate() {
		try {
			if (cliente.getId() == null) {
				cliente = service.save(cliente, usuarioLogado);
				JsfUtil.addSuccessMessage("Cliente cadastrado com sucesso.");
				carregarLista();
			} else {
				cliente = service.update(cliente, usuarioLogado);
				JsfUtil.addSuccessMessage("Cliente atualizado com sucesso.");
				carregarLista();
			}
			JsfUtil.closeModal("clienteDialog");
		} catch (Exception e) {
			JsfUtil.addErrorMessage(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void prepararEditar(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public void limpar() {
		cliente = new Cliente();
	}

	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

}
