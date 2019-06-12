package br.com.financeira.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.financeira.entities.Cliente;
import br.com.financeira.entities.Contato;
import br.com.financeira.entities.Funcionario;
import br.com.financeira.entities.Log;
import br.com.financeira.entities.Usuario;
import br.com.financeira.persist.ifs.IContatoDao;

@Stateless
public class ContatoService extends LogService {
	
	@Inject
	private IContatoDao contatoDao;
	
	public Contato save(Contato contato, Usuario usuarioLogado) {
		contato.setFuncionarioId(usuarioLogado.getFuncionarioList().get(0));
		Contato result =  contatoDao.save(contato);
		Log log = createLog(ACAO_INSERT, result.getId(), usuarioLogado, "Contato");
		salvar(log);
		return result;	
	}
	
	public Contato update(Contato contato, Usuario usuarioLogado) {
		Contato result = contatoDao.update(contato);
		Log log = createLog(ACAO_UPDATE, result.getId(), usuarioLogado, "Contato");
		salvar(log);
		return result;
	}
	
	public List<Contato> findByFuncionario(Funcionario funcionario) {
		return contatoDao.findByFuncionario(funcionario);
	}
	
	public List<Contato> findByCliente(Cliente cliente) {
		return contatoDao.findByCliente(cliente);
	}
	
	public List<Contato> findByFuncionarios(List<Funcionario> funcionarios) {
		List<Contato> contatos = new ArrayList<Contato>();
		for (Funcionario funcionario2 : funcionarios) {
			contatos.addAll(contatoDao.findByFuncionario(funcionario2));
		}
		return contatos;
	}

	
	public Contato findById(Contato contato) {
		return contatoDao.findById(contato);
	}
	
	public List<Contato> findAll() {
		return contatoDao.findAll();
	}

	
	

}
