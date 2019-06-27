package br.com.financeira.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.financeira.entities.Banco;
import br.com.financeira.entities.Log;
import br.com.financeira.entities.Usuario;
import br.com.financeira.persist.ifs.IBancoDao;

@Stateless
public class BancoService extends LogService {
	
	@Inject
	private IBancoDao bancoDao;
	
	public Banco save(Banco banco, Usuario usuarioLogado) {
		banco.setAtivo(true);
		Banco result =  bancoDao.save(banco);
		Log log = createLog(ACAO_INSERT, result.getId(), usuarioLogado, "Banco");
		salvar(log);
		return result;	
	}
	
	public Banco update(Banco banco, Usuario usuarioLogado) {
		Banco result = bancoDao.update(banco);
		Log log = createLog(ACAO_UPDATE, result.getId(), usuarioLogado, "Banco");
		salvar(log);
		return result;
	}
	
	public Banco findById(Banco banco) {
		return bancoDao.findById(banco);
	}
	
	public List<Banco> findAll() {
		return bancoDao.findAll();
	}

	
	

}
