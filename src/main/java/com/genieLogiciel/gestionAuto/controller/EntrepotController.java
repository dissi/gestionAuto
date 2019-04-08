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

import com.genieLogiciel.gestionAuto.model.DemandeMissionModel;
import com.genieLogiciel.gestionAuto.model.EntrepotModel;
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
@RequestMapping(value = "/entrepot")
public class EntrepotController {
    
    @Autowired
    private PersonnelService personnelService;
    
    @GetMapping(value = "/listeEntrepot")
    public String listeEntrepot(Model model) {
        model.addAttribute("title", "Liste des Entrepots");
        final List<EntrepotModel> listEntrepots = personnelService.allEntrepots2() ;   
        model.addAttribute("listEntrepots", listEntrepots);
        model.addAttribute("entrepotMdl",new EntrepotModel());
        model.addAttribute("message", "dddds");
        final List<DemandeMissionModel> listDemandeMissionE = personnelService.allDemandeMissionsByEtat("0");

        int notifMissionE = 0;
        if (!listDemandeMissionE.isEmpty()) {
            notifMissionE = listDemandeMissionE.size();
        }

        model.addAttribute("notifMissionE", notifMissionE);

        return "listeEntrepot";
    }
    
    @RequestMapping(value = "/enregistrerEntrepot", method = RequestMethod.POST)
    public String addEntrepot(Model model, EntrepotModel entrepotMdl, final BindingResult result) {
        personnelService.addEntrepot(entrepotMdl);
        model.addAttribute("message", "enregistre");
         //redirectAttributes.addFlashAttribute("message", "enregistre");
        return "redirect:/entrepot/listeEntrepot";
    }
    
    @RequestMapping(value = "/saveUpdateEntrepot", method = RequestMethod.POST)
    public String validerUpdate(Model model, EntrepotModel entrepotMdl, final BindingResult result) {
        personnelService.validupdateEntrepot(entrepotMdl);
        model.addAttribute("message", "enregistre");
         //redirectAttributes.addFlashAttribute("message", "enregistre");
        return "redirect:/entrepot/listeEntrepot";
    }
    @RequestMapping(value = "/updateEntrepot/{entrepotId}", method = RequestMethod.GET)
    public String showFormForUpdate(@PathVariable("entrepotId") long entrepotId, Model theModel) {
        theModel.addAttribute("title", "Liste des Entrepots");
        theModel.addAttribute("title2", "Modification de la entrepot");
        final List<EntrepotModel> listEntrepots = personnelService.allEntrepots2();
        theModel.addAttribute("listEntrepots", listEntrepots);
        EntrepotModel laentrepot = personnelService.findByEntrepotId2(entrepotId);
        theModel.addAttribute("entrepotMdl", laentrepot);
        final List<DemandeMissionModel> listDemandeMissionE = personnelService.allDemandeMissionsByEtat("0");

        int notifMissionE = 0;
        if (!listDemandeMissionE.isEmpty()) {
            notifMissionE = listDemandeMissionE.size();
        }

        theModel.addAttribute("notifMissionE", notifMissionE);

        return "updateentrepot";
    }
	
    @RequestMapping(value = "/deleteEntrepot/{entrepotId}", method = RequestMethod.GET)
    public String deletefonc(@PathVariable("entrepotId") long entrepotId, Model theModel) {
        personnelService.deleteEntrepot(entrepotId);
        return "redirect:/entrepot/listeEntrepot";
    }
	
    
 
    
}