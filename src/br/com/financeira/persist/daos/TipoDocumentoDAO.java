package br.com.financeira.persist.daos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.validation.ConstraintViolationException;

import br.com.financeira.entities.TipoDocumento;
import br.com.financeira.persist.DataAccess;
import br.com.financeira.persist.ifs.ITipoDocumento;

@Stateless
public class TipoDocumentoDAO extends DataAccess<TipoDocumento>  implements ITipoDocumento{

	@Override
	public TipoDocumento save(TipoDocumento tipoLembrete) {
		try {
			TipoDocumento result = super.create(tipoLembrete);
			return result;
		} catch (ConstraintViolationException e) {
			System.out.println(e.getConstraintViolations());
			return tipoLembrete;
		} 
	}
	
	@Override
	public TipoDocumento update(TipoDocumento tipoLembrete) {
		try {
			TipoDocumento result = super.update(tipoLembrete);
			return result;
		} catch (ConstraintViolationException e) {
			System.out.println(e.getConstraintViolations());
			return tipoLembrete;
		} 
	}

	@Override
	public List<TipoDocumento> findAll() {
		return super.findWithNamedQuery("TipoDocumento.findAll");
	}

	@Override
	public TipoDocumento findById(TipoDocumento tipoLembrete) {
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("id", tipoLembrete.getId());
		return (TipoDocumento) super.findWithNamedQueryUniqueOrNull("TipoDocumento.findById", parametros);
	}

}
