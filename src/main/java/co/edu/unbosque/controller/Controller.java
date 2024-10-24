package co.edu.unbosque.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.unbosque.model.Calculadora;

@org.springframework.stereotype.Controller
public class Controller {
	
	private Calculadora c;

	@GetMapping
	public String index() {
		return "index";
	}
	
	@PostMapping("/calcular")
	public String calcular(@RequestParam("ipv4") String ipOriginal,
	                       @RequestParam("mask") String mascaraIngresada, 
	                       Model model) {
	    
	    String ipPatron = "^([0-9]{1,3}\\.){3}[0-9]{1,3}$";
	    if (!ipOriginal.matches(ipPatron)) {
	        model.addAttribute("error", "La dirección IP no es válida.");
	        return "index";  
	    }

	    
	    String[] octetos = ipOriginal.split("\\.");
	    for (String octeto : octetos) {
	        int valorOcteto = Integer.parseInt(octeto);
	        if (valorOcteto < 0 || valorOcteto > 255) {
	            model.addAttribute("error", "La dirección IP tiene octetos fuera del rango permitido (0-255).");
	            return "index";
	        }
	    }

	    
	    int mascara;
	    try {
	        mascara = Integer.parseInt(mascaraIngresada);
	        if (mascara < 0 || mascara > 32) {
	            model.addAttribute("error", "La máscara debe estar entre 0 y 32.");
	            return "index";
	        }
	    } catch (NumberFormatException e) {
	        model.addAttribute("error", "La máscara no es válida.");
	        return "index";
	    }

	    
	    c = new Calculadora(ipOriginal, mascara);

	    model.addAttribute("cantidadHost", c.cantidadHost(mascara));
	    model.addAttribute("ipRed", c.ipRed());
	    model.addAttribute("ipBroadcast", c.ipBroadcast());
	    model.addAttribute("claseIP", c.clase());
	    model.addAttribute("claIP", c.clasificacionPrivadaPublica());
	    model.addAttribute("rango", c.primeraIpUtil() + " - " + c.ultimaIpUtil());
	    model.addAttribute("porcionRed", c.getPorcionRed());
	    model.addAttribute("porcionHost", c.getPorcionHost());

	    return "index";
	}
	
}
