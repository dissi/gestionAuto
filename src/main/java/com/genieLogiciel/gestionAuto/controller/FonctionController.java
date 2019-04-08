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
import com.genieLogiciel.gestionAuto.model.FonctionModel;
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
@RequestMapping(value = "/fonction")
public class FonctionController {
    
    @Autowired
    private PersonnelService personnelService;
    
    @GetMapping(value = "/listeFonction")
    public String listeFonction(Model model) {
        model.addAttribute("title", "Liste des Fonctions");
        final List<FonctionModel> listFonctions = personnelService.allFonctions2() ;   
        model.addAttribute("listFonctions", listFonctions);
        model.addAttribute("fonctionMdl",new FonctionModel());
        model.addAttribute("message", "dddds");
        final List<DemandeMissionModel> listDemandeMissionE = personnelService.allDemandeMissionsByEtat("0");

        int notifMissionE = 0;
        if (!listDemandeMissionE.isEmpty()) {
            notifMissionE = listDemandeMissionE.size();
        }

        model.addAttribute("notifMissionE", notifMissionE);

        return "listeFonction";
    }
    
    @RequestMapping(value = "/enregistrerFonction", method = RequestMethod.POST)
    public String addFonction(Model model, FonctionModel fonctionMdl, final BindingResult result) {
        personnelService.addFonction(fonctionMdl);
        model.addAttribute("message", "enregistre");
         //redirectAttributes.addFlashAttribute("message", "enregistre");
        return "redirect:/fonction/listeFonction";
    }
    
    @RequestMapping(value = "/saveUpdateFonction", method = RequestMethod.POST)
    public String validerUpdate(Model model, FonctionModel fonctionMdl, final BindingResult result) {
        personnelService.validupdatefonction(fonctionMdl);
        model.addAttribute("message", "enregistre");
         //redirectAttributes.addFlashAttribute("message", "enregistre");
        return "redirect:/fonction/listeFonction";
    }
    @RequestMapping(value = "/updateFonction/{fonctionId}", method = RequestMethod.GET)
    public String showFormForUpdate(@PathVariable("fonctionId") long fonctionId, Model theModel) {
        theModel.addAttribute("title", "Liste des Fonctions");
        theModel.addAttribute("title2", "Modification de la fonction");
        final List<FonctionModel> listFonctions = personnelService.allFonctions2();
        theModel.addAttribute("listFonctions", listFonctions);
        FonctionModel lafonction = personnelService.findByFonctionId2(fonctionId);
        theModel.addAttribute("fonctionMdl", lafonction);
        return "updatefonction";
    }
	
    @RequestMapping(value = "/deleteFonction/{fonctionId}", method = RequestMethod.GET)
    public String deletefonc(@PathVariable("fonctionId") long fonctionId, Model theModel) {
        personnelService.deleteFonction(fonctionId);
        return "redirect:/fonction/listeFonction";
    }
	
    
 
    
}