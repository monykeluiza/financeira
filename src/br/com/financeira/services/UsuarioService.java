package br.com.financeira.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.financeira.entities.Usuario;
import br.com.financeira.persist.ifs.IUsuario;

@Stateless
public class UsuarioService {
	
	@Inject
	private IUsuario usuarioDao;
	
	public List<Usuario> findAll() {
		return usuarioDao.findAll();
	}
	
	public Usuario update(Usuario usuario, Usuario usuarioLogado) {
		Usuario retorno = usuarioDao.update(usuario);
		return retorno;
	}
	
	public Usuario save(Usuario usuario, Usuario usuarioLogado) throws Exception {
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
	
}
