/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.genieLogiciel.gestionAuto.mapper;

import com.genieLogiciel.gestionAuto.model.ZoneModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;


/**
 *
 * @author dissirama
 */
public class ZoneMapper implements RowMapper<ZoneModel> {
    
    public static final String BASE_SQL //
            = "SELECT z.id_zone, z.nom_zone FROM zone AS z";
 

    @Override
    public ZoneModel mapRow(ResultSet rs, int i) throws SQLException {
        ZoneModel zone = new ZoneModel();
        zone.setIdZone(rs.getLong("id_zone"));
        zone.setNomZone(rs.getString("nom_zone"));
       
        return zone;
    }
    

}
