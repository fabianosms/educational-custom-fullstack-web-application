package com.machado.fabiano.mvc.mudi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.machado.fabiano.mvc.mudi.dto.RequisicaoNovoPedido;
import com.machado.fabiano.mvc.mudi.model.Pedido;
import com.machado.fabiano.mvc.mudi.model.User;
import com.machado.fabiano.mvc.mudi.repository.PedidoRepository;
import com.machado.fabiano.mvc.mudi.repository.UserRepository;

@Controller
@RequestMapping("pedidos")
public class PedidoController {
	
	@Autowired
	PedidoRepository pedidoRepository;
	
	@Autowired
	UserRepository usuarioRepository;

	@GetMapping("formulario")
	public String formulario(RequisicaoNovoPedido requisicao) {
		return "pedidos/formulario";
	}
	
	@PostMapping("novo")
	public String novo(@Valid RequisicaoNovoPedido requisicao, BindingResult resultado) {
		if (resultado.hasErrors()) {
			return "pedidos/formulario";
		}
		
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = usuarioRepository.findByUsername(username);
		
		Pedido pedido = requisicao.toPedido();
		pedido.setUser(user);
		pedidoRepository.save(pedido);
		
		return "redirect:/user/pedidos";
	}
}