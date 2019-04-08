/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.genieLogiciel.gestionAuto.dao;

import com.genieLogiciel.gestionAuto.model.CarburantModel;
import java.util.List;

/**
 *
 * @author dissirama
 */
public interface CarburantDAO {

    public void addCarburant(CarburantModel carburant);
    public void updateCarburant(CarburantModel carburant);
    public void deleteCarburant(long carburantId);
    public CarburantModel findByCarburantId(long carburantId);
    public CarburantModel findByCarburantId2(long carburantId);
    public List<CarburantModel> tousCarburants();
    public List<CarburantModel> tousCarburants2();
    public String findCarburantLibelleById(long carburantId);
    public int findTotalCarburantModel();
    public void insertBatchSQL(String sql);
}
