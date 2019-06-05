package br.com.financeira.persist.daos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.validation.ConstraintViolationException;

import br.com.financeira.entities.Funcionario;
import br.com.financeira.entities.Usuario;
import br.com.financeira.persist.DataAccess;
import br.com.financeira.persist.ifs.IFuncionarioDao;

@Stateless
public class FuncionarioDAO extends DataAccess<Funcionario>  implements IFuncionarioDao{

	@Override
	public Funcionario save(Funcionario funcionario) {
		try {
			Funcionario result = super.create(funcionario);
			return result;
		} catch (ConstraintViolationException e) {
			System.out.println(e.getConstraintViolations());
			return funcionario;
		} 
	}
	
	@Override
	public Funcionario update(Funcionario funcionario) {
		try {
			Funcionario result = super.update(funcionario);
			return result;
		} catch (ConstraintViolationException e) {
			System.out.println(e.getConstraintViolations());
			return funcionario;
		} 
	}

	@Override
	public List<Funcionario> findAll() {
		return super.findWithNamedQuery("Funcionario.findAll");
	}

	@Override
	public Funcionario findById(Funcionario funcionario) {
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("id", funcionario.getId());
		return (Funcionario) super.findWithNamedQueryUniqueOrNull("Funcionario.findById", parametros);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Funcionario> findSubordinados(Funcionario chefe) {
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("chefeId", chefe.getId());
		return super.findWithNamedQuery("Funcionario.findByChefe", parametros);
	}

	@Override
	public List<Funcionario> findChefes() {
		return super.findWithNamedQuery("Funcionario.findChefes");
	}

	@Override
	public Funcionario findByUsuario(Usuario usuario) {
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("usuarioId", usuario.getId());
		return (Funcionario) super.findWithNamedQueryUniqueOrNull("Funcionario.findByUsuario", parametros);
	}

}
