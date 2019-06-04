package br.com.financeira.persist.daos;

import javax.ejb.Stateless;

import br.com.financeira.entities.Log;
import br.com.financeira.persist.DataAccess;
import br.com.financeira.persist.ifs.ILogDao;

@Stateless
public class LogDAO extends DataAccess<Log> implements ILogDao {

	@Override
	public void inserir(Log log) {
		super.create(log);
	}
	
	

}
