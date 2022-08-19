package com.machado.fabiano.mvc.mudi.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.machado.fabiano.mvc.mudi.model.Pedido;
import com.machado.fabiano.mvc.mudi.model.StatusPedido;
import com.machado.fabiano.mvc.mudi.repository.PedidoRepository;

@Controller
@RequestMapping("user")
public class UserController {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@GetMapping("pedidos")
	public String home(Model model, Principal principal) {
		List<Pedido> pedidos = pedidoRepository.buscarPedidosPorUser(principal.getName());
		model.addAttribute("pedidos", pedidos);
		return "user/pedidos";
	}
	
	@GetMapping("pedidos/{status}")
	public String porStatus(@PathVariable("status") String status, Model model, Principal principal) {
		List<Pedido> pedidos = pedidoRepository.buscarPedidosPorUserEStatus(
				principal.getName(), StatusPedido.valueOf(status.toUpperCase()));
		model.addAttribute("pedidos", pedidos);
		model.addAttribute("status", status);
		return "user/pedidos";
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public String onError() {
		return "redirect:/user/pedidos";
	}
}
