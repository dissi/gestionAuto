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

import com.genieLogiciel.gestionAuto.model.AffectationModel;
import com.genieLogiciel.gestionAuto.model.AgentModel;
import com.genieLogiciel.gestionAuto.model.DemandeMissionModel;
import com.genieLogiciel.gestionAuto.service.PersonnelService;
import java.security.Principal;
 
import com.genieLogiciel.gestionAuto.utils.WebUtils;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@EnableAutoConfiguration
@Controller
@RequestMapping(value = "/demandeMission")
public class DemandeMissionController {
    
    @Autowired
    private PersonnelService personnelService;
    
    @GetMapping(value = "/listeDemandeMission")
    public String listeDemandeMission(Model model) {
        model.addAttribute("title", "Liste des Demandes de Missions");
        final List<DemandeMissionModel> listDemandeMissions = personnelService.allDemandeMissions() ;   
        model.addAttribute("listDemandeMissions", listDemandeMissions);
        model.addAttribute("demandeMissionMdl",new DemandeMissionModel());
        model.addAttribute("message", "dddds");
        final List<DemandeMissionModel> listDemandeMissionE = personnelService.allDemandeMissionsByEtat("0");

        int notifMissionE = 0;
        if (!listDemandeMissionE.isEmpty()) {
            notifMissionE = listDemandeMissionE.size();
        }

        model.addAttribute("notifMissionE", notifMissionE);
        return "listeDemandeMission";
    }
    @GetMapping(value = "/listeEnCours/{groupe}")
    public String listeEnCours(@PathVariable("groupe") String groupe,Model model) {
        model.addAttribute("title", "Liste des Demandes de Missions en cours");
        final List<DemandeMissionModel> listDemandeMission = personnelService.allDemandeMissionsByEtat("0") ;   
        model.addAttribute("listDemandeMissions", listDemandeMission);
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
            return "agtListeDemandeMissionC";
        }else if("adm".equals(groupe)){
            return "admListeDemandeMissionC";          
        }else{
            return "magListeDemandeMissionC";
        }
    }
    @GetMapping(value = "/listeValider/{groupe}")
    public String listeValider(@PathVariable("groupe") String groupe,Model model) {
        model.addAttribute("title", "Liste des Demandes de Missions validées");
        final List<DemandeMissionModel> listDemandeMission = personnelService.allDemandeMissionsByEtat("1") ;   
        model.addAttribute("listDemandeMissions", listDemandeMission);
        model.addAttribute("message", "");
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
            return "agtListeDemandeMissionV";
        }else{
            return "admListeDemandeMissionV";          
        }
    }
    @GetMapping(value = "/listeTraiter/{groupe}")
    public String listeTraiter(@PathVariable("groupe") String groupe,Model model) {
        model.addAttribute("title", "Liste des Demandes de Missions traitées");
        final List<AffectationModel> listAffectation = personnelService.allAffectations(); 
        System.out.println("com.genieLogiciel.gestionAuto.controller.DemandeMissionController.listeTraiter()");
        System.out.println(listAffectation.isEmpty());
        model.addAttribute("listAffectation", listAffectation);
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
        model.addAttribute("message", "");
        if("agt".equals(groupe)){
            return "agtListeDemandeMissionT";
        }else{
            return "admListeDemandeMissionT";          
        }
    }
    @GetMapping(value = "/vehiculeAffecter/{groupe}")
    public String listevehiculeAffecter(@PathVariable("groupe") String groupe,Model model) {
        model.addAttribute("title", "Liste des Vehicules Affectés");
        final List<AffectationModel> listAffectation = personnelService.allAffectations(); 
        System.out.println("com.genieLogiciel.gestionAuto.controller.DemandeMissionController.listeTraiter()");
        System.out.println(listAffectation.isEmpty());
        model.addAttribute("listAffectation", listAffectation);
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
        model.addAttribute("message", "");
        if("agt".equals(groupe)){
            return "agtListeVehiculeA";
        }else{
            return "admListeVehiculeA";          
        }
    }
    @GetMapping(value = "/listeChauffeurAffecter/{groupe}")
    public String listeChauffeurAffecter(@PathVariable("groupe") String groupe,Model model) {
        model.addAttribute("title", "Liste des Chauffeurs Affectés");
        final List<AffectationModel> listAffectation = personnelService.allAffectations(); 
        model.addAttribute("listAffectation", listAffectation);
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
        model.addAttribute("message", "");
        model.addAttribute("notifMissionE", notifMissionE);
        if("agt".equals(groupe)){
            return "agtListeChauffeurA";
        }else{
            return "admListeChauffeurA";          
        }
    }
