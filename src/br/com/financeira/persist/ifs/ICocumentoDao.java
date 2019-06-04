package br.com.financeira.persist.ifs;

import java.util.List;

import br.com.financeira.entities.Banco;

public interface ICocumentoDao {
	
	public Banco save(Banco banco);
	
	public Banco update(Banco banco);
	
	public List<Banco> findAll();
	
	public Banco findById(Banco banco);
	
}
