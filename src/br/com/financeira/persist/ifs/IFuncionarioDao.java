package br.com.financeira.persist.ifs;

import java.util.List;

import br.com.financeira.entities.Funcionario;

public interface IFuncionarioDao {
	
	public Funcionario save(Funcionario funcionario);
	
	public Funcionario update(Funcionario funcionario);
	
	public List<Funcionario> findAll();
	
	public Funcionario findById(Funcionario funcionario);
	
}
