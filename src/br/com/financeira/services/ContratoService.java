package br.com.financeira.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.financeira.entities.Funcionario;
import br.com.financeira.entities.Log;
import br.com.financeira.entities.StatusContrato;
import br.com.financeira.entities.Cliente;
import br.com.financeira.entities.Contrato;
import br.com.financeira.entities.ContratoHasStatusContrato;
import br.com.financeira.entities.ContratoHasStatusContratoPK;
import br.com.financeira.entities.Usuario;
import br.com.financeira.persist.ifs.IContratoDao;
import br.com.financeira.persist.ifs.IContratoHasStatusContratoDao;

@Stateless
public class ContratoService extends LogService {
	
	@Inject
	private IContratoDao contratoDao;
	
	@Inject
	private IContratoHasStatusContratoDao statusDao;
	
	@Inject
	private MetaService metaService;
	
	public Contrato save(Contrato contrato, Usuario usuarioLogado) {
		Contrato result =  contratoDao.save(contrato);
		Log log = createLog(ACAO_INSERT, result.getId(), usuarioLogado, "Contrato");
		salvar(log);
		updateStatus(result, usuarioLogado, StatusContrato.ANDAMENTO);
		metaService.verificarAtualizacaoMeta(contrato.getFuncionarioId());
		return result;	
	}
	
	public Contrato update(Contrato contrato, Usuario usuarioLogado) {
		Contrato result = contratoDao.update(contrato);
		Log log = createLog(ACAO_UPDATE, result.getId(), usuarioLogado, "Contrato");
		salvar(log);
		return result;
	}
	
	public Contrato updateStatus(Contrato contrato, Usuario usuarioLogado, Integer statusId) {
		ContratoHasStatusContrato status = new ContratoHasStatusContrato();
		status.setContrato(contrato);
		status.setData(new Date());
		status.setStatusContrato(new StatusContrato(statusId));
		status.setUsuarioId(usuarioLogado);
		status.setContratoHasStatusContratoPK(new ContratoHasStatusContratoPK(contrato.getId(), statusId));
		contrato.getContratoHasStatusContratoList().add(statusDao.save(status));
		Contrato result = update(contrato, usuarioLogado);
		return result;
	}
	
	public List<Contrato> findByFuncionario(Funcionario funcionario) {
		return contratoDao.findByFuncionario(funcionario);
	}
	
	public List<Contrato> findByCliente(Cliente cliente) {
		return contratoDao.findByCliente(cliente);
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
