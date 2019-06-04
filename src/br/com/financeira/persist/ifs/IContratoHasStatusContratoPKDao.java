package br.com.financeira.persist.ifs;

import java.util.List;

import br.com.financeira.entities.ContratoHasStatusContratoPK;

public interface IContratoHasStatusContratoPKDao {
	
	public ContratoHasStatusContratoPK save(ContratoHasStatusContratoPK contratoHasStatusContratoPK);
	
	public ContratoHasStatusContratoPK update(ContratoHasStatusContratoPK contratoHasStatusContratoPK);
	
	public List<ContratoHasStatusContratoPK> findAll();
	
	public ContratoHasStatusContratoPK findById(ContratoHasStatusContratoPK contratoHasStatusContratoPK);
	
}
