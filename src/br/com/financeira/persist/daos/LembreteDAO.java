package br.com.financeira.persist.daos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.validation.ConstraintViolationException;

import br.com.financeira.entities.Lembrete;
import br.com.financeira.entities.Funcionario;
import br.com.financeira.persist.DataAccess;
import br.com.financeira.persist.ifs.ILembreteDao;

@Stateless
public class LembreteDAO extends DataAccess<Lembrete>  implements ILembreteDao{

	@Override
	public Lembrete save(Lembrete lembrete) {
		try {
			Lembrete result = super.create(lembrete);
			return result;
		} catch (ConstraintViolationException e) {
			System.out.println(e.getConstraintViolations());
			return lembrete;
		} 
	}
	
	@Override
	public Lembrete update(Lembrete lembrete) {
		try {
			Lembrete result = super.update(lembrete);
			return result;
		} catch (ConstraintViolationException e) {
			System.out.println(e.getConstraintViolations());
			return lembrete;
		} 
	}

	@Override
	public List<Lembrete> findAll() {
		return super.findWithNamedQuery("Lembrete.findAll");
	}

	@Override
	public Lembrete findById(Lembrete lembrete) {
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("id", lembrete.getId());
		return (Lembrete) super.findWithNamedQueryUniqueOrNull("Lembrete.findById", parametros);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Lembrete> findByFuncionario(Funcionario funcionario) {
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("idFuncionario", funcionario.getId());
		return super.findWithNamedQuery("Lembrete.findByFuncionario", parametros);
	}

}
