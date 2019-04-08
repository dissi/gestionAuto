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
import com.genieLogiciel.gestionAuto.model.ProduitModel;
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
@RequestMapping(value = "/produit")
public class ProduitController {
    
    @Autowired
    private PersonnelService personnelService;
    
    @GetMapping(value = "/listeProduit")
    public String listeProduit(Model model) {
        model.addAttribute("title", "Liste des Produits");
        final List<ProduitModel> listProduits = personnelService.allProduits2() ;   
        model.addAttribute("listProduits", listProduits);
        model.addAttribute("produitMdl",new ProduitModel());
        model.addAttribute("message", "dddds");
        final List<DemandeMissionModel> listDemandeMissionE = personnelService.allDemandeMissionsByEtat("0");

        int notifMissionE = 0;
        if (!listDemandeMissionE.isEmpty()) {
            notifMissionE = listDemandeMissionE.size();
        }

        model.addAttribute("notifMissionE", notifMissionE);

        return "listeProduit";
    }
    
    @RequestMapping(value = "/enregistrerProduit", method = RequestMethod.POST)
    public String addProduit(Model model, ProduitModel produitMdl, final BindingResult result) {
        personnelService.addProduit(produitMdl);
        model.addAttribute("message", "enregistre");
         //redirectAttributes.addFlashAttribute("message", "enregistre");
        return "redirect:/produit/listeProduit";
    }
    
    @RequestMapping(value = "/saveUpdateProduit", method = RequestMethod.POST)
    public String validerUpdate(Model model, ProduitModel produitMdl, final BindingResult result) {
        personnelService.validupdateProduit(produitMdl);
        model.addAttribute("message", "enregistre");
         //redirectAttributes.addFlashAttribute("message", "enregistre");
        return "redirect:/produit/listeProduit";
    }
    @RequestMapping(value = "/updateProduit/{produitId}", method = RequestMethod.GET)
    public String showFormForUpdate(@PathVariable("produitId") long produitId, Model theModel) {
        theModel.addAttribute("title", "Liste des Produits");
        theModel.addAttribute("title2", "Modification de la produit");
        final List<ProduitModel> listProduits = personnelService.allProduits2();
        theModel.addAttribute("listProduits", listProduits);
        ProduitModel laproduit = personnelService.findByProduitId2(produitId);
        theModel.addAttribute("produitMdl", laproduit);
        final List<DemandeMissionModel> listDemandeMissionE = personnelService.allDemandeMissionsByEtat("0");

        int notifMissionE = 0;
        if (!listDemandeMissionE.isEmpty()) {
            notifMissionE = listDemandeMissionE.size();
        }

        theModel.addAttribute("notifMissionE", notifMissionE);

        return "updateproduit";
    }
	
    @RequestMapping(value = "/deleteProduit/{produitId}", method = RequestMethod.GET)
    public String deletefonc(@PathVariable("produitId") long produitId, Model theModel) {
        personnelService.deleteProduit(produitId);
        return "redirect:/produit/listeProduit";
    }
	
    
 
    
}