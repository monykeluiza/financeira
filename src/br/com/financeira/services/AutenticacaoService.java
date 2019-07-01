package br.com.financeira.services;

import java.util.Date;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.financeira.entities.Usuario;
import br.com.financeira.persist.ifs.IUsuario;
import br.com.financeira.utils.Util;

@Stateless
public class AutenticacaoService {
	
	@Inject
	private IUsuario usuarioDao; 
	
	public Usuario autenticar(Usuario usuario) throws Exception {
		Usuario usuarioBanco = usuarioDao.findByLoginAtivo(usuario);
		if (usuarioBanco != null && usuarioBanco.getId() != null) {
			if (!Util.getMD5String(usuario.getSenha()).equals(usuarioBanco.getSenha())) {
				throw new Exception("Senha não confere!");
			} else {
				if (usuarioBanco.getDataUltimoAcesso() == null) {
					usuario.setId(usuarioBanco.getId());
				}
				usuarioBanco.setDataUltimoAcesso(new Date());
				usuarioDao.update(usuarioBanco);
				return usuarioBanco;
			}
		} else {
			throw new Exception("Usuário não tem permissão para acesso ao sistema!");
		}
	}
	

}
