/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.genieLogiciel.gestionAuto.service;

import com.genieLogiciel.gestionAuto.model.AffectationModel;
import com.genieLogiciel.gestionAuto.model.AgentModel;
import com.genieLogiciel.gestionAuto.model.EntrepotModel;
import com.genieLogiciel.gestionAuto.model.FonctionModel;
import com.genieLogiciel.gestionAuto.model.ProduitModel;
import com.genieLogiciel.gestionAuto.model.ZoneModel;
import com.genieLogiciel.gestionAuto.model.CarburantModel;
import com.genieLogiciel.gestionAuto.model.DemandeMissionModel;
import com.genieLogiciel.gestionAuto.model.VehiculeModel;
import java.util.Date;
import java.util.List;

/**
 *
 * @author dissirama
 */
public interface PersonnelService {
    
    public void addFonction(FonctionModel fonction);

    public void validupdatefonction(FonctionModel fonction);

    public void deleteFonction(long idfonction);

    public FonctionModel findByFonctionId(long fonctId);

    public FonctionModel findByFonctionId2(long fonctId);

    public List<FonctionModel> allFonctions();

    public List<FonctionModel> allFonctions2();

    public String findFonctionLibelleById(long fonctId);

    public int findTotalFonctionModel();
    
    
    /*agent */
     public void addAgent(AgentModel agent);

    public void validupdateAgent(AgentModel agent);

    public void deleteAgent(long idagent);

    public AgentModel findByAgentId(long idagent);

    public AgentModel findByAgentId2(long idagent);

    public List<AgentModel> allAgents();

    public List<AgentModel> allAgents2();
    public List<AgentModel> allAgentsChauffeurs(long idfonction);
    public List<AgentModel> allChauffeursByDate(Date datedebut);
    
    
    /*agent */
    public void addZone(ZoneModel agent);

    public void validupdateZone(ZoneModel agent);

    public void deleteZone(long idzone);

    public ZoneModel findByZoneId(long idzone);

    public ZoneModel findByZoneId2(long idzone);

    public List<ZoneModel> allZones();

    public List<ZoneModel> allZones2();
        
    /*entrepot */
    public void addEntrepot(EntrepotModel entrepot);

    public void validupdateEntrepot(EntrepotModel entrepot);

    public void deleteEntrepot(long identrepot);

    public EntrepotModel findByEntrepotId(long identrepot);

    public EntrepotModel findByEntrepotId2(long identrepot);

    public List<EntrepotModel> allEntrepots();

    public List<EntrepotModel> allEntrepots2();
    
    
    /*produit */
     public void addProduit(ProduitModel produit);

    public void validupdateProduit(ProduitModel produit);

    public void deleteProduit(long idproduit);

    public ProduitModel findByProduitId(long idproduit);

    public ProduitModel findByProduitId2(long idproduit);

    public List<ProduitModel> allProduits();

    public List<ProduitModel> allProduits2();
    
    /*carburant */
     public void addCarburant(CarburantModel carburant);

    public void validupdateCarburant(CarburantModel carburant);

    public void deleteCarburant(long idcarburant);

    public CarburantModel findByCarburantId(long idcarburant);

    public CarburantModel findByCarburantId2(long idcarburant);

    public List<CarburantModel> allCarburants();

    public List<CarburantModel> allCarburants2();
    
    /*vehicule */
     public void addVehicule(VehiculeModel vehicule);

    public void validupdateVehicule(VehiculeModel vehicule);

    public void deleteVehicule(long idvehicule);

    public VehiculeModel findByVehiculeId(long idvehicule);

    public VehiculeModel findByVehiculeId2(long idvehicule);

    public List<VehiculeModel> allVehicules();

    public List<VehiculeModel> allVehicules2();
    
    public List<VehiculeModel> allVehiculesByDate(Date datedebut);
    
    /*DemandeMission */
     public void addDemandeMission(DemandeMissionModel demandeMission);

    public void validupdateDemandeMission(DemandeMissionModel demandeMission);

    public void deleteDemandeMission(long idDemandeMission);

    public DemandeMissionModel findByDemandeMissionId(long idDemandeMission);

    public DemandeMissionModel findByDemandeMissionId2(long idDemandeMission);

    public List<DemandeMissionModel> allDemandeMissions();

    public List<DemandeMissionModel> allDemandeMissions2();
    public List<DemandeMissionModel> allDemandeMissionsByEtat(String etat);

    public void validTraiterDemandeMission(AffectationModel affectationModelMdl);
    
    /*affectation */
     public void addAffectation(AffectationModel affectation);

    public void validupdateAffectation(AffectationModel affectation);

    public void deleteAffectation(long idDemandeMission);

    public AffectationModel findByAffectationId(long idDemandeMission);

    public AffectationModel findByAffectationId2(long idDemandeMission);

    public List<AffectationModel> allAffectations();

    public List<AffectationModel> allAffectations2();
//    public List<DemandeMissionModel> allDemandeMissionsByEtat(String etat);
//
//    public void validTraiterDemandeMission(AffectationModel affectationModelMdl);
//    
    
}
