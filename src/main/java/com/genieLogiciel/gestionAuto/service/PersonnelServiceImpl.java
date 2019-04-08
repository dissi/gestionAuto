/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.genieLogiciel.gestionAuto.service;

import com.genieLogiciel.gestionAuto.dao.AffectationDAO;
import com.genieLogiciel.gestionAuto.dao.EntrepotDAO;
import com.genieLogiciel.gestionAuto.dao.ProduitDAO;
import com.genieLogiciel.gestionAuto.dao.ZoneDAO;
import com.genieLogiciel.gestionAuto.dao.CarburantDAO;
import com.genieLogiciel.gestionAuto.dao.DemandeMissionDAO;
import com.genieLogiciel.gestionAuto.dao.VehiculeDAO;
import com.genieLogiciel.gestionAuto.dao.impl.AgentDAOImp;
import com.genieLogiciel.gestionAuto.dao.impl.FonctionDAOImp;
import com.genieLogiciel.gestionAuto.model.AffectationModel;
import com.genieLogiciel.gestionAuto.model.AgentModel;
import com.genieLogiciel.gestionAuto.model.CarburantModel;
import com.genieLogiciel.gestionAuto.model.DemandeMissionModel;
import com.genieLogiciel.gestionAuto.model.EntrepotModel;
import com.genieLogiciel.gestionAuto.model.FonctionModel;
import com.genieLogiciel.gestionAuto.model.ProduitModel;
import com.genieLogiciel.gestionAuto.model.VehiculeModel;
import com.genieLogiciel.gestionAuto.model.ZoneModel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author dissirama
 */
@Service
public class PersonnelServiceImpl implements PersonnelService{
    
    @Autowired
    private FonctionDAOImp fonctionDaoImp;
    
    @Autowired
    private AgentDAOImp agentDaoImp;
    @Autowired
    private ZoneDAO zoneDAO;
    @Autowired
    private EntrepotDAO entrepotDAO;
    @Autowired
    private ProduitDAO produitDAO;
    @Autowired
    private CarburantDAO carburantDAO;
    @Autowired
    private VehiculeDAO vehiculeDAO;
    @Autowired
    private AffectationDAO affectationDAO;
    @Autowired
    private DemandeMissionDAO demandeMissionDAO;
    
    @Override
    public void addFonction(FonctionModel fonction) {
        fonctionDaoImp.addFonction(fonction);
    }

    @Override
    public void validupdatefonction(FonctionModel fonction) {
       fonctionDaoImp.updateFonction(fonction);
    }

    @Override
    public void deleteFonction(long idfonction) {
        fonctionDaoImp.deleteFonction(idfonction);
    }

    @Override
    public FonctionModel findByFonctionId(long fonctId) {
        FonctionModel fonct = fonctionDaoImp.findByFonctionId(fonctId);
        return fonct;
    }

    @Override
    public FonctionModel findByFonctionId2(long fonctId) {
        FonctionModel fonct = fonctionDaoImp.findByFonctionId2(fonctId);
        return fonct;
    }

    @Override
    public List<FonctionModel> allFonctions() {
        return fonctionDaoImp.tousFonctions();
    }

    @Override
    public List<FonctionModel> allFonctions2() {
             
        return fonctionDaoImp.tousFonctions2();
    }

