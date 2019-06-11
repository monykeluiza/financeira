package br.com.financeira.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.financeira.entities.Funcionario;
import br.com.financeira.entities.Log;
import br.com.financeira.entities.Contrato;
import br.com.financeira.entities.Usuario;
import br.com.financeira.persist.ifs.IContratoDao;

@Stateless
public class ContratoService extends LogService {
	
	@Inject
	private IContratoDao contratoDao;
	
	public Contrato save(Contrato contrato, Usuario usuarioLogado) {
		Contrato result =  contratoDao.save(contrato);
		Log log = createLog(ACAO_INSERT, result.getId(), usuarioLogado, "Contrato");
		salvar(log);
		return result;	
	}
	
	public Contrato update(Contrato contrato, Usuario usuarioLogado) {
		Contrato result = contratoDao.update(contrato);
		Log log = createLog(ACAO_UPDATE, result.getId(), usuarioLogado, "Contrato");
		salvar(log);
		return result;
	}
	
	public List<Contrato> findByFuncionario(Funcionario funcionario) {
		return contratoDao.findByFuncionario(funcionario);
	}
	
	public List<Contrato> findByFuncionarios(List<Funcionario> funcionarios) {
		List<Contrato> contratos = new ArrayList<Contrato>();
		for (Funcionario funcionario2 : funcionarios) {
			contratos.addAll(contratoDao.findByFuncionario(funcionario2));
		}
		return contratos;
	}

	
	public Contrato findById(Contrato contrato) {
		return contratoDao.findById(contrato);
	}
	
	public List<Contrato> findAll() {
		return contratoDao.findAll();
	}

	
	

}
