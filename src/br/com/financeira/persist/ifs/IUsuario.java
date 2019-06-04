package br.com.financeira.persist.ifs;

import java.util.List;

import br.com.financeira.entities.Usuario;

public interface IUsuario {
	
	public Usuario save(Usuario usuario);
	
	public Usuario update(Usuario usuario);
	
	public List<Usuario> findAll();
	
	public Usuario findById(Usuario usuario);
	
	public Usuario findByLogin(Usuario usuario);
	
	public Usuario findByLoginAtivo(Usuario usuario);
	
}
