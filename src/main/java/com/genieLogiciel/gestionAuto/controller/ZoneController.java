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
import com.genieLogiciel.gestionAuto.model.ZoneModel;
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
@RequestMapping(value = "/zone")
public class ZoneController {
    
    @Autowired
    private PersonnelService personnelService;
    
    @GetMapping(value = "/listeZone")
    public String listeZone(Model model) {
        model.addAttribute("title", "Liste des Zones");
        final List<ZoneModel> listZones = personnelService.allZones2() ;   
        model.addAttribute("listZones", listZones);
        model.addAttribute("zoneMdl",new ZoneModel());
        model.addAttribute("message", "dddds");
        final List<DemandeMissionModel> listDemandeMissionE;
        listDemandeMissionE = personnelService.allDemandeMissionsByEtat("0");

        int notifMissionE = 0;
        if (!listDemandeMissionE.isEmpty()) {
            notifMissionE = listDemandeMissionE.size();
        }

        model.addAttribute("notifMissionE", notifMissionE);

        return "listeZone";
    }
    
    @RequestMapping(value = "/enregistrerZone", method = RequestMethod.POST)
    public String addZone(Model model, ZoneModel zoneMdl, final BindingResult result) {
        personnelService.addZone(zoneMdl);
        model.addAttribute("message", "enregistre");
         //redirectAttributes.addFlashAttribute("message", "enregistre");
        return "redirect:/zone/listeZone";
    }
    
    @RequestMapping(value = "/saveUpdateZone", method = RequestMethod.POST)
    public String validerUpdate(Model model, ZoneModel zoneMdl, final BindingResult result) {
        personnelService.validupdateZone(zoneMdl);
        model.addAttribute("message", "enregistre");
         //redirectAttributes.addFlashAttribute("message", "enregistre");
        return "redirect:/zone/listeZone";
    }
    @RequestMapping(value = "/updateZone/{zoneId}", method = RequestMethod.GET)
    public String showFormForUpdate(@PathVariable("zoneId") long zoneId, Model theModel) {
        theModel.addAttribute("title", "Liste des Zones");
        theModel.addAttribute("title2", "Modification de la zone");
        final List<ZoneModel> listZones = personnelService.allZones2();
        theModel.addAttribute("listZones", listZones);
        ZoneModel lazone = personnelService.findByZoneId2(zoneId);
        theModel.addAttribute("zoneMdl", lazone);
        final List<DemandeMissionModel> listDemandeMissionE = personnelService.allDemandeMissionsByEtat("0");

        int notifMissionE = 0;
        if (!listDemandeMissionE.isEmpty()) {
            notifMissionE = listDemandeMissionE.size();
        }

        theModel.addAttribute("notifMissionE", notifMissionE);

        return "updatezone";
    }
	
    @RequestMapping(value = "/deleteZone/{zoneId}", method = RequestMethod.GET)
    public String deletefonc(@PathVariable("zoneId") long zoneId, Model theModel) {
        personnelService.deleteZone(zoneId);
        return "redirect:/zone/listeZone";
    }
	
    
 
    
}