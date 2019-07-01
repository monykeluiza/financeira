package br.com.financeira.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.financeira.entities.Funcionario;
import br.com.financeira.entities.Log;
import br.com.financeira.entities.Usuario;
import br.com.financeira.persist.ifs.IFuncionarioDao;

@Stateless
public class FuncionarioService extends LogService {
	
	@Inject
	private IFuncionarioDao dao;
	
	@Inject
	private UsuarioService userService;
	
	
	public Funcionario save(Funcionario funcionario, Usuario usuarioLogado) {
		funcionario.setChefiaId(null);
		funcionario.setUsuarioId(userService.criarUsuarioParaFuncionario(funcionario, usuarioLogado));
		Funcionario result =  dao.save(funcionario);
		
		Log log = createLog(ACAO_INSERT, result.getId(), usuarioLogado, "Funcionario");
		salvar(log);
		return result;	
	}
	
	public Funcionario update(Funcionario funcionario, Usuario usuarioLogado) {
		Funcionario result = dao.update(funcionario);
		Log log = createLog(ACAO_UPDATE, result.getId(), usuarioLogado, "Funcionario");
		salvar(log);
		return result;
	}
	
	public void desligarFuncionario(Funcionario funcionario, Usuario usuarioLogado) {
		funcionario.getUsuarioId().setAtivo(false);
		userService.update(funcionario.getUsuarioId(), usuarioLogado);
		update(funcionario, usuarioLogado);
	}
	
	public Funcionario findById(Funcionario funcionario) {
		return dao.findById(funcionario);
	}
	
	public List<Funcionario> findAll() {
		return dao.findAll();
	}
	
	public List<Funcionario> findSubordinados(Funcionario chefe) {
		return dao.findSubordinados(chefe);
	}

	public List<Funcionario> findChefes() {
		return dao.findChefes();
	}

	public Funcionario findByUsuario(Usuario usuario) {
		return dao.findByUsuario(usuario);
	}
	

	
	

}
