package br.com.financeira.persist.daos;

import java.util.List;

import javax.ejb.Stateless;

import br.com.financeira.entities.ContratoHasStatusContrato;
import br.com.financeira.persist.DataAccess;
import br.com.financeira.persist.ifs.IContratoHasStatusContratoDao;

@Stateless
public class ContratoHasStatusContratoDAO extends DataAccess<ContratoHasStatusContrato> implements IContratoHasStatusContratoDao {

	@Override
	public ContratoHasStatusContrato save(ContratoHasStatusContrato contratoHasStatusContrato) {
		return super.create(contratoHasStatusContrato);
	}


	@Override
	public List<ContratoHasStatusContrato> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ContratoHasStatusContrato findById(ContratoHasStatusContrato contratoHasStatusContrato) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
