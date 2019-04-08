/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.genieLogiciel.gestionAuto.dao;

import com.genieLogiciel.gestionAuto.model.AffectationModel;
import java.util.List;

/**
 *
 * @author dissirama
 */
public interface AffectationDAO {

    public void addAffectation(AffectationModel affectation);
    public void updateAffectation(AffectationModel affectation);
    public void deleteAffectation(long idaffectation);
    public AffectationModel findByAffectationId(long affectationId);
    public AffectationModel findByAffectationId2(long affectationId);
    public List<AffectationModel> tousAffectations();
    public List<AffectationModel> tousAffectations2();
    public List<AffectationModel> tousAffectationsByEtat(String etat);
    public String findAffectationLibelleById(long affectationId);
    public int findTotalAffectationModel();
    public void insertBatchSQL(String sql);
}
