package br.com.financeira.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.financeira.entities.Contrato;
import br.com.financeira.entities.Funcionario;
import br.com.financeira.entities.Log;
import br.com.financeira.entities.Meta;
import br.com.financeira.entities.Usuario;
import br.com.financeira.persist.ifs.IMetaDao;

@Stateless
public class MetaService extends LogService {
	
	@Inject
	private IMetaDao metaDao;
	
	public Meta save(Meta meta, Usuario usuarioLogado) {
		Meta result =  metaDao.save(meta);
		Log log = createLog(ACAO_INSERT, result.getId(), usuarioLogado, "Meta");
		salvar(log);
		return result;	
	}
	
	public Meta update(Meta meta, Usuario usuarioLogado) {
		Meta result = metaDao.update(meta);
		Log log = createLog(ACAO_UPDATE, result.getId(), usuarioLogado, "Meta");
		salvar(log);
		return result;
	}
	
	private Meta update(Meta meta) {
		Meta result = metaDao.update(meta);
		return result;
	}
	
	public List<Meta> findByFuncionario(Funcionario funcionario) {
		return metaDao.findByFuncionario(funcionario);
	}
	
	public List<Meta> findByFuncionarioAtivas(Funcionario funcionario) {
		return metaDao.findByFuncionarioAtivas(funcionario);
	}
	
	public List<Meta> findByFuncionarios(List<Funcionario> funcionarios) {
		List<Meta> metas = new ArrayList<Meta>();
		for (Funcionario funcionario2 : funcionarios) {
			metas.addAll(metaDao.findByFuncionario(funcionario2));
		}
		return metas;
	}

	
	public Meta findById(Meta meta) {
		return metaDao.findById(meta);
	}
	
	public List<Meta> findAll() {
		return metaDao.findAll();
	}
	
	public void verificarAtualizacaoMeta(Funcionario funcionario) {
		List<Meta> metas = findByFuncionarioAtivas(funcionario);
		for (Meta meta : metas) {
			BigDecimal totalContrato = new BigDecimal(0);
			for (Contrato contrato : funcionario.getContratoList()) {
				if (contrato.getDataCriacao().after(meta.getDataInicio()) && contrato.getDataCriacao().before(meta.getDataVencimento())) {
					totalContrato.add(contrato.getValorCliente());
				}
			}
			if (totalContrato.compareTo(meta.getValor()) >= 0) {
				meta.setBatida(true);
				meta.setDataAlcance(new Date());
				update(meta);
			}
		}
	}
	

}
