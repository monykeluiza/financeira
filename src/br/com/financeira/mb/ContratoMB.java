package br.com.financeira.mb;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import br.com.financeira.entities.Cliente;
import br.com.financeira.entities.Contrato;
import br.com.financeira.entities.Perfil;
import br.com.financeira.entities.StatusContrato;
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
	
	private BigDecimal totalValorPago;
	private BigDecimal totalValorCliente;
	private BigDecimal totalValorContrato;
	
	@Inject
	private ContratoService service;
	
	@PostConstruct
	public void init() {
		try {
			if (usuarioLogado == null) {
				HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
				usuarioLogado = (Usuario) request.getSession().getAttribute("usuarioLogado");
			}
			
			contrato = new Contrato();
			if (usuarioLogado.getPerfilId().getId().equals(Perfil.PERFIL_FUNCIONARIO)) {
				contrato.setFuncionarioId(usuarioLogado.getFuncionarioList().get(0));
				isFuncionario = true;
			}
			listaContratos = new ArrayList<Contrato>();
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
		contrato.setDataCriacao(new Date());
	}
	
	public void limparSetandoCliente(Cliente cliente) {
		limpar();
		contrato.setClienteId(cliente);
		contrato.setFuncionarioId(cliente.getFuncionarioId());
	}
	
	public void updateStatusPago() {
		contrato = service.updateStatus(contrato, usuarioLogado, StatusContrato.PAGO);
		JsfUtil.addSuccessMessage("Contrato atualizado com sucesso.");
		JsfUtil.closeModal("pagoDialog");
		carregarLista();
	}
	
	public void updateStatusCancelado(Contrato c) {
		contrato = service.updateStatus(c, usuarioLogado, StatusContrato.CANCELADO);
		JsfUtil.addSuccessMessage("Contrato atualizado com sucesso.");
		JsfUtil.closeModal("pagoDialog");
		carregarLista();
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

	public BigDecimal getTotalValorPago() {
		totalValorPago = new BigDecimal(0);
		for (Contrato contrato : listaContratos) {
			if (contrato.getValorPago() != null) {
				totalValorPago = totalValorPago.add(contrato.getValorPago());
			}
		}
		return totalValorPago;
	}

	public void setTotalValorPago(BigDecimal totalValorPago) {
		this.totalValorPago = totalValorPago;
	}

	public BigDecimal getTotalValorCliente() {
		totalValorCliente = new BigDecimal(0);
		for (Contrato contrato : listaContratos) {
			totalValorCliente = totalValorCliente.add(contrato.getValorCliente());
		}
		return totalValorCliente;
	}

	public void setTotalValorCliente(BigDecimal totalValorCliente) {
		this.totalValorCliente = totalValorCliente;
	}

	public BigDecimal getTotalValorContrato() {
		totalValorContrato = new BigDecimal(0);
		for (Contrato contrato : listaContratos) {
			totalValorContrato = totalValorContrato.add(contrato.getValorContrato());
		}
		return totalValorContrato;
	}

	public void setTotalValorContrato(BigDecimal totalValorContrato) {
		this.totalValorContrato = totalValorContrato;
	}

}
