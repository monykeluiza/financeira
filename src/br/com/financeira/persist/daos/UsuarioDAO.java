package br.com.financeira.persist.daos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.validation.ConstraintViolationException;

import br.com.financeira.entities.Usuario;
import br.com.financeira.persist.DataAccess;
import br.com.financeira.persist.ifs.IUsuario;

@Stateless
public class UsuarioDAO extends DataAccess<Usuario> implements IUsuario {

	@Override
	public Usuario save(Usuario usuario) {
		try {
			Usuario usuarioResult = super.create(usuario);
			return usuarioResult;
		} catch (ConstraintViolationException e) {
			System.out.println(e.getConstraintViolations());
			return usuario;
		}
	}

	@Override
	public List<Usuario> findAll() {
		return super.findWithNamedQuery("Usuario.findAll");
	}

//	@SuppressWarnings("unchecked")
//	@Override
//	public List<Usuario> pesquisarPorNome(Usuario usuario) {
//		Map<String, Object> parametros = new HashMap<String, Object>();
//		parametros.put("nomeGrupo", usuario.getNome().toUpperCase() + "%");
//		return super.findWithNamedQuery("Usuario.findByNome", parametros);
//	}

	@Override
	public Usuario update(Usuario usuario) {
		Usuario usuarioResult = super.update(usuario);
		return usuarioResult;
	}

	@Override
	public Usuario findById(Usuario usuario) {
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("id", usuario.getId());
		return (Usuario) super.findWithNamedQueryUniqueOrNull("Usuario.findById", parametros);
	}

	@Override
	public Usuario findByLoginAtivo(Usuario usuario) {
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("login", usuario.getLogin());
		return (Usuario) super.findWithNamedQueryUniqueOrNull("Usuario.findByLoginAtivo", parametros);
	}
	
	@Override
	public Usuario findByLogin(Usuario usuario) {
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("login", usuario.getLogin());
		return (Usuario) super.findWithNamedQueryUniqueOrNull("Usuario.findByLogin", parametros);
	}
	

	
}
