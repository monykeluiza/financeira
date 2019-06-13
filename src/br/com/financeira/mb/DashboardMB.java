package br.com.financeira.mb;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import br.com.financeira.entities.Contrato;
import br.com.financeira.entities.Funcionario;
import br.com.financeira.entities.Lembrete;
import br.com.financeira.entities.Perfil;
import br.com.financeira.entities.StatusContrato;
import br.com.financeira.entities.Usuario;
import br.com.financeira.services.ContratoService;
import br.com.financeira.services.FuncionarioService;
import br.com.financeira.services.LembreteService;
import br.com.financeira.utils.Util;

@ManagedBean(name="dashboardMB")
@ViewScoped
public class DashboardMB implements Serializable {

	private static final long serialVersionUID = 5537056707570047731L;
	private int totalContratosPagos;
	private BigDecimal valorTotalContratosPagos;
	private int totalContratosAndamento;
	private BigDecimal valorTotalContratosAndamento;
	private int totalContratosCancelados;
	private BigDecimal valorTotalContratosCancelados;
	private Integer anoPesquisa;
	private Integer mesPesquisa;
	
	private List<Contrato> listaContratos;
	private List<Lembrete> listaLembretes;
	private List<Funcionario> listaFuncionarios;
	private Usuario usuarioLogado;
	
	@Inject
	private ContratoService contratoService;
	
	@Inject
	private LembreteService lembreteService;
	
	@Inject
	private FuncionarioService funcionarioService;
	
	@PostConstruct
	public void init() {
		if (usuarioLogado == null) {
			HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
			usuarioLogado = (Usuario) request.getSession().getAttribute("usuarioLogado");
		}
		listaFuncionarios = new ArrayList<Funcionario>();
		listaContratos = new ArrayList<Contrato>();
		carregarLista();
		pesquisar();
		
		totalContratosPagos = 0;
		valorTotalContratosPagos = new BigDecimal(0);
		valorTotalContratosAndamento = new BigDecimal(0);
		valorTotalContratosCancelados = new BigDecimal(0);
		for (Contrato contrato : listaContratos) {
			if (contrato.getUltimoStatus().getId().equals(StatusContrato.PAGO)) {
				totalContratosPagos++;
				valorTotalContratosPagos = valorTotalContratosPagos.add(contrato.getValorPago());
			}
		}
		
		totalContratosAndamento = 0;
		for (Contrato contrato : listaContratos) {
			if (contrato.getUltimoStatus().getId().equals(StatusContrato.ANDAMENTO)) {
				totalContratosAndamento++;
				valorTotalContratosAndamento = valorTotalContratosAndamento.add(contrato.getValorContrato());
			}
		}
		totalContratosCancelados = 0;
		for (Contrato contrato : listaContratos) {
			if (contrato.getUltimoStatus().getId().equals(StatusContrato.CANCELADO)) {
				totalContratosCancelados++;
				valorTotalContratosCancelados = valorTotalContratosCancelados.add(contrato.getValorContrato());
			}
		}
	}
	
	public void carregarLista() {
		if (usuarioLogado.getAdmin() || usuarioLogado.getPerfilId().getId().equals(Perfil.PERFIL_ADMIN)) {
			listaContratos = contratoService.findAll();
			listaFuncionarios = funcionarioService.findAll();
		} else {
			if (usuarioLogado.getPerfilId().getId().equals(Perfil.PERFIL_GERENTE) || usuarioLogado.getPerfilId().getId().equals(Perfil.PERFIL_SUPERVISOR)) {
				listaContratos = contratoService.findByFuncionario(usuarioLogado.getFuncionarioList().get(0));
				listaContratos.addAll(contratoService.findByFuncionarios(usuarioLogado.getFuncionarioList().get(0).getFuncionarioList()));
				listaFuncionarios = funcionarioService.findSubordinados(usuarioLogado.getFuncionarioList().get(0));
			} else if (usuarioLogado.getPerfilId().getId().equals(Perfil.PERFIL_FUNCIONARIO)) {
				listaContratos = contratoService.findByFuncionario(usuarioLogado.getFuncionarioList().get(0));
				listaFuncionarios.add(funcionarioService.findById(usuarioLogado.getFuncionarioList().get(0)));
			} 
		}
		listaLembretes = lembreteService.findByFuncionarioAtivas(usuarioLogado.getFuncionarioList().get(0));
	}
	
