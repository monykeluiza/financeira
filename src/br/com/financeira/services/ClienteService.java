package br.com.financeira.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.financeira.entities.Cliente;
import br.com.financeira.entities.Funcionario;
import br.com.financeira.entities.Log;
import br.com.financeira.entities.Usuario;
import br.com.financeira.persist.ifs.IClienteDao;

@Stateless
public class ClienteService extends LogService {
	
	@Inject
	private IClienteDao dao;
	
	public Cliente save(Cliente cliente, Usuario usuarioLogado) {
		Cliente result =  dao.save(cliente);
		Log log = createLog(ACAO_INSERT, result.getId(), usuarioLogado, "Cliente");
		salvar(log);
		return result;	
	}
	
	public Cliente update(Cliente cliente, Usuario usuarioLogado) {
		Cliente result = dao.update(cliente);
		Log log = createLog(ACAO_UPDATE, result.getId(), usuarioLogado, "Cliente");
		salvar(log);
		return result;
	}
	
	public Cliente findById(Cliente cliente) {
		return dao.findById(cliente);
	}
	
	public List<Cliente> findByFuncionarios(List<Funcionario> funcionarios) {
		List<Cliente> result = new ArrayList<Cliente>();
		for (Funcionario funcionario : funcionarios) {
			result.addAll(findByFuncionario(funcionario));
		}
		return result;
	}
	
	public List<Cliente> findByFuncionario(Funcionario funcionario) {
		return dao.findByFuncionario(funcionario);
	}
	
	public List<Cliente> findAll() {
		return dao.findAll();
	}

	
	

}
