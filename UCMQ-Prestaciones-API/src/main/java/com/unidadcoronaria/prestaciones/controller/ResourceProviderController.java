package com.unidadcoronaria.prestaciones.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.unidadcoronaria.prestaciones.constant.Constants;
import com.unidadcoronaria.prestaciones.domain.Resource;
import com.unidadcoronaria.prestaciones.domain.ResourceProvider;
import com.unidadcoronaria.prestaciones.service.AuthorizationService;
import com.unidadcoronaria.prestaciones.service.ResourceProviderService;
import com.unidadcoronaria.prestaciones.service.ResourceService;

@Controller
@EnableAutoConfiguration
public class ResourceProviderController {
	
	@Autowired
	private AuthorizationService authorizationService;
	
	@Autowired
	private ResourceProviderService resourceProviderService;
	
	@Autowired
	private ResourceService resourceService; 
	
	@RequestMapping(value = "/resourcesProviders",  method = RequestMethod.GET)
	@ResponseBody
	List<ResourceProvider> listResourceProvider(@RequestHeader(value = Constants.AUTHORIZATION_HEADER, required = false) final String token) {
		this.authorizationService.validateToken("451236200698230");
		Resource resource;
		resource = this.resourceService.getResourceByImei("451236200698230");
		return this.resourceProviderService.getResourceProviderId(resource.getResourceId());
	}

}
