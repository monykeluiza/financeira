package br.com.financeira.persist.ifs;

import java.util.List;

import br.com.financeira.entities.Funcionario;
import br.com.financeira.entities.Usuario;

public interface IFuncionarioDao {
	
	public Funcionario save(Funcionario funcionario);
	
	public Funcionario update(Funcionario funcionario);
	
	public List<Funcionario> findAll();
	
	public Funcionario findById(Funcionario funcionario);
	
	public List<Funcionario> findSubordinados(Funcionario chefe);
	
	public List<Funcionario> findChefes();
	
	public Funcionario findByUsuario(Usuario usuario);
	
}
