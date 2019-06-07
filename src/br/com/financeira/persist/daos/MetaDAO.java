package br.com.financeira.persist.daos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.validation.ConstraintViolationException;

import br.com.financeira.entities.Meta;
import br.com.financeira.entities.Funcionario;
import br.com.financeira.persist.DataAccess;
import br.com.financeira.persist.ifs.IMetaDao;

@Stateless
public class MetaDAO extends DataAccess<Meta>  implements IMetaDao{

	@Override
	public Meta save(Meta meta) {
		try {
			Meta result = super.create(meta);
			return result;
		} catch (ConstraintViolationException e) {
			System.out.println(e.getConstraintViolations());
			return meta;
		} 
	}
	
	@Override
	public Meta update(Meta meta) {
		try {
			Meta result = super.update(meta);
			return result;
		} catch (ConstraintViolationException e) {
			System.out.println(e.getConstraintViolations());
			return meta;
		} 
	}

	@Override
	public List<Meta> findAll() {
		return super.findWithNamedQuery("Meta.findAll");
	}

	@Override
	public Meta findById(Meta meta) {
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("id", meta.getId());
		return (Meta) super.findWithNamedQueryUniqueOrNull("Meta.findById", parametros);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Meta> findByFuncionario(Funcionario funcionario) {
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("idFuncionario", funcionario.getId());
		return super.findWithNamedQuery("Meta.findByFuncionario", parametros);
	}

}
