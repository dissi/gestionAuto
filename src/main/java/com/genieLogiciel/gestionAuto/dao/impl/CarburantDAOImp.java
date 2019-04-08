/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.genieLogiciel.gestionAuto.dao.impl;

import javax.sql.DataSource;
import java.util.List;
import  com.genieLogiciel.gestionAuto.mapper.CarburantMapper;
import com.genieLogiciel.gestionAuto.dao.CarburantDAO;
import com.genieLogiciel.gestionAuto.model.CarburantModel;
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
public class CarburantDAOImp extends JdbcDaoSupport implements CarburantDAO {
    

    @Autowired
    public CarburantDAOImp(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
    
    
    @Override
    public void addCarburant(CarburantModel carburant) {
        String sql = "INSERT INTO carburant (libelle,prix) VALUES (?,?)";
	getJdbcTemplate().update(sql, new Object[] {carburant.getLibelle(),carburant.getPrix()});
    }
    @Override
    public void updateCarburant(CarburantModel carburant) {
        String sql = "update carburant set libelle=?,prix=? where id_carburant=?";
        getJdbcTemplate().update(sql,carburant.getLibelle(),carburant.getPrix(), carburant.getIdCarburant());
	
    }
    
    @Override
    public void insertBatchSQL(String sql) {
        getJdbcTemplate().batchUpdate(new String[]{sql});
    }

    @Override
    public CarburantModel findByCarburantId(long carburantId) {
        String sql = "SELECT * FROM carburant WHERE id_carburant = ?";
 
        CarburantModel carburant = (CarburantModel)getJdbcTemplate().queryForObject(
				sql, new Object[] { carburantId }, new CarburantMapper());
	
        return carburant;
        
    }

    //query single row with BeanPropertyRowMapper (Customer.class)
    @Override
    public CarburantModel findByCarburantId2(long carburantId) {
        String sql = "SELECT * FROM carburant WHERE id_carburant = ?";

        CarburantModel carburant = (CarburantModel) getJdbcTemplate().queryForObject(sql, new Object[]{carburantId},
                new BeanPropertyRowMapper(CarburantModel.class));
        
        return carburant;
    }

    
    @Override
    public List<CarburantModel> tousCarburants() {
        

        String sql = "SELECT * FROM carburant";

        List<CarburantModel> lescarburants = new ArrayList<CarburantModel>();
        
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
        try{
            for (Map row : rows) {
                CarburantModel carburant = new CarburantModel();
                carburant.setIdCarburant((Long) (row.get("id_carburant")));
                carburant.setLibelle((String) row.get("libelle"));
                carburant.setPrix((Double) row.get("prix"));
                lescarburants.add(carburant);
            }
        return lescarburants;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    
    }

    //query mutiple rows with BeanPropertyRowMapper (CarburantModel.class)
    @Override
    public List<CarburantModel> tousCarburants2() {
        String sql = "SELECT * FROM carburant";
        try {
        List<CarburantModel> lescarburants  = getJdbcTemplate().query(sql,
				new BeanPropertyRowMapper(CarburantModel.class));
		
        return lescarburants;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public String findCarburantLibelleById(long carburantId) {
        String sql = "SELECT libelle FROM carburant WHERE id_carburant = ?";

        String nom_carburant = (String) getJdbcTemplate().queryForObject(
                sql, new Object[]{carburantId}, String.class);

        return nom_carburant;

    }

    @Override
    public int findTotalCarburantModel() {
        String sql = "SELECT COUNT(*) FROM carburant";
        int total = getJdbcTemplate().queryForObject(sql,Integer.class);       
        return total;
    }

    @Override
    public void deleteCarburant(long idcarburant) {
       CarburantModel carburant=this.findByCarburantId2(idcarburant);
        String sql = "DELETE FROM carburant where id_carburant=?";
        getJdbcTemplate().update(sql,carburant.getIdCarburant());
    }
    
}
