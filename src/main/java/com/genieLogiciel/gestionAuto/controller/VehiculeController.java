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
import com.genieLogiciel.gestionAuto.model.VehiculeModel;
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
@RequestMapping(value = "/vehicule")

public class VehiculeController {
    
    @Autowired
    private PersonnelService personnelService;
   
    @GetMapping(value = "/listeVehicule/{groupe}")
    public String listeVehicule(@PathVariable("groupe") String groupe,Model model) {
        model.addAttribute("title", "Liste des Vehicules");
        final List<VehiculeModel> listVehicules = personnelService.allVehicules() ;   
        model.addAttribute("listVehicules", listVehicules);
        model.addAttribute("vehiculeMdl",new VehiculeModel());
        model.addAttribute("message", "dddds");
        final List<DemandeMissionModel> listDemandeMissionE;
        if ("agt".equals(groupe)) {
             listDemandeMissionE = personnelService.allDemandeMissionsByEtat("1");
        
        } else {
            listDemandeMissionE = personnelService.allDemandeMissionsByEtat("0");
        
        }
        int notifMissionE = 0;
        if (!listDemandeMissionE.isEmpty()) {
            notifMissionE = listDemandeMissionE.size();
        }
        
        model.addAttribute("notifMissionE", notifMissionE);

        if("agt".equals(groupe)){
            return "agtListeVehicule";
        }else{
            return "admListeVehicule";          
        }
    }
    @GetMapping(value = "/nouveauVehicule")
    public String nouveauVehicule(Model model) {
        model.addAttribute("title", "Ajout d'un Vehicule");
        model.addAttribute("vehiculeMdl",new VehiculeModel());
        model.addAttribute("message", "");
        final List<DemandeMissionModel> listDemandeMissionE = personnelService.allDemandeMissionsByEtat("0");

        int notifMissionE = 0;
        if (!listDemandeMissionE.isEmpty()) {
            notifMissionE = listDemandeMissionE.size();
        }

        model.addAttribute("notifMissionE", notifMissionE);

        return "formAjoutVehicule";
    }
    
    @RequestMapping(value = "/enregistrerVehicule", method = RequestMethod.POST)
    public String addVehicule(Model model, VehiculeModel agtMdl, final BindingResult result) {
        personnelService.addVehicule(agtMdl);
        model.addAttribute("message", "enregistre");
        model.addAttribute("vehiculeMdl",new VehiculeModel());
        model.addAttribute("fonctionslist",personnelService.allFonctions2());
        final List<DemandeMissionModel> listDemandeMissionE = personnelService.allDemandeMissionsByEtat("0");

        int notifMissionE = 0;
        if (!listDemandeMissionE.isEmpty()) {
            notifMissionE = listDemandeMissionE.size();
        }

        model.addAttribute("notifMissionE", notifMissionE);

        return "formAjoutVehicule";
    }
    
    @RequestMapping(value = "/saveUpdateVehicule", method = RequestMethod.POST)
    public String validerUpdate(Model model, VehiculeModel vehiculeMdl, final BindingResult result) {
        personnelService.validupdateVehicule(vehiculeMdl);
        model.addAttribute("message", "enregistre");
         //redirectAttributes.addFlashAttribute("message", "enregistre");
        return "redirect:/vehicule/listeVehicule/adm";
    }
    @RequestMapping(value = "/updateVehicule/{vehiculeId}", method = RequestMethod.GET)
    public String showFormForUpdate(@PathVariable("vehiculeId") long vehiculeId, Model theModel) {
        theModel.addAttribute("title", "Modification de la vehicule");
        theModel.addAttribute("title2", "Modification de la vehicule");
        
        theModel.addAttribute("fonctionslist",personnelService.allFonctions2());
       
        VehiculeModel lavehicule = personnelService.findByVehiculeId(vehiculeId);
        theModel.addAttribute("vehiculeMdl", lavehicule);
        return "updatevehicule";
    }
	
    @RequestMapping(value = "/deleteVehicule/{vehiculeId}", method = RequestMethod.GET)
    public String deletefonc(@PathVariable("vehiculeId") long vehiculeId, Model theModel) {
        personnelService.deleteVehicule(vehiculeId);
        return "redirect:/vehicule/listeVehicule/adm";
    }
	
    
 
    
}