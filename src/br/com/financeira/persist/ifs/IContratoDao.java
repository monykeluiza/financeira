package br.com.financeira.persist.ifs;

import java.util.List;

import br.com.financeira.entities.Contrato;
import br.com.financeira.entities.Funcionario;

public interface IContratoDao {
	
	public Contrato save(Contrato contrato);
	
	public Contrato update(Contrato contrato);
	
	public List<Contrato> findAll();
	
	public Contrato findById(Contrato contrato);
	
	public List<Contrato> findByFuncionario(Funcionario funcionario);

	
}
