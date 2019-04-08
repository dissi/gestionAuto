/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.genieLogiciel.gestionAuto.mapper;

import com.genieLogiciel.gestionAuto.model.FonctionModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;


/**
 *
 * @author dissirama
 */
public class FonctionMapper implements RowMapper<FonctionModel> {
    
    public static final String BASE_SQL //
            = "SELECT f.`id_fonction`, f.`libelle` FROM `fonction` f";
 

    @Override
    public FonctionModel mapRow(ResultSet rs, int i) throws SQLException {
        FonctionModel fonction = new FonctionModel();
        fonction.setIdFonction(rs.getLong("id_fonction"));
        fonction.setLibelle(rs.getString("libelle"));
       
        return fonction;
    }
    

}
