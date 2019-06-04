package br.com.financeira.persist.daos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.validation.ConstraintViolationException;

import br.com.financeira.entities.TipoLembrete;
import br.com.financeira.persist.DataAccess;
import br.com.financeira.persist.ifs.ITipoLembreteDao;

@Stateless
public class TipoLembreteDAO extends DataAccess<TipoLembrete>  implements ITipoLembreteDao{

	@Override
	public TipoLembrete save(TipoLembrete tipoLembrete) {
		try {
			TipoLembrete result = super.create(tipoLembrete);
			return result;
		} catch (ConstraintViolationException e) {
			System.out.println(e.getConstraintViolations());
			return tipoLembrete;
		} 
	}
	
	@Override
	public TipoLembrete update(TipoLembrete tipoLembrete) {
		try {
			TipoLembrete result = super.update(tipoLembrete);
			return result;
		} catch (ConstraintViolationException e) {
			System.out.println(e.getConstraintViolations());
			return tipoLembrete;
		} 
	}

	@Override
	public List<TipoLembrete> findAll() {
		return super.findWithNamedQuery("TipoLembrete.findAll");
	}

	@Override
	public TipoLembrete findById(TipoLembrete tipoLembrete) {
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("id", tipoLembrete.getId());
		return (TipoLembrete) super.findWithNamedQueryUniqueOrNull("TipoLembrete.findById", parametros);
	}

}
