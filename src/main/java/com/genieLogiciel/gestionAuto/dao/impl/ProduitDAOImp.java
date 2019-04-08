/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.genieLogiciel.gestionAuto.dao.impl;

import javax.sql.DataSource;
import java.util.List;
import  com.genieLogiciel.gestionAuto.mapper.ProduitMapper;
import com.genieLogiciel.gestionAuto.dao.ProduitDAO;
import com.genieLogiciel.gestionAuto.model.ProduitModel;
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
public class ProduitDAOImp extends JdbcDaoSupport implements ProduitDAO {
    

    @Autowired
    public ProduitDAOImp(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
    
    
    @Override
    public void addProduit(ProduitModel produit) {
        String sql = "INSERT INTO produit (libelle,type_produit) VALUES (?,?)";
	getJdbcTemplate().update(sql, new Object[] {produit.getLibelle(),produit.getTypeProduit()});
    }
    @Override
    public void updateProduit(ProduitModel produit) {
        String sql = "update produit set libelle=?,type_produit=? where id_produit=?";
        getJdbcTemplate().update(sql,produit.getLibelle(),produit.getTypeProduit(), produit.getIdProduit());
	
    }
    
    @Override
    public void insertBatchSQL(String sql) {
        getJdbcTemplate().batchUpdate(new String[]{sql});
    }

    @Override
    public ProduitModel findByProduitId(long produitId) {
        String sql = "SELECT * FROM produit WHERE id_produit = ?";
 
        ProduitModel produit = (ProduitModel)getJdbcTemplate().queryForObject(
				sql, new Object[] { produitId }, new ProduitMapper());
	
        return produit;
        
    }

    //query single row with BeanPropertyRowMapper (Customer.class)
    @Override
    public ProduitModel findByProduitId2(long produitId) {
        String sql = "SELECT * FROM produit WHERE id_produit = ?";

        ProduitModel produit = (ProduitModel) getJdbcTemplate().queryForObject(sql, new Object[]{produitId},
                new BeanPropertyRowMapper(ProduitModel.class));
        
        return produit;
    }

    
    @Override
    public List<ProduitModel> tousProduits() {
        

        String sql = "SELECT * FROM produit";

        List<ProduitModel> lesproduits = new ArrayList<ProduitModel>();
        
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
        try{
            for (Map row : rows) {
                ProduitModel produit = new ProduitModel();
                produit.setIdProduit((Long) (row.get("id_produit")));
                produit.setLibelle((String) row.get("libelle"));
                produit.setTypeProduit((String) row.get("type_produit"));
                lesproduits.add(produit);
            }
        return lesproduits;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    
    }

    //query mutiple rows with BeanPropertyRowMapper (ProduitModel.class)
    @Override
    public List<ProduitModel> tousProduits2() {
        String sql = "SELECT * FROM produit";
        try {
        List<ProduitModel> lesproduits  = getJdbcTemplate().query(sql,
				new BeanPropertyRowMapper(ProduitModel.class));
		
        return lesproduits;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public String findProduitLibelleById(long produitId) {
        String sql = "SELECT libelle FROM produit WHERE id_produit = ?";

        String nom_produit = (String) getJdbcTemplate().queryForObject(
                sql, new Object[]{produitId}, String.class);

        return nom_produit;

    }

    @Override
    public int findTotalProduitModel() {
        String sql = "SELECT COUNT(*) FROM produit";
        int total = getJdbcTemplate().queryForObject(sql,Integer.class);       
        return total;
    }

    @Override
    public void deleteProduit(long idproduit) {
       ProduitModel produit=this.findByProduitId2(idproduit);
        String sql = "DELETE FROM produit where id_produit=?";
        getJdbcTemplate().update(sql,produit.getIdProduit());
    }
    
}
