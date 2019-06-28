package br.com.financeira.persist.daos;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.validation.ConstraintViolationException;

import br.com.financeira.entities.Cliente;
import br.com.financeira.entities.Funcionario;
import br.com.financeira.persist.DataAccess;
import br.com.financeira.persist.ifs.IClienteDao;
import br.com.financeira.utils.Util;

@Stateless
public class ClienteDAO extends DataAccess<Cliente>  implements IClienteDao{

	@Override
	public Cliente save(Cliente cliente) {
		try {
			Cliente result = super.create(cliente);
			return result;
		} catch (ConstraintViolationException e) {
			System.out.println(e.getConstraintViolations());
			return cliente;
		} 
	}
	
	@Override
	public Cliente update(Cliente cliente) {
		try {
			Cliente result = super.update(cliente);
			return result;
		} catch (ConstraintViolationException e) {
			System.out.println(e.getConstraintViolations());
			return cliente;
		} 
	}

	@Override
	public List<Cliente> findAll() {
		return super.findWithNamedQuery("Cliente.findAll");
	}

	@Override
	public Cliente findById(Cliente cliente) {
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("id", cliente.getId());
		return (Cliente) super.findWithNamedQueryUniqueOrNull("Cliente.findById", parametros);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cliente> findByFuncionario(Funcionario funcionario) {
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("idFuncionario", funcionario.getId());
		return super.findWithNamedQuery("Cliente.findByFuncionario", parametros);
	}
	
	@SuppressWarnings("unchecked")
	public List<Cliente> findByAniversariantes() {
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("mes", Util.getMonthFromDate(new Date()));
//		parametros.put("dia", Util.getDayFromDate(new Date()));
		return super.findWithNamedQuery("Cliente.findByDataNasc", parametros);
	}

}
