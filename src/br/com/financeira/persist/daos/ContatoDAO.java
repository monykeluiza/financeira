package br.com.financeira.persist.daos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.validation.ConstraintViolationException;

import br.com.financeira.entities.Cliente;
import br.com.financeira.entities.Contato;
import br.com.financeira.entities.Funcionario;
import br.com.financeira.persist.DataAccess;
import br.com.financeira.persist.ifs.IContatoDao;

@Stateless
public class ContatoDAO extends DataAccess<Contato>  implements IContatoDao{

	@Override
	public Contato save(Contato contato) {
		try {
			Contato result = super.create(contato);
			return result;
		} catch (ConstraintViolationException e) {
			System.out.println(e.getConstraintViolations());
			return contato;
		} 
	}
	
	@Override
	public Contato update(Contato contato) {
		try {
			Contato result = super.update(contato);
			return result;
		} catch (ConstraintViolationException e) {
			System.out.println(e.getConstraintViolations());
			return contato;
		} 
	}

	@Override
	public List<Contato> findAll() {
		return super.findWithNamedQuery("Contato.findAll");
	}

	@Override
	public Contato findById(Contato contato) {
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("id", contato.getId());
		return (Contato) super.findWithNamedQueryUniqueOrNull("Contato.findById", parametros);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Contato> findByFuncionario(Funcionario funcionario) {
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("funcionarioId", funcionario.getId());
		return super.findWithNamedQuery("Contato.findByFuncionario", parametros);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Contato> findByCliente(Cliente cliente) {
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("clienteId", cliente.getId());
		return super.findWithNamedQuery("Contato.findByCliente", parametros);
	}

}
