/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.genieLogiciel.gestionAuto.dao.impl;

import javax.sql.DataSource;
import java.util.List;
import  com.genieLogiciel.gestionAuto.mapper.ZoneMapper;
import com.genieLogiciel.gestionAuto.dao.ZoneDAO;
import com.genieLogiciel.gestionAuto.model.ZoneModel;
import java.util.ArrayList;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author dissirama
 */

@Repository
@Transactional
@SuppressWarnings("unchecked")
public class ZoneDAOImp extends JdbcDaoSupport implements ZoneDAO {
    

    @Autowired
    public ZoneDAOImp(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
    
    
    @Override
    public void addZone(ZoneModel zone) {
        String sql = "INSERT INTO zone (nom_zone) VALUES (?)";
	getJdbcTemplate().update(sql, new Object[] {zone.getNomZone()});
    }
    @Override
    public void updateZone(ZoneModel zone) {
        String sql = "update zone set nom_zone=? where id_zone=?";
        getJdbcTemplate().update(sql,zone.getNomZone(), zone.getIdZone());
	
    }
    
    @Override
    public void insertBatchSQL(String sql) {
        getJdbcTemplate().batchUpdate(new String[]{sql});
    }

    @Override
    public ZoneModel findByZoneId(long fonctId) {
        String sql = "SELECT * FROM zone WHERE id_zone = ?";
 
        ZoneModel zone = (ZoneModel)getJdbcTemplate().queryForObject(
				sql, new Object[] { fonctId }, new ZoneMapper());
	
        return zone;
        
    }

    //query single row with BeanPropertyRowMapper (Customer.class)
    @Override
    public ZoneModel findByZoneId2(long fonctId) {
        String sql = "SELECT * FROM zone WHERE id_zone = ?";

        ZoneModel zone = (ZoneModel) getJdbcTemplate().queryForObject(sql, new Object[]{fonctId},
                new BeanPropertyRowMapper(ZoneModel.class));
        
        return zone;
    }

    
    @Override
    public List<ZoneModel> tousZones() {
        

        String sql = "SELECT * FROM zone";

        List<ZoneModel> leszones = new ArrayList<ZoneModel>();
        
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
        try{
            for (Map row : rows) {
                ZoneModel zone = new ZoneModel();
                zone.setIdZone((Long) (row.get("id_zone")));
                zone.setNomZone((String) row.get("nom_zone"));
                leszones.add(zone);
            }
        return leszones;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    
    }

    //query mutiple rows with BeanPropertyRowMapper (ZoneModel.class)
    @Override
    public List<ZoneModel> tousZones2() {
        String sql = "SELECT * FROM zone";
        try {
        List<ZoneModel> leszones  = getJdbcTemplate().query(sql,
				new BeanPropertyRowMapper(ZoneModel.class));
		
        return leszones;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public String findZoneLibelleById(long fonctId) {
        String sql = "SELECT nom_zone FROM zone WHERE id_zone = ?";

        String nom_zone = (String) getJdbcTemplate().queryForObject(
                sql, new Object[]{fonctId}, String.class);

        return nom_zone;

    }

    @Override
    public int findTotalZoneModel() {
        String sql = "SELECT COUNT(*) FROM zone";
        int total = getJdbcTemplate().queryForObject(sql,Integer.class);       
        return total;
    }

    @Override
    public void deleteZone(long idzone) {
       ZoneModel zone=this.findByZoneId2(idzone);
        String sql = "DELETE FROM zone where id_zone=?";
        getJdbcTemplate().update(sql,zone.getIdZone());
    }
    
}
