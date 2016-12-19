package com.unidadcoronaria.prestaciones.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.unidadcoronaria.prestaciones.constant.Constants;
import com.unidadcoronaria.prestaciones.domain.MedicalServiceResource;
import com.unidadcoronaria.prestaciones.domain.Resource;
import com.unidadcoronaria.prestaciones.domain.dto.MedicalServiceResourceDTO;
import com.unidadcoronaria.prestaciones.service.AuthorizationService;
import com.unidadcoronaria.prestaciones.service.MedicalServiceResourceService;
import com.unidadcoronaria.prestaciones.service.MedicalServiceService;
import com.unidadcoronaria.prestaciones.service.MedicamentService;
import com.unidadcoronaria.prestaciones.service.ResourceService;


@Controller
@EnableAutoConfiguration
public class MedicalServiceResourceController {

	@Autowired
	private AuthorizationService authorizationService;
	
	@Autowired
	private MedicalServiceService medicalService;
	
	@Autowired
	private MedicalServiceResourceService medicalServiceResourceService;
	
	@Autowired
	private ResourceService resourceService;
	
	@Autowired
	private MedicamentService medicamentService;
	

	@RequestMapping(value = "/medicalServiceResource",  method = RequestMethod.GET)
	@ResponseBody
	List<MedicalServiceResource> listMedicalServiceResource(@RequestHeader(value = Constants.AUTHORIZATION_HEADER) final String token) {
		this.authorizationService.validateToken(token);
		Resource resource = resourceService.getResourceByImei(token);
		return this.medicalServiceResourceService.getMedicalServiceResourceList(resource.getResourceId());
	}
	
	@RequestMapping(value = "/medicalServiceResource/{medicalServiceResourceId}/currentState",  method = RequestMethod.GET)
	@ResponseBody  
	public Integer getCurrentState(@PathVariable("medicalServiceResourceId") Integer medicalServiceResourceId, @RequestHeader(value = Constants.AUTHORIZATION_HEADER) final String token) {
		this.authorizationService.validateToken(token);
		return medicalServiceResourceService.getMedicalServicesResourceCurrentState(medicalServiceResourceId);
	}
	
	@RequestMapping(value = "/medicalServiceResource/{medicalServiceResourceId}/authorizedStates",  method = RequestMethod.GET)
	@ResponseBody
	List<Integer> getAuthorizedStates(@PathVariable("medicalServiceResourceId") Integer medicalServiceResourceId, @RequestHeader(value = Constants.AUTHORIZATION_HEADER) final String token) {
		this.authorizationService.validateToken(token);
		return medicalServiceResourceService.getMedicalServicesResourceAuthorizedStates(medicalServiceResourceId);
	}
	
	@RequestMapping(value = "/medicalServiceResource/setState",  method = RequestMethod.PUT)
	@ResponseBody 
	void setState(@RequestBody MedicalServiceResourceDTO dto, @RequestHeader(value = Constants.AUTHORIZATION_HEADER ) final String token) {
		this.authorizationService.validateToken(token);
		medicalServiceResourceService.setMedicalServicesResourceState(dto);
	}
	
	
}