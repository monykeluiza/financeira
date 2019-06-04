package br.com.financeira.services;

import java.util.Date;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.financeira.entities.Usuario;
import br.com.financeira.persist.ifs.IUsuario;

@Stateless
public class AutenticacaoService {
	
	@Inject
	private IUsuario usuarioDao; 
	
	public Usuario autenticar(Usuario usuario) throws Exception {
		Usuario usuarioBanco = usuarioDao.findByLoginAtivo(usuario);
		if (usuarioBanco != null && usuarioBanco.getId() != null) {
			if (usuarioBanco.getDataUltimoAcesso() == null) {
				usuario.setId(usuarioBanco.getId());
			}
//			usuarioBanco.setNome(usuario.getNome());
			usuarioBanco.setDataUltimoAcesso(new Date());
			usuarioDao.update(usuarioBanco);
			return usuarioBanco;
		} else {
			throw new Exception("Usuário não tem permissão para acesso ao sistema!");
		}
	}
	

}
