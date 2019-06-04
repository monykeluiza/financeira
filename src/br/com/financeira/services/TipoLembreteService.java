package br.com.financeira.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.financeira.entities.Log;
import br.com.financeira.entities.TipoLembrete;
import br.com.financeira.entities.Usuario;
import br.com.financeira.persist.ifs.ITipoLembreteDao;

@Stateless
public class TipoLembreteService extends LogService {
	
	@Inject
	private ITipoLembreteDao dao;
	
	public TipoLembrete save(TipoLembrete tipoLembrete, Usuario usuarioLogado) {
		TipoLembrete result =  dao.save(tipoLembrete);
		Log log = createLog(ACAO_INSERT, result.getId(), usuarioLogado, "TipoLembrete");
		salvar(log);
		return result;	
	}
	
	public TipoLembrete update(TipoLembrete tipoLembrete, Usuario usuarioLogado) {
		TipoLembrete result = dao.update(tipoLembrete);
		Log log = createLog(ACAO_UPDATE, result.getId(), usuarioLogado, "TipoLembrete");
		salvar(log);
		return result;
	}
	
	public TipoLembrete findById(TipoLembrete tipoLembrete) {
		return dao.findById(tipoLembrete);
	}
	
	public List<TipoLembrete> findAll() {
		return dao.findAll();
	}

	
	

}
