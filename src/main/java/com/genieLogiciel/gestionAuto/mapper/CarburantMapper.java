/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.genieLogiciel.gestionAuto.mapper;

import com.genieLogiciel.gestionAuto.model.CarburantModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;


/**
 *
 * @author dissirama
 */
public class CarburantMapper implements RowMapper<CarburantModel> {
    
    public static final String BASE_SQL //
            = "SELECT c.`id_carburant`,c.`libelle`, c.`prix` FROM `carburant` AS c";
 

    @Override
    public CarburantModel mapRow(ResultSet rs, int i) throws SQLException {
        CarburantModel carburant = new CarburantModel();
        carburant.setIdCarburant(rs.getLong("id_carburant"));
        carburant.setLibelle(rs.getString("libelle"));
        carburant.setPrix(rs.getDouble("prix"));    
        return carburant;
    }
    

}