//    @GetMapping(value = "/listeTraiter/{groupe}")
//    public String listeTraiter(@PathVariable("groupe") String groupe,Model model) {
//        model.addAttribute("title", "Liste des Demandes de Missions traitées");
//        final List<DemandeMissionModel> listDemandeMission = personnelService.allDemandeMissionsByEtat("2") ;   
//        model.addAttribute("listDemandeMissions", listDemandeMission);
//        model.addAttribute("message", "");
//        if("agt".equals(groupe)){
//            return "agtListeDemandeMissionT";
//        }else{
//            return "admListeDemandeMissionT";          
//        }
//    }
    
    @GetMapping(value = "/nouveauDemandeMission")
    public String nouveauDemandeMission(Model model) {
        model.addAttribute("title", "Ajout d'une Demande de Mission");
        DemandeMissionModel nvldemande=new DemandeMissionModel();
        model.addAttribute("demandeMissionMdl",nvldemande);
        model.addAttribute("agentlist",personnelService.allAgents());
        model.addAttribute("zonelist",personnelService.allZones2());
        model.addAttribute("produitlist",personnelService.allProduits2());
        model.addAttribute("entrepotlist",personnelService.allEntrepots2());
        
        model.addAttribute("message", "");
        return "formAjoutDemandeMission";
    }
    
    @RequestMapping(value = "/enregistrerDemandeMission", method = RequestMethod.POST)
    public String addDemandeMission(Model model, @Valid @ModelAttribute("demandeMissionMdl") DemandeMissionModel demandeMissionMdl, final BindingResult result) {
        demandeMissionMdl.setEtat("0");
        personnelService.addDemandeMission(demandeMissionMdl);
        model.addAttribute("message", "enregistre");  AgentModel nvagt= personnelService.findByAgentId(1);
//        System.out.println("com.genieLogiciel.gestionAuto.controller.DemandeMissionController.addDemandeMission()");
//        System.out.println(nvagt.getNom());
        final List<DemandeMissionModel> listDemandeMissionE = personnelService.allDemandeMissionsByEtat("1");

        int notifMissionE = 0;
        if (!listDemandeMissionE.isEmpty()) {
            notifMissionE = listDemandeMissionE.size();
        }

        model.addAttribute("notifMissionE", notifMissionE);
        model.addAttribute("demandeMissionMdl", new DemandeMissionModel());
        model.addAttribute("agentlist", personnelService.allAgents());
        model.addAttribute("zonelist", personnelService.allZones2());
        model.addAttribute("produitlist", personnelService.allProduits2());
        model.addAttribute("entrepotlist", personnelService.allEntrepots2());
        return "formAjoutDemandeMission";
    }
    
    @RequestMapping(value = "/saveUpdateDemandeMission", method = RequestMethod.POST)
    public String validerUpdate(Model model, DemandeMissionModel demandeMissionMdl, final BindingResult result) {
        personnelService.validupdateDemandeMission(demandeMissionMdl);
        model.addAttribute("message", "Modification reussie");
        return "redirect:/demandeMission/listeEnCours/agt";
    }
    @RequestMapping(value = "/updateDemandeMission/{demandeMissionId}", method = RequestMethod.GET)
    public String showFormForUpdate(@PathVariable("demandeMissionId") long demandeMissionId, Model theModel) {
        theModel.addAttribute("title", "Modification de la demande Mission");
                
        theModel.addAttribute("agentlist", personnelService.allAgents());
        theModel.addAttribute("zonelist", personnelService.allZones2());
        theModel.addAttribute("produitlist", personnelService.allProduits2());
        theModel.addAttribute("entrepotlist", personnelService.allEntrepots2());
        DemandeMissionModel laDemandeMission = personnelService.findByDemandeMissionId(demandeMissionId);
        theModel.addAttribute("demandeMissionMdl", laDemandeMission);
        return "updatedemandemission";
    }
	
    @RequestMapping(value = "/deleteDemandeMission/{demandeMissionId}", method = RequestMethod.GET)
    public String deletefonc(@PathVariable("demandeMissionId") long demandeMissionId, Model theModel) {
        personnelService.deleteDemandeMission(demandeMissionId);
        return "redirect:/demandeMission/listeDemandeMission";
    }
    @RequestMapping(value = "/validerDemandeMission/{demandeMissionId}", method = RequestMethod.GET)
    public String validerfonc(@PathVariable("demandeMissionId") long demandeMissionId, Model theModel) {
        DemandeMissionModel laDemandeMission = personnelService.findByDemandeMissionId(demandeMissionId);
        laDemandeMission.setEtat("1");
        personnelService.validupdateDemandeMission(laDemandeMission);
        return "redirect:/demandeMission/listeValider/adm";
    }
	
    @RequestMapping(value = "/traiterDemandeMission/{demandeMissionId}", method = RequestMethod.GET)
    public String showFormForTraite(@PathVariable("demandeMissionId") long demandeMissionId, Model theModel) {
        theModel.addAttribute("title", "Affectation de la demande Mission");

        theModel.addAttribute("agentlist", personnelService.allAgents());
        theModel.addAttribute("zonelist", personnelService.allZones2());
        theModel.addAttribute("produitlist", personnelService.allProduits2());
        theModel.addAttribute("entrepotlist", personnelService.allEntrepots2());
        DemandeMissionModel laDemandeMission = personnelService.findByDemandeMissionId(demandeMissionId);
        
        theModel.addAttribute("chauffeurlist", personnelService.allChauffeursByDate(laDemandeMission.getDateDebut()));
        theModel.addAttribute("vehiculelist", personnelService.allVehiculesByDate(laDemandeMission.getDateDebut()));
        theModel.addAttribute("demandeMissionMdl", laDemandeMission);
        AffectationModel affectationMdl= new AffectationModel();
        affectationMdl.setDateDebut(laDemandeMission.getDateDebut());
        affectationMdl.setDateFin(laDemandeMission.getDateFin());
        affectationMdl.setDemandeMission(laDemandeMission);
        affectationMdl.setEtat("0");
        theModel.addAttribute("affectationMdl", affectationMdl);
         final List<DemandeMissionModel> listDemandeMissionE = personnelService.allDemandeMissionsByEtat("0");

        int notifMissionE = 0;
        if (!listDemandeMissionE.isEmpty()) {
            notifMissionE = listDemandeMissionE.size();
        }

        theModel.addAttribute("notifMissionE", notifMissionE);
        return "traiterdemandemission";
    }
    @RequestMapping(value = "/saveTraiterDemandeMission", method = RequestMethod.POST)
    public String validerTraiter(Model model, AffectationModel affectationModelMdl, final BindingResult result) {
        
        DemandeMissionModel laDemandeMission =personnelService.findByDemandeMissionId(affectationModelMdl.getDemandeMission().getIdDemandeMission());
        personnelService.validTraiterDemandeMission(affectationModelMdl);
         System.out.println(affectationModelMdl.getDateDebut());
         System.out.println("saveTraiterDemandeMission descr"+laDemandeMission.getDescription());
        
        System.out.println(affectationModelMdl.getDemandeMission());
        System.out.println(affectationModelMdl.getDemandeMission().getIdDemandeMission());
        
        laDemandeMission.setEtat("2");
        laDemandeMission.setObjet("Accordé");
        System.out.println(laDemandeMission);
        System.out.println("saveTraiterDemandeMission etat"+laDemandeMission.getAgentM().getNom());
        
        personnelService.validupdateDemandeMission(laDemandeMission);
        
        model.addAttribute("message", "traiter");
        return "redirect:/demandeMission/listeTraiter/adm";
    }
 
    
}