package br.com.financeira.entities;

public class ResetSenha {
	
	private String senhaAtual;
	private String novaSenha;
	private String confirmNovaSenha;
	
	public String getSenhaAtual() {
		return senhaAtual;
	}
	public void setSenhaAtual(String senhaAtual) {
		this.senhaAtual = senhaAtual;
	}
	public String getNovaSenha() {
		return novaSenha;
	}
	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}
	public String getConfirmNovaSenha() {
		return confirmNovaSenha;
	}
	public void setConfirmNovaSenha(String confirmNovaSenha) {
		this.confirmNovaSenha = confirmNovaSenha;
	}

}
