package br.com.financeira.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.financeira.entities.Log;
import br.com.financeira.entities.TipoOperacao;
import br.com.financeira.entities.Usuario;
import br.com.financeira.persist.ifs.ITipoOperacao;

@Stateless
public class TipoOperacaoService extends LogService {
	
	@Inject
	private ITipoOperacao dao;
	
	public TipoOperacao save(TipoOperacao tipoOperacao, Usuario usuarioLogado) {
		TipoOperacao result =  dao.save(tipoOperacao);
		Log log = createLog(ACAO_INSERT, result.getId(), usuarioLogado, "tipo_operacao");
		salvar(log);
		return result;	
	}
	
	public TipoOperacao update(TipoOperacao tipoOperacao, Usuario usuarioLogado) {
		TipoOperacao result = dao.update(tipoOperacao);
		Log log = createLog(ACAO_UPDATE, result.getId(), usuarioLogado, "tipo_operacao");
		salvar(log);
		return result;
	}
	
	public TipoOperacao findById(TipoOperacao tipoOperacao) {
		return dao.findById(tipoOperacao);
	}
	
	public List<TipoOperacao> findAll() {
		return dao.findAll();
	}

	
	

}
