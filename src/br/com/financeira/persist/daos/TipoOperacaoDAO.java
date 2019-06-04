package br.com.financeira.persist.daos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.validation.ConstraintViolationException;

import br.com.financeira.entities.TipoOperacao;
import br.com.financeira.persist.DataAccess;
import br.com.financeira.persist.ifs.ITipoOperacao;

@Stateless
public class TipoOperacaoDAO extends DataAccess<TipoOperacao>  implements ITipoOperacao{

	@Override
	public TipoOperacao save(TipoOperacao tipoOperacao) {
		try {
			TipoOperacao result = super.create(tipoOperacao);
			return result;
		} catch (ConstraintViolationException e) {
			System.out.println(e.getConstraintViolations());
			return tipoOperacao;
		} 
	}
	
	@Override
	public TipoOperacao update(TipoOperacao tipoOperacao) {
		try {
			TipoOperacao result = super.update(tipoOperacao);
			return result;
		} catch (ConstraintViolationException e) {
			System.out.println(e.getConstraintViolations());
			return tipoOperacao;
		} 
	}

	@Override
	public List<TipoOperacao> findAll() {
		return super.findWithNamedQuery("TipoOperacao.findAll");
	}

	@Override
	public TipoOperacao findById(TipoOperacao tipoOperacao) {
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("id", tipoOperacao.getId());
		return (TipoOperacao) super.findWithNamedQueryUniqueOrNull("TipoOperacao.findById", parametros);
	}

}
