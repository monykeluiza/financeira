package br.com.financeira.mb;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import br.com.financeira.entities.Contrato;
import br.com.financeira.entities.StatusContrato;
import br.com.financeira.services.ContratoService;

@ManagedBean(name="dashboardMB")
@ViewScoped
public class DashboardMB implements Serializable {

	private static final long serialVersionUID = 5537056707570047731L;
	private int totalContratosPagos;
	private int totalContratosAndamento;
	private int totalContratosCancelados;
//	private int totalInscricoesHomologadas;
//	private int totalAProcessar;
//	private int totalRestantes;
	
	@Inject
	private ContratoService contratoService;
	
	@PostConstruct
	public void init() {
		totalContratosPagos = 0;
		for (Contrato contrato : contratoService.findAll()) {
			if (contrato.getUltimoStatus().getId().equals(StatusContrato.PAGO)) {
				totalContratosPagos++;
			}
		}
		totalContratosAndamento = 0;
		for (Contrato contrato : contratoService.findAll()) {
			if (contrato.getUltimoStatus().getId().equals(StatusContrato.ANDAMENTO)) {
				totalContratosAndamento++;
			}
		}
		totalContratosCancelados = 0;
		for (Contrato contrato : contratoService.findAll()) {
			if (contrato.getUltimoStatus().getId().equals(StatusContrato.CANCELADO)) {
				totalContratosCancelados++;
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
	 
}
