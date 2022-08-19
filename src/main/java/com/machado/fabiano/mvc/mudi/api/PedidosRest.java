package com.machado.fabiano.mvc.mudi.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.machado.fabiano.mvc.mudi.model.Pedido;
import com.machado.fabiano.mvc.mudi.model.StatusPedido;
import com.machado.fabiano.mvc.mudi.repository.PedidoRepository;

@RestController
@RequestMapping("/api/pedidos")
public class PedidosRest {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@GetMapping("aguardando")
	public List<Pedido> getPedidosAguardando() {
		
		Sort sort = Sort.by("id").descending();
		PageRequest paginacao = PageRequest.of(0, 5, sort);
		
		return pedidoRepository.findByStatus(StatusPedido.AGUARDANDO, paginacao);
	}
}
