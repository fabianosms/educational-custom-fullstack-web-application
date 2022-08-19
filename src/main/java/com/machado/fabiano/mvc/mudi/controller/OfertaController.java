package com.machado.fabiano.mvc.mudi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ofertas")
public class OfertaController {

	@GetMapping
	public String getFormularioParaOfertas() {
		return "ofertas/formulario_ofertas";
	}
}
