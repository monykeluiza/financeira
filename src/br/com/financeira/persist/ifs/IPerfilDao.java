package br.com.financeira.persist.ifs;

import java.util.List;

import br.com.financeira.entities.Perfil;

public interface IPerfilDao {
	
	public Perfil save(Perfil perfil);
	
	public Perfil update(Perfil perfil);
	
	public List<Perfil> findAll();
	
	public Perfil findById(Perfil perfil);
	
}
