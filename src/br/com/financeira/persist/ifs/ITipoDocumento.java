package br.com.financeira.persist.ifs;

import java.util.List;

import br.com.financeira.entities.TipoDocumento;

public interface ITipoDocumento {
	
	public TipoDocumento save(TipoDocumento tipoDocumento);
	
	public TipoDocumento update(TipoDocumento tipoDocumento);
	
	public List<TipoDocumento> findAll();
	
	public TipoDocumento findById(TipoDocumento tipoDocumento);
	
}
