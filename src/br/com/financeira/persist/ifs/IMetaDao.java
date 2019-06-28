package br.com.financeira.persist.ifs;

import java.util.List;

import br.com.financeira.entities.Funcionario;
import br.com.financeira.entities.Meta;

public interface IMetaDao {
	
	public Meta save(Meta meta);
	
	public Meta update(Meta meta);
	
	public List<Meta> findAll();
	
	public List<Meta> findByFuncionario(Funcionario funcionario);
	
	public Meta findById(Meta meta);

	List<Meta> findByFuncionarioAtivas(Funcionario funcionario);
	
}
