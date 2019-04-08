/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.genieLogiciel.gestionAuto.dao.impl;

import javax.sql.DataSource;
import java.util.List;
import  com.genieLogiciel.gestionAuto.mapper.VehiculeMapper;
import com.genieLogiciel.gestionAuto.dao.VehiculeDAO;
import com.genieLogiciel.gestionAuto.dao.FonctionDAO;
import com.genieLogiciel.gestionAuto.model.VehiculeModel;
import com.genieLogiciel.gestionAuto.model.FonctionModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
public class VehiculeDAOImp extends JdbcDaoSupport implements VehiculeDAO {
    
    @Autowired
    private FonctionDAOImp fonctionDaoImp;
    
    @Autowired
    public VehiculeDAOImp(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

   
    
    
    @Override
    public void addVehicule(VehiculeModel vehicule) {
        String sql = "INSERT INTO vehicule ( charge,consom_moyenne,etat,immatriculation,type_vehicule,typecarburant ) VALUES (?,?,?,?,?,?)";
	getJdbcTemplate().update(sql, new Object[] {vehicule.getCharge(),vehicule.getConsomMoyenne()
        ,vehicule.getEtat(),vehicule.getImmatriculation(),vehicule.getTypeVehicule(),vehicule.getTypecarburant()});
    }
    @Override
    public void updateVehicule(VehiculeModel vehicule) {
        String sql = "update vehicule set `date_embauche`=?, `date_naissance`=?, "
                + "`date_retraite`=?, `nom_vehicule`=?, `premis_vehicule`=?, `prenom_vehicule`=?,`fonction_id`=? where id_vehicule=?";
        getJdbcTemplate().update(sql,vehicule.getCharge(),vehicule.getConsomMoyenne()
        ,vehicule.getEtat(),vehicule.getImmatriculation(),vehicule.getTypeVehicule(),vehicule.getTypecarburant(), vehicule.getIdVehicule());
	
    }
    
    @Override
    public void insertBatchSQL(String sql) {
        getJdbcTemplate().batchUpdate(new String[]{sql});
    }

    @Override
    public VehiculeModel findByVehiculeId(long agtId) {
        //String sql = "SELECT * FROM vehicule WHERE id_vehicule = ?";
       
        String sql = "SELECT v.id_vehicule,v.charge,v.consom_moyenne,v.etat,v.immatriculation,v.type_vehicule,v.typecarburant FROM vehicule AS v WHERE  v.id_vehicule = ?";
 
        VehiculeModel vehicule = (VehiculeModel)getJdbcTemplate().queryForObject(
				sql, new Object[] { agtId }, new RowMapper() {
     public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
      VehiculeModel vehiculeT = new VehiculeModel();
     
         vehiculeT.setIdVehicule(rs.getLong("id_vehicule"));
         vehiculeT.setImmatriculation(rs.getString("immatriculation"));
         vehiculeT.setTypecarburant(rs.getString("typecarburant"));
         vehiculeT.setTypeVehicule(rs.getString("type_vehicule"));
         vehiculeT.setConsomMoyenne(rs.getDouble("consom_moyenne"));
         vehiculeT.setCharge(rs.getDouble("charge"));
         vehiculeT.setEtat(rs.getString("etat"));
         return vehiculeT;
     }
     }
                
                );
	
        return vehicule;
        
    }

    //query single row with BeanPropertyRowMapper (Customer.class)
    @Override
    public VehiculeModel findByVehiculeId2(long agtId) {
        String sql = "SELECT * FROM vehicule WHERE id_vehicule = ?";

        VehiculeModel vehicule = (VehiculeModel) getJdbcTemplate().queryForObject(sql, new Object[]{agtId},
                new BeanPropertyRowMapper(VehiculeModel.class));
        
        return vehicule;
    }

    
    @Override
    public List<VehiculeModel> tousVehicules() {
        

        String sql = "SELECT * FROM vehicule";

        List<VehiculeModel> lesvehicules = new ArrayList<VehiculeModel>();
        
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
        try{
            for (Map row : rows) {
                VehiculeModel vehicule = new VehiculeModel();
                vehicule.setIdVehicule((Long) (row.get("id_vehicule")));
                vehicule.setImmatriculation((String) row.get("immatriculation"));
                vehicule.setTypecarburant((String) row.get("typecarburant"));
                vehicule.setTypeVehicule((String) row.get("type_vehicule"));
               vehicule.setConsomMoyenne((Double) row.get("consom_moyenne"));
                vehicule.setCharge((Double) row.get("charge"));
                vehicule.setEtat((String) row.get("etat"));
                lesvehicules.add(vehicule);
            }
        return lesvehicules;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    
    }

    //query mutiple rows with BeanPropertyRowMapper (VehiculeModel.class)
    @Override
    public List<VehiculeModel> tousVehicules2() {
        String sql = "SELECT * FROM vehicule";
        try {
        List<VehiculeModel> lesvehicules  = getJdbcTemplate().query(sql,
				new BeanPropertyRowMapper(VehiculeModel.class));
		
        return lesvehicules;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public String findVehiculeLibelleById(long agtId) {
        String sql = "SELECT libelle FROM vehicule WHERE id_vehicule = ?";

        String libelle = (String) getJdbcTemplate().queryForObject(
                sql, new Object[]{agtId}, String.class);

        return libelle;

    }

    @Override
    public int findTotalVehiculeModel() {
        String sql = "SELECT COUNT(*) FROM vehicule";

        int total = getJdbcTemplate().queryForObject(sql,Integer.class);
        
        return total;
    }

    @Override
    public void deleteVehicule(long idvehicule) {
       VehiculeModel vehicule=this.findByVehiculeId2(idvehicule);
        String sql = "DELETE FROM vehicule where id_vehicule=?";
        getJdbcTemplate().update(sql,vehicule.getIdVehicule());
    }

    @Override
    public List<VehiculeModel> allVehiculesByDate(Date datedebut) {
        

        String sql = "SELECT * FROM vehicule where vehicule.id_vehicule not in (SELECT affectation.vehicule_id FROM `affectation` INNER JOIN demandemission on affectation.demande_mission_id=demandemission.id_demande_mission  WHERE affectation.date_fin >'"+datedebut+"')";
        System.out.println("com.genieLogiciel.gestionAuto.dao.impl.VehiculeDAOImp.allVehiculesByDate()");
        System.out.println(sql);
        List<VehiculeModel> lesvehicules = new ArrayList<VehiculeModel>();
        
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
        try{
            for (Map row : rows) {
                VehiculeModel vehicule = new VehiculeModel();
                vehicule.setIdVehicule((Long) (row.get("id_vehicule")));
                vehicule.setImmatriculation((String) row.get("immatriculation"));
                vehicule.setTypecarburant((String) row.get("typecarburant"));
                vehicule.setTypeVehicule((String) row.get("type_vehicule"));
                vehicule.setConsomMoyenne((Double) row.get("consom_moyenne"));
                vehicule.setCharge((Double) row.get("charge"));
                vehicule.setEtat((String) row.get("etat"));
                lesvehicules.add(vehicule);
            }
        return lesvehicules;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    
}
