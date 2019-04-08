/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.genieLogiciel.gestionAuto.dao;

import com.genieLogiciel.gestionAuto.model.EntrepotModel;
import java.util.List;

/**
 *
 * @author dissirama
 */
public interface EntrepotDAO {

    public void addEntrepot(EntrepotModel entrepot);
    public void updateEntrepot(EntrepotModel entrepot);
    public void deleteEntrepot(long entrepotId);
    public EntrepotModel findByEntrepotId(long entrepotId);
    public EntrepotModel findByEntrepotId2(long entrepotId);
    public List<EntrepotModel> tousEntrepots();
    public List<EntrepotModel> tousEntrepots2();
    public String findEntrepotLibelleById(long entrepotId);
    public int findTotalEntrepotModel();
    public void insertBatchSQL(String sql);
}
