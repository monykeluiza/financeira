package br.com.financeira.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.financeira.entities.Funcionario;
import br.com.financeira.entities.Lembrete;
import br.com.financeira.entities.Log;
import br.com.financeira.entities.Usuario;
import br.com.financeira.persist.ifs.ILembreteDao;

@Stateless
public class LembreteService extends LogService {
	
	@Inject
	private ILembreteDao lembreteDao;
	
	public Lembrete save(Lembrete lembrete, Usuario usuarioLogado) {
		Lembrete result =  lembreteDao.save(lembrete);
		Log log = createLog(ACAO_INSERT, result.getId(), usuarioLogado, "Lembrete");
		salvar(log);
		return result;	
	}
	
	public Lembrete update(Lembrete lembrete, Usuario usuarioLogado) {
		Lembrete result = lembreteDao.update(lembrete);
		Log log = createLog(ACAO_UPDATE, result.getId(), usuarioLogado, "Lembrete");
		salvar(log);
		return result;
	}
	
	public List<Lembrete> findByFuncionario(Funcionario funcionario) {
		return lembreteDao.findByFuncionario(funcionario);
	}
	
	public List<Lembrete> findByFuncionarios(List<Funcionario> funcionarios) {
		List<Lembrete> lembretes = new ArrayList<Lembrete>();
		for (Funcionario funcionario2 : funcionarios) {
			lembretes.addAll(lembreteDao.findByFuncionario(funcionario2));
		}
		return lembretes;
	}

	
	public Lembrete findById(Lembrete lembrete) {
		return lembreteDao.findById(lembrete);
	}
	
	public List<Lembrete> findAll() {
		return lembreteDao.findAll();
	}

	
	

}
