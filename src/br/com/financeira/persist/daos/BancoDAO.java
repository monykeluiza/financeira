package br.com.financeira.persist.daos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.validation.ConstraintViolationException;

import br.com.financeira.entities.Banco;
import br.com.financeira.persist.DataAccess;
import br.com.financeira.persist.ifs.IBancoDao;

@Stateless
public class BancoDAO extends DataAccess<Banco>  implements IBancoDao{

	@Override
	public Banco save(Banco banco) {
		try {
			Banco result = super.create(banco);
			return result;
		} catch (ConstraintViolationException e) {
			System.out.println(e.getConstraintViolations());
			return banco;
		} 
	}
	
	@Override
	public Banco update(Banco banco) {
		try {
			Banco result = super.update(banco);
			return result;
		} catch (ConstraintViolationException e) {
			System.out.println(e.getConstraintViolations());
			return banco;
		} 
	}

	@Override
	public List<Banco> findAll() {
		return super.findWithNamedQuery("Banco.findAll");
	}

	@Override
	public Banco findById(Banco banco) {
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("id", banco.getId());
		return (Banco) super.findWithNamedQueryUniqueOrNull("Banco.findById", parametros);
	}

}
