package br.com.financeira.entities;

public class Email {
	
	private String assunto;
	private String conteudo;
	private Usuario usuario;
	private String mailTo;
	
	public static final String MAIL_HOST = "smtp.gmail.com";
	public static final String MAIL_SENDER = "vazolisalvador@gmail.com";
	public static final String MAIL_USER = "vazolisalvador";
	public static final String MAIl_PORT = "465";
	public static final String MAIL_SENDER_PASS = "vazoli@cabssa1234";
	
	public String getAssunto() {
		return assunto;
	}
	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}
	public String getConteudo() {
		return conteudo;
	}
	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public String getMailTo() {
		return mailTo;
	}
	public void setMailTo(String mailTo) {
		this.mailTo = mailTo;
	}
	
	

}
