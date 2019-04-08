/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.genieLogiciel.gestionAuto.controller;

/**
 *
 * @author dissirama
 */

import com.genieLogiciel.gestionAuto.model.CarburantModel;
import com.genieLogiciel.gestionAuto.service.PersonnelService;
//import com.genieLogiciel.gestionAuto.service.PersonnelServiceImpl;
import java.security.Principal;
 
import com.genieLogiciel.gestionAuto.utils.WebUtils;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@EnableAutoConfiguration
@Controller
@RequestMapping(value = "/carburant")
public class CarburantController {
    
    @Autowired
    private PersonnelService personnelService;
    
    @GetMapping(value = "/listeCarburant")
    public String listeCarburant(Model model) {
        model.addAttribute("title", "Liste des Carburants");
        final List<CarburantModel> listCarburants = personnelService.allCarburants2() ;   
        model.addAttribute("listCarburants", listCarburants);
        model.addAttribute("carburantMdl",new CarburantModel());
        model.addAttribute("message", "dddds");
        return "listeCarburant";
    }
    
    @RequestMapping(value = "/enregistrerCarburant", method = RequestMethod.POST)
    public String addCarburant(Model model, CarburantModel carburantMdl, final BindingResult result) {
        personnelService.addCarburant(carburantMdl);
        model.addAttribute("message", "enregistre");
         //redirectAttributes.addFlashAttribute("message", "enregistre");
        return "redirect:/carburant/listeCarburant";
    }
    
    @RequestMapping(value = "/saveUpdateCarburant", method = RequestMethod.POST)
    public String validerUpdate(Model model, CarburantModel carburantMdl, final BindingResult result) {
        personnelService.validupdateCarburant(carburantMdl);
        model.addAttribute("message", "enregistre");
         //redirectAttributes.addFlashAttribute("message", "enregistre");
        return "redirect:/carburant/listeCarburant";
    }
    @RequestMapping(value = "/updateCarburant/{carburantId}", method = RequestMethod.GET)
    public String showFormForUpdate(@PathVariable("carburantId") long carburantId, Model theModel) {
        theModel.addAttribute("title", "Liste des Carburants");
        theModel.addAttribute("title2", "Modification de la carburant");
        final List<CarburantModel> listCarburants = personnelService.allCarburants2();
        theModel.addAttribute("listCarburants", listCarburants);
        CarburantModel lacarburant = personnelService.findByCarburantId2(carburantId);
        theModel.addAttribute("carburantMdl", lacarburant);
        return "updatecarburant";
    }
	
    @RequestMapping(value = "/deleteCarburant/{carburantId}", method = RequestMethod.GET)
    public String deletefonc(@PathVariable("carburantId") long carburantId, Model theModel) {
        personnelService.deleteCarburant(carburantId);
        return "redirect:/carburant/listeCarburant";
    }
	
    
 
    
}