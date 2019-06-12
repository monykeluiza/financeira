package br.com.financeira.persist.daos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.validation.ConstraintViolationException;

import br.com.financeira.entities.Cliente;
import br.com.financeira.entities.Contrato;
import br.com.financeira.entities.Funcionario;
import br.com.financeira.persist.DataAccess;
import br.com.financeira.persist.ifs.IContratoDao;

@Stateless
public class ContratoDAO extends DataAccess<Contrato>  implements IContratoDao{

	@Override
	public Contrato save(Contrato contrato) {
		try {
			Contrato result = super.create(contrato);
			return result;
		} catch (ConstraintViolationException e) {
			System.out.println(e.getConstraintViolations());
			return contrato;
		} 
	}
	
	@Override
	public Contrato update(Contrato contrato) {
		try {
			Contrato result = super.update(contrato);
			return result;
		} catch (ConstraintViolationException e) {
			System.out.println(e.getConstraintViolations());
			return contrato;
		} 
	}

	@Override
	public List<Contrato> findAll() {
		return super.findWithNamedQuery("Contrato.findAll");
	}

	@Override
	public Contrato findById(Contrato contrato) {
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("id", contrato.getId());
		return (Contrato) super.findWithNamedQueryUniqueOrNull("Contrato.findById", parametros);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Contrato> findByFuncionario(Funcionario funcionario) {
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("idFuncionario", funcionario.getId());
		return super.findWithNamedQuery("Contrato.findByFuncionario", parametros);
	}
	
	@SuppressWarnings("unchecked")
	public List<Contrato> findByCliente(Cliente cliente) {
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("idCliente", cliente.getId());
		return super.findWithNamedQuery("Contrato.findBycliente", parametros);
	}

}
