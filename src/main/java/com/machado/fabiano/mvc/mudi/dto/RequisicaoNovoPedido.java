package com.machado.fabiano.mvc.mudi.dto;

import javax.validation.constraints.NotBlank;

import com.machado.fabiano.mvc.mudi.model.Pedido;
import com.machado.fabiano.mvc.mudi.model.StatusPedido;

public class RequisicaoNovoPedido {

	@NotBlank
	private String nomeProduto;
	
	@NotBlank
	private String urlProduto;
	
	@NotBlank
	private String urlImagem;
	
	private String descricao;
	
	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
	public void setUrlProduto(String urlProduto) {
		this.urlProduto = urlProduto;
	}
	public void setUrlImagem(String urlImagem) {
		this.urlImagem = urlImagem;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getNomeProduto() {
		return nomeProduto;
	}
	public String getUrlProduto() {
		return urlProduto;
	}
	public String getUrlImagem() {
		return urlImagem;
	}
	public String getDescricao() {
		return descricao;
	}
	
	public Pedido toPedido() {
		Pedido pedido = new Pedido();
		pedido.setNome(nomeProduto);
		pedido.setUrlProduto(urlProduto);
		pedido.setUrlImagem(urlImagem);
		pedido.setDescricao(descricao);
		pedido.setStatus(StatusPedido.AGUARDANDO);
		
		return pedido;
	}
}
