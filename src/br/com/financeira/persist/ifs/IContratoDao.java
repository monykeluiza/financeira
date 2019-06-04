package br.com.financeira.persist.ifs;

import java.util.List;

import br.com.financeira.entities.Contrato;

public interface IContratoDao {
	
	public Contrato save(Contrato contrato);
	
	public Contrato update(Contrato contrato);
	
	public List<Contrato> findAll();
	
	public Contrato findById(Contrato contrato);
	
}
