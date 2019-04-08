/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.genieLogiciel.gestionAuto.dao;

import com.genieLogiciel.gestionAuto.model.DemandeMissionModel;
import java.util.List;

/**
 *
 * @author dissirama
 */
public interface DemandeMissionDAO {

    public void addDemandeMission(DemandeMissionModel demandeMission);
    public void updateDemandeMission(DemandeMissionModel demandeMission);
    public void deleteDemandeMission(long iddemandeMission);
    public DemandeMissionModel findByDemandeMissionId(long demandeMissionId);
    public DemandeMissionModel findByDemandeMissionId2(long demandeMissionId);
    public List<DemandeMissionModel> tousDemandeMissions();
    public List<DemandeMissionModel> tousDemandeMissions2();
    public List<DemandeMissionModel> tousDemandeMissionsByEtat(String etat);
    public String findDemandeMissionLibelleById(long demandeMissionId);
    public int findTotalDemandeMissionModel();
    public void insertBatchSQL(String sql);
}
