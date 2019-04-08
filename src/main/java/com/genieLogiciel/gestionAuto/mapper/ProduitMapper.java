/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.genieLogiciel.gestionAuto.mapper;

import com.genieLogiciel.gestionAuto.model.ProduitModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;


/**
 *
 * @author dissirama
 */
public class ProduitMapper implements RowMapper<ProduitModel> {
    
    public static final String BASE_SQL //
            = "SELECT p.id_produit, p.libelle, p.type_produit FROM produit AS p";
 

    @Override
    public ProduitModel mapRow(ResultSet rs, int i) throws SQLException {
        ProduitModel produit = new ProduitModel();
        produit.setIdProduit(rs.getLong("id_produit"));
        produit.setLibelle(rs.getString("libelle"));
        produit.setTypeProduit(rs.getString("type_produit"));
       
        return produit;
    }
    

}
