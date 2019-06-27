package br.com.financeira.entities;

import java.math.BigDecimal;
import java.util.List;

public class Consolidado {

	private Funcionario funcionario;
	private BigDecimal totalPago;
	private BigDecimal totalDigitado;
	private List<Contrato> listaContratos;
	
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncioario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	public BigDecimal getTotalPago() {
		return totalPago;
	}
	public void setTotalPago(BigDecimal totalPago) {
		this.totalPago = totalPago;
	}
	public BigDecimal getTotalDigitado() {
		return totalDigitado;
	}
	public void setTotalDigitado(BigDecimal totalDigitado) {
		this.totalDigitado = totalDigitado;
	}
	public List<Contrato> getListaContratos() {
		return listaContratos;
	}
	public void setListaContratos(List<Contrato> listaContratos) {
		this.listaContratos = listaContratos;
	}
	
}
