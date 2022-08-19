package com.machado.fabiano.mvc.mudi.interceptor;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;


public class InterceptadorDeAcessos implements HandlerInterceptor {

	public static List<Acesso> acessos = new ArrayList<Acesso>();
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		Acesso acesso = new Acesso();
		acesso.path = request.getRequestURI();
		acesso.momento = LocalDateTime.now();
		
		request.setAttribute("acesso", acesso);
		
		return true;
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
		Acesso acesso = (Acesso)request.getAttribute("acesso");
		acesso.duracao = Duration.between(acesso.momento, LocalDateTime.now());
		acessos.add(acesso);
	}
	
	public static class Acesso {
		private String path;
		private LocalDateTime momento;
		private Duration duracao;
		
		public String getPath() {
			return path;
		}
		public void setPath(String path) {
			this.path = path;
		}
		public LocalDateTime getMomento() {
			return momento;
		}
		public void setMomento(LocalDateTime momento) {
			this.momento = momento;
		}
		public String getDuracao() {
			return "" + duracao.toMillis() + " ms";
		}
		public void setDuracao(Duration duracao) {
			this.duracao = duracao;
		}
	}
}
