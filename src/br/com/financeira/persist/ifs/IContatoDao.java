package br.com.financeira.persist.ifs;

import java.util.List;

import br.com.financeira.entities.Cliente;
import br.com.financeira.entities.Contato;
import br.com.financeira.entities.Funcionario;

public interface IContatoDao {
	
	public Contato save(Contato contato);
	
	public Contato update(Contato contato);
	
	public List<Contato> findAll();
	
	public Contato findById(Contato contato);
	
	public List<Contato> findByCliente(Cliente cliente);

	public List<Contato> findByFuncionario(Funcionario funcionario);

	
}
