package br.com.financeira.persist.ifs;

import java.util.List;

import br.com.financeira.entities.TipoOperacao;

public interface ITipoOperacao {
	
	public TipoOperacao save(TipoOperacao tipoOperacao);
	
	public TipoOperacao update(TipoOperacao tipoOperacao);
	
	public List<TipoOperacao> findAll();
	
	public TipoOperacao findById(TipoOperacao tipoOperacao);
	
}
