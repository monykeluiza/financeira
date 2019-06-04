package br.com.financeira.persist.ifs;

import java.util.List;

import br.com.financeira.entities.TipoLembrete;

public interface ITipoLembreteDao {
	
	public TipoLembrete save(TipoLembrete tipoLembrete);
	
	public TipoLembrete update(TipoLembrete tipoLembrete);
	
	public List<TipoLembrete> findAll();
	
	public TipoLembrete findById(TipoLembrete tipoLembrete);
	
}
