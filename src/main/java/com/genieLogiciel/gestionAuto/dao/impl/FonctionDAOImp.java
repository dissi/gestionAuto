/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.genieLogiciel.gestionAuto.dao.impl;

import javax.sql.DataSource;
import java.util.List;
import  com.genieLogiciel.gestionAuto.mapper.FonctionMapper;
import com.genieLogiciel.gestionAuto.dao.FonctionDAO;
import com.genieLogiciel.gestionAuto.model.FonctionModel;
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
public class FonctionDAOImp extends JdbcDaoSupport implements FonctionDAO {
    

    @Autowired
    public FonctionDAOImp(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
    
    
    @Override
    public void addFonction(FonctionModel fonction) {
        String sql = "INSERT INTO fonction (libelle) VALUES (?)";
	getJdbcTemplate().update(sql, new Object[] {fonction.getLibelle()});
    }
    @Override
    public void updateFonction(FonctionModel fonction) {
        String sql = "update fonction set libelle=? where id_fonction=?";
        getJdbcTemplate().update(sql,fonction.getLibelle(), fonction.getIdFonction());
	
    }
    
    @Override
    public void insertBatchSQL(String sql) {
        getJdbcTemplate().batchUpdate(new String[]{sql});
    }

    @Override
    public FonctionModel findByFonctionId(long fonctId) {
        String sql = "SELECT * FROM fonction WHERE id_fonction = ?";
 
        FonctionModel fonction = (FonctionModel)getJdbcTemplate().queryForObject(
				sql, new Object[] { fonctId }, new FonctionMapper());
	
        return fonction;
        
    }

    //query single row with BeanPropertyRowMapper (Customer.class)
    @Override
    public FonctionModel findByFonctionId2(long fonctId) {
        String sql = "SELECT * FROM fonction WHERE id_fonction = ?";

        FonctionModel fonction = (FonctionModel) getJdbcTemplate().queryForObject(sql, new Object[]{fonctId},
                new BeanPropertyRowMapper(FonctionModel.class));
        
        return fonction;
    }

    
    @Override
    public List<FonctionModel> tousFonctions() {
        

        String sql = "SELECT * FROM fonction";

        List<FonctionModel> lesfonctions = new ArrayList<FonctionModel>();
        
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
        try{
            for (Map row : rows) {
                FonctionModel fonction = new FonctionModel();
                fonction.setIdFonction((Long) (row.get("id_fonction")));
                fonction.setLibelle((String) row.get("libelle"));
                lesfonctions.add(fonction);
            }
        return lesfonctions;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    
    }

    //query mutiple rows with BeanPropertyRowMapper (FonctionModel.class)
    @Override
    public List<FonctionModel> tousFonctions2() {
        String sql = "SELECT * FROM fonction";
        try {
        List<FonctionModel> lesfonctions  = getJdbcTemplate().query(sql,
				new BeanPropertyRowMapper(FonctionModel.class));
		
        return lesfonctions;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public String findFonctionLibelleById(long fonctId) {
        String sql = "SELECT libelle FROM fonction WHERE id_fonction = ?";

        String libelle = (String) getJdbcTemplate().queryForObject(
                sql, new Object[]{fonctId}, String.class);

        return libelle;

    }

    @Override
    public int findTotalFonctionModel() {
        String sql = "SELECT COUNT(*) FROM fonction";

        int total = getJdbcTemplate().queryForObject(sql,Integer.class);
        
        return total;
    }

    @Override
    public void deleteFonction(long idfonction) {
       FonctionModel fonction=this.findByFonctionId2(idfonction);
        String sql = "DELETE FROM fonction where id_fonction=?";
        getJdbcTemplate().update(sql,fonction.getIdFonction());
    }
    
}
