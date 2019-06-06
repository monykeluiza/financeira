package br.com.financeira.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.financeira.entities.Funcionario;
import br.com.financeira.entities.Perfil;
import br.com.financeira.entities.Usuario;
import br.com.financeira.persist.ifs.IUsuario;
import br.com.financeira.utils.PasswordGenerator;
import br.com.financeira.utils.Util;

@Stateless
public class UsuarioService extends LogService {
	
	@Inject
	private IUsuario usuarioDao;
	
	public List<Usuario> findAll() {
		return usuarioDao.findAll();
	}
	
	public Usuario update(Usuario usuario, Usuario usuarioLogado) {
		Usuario retorno = usuarioDao.update(usuario);
		salvar(createLog(LogService.ACAO_UPDATE, retorno.getId(), usuarioLogado, "USUARIO"));
		return retorno;
	}
	
	public Usuario save(Usuario usuario, Usuario usuarioLogado) throws Exception {
		Usuario retorno = save(usuario);
		salvar(createLog(LogService.ACAO_INSERT, retorno.getId(), usuarioLogado, "USUARIO"));
		return retorno;
	}
	
	private Usuario save(Usuario usuario) throws Exception {
		usuario.setAtivo(true);
		if (verificarUsuarioExiste(usuario)) {
			throw new Exception("Este usuário já está cadastrado no sistema. Por favor, verifique suas informações.");
		} else {
			Usuario retorno = usuarioDao.save(usuario);
			return retorno;
		}
	}
	
	private boolean verificarUsuarioExiste(Usuario usuario) {
		Usuario usuarioBanco = usuarioDao.findByLogin(usuario);
		if (usuarioBanco != null && usuarioBanco.getId() != null) {
			return true;
		} else {
			return false;
		}
	}
	
	public Usuario criarUsuarioParaFuncionario(Funcionario funcionario) {
		Usuario usuario = new Usuario();
		try {
			usuario.setAdmin(false);
			usuario.setAtivo(true);
			usuario.setLogin(funcionario.getCpf());
			usuario.setPerfilId(new Perfil(Perfil.PERFIL_FUNCIONARIO));
			usuario.setSenha(Util.getMD5String(PasswordGenerator.getRandomPassword(6)));
			save(usuario);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuario;
	}
	
}