	public void pesquisar() {
		totalContratosPagos = 0;
		valorTotalContratosPagos = new BigDecimal(0);
		valorTotalContratosAndamento = new BigDecimal(0);
		valorTotalContratosCancelados = new BigDecimal(0);
		for (Contrato contrato : listaContratos) {
			if (contrato.getUltimoStatus().getId().equals(StatusContrato.PAGO)) {
				if (mesPesquisa == null) {
					if (Util.getYearFromDate(contrato.getDataPrimeiroStatus()).equals(anoPesquisa)) {
						totalContratosPagos++;
						valorTotalContratosPagos = valorTotalContratosPagos.add(contrato.getValorPago());
					}
				} else {
					if (Util.getYearFromDate(contrato.getDataPrimeiroStatus()).equals(anoPesquisa) && Util.getMonthFromDate(contrato.getDataPrimeiroStatus()).equals(mesPesquisa)) {
						totalContratosPagos++;
						valorTotalContratosPagos = valorTotalContratosPagos.add(contrato.getValorPago());
					}
				}
			}
		}
		
		totalContratosAndamento = 0;
		for (Contrato contrato : listaContratos) {
			if (contrato.getUltimoStatus().getId().equals(StatusContrato.ANDAMENTO)) {
				if (mesPesquisa == null) {
					if (Util.getYearFromDate(contrato.getDataPrimeiroStatus()).equals(anoPesquisa)) {
						totalContratosAndamento++;
						valorTotalContratosAndamento = valorTotalContratosAndamento.add(contrato.getValorContrato());
					}
				} else {
					if (Util.getYearFromDate(contrato.getDataPrimeiroStatus()).equals(anoPesquisa) && Util.getMonthFromDate(contrato.getDataPrimeiroStatus()).equals(mesPesquisa)) {
						totalContratosAndamento++;
						valorTotalContratosAndamento = valorTotalContratosAndamento.add(contrato.getValorContrato());
					}
				}
			}
		}
		totalContratosCancelados = 0;
		for (Contrato contrato : listaContratos) {
			if (contrato.getUltimoStatus().getId().equals(StatusContrato.ANDAMENTO)) {
				if (mesPesquisa == null) {
					if (Util.getYearFromDate(contrato.getDataPrimeiroStatus()).equals(anoPesquisa)) {
						totalContratosCancelados++;
						valorTotalContratosCancelados = valorTotalContratosCancelados.add(contrato.getValorContrato());
					}
				} else {
					if (Util.getYearFromDate(contrato.getDataPrimeiroStatus()).equals(anoPesquisa) && Util.getMonthFromDate(contrato.getDataPrimeiroStatus()).equals(mesPesquisa)) {
						totalContratosCancelados++;
						valorTotalContratosCancelados = valorTotalContratosCancelados.add(contrato.getValorContrato());
					}
				}
			}
		}
	}

	public int getTotalContratosPagos() {
		return totalContratosPagos;
	}


	public void setTotalContratosPagos(int totalContratosPagos) {
		this.totalContratosPagos = totalContratosPagos;
	}


	public int getTotalContratosAndamento() {
		return totalContratosAndamento;
	}


	public void setTotalContratosAndamento(int totalContratosAndamento) {
		this.totalContratosAndamento = totalContratosAndamento;
	}


	public int getTotalContratosCancelados() {
		return totalContratosCancelados;
	}


	public void setTotalContratosCancelados(int totalContratosCancelados) {
		this.totalContratosCancelados = totalContratosCancelados;
	}

	public BigDecimal getValorTotalContratosPagos() {
		return valorTotalContratosPagos;
	}

	public void setValorTotalContratosPagos(BigDecimal valorTotalContratosPagos) {
		this.valorTotalContratosPagos = valorTotalContratosPagos;
	}

	public BigDecimal getValorTotalContratosAndamento() {
		return valorTotalContratosAndamento;
	}

	public void setValorTotalContratosAndamento(BigDecimal valorTotalContratosAndamento) {
		this.valorTotalContratosAndamento = valorTotalContratosAndamento;
	}

	public BigDecimal getValorTotalContratosCancelados() {
		return valorTotalContratosCancelados;
	}

	public void setValorTotalContratosCancelados(BigDecimal valorTotalContratosCancelados) {
		this.valorTotalContratosCancelados = valorTotalContratosCancelados;
	}

	public Integer getAnoPesquisa() {
		return anoPesquisa;
	}

	public void setAnoPesquisa(Integer anoPesquisa) {
		this.anoPesquisa = anoPesquisa;
	}

	public Integer getMesPesquisa() {
		return mesPesquisa;
	}

	public void setMesPesquisa(Integer mesPesquisa) {
		this.mesPesquisa = mesPesquisa;
	}

	public List<Contrato> getListaContratos() {
		return listaContratos;
	}

	public void setListaContratos(List<Contrato> listaContratos) {
		this.listaContratos = listaContratos;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public List<Lembrete> getListaLembretes() {
		return listaLembretes;
	}

	public void setListaLembretes(List<Lembrete> listaLembretes) {
		this.listaLembretes = listaLembretes;
	}

	public LembreteService getLembreteService() {
		return lembreteService;
	}

	public void setLembreteService(LembreteService lembreteService) {
		this.lembreteService = lembreteService;
	}

	public List<Funcionario> getListaFuncionarios() {
		return listaFuncionarios;
	}

	public void setListaFuncionarios(List<Funcionario> listaFuncionarios) {
		this.listaFuncionarios = listaFuncionarios;
	}
	 
}
