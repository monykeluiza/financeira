package br.com.financeira.persist.ifs;

import java.util.List;

import br.com.financeira.entities.Contato;

public interface IContatoDao {
	
	public Contato save(Contato contato);
	
	public Contato update(Contato contato);
	
	public List<Contato> findAll();
	
	public Contato findById(Contato contato);
	
}
