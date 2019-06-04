package br.com.financeira.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.financeira.entities.Usuario;


@WebFilter("/pages/*")
public class LoginFilter implements Filter {
	
	private boolean usuarioLogado;

    public LoginFilter() {}

	public void destroy() {}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		Usuario usuario = (Usuario) ((HttpServletRequest) request).getSession().getAttribute("usuarioLogado");
		usuarioLogado = (usuario == null?false:true);
		String contextPath = ((HttpServletRequest) request).getContextPath();
		if (!usuarioLogado) {
			 ((HttpServletResponse) response).sendRedirect(contextPath + "/index.xhtml");
		} else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}


}
