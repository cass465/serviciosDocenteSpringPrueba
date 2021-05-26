package co.edu.unicundi.SpringPrueba.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unicundi.SpringPrueba.service.IUsuarioService;
import io.swagger.annotations.Api;

@RestController
@RequestMapping("/tokens")
@Api(value = "Users microservice", description = "Gestion de login")
public class TokenController {
	
	@Autowired
	private IUsuarioService usuarioService;
	
	
}
