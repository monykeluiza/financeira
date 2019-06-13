package br.com.financeira.persist.ifs;

import java.util.List;

import br.com.financeira.entities.Funcionario;
import br.com.financeira.entities.Lembrete;

public interface ILembreteDao {
	
	public Lembrete save(Lembrete lembrete);
	
	public Lembrete update(Lembrete lembrete);
	
	public List<Lembrete> findAll();
	
	public Lembrete findById(Lembrete lembrete);
	
	public List<Lembrete> findByFuncionario(Funcionario funcionario);

	public List<Lembrete> findByFuncionarioAtivas(Funcionario funcionario);

	
}
