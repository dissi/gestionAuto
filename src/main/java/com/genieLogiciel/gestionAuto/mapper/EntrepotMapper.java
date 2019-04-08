/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.genieLogiciel.gestionAuto.mapper;

import com.genieLogiciel.gestionAuto.model.EntrepotModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;


/**
 *
 * @author dissirama
 */
public class EntrepotMapper implements RowMapper<EntrepotModel> {
    
    public static final String BASE_SQL //
            = "SELECT e.id_entrepot,e.lieu,e.nom_entrepot FROM entrepot AS e";
 

    @Override
    public EntrepotModel mapRow(ResultSet rs, int i) throws SQLException {
        EntrepotModel entrepot = new EntrepotModel();
        entrepot.setIdEntrepot(rs.getLong("id_entrepot"));
        entrepot.setNomEntrepot(rs.getString("nom_entrepot"));
        entrepot.setLieu(rs.getString("lieu"));
       
        return entrepot;
    }
    

}
