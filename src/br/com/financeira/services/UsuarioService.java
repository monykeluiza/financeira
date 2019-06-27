package br.com.financeira.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.commons.mail.EmailException;

import br.com.financeira.entities.Email;
import br.com.financeira.entities.Funcionario;
import br.com.financeira.entities.Perfil;
import br.com.financeira.entities.ResetSenha;
import br.com.financeira.entities.Usuario;
import br.com.financeira.persist.ifs.IUsuario;
import br.com.financeira.utils.PasswordGenerator;
import br.com.financeira.utils.Util;

@Stateless
public class UsuarioService extends LogService {
	
	@Inject
	private IUsuario usuarioDao;
	
	@Inject
	private EmailService mailService;
	
	public List<Usuario> findAll() {
		return usuarioDao.findAll();
	}
	
	public Usuario update(Usuario usuario, Usuario usuarioLogado) {
		Usuario retorno = usuarioDao.update(usuario);
		salvar(createLog(LogService.ACAO_UPDATE, retorno.getId(), usuarioLogado, "USUARIO"));
		return retorno;
	}
	
	public Usuario save(Usuario usuario, Usuario usuarioLogado) throws Exception {
		if (verificarUsuarioExiste(usuario)) {
			throw new Exception("Este usuário já está cadastrado no sistema. Por favor, verifique suas informações.");
		} else {
			String senhaGerada = PasswordGenerator.getRandomPassword(6);
			String senhaMd5 = Util.getMD5String(senhaGerada);
			usuario.setSenha(senhaMd5);
			usuario.setAtivo(true);
			Usuario retorno = usuarioDao.save(usuario);
			enviarEmailNovoUsuario(usuario, senhaGerada);
			salvar(createLog(LogService.ACAO_INSERT, retorno.getId(), usuarioLogado, "USUARIO"));
			return retorno;
		}
	}
		
	
	private void enviarEmailNovoUsuario(Usuario usuario, String senhaGerada) {
		String conteudo = "Prezado(a),\n\nSeu usuário para acesso ao SAF - SISTEMA DE ADMINISTRAÇÃO DE FINANCEIRAS foi criado com sucesso. Seguem abaixo as instruções para acesso ao sistema: \n \n"
				+ "Login: " + usuario.getLogin() + " \nSenha: " + senhaGerada 
				+ "\n\n\n\nAtenção! Ao acessar pela primeira vez será solicitada a mudança da senha. \n\n\nEssa é uma mensagem automática, favor não responder.";
		String assunto = "SAF - SISTEMA DE ADMINISTRAÇÃO DE FINANCEIRAS - Novo usuário";
		Email email = new Email();
		email.setAssunto(assunto);
		email.setConteudo(conteudo);
		email.setUsuario(usuario);
		try {
			mailService.enviarEmail(email);
		} catch (EmailException e) {
			e.printStackTrace();
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
	
	public Usuario criarUsuarioParaFuncionario(Funcionario funcionario, Usuario usuarioLogado) {
		Usuario usuario = new Usuario();
		try {
			usuario.setAdmin(false);
			usuario.setAtivo(true);
			usuario.setLogin(funcionario.getCpf());
			usuario.setEmail(funcionario.getEmail());
			usuario.setPerfilId(new Perfil(Perfil.PERFIL_FUNCIONARIO));
			save(usuario, usuarioLogado);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuario;
	}
	
	
	public String redefinirSenha(Usuario usuario, ResetSenha resetSenha, Usuario usuarioLogado) throws Exception {
		String msg = null;
		if (!usuario.getSenha().equals(Util.getMD5String(resetSenha.getSenhaAtual()))) {
			msg = "Senha atual inválida! Verifique suas informações e tente novamente.";
			return msg;
		}
		if (!resetSenha.getNovaSenha().equals(resetSenha.getConfirmNovaSenha())) {
			msg = "Nova Senha e Confirmar Senha estão com valores divergentes. Verifique suas informações e tente novamente!";
			return msg;
		}
		
		if (resetSenha.getNovaSenha().length() < 6) {
			msg = "A senha deve ter no mínimo 6 caracteres. Verifique suas informações e tente novamente!";
			return msg;
		}
		
		if (resetSenha.getNovaSenha().equals(resetSenha.getSenhaAtual())) {
			msg = "A nova senha não pode ser igual a senha atual. Verifique suas informações e tente novamente!";
			return msg;
		}
		
		usuario.setSenha(Util.getMD5String(resetSenha.getNovaSenha()));
		usuario.setAtivo(true);
		update(usuario, usuarioLogado);
		enviarEmailRedefinicaoSenha(usuario, resetSenha.getNovaSenha());
		return msg;
	}
	
	
	public String esqueciSenha(Usuario usuario) throws Exception {
		Usuario user = usuarioDao.findByLogin(usuario);
		if (user != null) {
			String senhaGerada = PasswordGenerator.getRandomPassword(6);
			String senhaMd5 = Util.getMD5String(senhaGerada);
			user.setSenha(senhaMd5);
			user.setAtivo(false);
			update(user, user);
			enviarEmailEsqueciSenha(user, senhaGerada);
			return null;
		} else {
			return "CPF não foi encontrado na nossa base. Verifique suas informações e tente novamente. Se o problema persistir entre em contato com o administrador do sistema para mais informações.";
		}
	}
	
//	private Usuario verificarEmailEsqueciSenha(String email) throws Exception {
//		Usuario user = new Usuario();
//		user.setEmail(email);
//		Usuario usuarioEncontrado = ((UsuarioDAO) dao).findByEmail(user);
//		if (usuarioEncontrado == null || usuarioEncontrado.getCodigo() == null) {
//			return null;
//		}
//		return usuarioEncontrado;
//	}
	
	private void enviarEmailRedefinicaoSenha(Usuario usuario, String senhaGerada) {
		String conteudo = "Prezado(a),\n\nSua senha para acesso ao SAF - SISTEMA DE ADMINISTRAÇÃO DE FINANCEIRAS  foi redefinida com sucesso. Utilize as novas informações para acesso ao sistema: \n \n"
				+ "Login: " + usuario.getLogin() + " \nSenha: " + senhaGerada 
				+ "\n\n\n\nAtenção! Ao acessar será solicitada a mudança da senha." + "\n\n\nEssa é uma mensagem automática, favor não responder.";
		String assunto = "SAF - SISTEMA DE ADMINISTRAÇÃO DE FINANCEIRAS - Senha Redefinida";
		Email email = new Email();
		email.setAssunto(assunto);
		email.setConteudo(conteudo);
		email.setUsuario(usuario);
		try {
			mailService.enviarEmail(email);
		} catch (EmailException e) {
			e.printStackTrace();
		}
	}
	
	private void enviarEmailEsqueciSenha(Usuario usuario, String senhaGerada) {
		String conteudo = "Prezado(a),\n\nSua senha para acesso ao SAF - SISTEMA DE ADMINISTRAÇÃO DE FINANCEIRAS  foi redefinida. Seguem abaixo as novas informações para acesso ao sistema: \n \n"
				+ "Login: " + usuario.getLogin() + " \nSenha: " + senhaGerada 
				+ "\n\n\nEssa é uma mensagem automática, favor não responder.";
		String assunto = "SAF - SISTEMA DE ADMINISTRAÇÃO DE FINANCEIRAS - Recuperação de senha";
		Email email = new Email();
		email.setAssunto(assunto);
		email.setConteudo(conteudo);
		email.setUsuario(usuario);
		try {
			mailService.enviarEmail(email);
		} catch (EmailException e) {
			e.printStackTrace();
		}
	}
	
	public Usuario findById(Usuario usuario) {
		return usuarioDao.findById(usuario);
	}
	
}
