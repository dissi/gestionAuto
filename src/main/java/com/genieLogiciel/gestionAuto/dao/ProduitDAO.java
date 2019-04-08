/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.genieLogiciel.gestionAuto.dao;

import com.genieLogiciel.gestionAuto.model.ProduitModel;
import java.util.List;

/**
 *
 * @author dissirama
 */
public interface ProduitDAO {

    public void addProduit(ProduitModel produit);
    public void updateProduit(ProduitModel produit);
    public void deleteProduit(long produitId);
    public ProduitModel findByProduitId(long produitId);
    public ProduitModel findByProduitId2(long produitId);
    public List<ProduitModel> tousProduits();
    public List<ProduitModel> tousProduits2();
    public String findProduitLibelleById(long produitId);
    public int findTotalProduitModel();
    public void insertBatchSQL(String sql);
}
