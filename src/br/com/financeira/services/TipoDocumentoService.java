package br.com.financeira.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.financeira.entities.Log;
import br.com.financeira.entities.TipoDocumento;
import br.com.financeira.entities.Usuario;
import br.com.financeira.persist.ifs.ITipoDocumento;

@Stateless
public class TipoDocumentoService extends LogService {
	
	@Inject
	private ITipoDocumento dao;
	
	public TipoDocumento save(TipoDocumento tipoDocumento, Usuario usuarioLogado) {
		TipoDocumento result =  dao.save(tipoDocumento);
		Log log = createLog(ACAO_INSERT, result.getId(), usuarioLogado, "TipoDocumento");
		salvar(log);
		return result;	
	}
	
	public TipoDocumento update(TipoDocumento tipoDocumento, Usuario usuarioLogado) {
		TipoDocumento result = dao.update(tipoDocumento);
		Log log = createLog(ACAO_UPDATE, result.getId(), usuarioLogado, "TipoDocumento");
		salvar(log);
		return result;
	}
	
	public TipoDocumento findById(TipoDocumento tipoDocumento) {
		return dao.findById(tipoDocumento);
	}
	
	public List<TipoDocumento> findAll() {
		return dao.findAll();
	}

	
	

}
