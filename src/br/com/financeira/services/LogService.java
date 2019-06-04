package br.com.financeira.services;

import java.util.Date;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.financeira.entities.Log;
import br.com.financeira.entities.Usuario;
import br.com.financeira.persist.ifs.ILogDao;

@Stateless
public class LogService {
	
	public static final String ACAO_INSERT = "INSERT";
	public static final String ACAO_DELETE = "DELETE";
	public static final String ACAO_UPDATE = "UPDATE";
	
	@Inject
	private ILogDao logDao;
	
	private Usuario usuarioLogado;
	
	public void salvar(Log log) {
		logDao.inserir(log);
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
	
	public Log createLog(String acao, Integer idTabela, Usuario usuarioLogado, String NomeTabela) {
		Log log = new Log();
		log.setAcao(acao);
		log.setData(new Date());
		if (idTabela != null) {
			log.setIdTabela(idTabela);
		}
		log.setIdUsuario(usuarioLogado.getId());
		log.setLogin(usuarioLogado.getLogin());
		log.setNome(usuarioLogado.getLogin());
		log.setNomeTabela(NomeTabela);
		return log;
	}

}
