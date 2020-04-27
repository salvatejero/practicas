package com.practicas.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.practicas.model.Make;
import com.practicas.services.UtilsService;

@RestController
@RequestMapping("maestros")
public class MaestrosRest {

	@Autowired
	private UtilsService utilsService;

	@GetMapping(value="/makes", produces = "application/json")
    public @ResponseBody List<Make> getMakes() {
        return utilsService.getCarsMakes();
    }
	
}
