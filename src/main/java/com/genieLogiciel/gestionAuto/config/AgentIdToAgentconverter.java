/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.genieLogiciel.gestionAuto.config;

import com.genieLogiciel.gestionAuto.model.AgentModel;
import com.genieLogiciel.gestionAuto.service.PersonnelService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * A converter class used in views to map id's to actual userProfile objects.
 */
@Component
public class AgentIdToAgentconverter implements Converter<Object,AgentModel> {

	@Autowired
	PersonnelService personnelService;

	/**
	 * Gets UserProfile by Id
	 * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
	 */
	public  AgentModel convert(Object element) {
		Integer id = Integer.parseInt((String)element);
		AgentModel fonction= personnelService.findByAgentId2(id);
		return fonction;
	}
	
}