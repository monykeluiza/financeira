package br.com.financeira.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.financeira.entities.Banco;
import br.com.financeira.persist.ifs.IBancoDao;

@Stateless
public class BancoService {
	
	@Inject
	private IBancoDao bancoDao;
	
	public Banco save(Banco banco) {
		return bancoDao.save(banco);
	}
	
	public Banco update(Banco banco) {
		return bancoDao.update(banco);
	}
	
	public Banco findById(Banco banco) {
		return bancoDao.findById(banco);
	}
	
	public List<Banco> findAll() {
		return bancoDao.findAll();
	}

	
	

}