    @Override
    public String findFonctionLibelleById(long fonctId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int findTotalFonctionModel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addAgent(AgentModel agent) {
        agentDaoImp.addAgent(agent);
    }

    @Override
    public void validupdateAgent(AgentModel agent) {
        agentDaoImp.updateAgent(agent);
    }

    @Override
    public void deleteAgent(long idagent) {
        agentDaoImp.deleteAgent(idagent);
    }

    @Override
    public AgentModel findByAgentId(long idagent) {
        AgentModel agent = agentDaoImp.findByAgentId(idagent);
        return agent;
    }

    @Override
    public AgentModel findByAgentId2(long idagent) {
        AgentModel agent = agentDaoImp.findByAgentId2(idagent);
        return agent;
    }

    @Override
    public List<AgentModel> allAgents() {
       return agentDaoImp.tousAgents();
    }

    @Override
    public List<AgentModel> allAgents2() {
       return agentDaoImp.tousAgents2(); 
    }

    @Override
    public void addZone(ZoneModel zone) {
        zoneDAO.addZone(zone);
    }

    @Override
    public void validupdateZone(ZoneModel zone) {
        zoneDAO.updateZone(zone);
    }

    @Override
    public void deleteZone(long idzone) {
        zoneDAO.deleteZone(idzone);
    }

    @Override
    public ZoneModel findByZoneId(long idzone) {
        ZoneModel zone = zoneDAO.findByZoneId(idzone);
        return zone;
    }

    @Override
    public ZoneModel findByZoneId2(long idzone) {
        ZoneModel zone = zoneDAO.findByZoneId2(idzone);
        return zone;
    }

    @Override
    public List<ZoneModel> allZones() {
        return zoneDAO.tousZones();
    }

    @Override
    public List<ZoneModel> allZones2() {
        return zoneDAO.tousZones2();
    }

    @Override
    public void addEntrepot(EntrepotModel entrepot) {
        entrepotDAO.addEntrepot(entrepot);
    }

    @Override
    public void validupdateEntrepot(EntrepotModel entrepot) {
        entrepotDAO.updateEntrepot(entrepot);
    }

    @Override
    public void deleteEntrepot(long identrepot) {
        entrepotDAO.deleteEntrepot(identrepot);
    }

    @Override
    public EntrepotModel findByEntrepotId(long identrepot) {
        EntrepotModel entrepotfind;
        entrepotfind=entrepotDAO.findByEntrepotId(identrepot);
        return entrepotfind;
    }

    @Override
    public EntrepotModel findByEntrepotId2(long identrepot) {
        EntrepotModel entrepotfind;
        entrepotfind = entrepotDAO.findByEntrepotId2(identrepot);
        return entrepotfind;
    }

    @Override
    public List<EntrepotModel> allEntrepots() {
        return entrepotDAO.tousEntrepots();
    }

    @Override
    public List<EntrepotModel> allEntrepots2() {
         return entrepotDAO.tousEntrepots2();
    }

    @Override
    public void addProduit(ProduitModel produit) {
        produitDAO.addProduit(produit);
    }

    @Override
    public void validupdateProduit(ProduitModel produit) {
        produitDAO.updateProduit(produit);
    }

    @Override
    public void deleteProduit(long idproduit) {
        produitDAO.deleteProduit(idproduit);
    }

    @Override
    public ProduitModel findByProduitId(long idproduit) {
       return produitDAO.findByProduitId(idproduit);
    }

    @Override
    public ProduitModel findByProduitId2(long idproduit) {
         return produitDAO.findByProduitId2(idproduit);
    }

    @Override
    public List<ProduitModel> allProduits() {
         return produitDAO.tousProduits();
    }

    @Override
    public List<ProduitModel> allProduits2() {
        return produitDAO.tousProduits2();

    }

    @Override
    public void addCarburant(CarburantModel carburant) {
         carburantDAO.addCarburant(carburant);
    }

    @Override
    public void validupdateCarburant(CarburantModel carburant) {
        carburantDAO.updateCarburant(carburant);
    }

    @Override
    public void deleteCarburant(long idcarburant) {
        carburantDAO.deleteCarburant(idcarburant);
    }

    @Override
    public CarburantModel findByCarburantId(long idcarburant) {
        return carburantDAO.findByCarburantId(idcarburant);
    }

    @Override
    public CarburantModel findByCarburantId2(long idcarburant) {
        return carburantDAO.findByCarburantId2(idcarburant);
    }

    @Override
    public List<CarburantModel> allCarburants() {
        return carburantDAO.tousCarburants();
    }

    @Override
    public List<CarburantModel> allCarburants2() {
         return carburantDAO.tousCarburants2();
    }

    @Override
    public void addVehicule(VehiculeModel vehicule) {
        vehiculeDAO.addVehicule(vehicule);
    }

    @Override
    public void validupdateVehicule(VehiculeModel vehicule) {
        vehiculeDAO.updateVehicule(vehicule);
    }

    @Override
    public void deleteVehicule(long idvehicule) {
        vehiculeDAO.deleteVehicule(idvehicule);
    }

    @Override
    public VehiculeModel findByVehiculeId(long idvehicule) {
        return vehiculeDAO.findByVehiculeId(idvehicule);
    }

    @Override
    public VehiculeModel findByVehiculeId2(long idvehicule) {
        return vehiculeDAO.findByVehiculeId2(idvehicule);
    }

    @Override
    public List<VehiculeModel> allVehicules() {
        return vehiculeDAO.tousVehicules();
    }

    @Override
    public List<VehiculeModel> allVehicules2() {
        return vehiculeDAO.tousVehicules2();
    }
    @Override
    public List<VehiculeModel> allVehiculesByDate(Date datedebut) {
        return vehiculeDAO.allVehiculesByDate(datedebut);
    }

    @Override
    public void addDemandeMission(DemandeMissionModel demandeMission) {
         
        demandeMissionDAO.addDemandeMission(demandeMission);
    }

    @Override
    public void validupdateDemandeMission(DemandeMissionModel demandeMission) {
        demandeMissionDAO.updateDemandeMission(demandeMission);
    }

    @Override
    public void deleteDemandeMission(long idDemandeMission) {
       demandeMissionDAO.deleteDemandeMission(idDemandeMission);
    }

    @Override
    public DemandeMissionModel findByDemandeMissionId(long idDemandeMission) {
        return demandeMissionDAO.findByDemandeMissionId(idDemandeMission);
    }

    @Override
    public DemandeMissionModel findByDemandeMissionId2(long idDemandeMission) {
        return demandeMissionDAO.findByDemandeMissionId2(idDemandeMission);
    }

    @Override
    public List<DemandeMissionModel> allDemandeMissions() {
        return demandeMissionDAO.tousDemandeMissions();
    }

    @Override
    public List<DemandeMissionModel> allDemandeMissions2() {
        return demandeMissionDAO.tousDemandeMissions2();
    }

    @Override
    public List<DemandeMissionModel> allDemandeMissionsByEtat(String etat) {
        return demandeMissionDAO.tousDemandeMissionsByEtat(etat);
    }

    @Override
    public List<AgentModel> allChauffeursByDate(Date datedebut) {
        return agentDaoImp.allChauffeursByDate(datedebut);
    }

    @Override
    public void validTraiterDemandeMission(AffectationModel affectationModelMdl) {
        affectationDAO.addAffectation(affectationModelMdl);
    }

    @Override
    public void addAffectation(AffectationModel affectation) {
         affectationDAO.addAffectation(affectation);
    }

    @Override
    public void validupdateAffectation(AffectationModel affectation) {
        affectationDAO.updateAffectation(affectation);
    }

    @Override
    public void deleteAffectation(long idDemandeMission) {
        affectationDAO.deleteAffectation(idDemandeMission);
    }

    @Override
    public AffectationModel findByAffectationId(long idDemandeMission) {
        return affectationDAO.findByAffectationId(idDemandeMission);
    }

    @Override
    public AffectationModel findByAffectationId2(long idDemandeMission) {
        return affectationDAO.findByAffectationId2(idDemandeMission);
    }

    @Override
    public List<AffectationModel> allAffectations() {
        return affectationDAO.tousAffectations();
    }

    @Override
    public List<AffectationModel> allAffectations2() {
        return affectationDAO.tousAffectations2();
    }

    @Override
    public List<AgentModel> allAgentsChauffeurs(long idfonction) {
        return agentDaoImp.tousAgentsFonction(idfonction);
    }
    
}
