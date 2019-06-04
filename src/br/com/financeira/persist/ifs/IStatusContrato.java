package br.com.financeira.persist.ifs;

import java.util.List;

import br.com.financeira.entities.StatusContrato;

public interface IStatusContrato {
	
	public StatusContrato save(StatusContrato statusContrato);
	
	public StatusContrato update(StatusContrato statusContrato);
	
	public List<StatusContrato> findAll();
	
	public StatusContrato findById(StatusContrato statusContrato);
	
}
