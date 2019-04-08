/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.genieLogiciel.gestionAuto.dao.impl;

import javax.sql.DataSource;
import java.util.List;
import  com.genieLogiciel.gestionAuto.mapper.EntrepotMapper;
import com.genieLogiciel.gestionAuto.dao.EntrepotDAO;
import com.genieLogiciel.gestionAuto.model.EntrepotModel;
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
public class EntrepotDAOImp extends JdbcDaoSupport implements EntrepotDAO {
    

    @Autowired
    public EntrepotDAOImp(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
    
    
    @Override
    public void addEntrepot(EntrepotModel entrepot) {
        String sql = "INSERT INTO entrepot (nom_entrepot,lieu) VALUES (?,?)";
	getJdbcTemplate().update(sql, new Object[] {entrepot.getNomEntrepot(),entrepot.getLieu()});
    }
    @Override
    public void updateEntrepot(EntrepotModel entrepot) {
        String sql = "update entrepot set nom_entrepot=?,lieu=? where id_entrepot=?";
        getJdbcTemplate().update(sql,entrepot.getNomEntrepot(),entrepot.getLieu(), entrepot.getIdEntrepot());
	
    }
    
    @Override
    public void insertBatchSQL(String sql) {
        getJdbcTemplate().batchUpdate(new String[]{sql});
    }

    @Override
    public EntrepotModel findByEntrepotId(long entrepotId) {
        String sql = "SELECT * FROM entrepot WHERE id_entrepot = ?";
 
        EntrepotModel entrepot = (EntrepotModel)getJdbcTemplate().queryForObject(
				sql, new Object[] { entrepotId }, new EntrepotMapper());
	
        return entrepot;
        
    }

    //query single row with BeanPropertyRowMapper (Customer.class)
    @Override
    public EntrepotModel findByEntrepotId2(long entrepotId) {
        String sql = "SELECT * FROM entrepot WHERE id_entrepot = ?";

        EntrepotModel entrepot = (EntrepotModel) getJdbcTemplate().queryForObject(sql, new Object[]{entrepotId},
                new BeanPropertyRowMapper(EntrepotModel.class));
        
        return entrepot;
    }

    
    @Override
    public List<EntrepotModel> tousEntrepots() {
        

        String sql = "SELECT * FROM entrepot";

        List<EntrepotModel> lesentrepots = new ArrayList<EntrepotModel>();
        
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
        try{
            for (Map row : rows) {
                EntrepotModel entrepot = new EntrepotModel();
                entrepot.setIdEntrepot((Long) (row.get("id_entrepot")));
                entrepot.setNomEntrepot((String) row.get("nom_entrepot"));
                entrepot.setLieu((String) row.get("lieu"));
                lesentrepots.add(entrepot);
            }
        return lesentrepots;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    
    }

    //query mutiple rows with BeanPropertyRowMapper (EntrepotModel.class)
    @Override
    public List<EntrepotModel> tousEntrepots2() {
        String sql = "SELECT * FROM entrepot";
        try {
        List<EntrepotModel> lesentrepots  = getJdbcTemplate().query(sql,
				new BeanPropertyRowMapper(EntrepotModel.class));
		
        return lesentrepots;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public String findEntrepotLibelleById(long entrepotId) {
        String sql = "SELECT nom_entrepot FROM entrepot WHERE id_entrepot = ?";

        String nom_entrepot = (String) getJdbcTemplate().queryForObject(
                sql, new Object[]{entrepotId}, String.class);

        return nom_entrepot;

    }

    @Override
    public int findTotalEntrepotModel() {
        String sql = "SELECT COUNT(*) FROM entrepot";
        int total = getJdbcTemplate().queryForObject(sql,Integer.class);       
        return total;
    }

    @Override
    public void deleteEntrepot(long identrepot) {
       EntrepotModel entrepot=this.findByEntrepotId2(identrepot);
        String sql = "DELETE FROM entrepot where id_entrepot=?";
        getJdbcTemplate().update(sql,entrepot.getIdEntrepot());
    }
    
}
