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
import com.genieLogiciel.gestionAuto.model.DemandeMissionModel;
import com.genieLogiciel.gestionAuto.model.ProduitModel;
import java.security.Principal;

import com.genieLogiciel.gestionAuto.service.PersonnelService;
import com.genieLogiciel.gestionAuto.utils.WebUtils;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
 
@Controller
public class MainController {
     @Autowired
    private PersonnelService personnelService;
    
    @RequestMapping(value = { "/welcome" }, method = RequestMethod.GET)
    public String welcomePage(Model model) {
        model.addAttribute("title", "Welcome");
        model.addAttribute("message", "This is welcome page!");
        return "welcomePage";
    }
 
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage(Model model, Principal principal) {
         
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
 
        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);
        final List<AffectationModel> listAffectation = personnelService.allAffectations();
        int nbreAffectation = 0;
        if(!listAffectation.isEmpty()){
            nbreAffectation = listAffectation.size();
        }
        final List<DemandeMissionModel> listDemandeMissionE = personnelService.allDemandeMissionsByEtat("0") ;   
        
         int notifMissionE = 0;
        if (!listDemandeMissionE.isEmpty()) {
            notifMissionE = listDemandeMissionE.size();
        }
        
        model.addAttribute("notifMissionE", notifMissionE);
        int nbreMissionE = 0;
        if (!listDemandeMissionE.isEmpty()) {
            nbreMissionE = listDemandeMissionE.size();
        }
        final List<DemandeMissionModel> listDemandeMissionV = personnelService.allDemandeMissionsByEtat("1") ;   
         int nbreMissionV = 0;
        if (!listDemandeMissionV.isEmpty()) {
            nbreMissionV = listDemandeMissionV.size();
        }
        final List<ProduitModel> listProduits = personnelService.allProduits() ;   
        int nbreProduit = 0;
        if (!listProduits.isEmpty()) {
            nbreProduit = listProduits.size();
        }
        model.addAttribute("nbreAffectation", nbreAffectation);
        model.addAttribute("nbreMissionV", nbreMissionV);
        model.addAttribute("nbreMissionE", nbreMissionE);
        model.addAttribute("nbreProduit", nbreProduit);
        return "adminPage";
    }
 
    @RequestMapping(value = "/magasinier", method = RequestMethod.GET)
    public String magasinierPage(Model model, Principal principal) {
         
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
 
        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);
        
        return "magasinierPage";
    }
    
    @RequestMapping(value = "/agentTerrain", method = RequestMethod.GET)
    public String agentTerrainPage(Model model, Principal principal) {
         
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
 
        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);
        final List<AffectationModel> listAffectation = personnelService.allAffectations();
        int nbreAffectation = 0;
        if (!listAffectation.isEmpty()) {
            nbreAffectation = listAffectation.size();
        }
        final List<DemandeMissionModel> listDemandeMissionEb = personnelService.allDemandeMissionsByEtat("1");
        int notifMissionE = 0;
        if (!listDemandeMissionEb.isEmpty()) {
            notifMissionE = listDemandeMissionEb.size();
        }
        
        final List<DemandeMissionModel> listDemandeMissionE = personnelService.allDemandeMissionsByEtat("0");
        model.addAttribute("notifMissionE", notifMissionE);
        int nbreMissionE = 0;
        if (!listDemandeMissionE.isEmpty()) {
            nbreMissionE = listDemandeMissionE.size();
        }
       
        final List<DemandeMissionModel> listDemandeMissionV = personnelService.allDemandeMissionsByEtat("1");
        int nbreMissionV = 0;
        if (!listDemandeMissionV.isEmpty()) {
            nbreMissionV = listDemandeMissionV.size();
        }
        final List<ProduitModel> listProduits = personnelService.allProduits2();
        int nbreProduit = 0;
        if (!listProduits.isEmpty()) {
            nbreProduit = listProduits.size();
        }
        model.addAttribute("nbreAffectation", nbreAffectation);
        model.addAttribute("nbreMissionV", nbreMissionV);
        model.addAttribute("nbreMissionE", nbreMissionE);
        model.addAttribute("nbreProduit", nbreProduit);
        
        return "agentPageNew";
    }
 
    @RequestMapping(value = {"/","/login"}, method = RequestMethod.GET)
    public String loginPage(Model model) {
 
        return "loginPage";
    }
 
    @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("deconne", "Vous êtes déconnecté !!");
        return "loginPage";
    }
 
    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    public String userInfo(Model model, Principal principal) {
 
        String userName = principal.getName();
 
        System.out.println("User Name: " + userName);
 
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
 
        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);
 
        String userRoles = WebUtils.toString(loginedUser);
        String lien="";
        switch (userRoles){
            case "ROLE_ADMIN":
                lien="redirect:/admin";
                break;
        case "ROLE_AGT":
            lien="redirect:/agentTerrain";
           break;
        case "ROLE_MAG":
            lien="redirect:/magasinier";
           break;
           // le role du magasiniers
           default:
           lien="redirect:/magasinier";
           break;

        }
        return lien;
               
//        if (userRoles.equalsIgnoreCase("ROLE_ADMIN")) {
//            
//            return "redirect:/admin";
//        }else if (userRoles.equalsIgnoreCase("")){
//            return "redirect:/admin";
//            
//        }else {
//            return "redirect:/admin";
//            
//        }
        
        
    }
 
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Model model, Principal principal) {
 
        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
 
            String userInfo = WebUtils.toString(loginedUser);
 
            model.addAttribute("userInfo", userInfo);
 
            String message = "Hi " + principal.getName() //
                    + "<br> You do not have permission to access this page!";
            model.addAttribute("message", loginedUser);
 
        }
 
        return "403Page";
    }
 
}