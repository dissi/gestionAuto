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

import com.genieLogiciel.gestionAuto.model.AgentModel;
import com.genieLogiciel.gestionAuto.model.DemandeMissionModel;
import com.genieLogiciel.gestionAuto.service.PersonnelService;
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
@RequestMapping(value = "/agent")

public class AgentController {
    
    @Autowired
    private PersonnelService personnelService;
    
    @GetMapping(value = "/listeAgent")
    public String listeAgent(Model model) {
        model.addAttribute("title", "Liste des Agents");
        final List<AgentModel> listAgents = personnelService.allAgents() ;   
        model.addAttribute("listAgents", listAgents);
        model.addAttribute("agentMdl",new AgentModel());
        model.addAttribute("message", "dddds");
        final List<DemandeMissionModel> listDemandeMissionE = personnelService.allDemandeMissionsByEtat("0");
        int notifMissionE = 0;
        if (!listDemandeMissionE.isEmpty()) {
            notifMissionE = listDemandeMissionE.size();
        }
        
        model.addAttribute("notifMissionE", notifMissionE);
        return "listeAgent";
    }
    
    @GetMapping(value = "/listeChauffeur/{groupe}")
    public String listeChauffeur(@PathVariable("groupe") String groupe,Model model) {
        model.addAttribute("title", "Liste des Chauffeurs");
        final List<AgentModel> listAgents = personnelService.allAgentsChauffeurs(2) ;   
        model.addAttribute("listAgents", listAgents);
        model.addAttribute("agentMdl",new AgentModel());
        model.addAttribute("message", "dddds");
        final List<DemandeMissionModel> listDemandeMissionE = personnelService.allDemandeMissionsByEtat("0");
        int notifMissionE = 0;
        if (!listDemandeMissionE.isEmpty()) {
            notifMissionE = listDemandeMissionE.size();
        }
        
        model.addAttribute("notifMissionE", notifMissionE);
        return "listeAgent";
    }
    @GetMapping(value = "/nouveauAgent")
    public String nouveauAgent(Model model) {
        model.addAttribute("title", "Ajout d'un Agent");
        model.addAttribute("agentMdl",new AgentModel());
        model.addAttribute("typecarburantslist",personnelService.allCarburants2());
        model.addAttribute("fonctionslist",personnelService.allFonctions2());
        model.addAttribute("message", "");
        final List<DemandeMissionModel> listDemandeMissionE = personnelService.allDemandeMissionsByEtat("0");
        int notifMissionE = 0;
        if (!listDemandeMissionE.isEmpty()) {
            notifMissionE = listDemandeMissionE.size();
        }
        
        model.addAttribute("notifMissionE", notifMissionE);
        return "formAjoutAgent";
    }
    
    @RequestMapping(value = "/enregistrerAgent", method = RequestMethod.POST)
    public String addAgent(Model model, AgentModel agtMdl, final BindingResult result) {
        personnelService.addAgent(agtMdl);
        final List<DemandeMissionModel> listDemandeMissionE = personnelService.allDemandeMissionsByEtat("0");

        int notifMissionE = 0;
        if (!listDemandeMissionE.isEmpty()) {
            notifMissionE = listDemandeMissionE.size();
        }

        model.addAttribute("notifMissionE", notifMissionE);
        model.addAttribute("message", "enregistre");
        model.addAttribute("agentMdl",new AgentModel());
        model.addAttribute("fonctionslist",personnelService.allFonctions2());
        return "formAjoutAgent";
    }
    
    @RequestMapping(value = "/saveUpdateAgent", method = RequestMethod.POST)
    public String validerUpdate(Model model, AgentModel agentMdl, final BindingResult result) {
        personnelService.validupdateAgent(agentMdl);
        model.addAttribute("message", "enregistre");
         //redirectAttributes.addFlashAttribute("message", "enregistre");
        return "redirect:/agent/listeAgent";
    }
    @RequestMapping(value = "/updateAgent/{agentId}", method = RequestMethod.GET)
    public String showFormForUpdate(@PathVariable("agentId") long agentId, Model theModel) {
        theModel.addAttribute("title", "Modification de la agent");
        theModel.addAttribute("title2", "Modification de la agent");
        
        theModel.addAttribute("fonctionslist",personnelService.allFonctions2());
       
        AgentModel laagent = personnelService.findByAgentId(agentId);
        theModel.addAttribute("agentMdl", laagent);
                final List<DemandeMissionModel> listDemandeMissionE = personnelService.allDemandeMissionsByEtat("0") ;   
        
         int notifMissionE = 0;
        if (!listDemandeMissionE.isEmpty()) {
            notifMissionE = listDemandeMissionE.size();
        }
        
        theModel.addAttribute("notifMissionE", notifMissionE);
        return "updateagent";
    }
	
    @RequestMapping(value = "/deleteAgent/{agentId}", method = RequestMethod.GET)
    public String deletefonc(@PathVariable("agentId") long agentId, Model theModel) {
        personnelService.deleteAgent(agentId);
        return "redirect:/agent/listeAgent";
    }
	
    
 
    
}