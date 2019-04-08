/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.genieLogiciel.gestionAuto.dao;

import com.genieLogiciel.gestionAuto.model.VehiculeModel;
import java.util.Date;
import java.util.List;

/**
 *
 * @author dissirama
 */
public interface VehiculeDAO {

    public void addVehicule(VehiculeModel vehicule);
    public void updateVehicule(VehiculeModel vehicule);
    public void deleteVehicule(long idvehicule);
    public VehiculeModel findByVehiculeId(long agtId);
    public VehiculeModel findByVehiculeId2(long agtId);
    public List<VehiculeModel> tousVehicules();
    public List<VehiculeModel> tousVehicules2();
    public String findVehiculeLibelleById(long agtId);
    public int findTotalVehiculeModel();
    public void insertBatchSQL(String sql);

    public List<VehiculeModel> allVehiculesByDate(Date datedebut);
}
