package br.com.financeira.persist.ifs;

import java.util.List;

import br.com.financeira.entities.ContratoHasStatusContrato;

public interface IContratoHasStatusContratoDao {
	
	public ContratoHasStatusContrato save(ContratoHasStatusContrato contratoHasStatusContrato);
	
	public ContratoHasStatusContrato update(ContratoHasStatusContrato contratoHasStatusContrato);
	
	public List<ContratoHasStatusContrato> findAll();
	
	public ContratoHasStatusContrato findById(ContratoHasStatusContrato contratoHasStatusContrato);
	
}
