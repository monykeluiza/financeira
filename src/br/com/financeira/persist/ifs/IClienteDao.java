package br.com.financeira.persist.ifs;

import java.util.List;

import br.com.financeira.entities.Cliente;

public interface IClienteDao {
	
	public Cliente save(Cliente cliente);
	
	public Cliente update(Cliente cliente);
	
	public List<Cliente> findAll();
	
	public Cliente findById(Cliente cliente);
	
}