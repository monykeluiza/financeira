package br.com.financeira.persist.ifs;

import java.util.List;

import br.com.financeira.entities.Documento;

public interface IDocumentoDao {
	
	public Documento save(Documento documento);
	
	public Documento update(Documento documento);
	
	public List<Documento> findAll();
	
	public Documento findById(Documento documento);
	
}
