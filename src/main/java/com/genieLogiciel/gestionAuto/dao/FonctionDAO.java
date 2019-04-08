/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.genieLogiciel.gestionAuto.dao;

import com.genieLogiciel.gestionAuto.model.FonctionModel;
import java.util.List;

/**
 *
 * @author dissirama
 */
public interface FonctionDAO {

    public void addFonction(FonctionModel fonction);
    public void updateFonction(FonctionModel fonction);
    public void deleteFonction(long idfonction);
    public FonctionModel findByFonctionId(long fonctId);
    public FonctionModel findByFonctionId2(long fonctId);
    public List<FonctionModel> tousFonctions();
    public List<FonctionModel> tousFonctions2();
    public String findFonctionLibelleById(long fonctId);
    public int findTotalFonctionModel();

    public void insertBatchSQL(String sql);
}
