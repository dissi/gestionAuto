/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.genieLogiciel.gestionAuto.dao.impl;

import javax.sql.DataSource;
import java.util.List;
import  com.genieLogiciel.gestionAuto.mapper.AffectationMapper;
import com.genieLogiciel.gestionAuto.dao.AffectationDAO;
import com.genieLogiciel.gestionAuto.dao.FonctionDAO;
import com.genieLogiciel.gestionAuto.dao.AgentDAO;
import com.genieLogiciel.gestionAuto.dao.DemandeMissionDAO;
import com.genieLogiciel.gestionAuto.dao.EntrepotDAO;
import com.genieLogiciel.gestionAuto.dao.ProduitDAO;
import com.genieLogiciel.gestionAuto.dao.VehiculeDAO;
import com.genieLogiciel.gestionAuto.dao.ZoneDAO;
import com.genieLogiciel.gestionAuto.model.AgentModel;
import com.genieLogiciel.gestionAuto.model.AffectationModel;
import com.genieLogiciel.gestionAuto.model.DemandeMissionModel;
import com.genieLogiciel.gestionAuto.model.EntrepotModel;
import com.genieLogiciel.gestionAuto.model.FonctionModel;
import com.genieLogiciel.gestionAuto.model.ProduitModel;
import com.genieLogiciel.gestionAuto.model.VehiculeModel;
import com.genieLogiciel.gestionAuto.model.ZoneModel;
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
public class AffectationDAOImp extends JdbcDaoSupport implements AffectationDAO {
    
    @Autowired
    private FonctionDAOImp fonctionDaoImp;
    @Autowired
    private AgentDAO agentDao;
    @Autowired
    private EntrepotDAO entrepotDao;
    @Autowired
    private ProduitDAO produitDao;
    @Autowired
    private ZoneDAO zoneDao;
    
    @Autowired
    private VehiculeDAO vehiculeDAO;
    
    @Autowired
    private DemandeMissionDAO demandeMissionDAO;
    
    
    @Autowired
    public AffectationDAOImp(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    @Override
    public void addAffectation(AffectationModel affectation) {
        String sql = "INSERT INTO affectation (`date_affectation`,`date_debut`, `date_fin`, `etat`, `agent_id`,"
                + " `demande_mission_id`, `vehicule_id`) VALUES (?,?,?,?,?,?,?)";     
        
//        System.out.println("com.genieLogiciel.gestionAuto.dao.impl.AffectationDAOImp.addAffectation()");
//        System.out.println(affectation.getDateDebut());
//        
//        System.out.println(affectation.getDateAffectation());
//        System.out.println(affectation.getDateFin());
//        System.out.println(affectation.getEtat());
//        System.out.println(affectation.getDemandeMission());
//        System.out.println(affectation.getDemandeMission().getIdDemandeMission());
//        System.out.println(affectation.getVehicule().getIdVehicule());
//        System.out.println(affectation.getChauffeur().getIdAgent());
        
	getJdbcTemplate().update(sql, new Object[] {
            affectation.getDateDebut(),
            affectation.getDateAffectation(),
            affectation.getDateFin(),
            affectation.getEtat(),affectation.getChauffeur().getIdAgent(),
            affectation.getDemandeMission().getIdDemandeMission(),
            affectation.getVehicule().getIdVehicule()
            
        });
    }
    @Override
    public void updateAffectation(AffectationModel affectation) {
        String sql = "update affectation set`id_affectation`=?, `date_affectation`=?, `date_debut`=?, `date_fin`=?,`etat`=?, `agent_id`=?, `demande_mission_id`=?, `vehicule_id`=? where id_affectation=?";
        getJdbcTemplate().update(sql,affectation.getDateDebut(),affectation.getDateAffectation(),affectation.getDateFin(),affectation.getEtat(),
            affectation.getDemandeMission().getIdDemandeMission(),
            affectation.getVehicule().getIdVehicule(),
            affectation.getChauffeur().getIdAgent(),affectation.getIdAffectation());
	
    }
    
    @Override
    public void insertBatchSQL(String sql) {
        getJdbcTemplate().batchUpdate(new String[]{sql});
    }

    @Override
    public AffectationModel findByAffectationId(long agtId) {
      
        String sql = "SELECT * FROM affectation WHERE id_affectation = ?";
 
        AffectationModel affectation = (AffectationModel)getJdbcTemplate().queryForObject(
				sql, new Object[] { agtId }, new RowMapper() {
     public Object mapRow(ResultSet row, int rowNum) throws SQLException {
         AffectationModel affectation = new AffectationModel();
         affectation.setIdAffectation((row.getLong("id_affectation")));
         affectation.setEtat((row.getString("etat")));
         affectation.setDateDebut((row.getDate("date_debut")));
         affectation.setDateFin((row.getDate("date_fin")));
         affectation.setChauffeur((AgentModel) agentDao.findByAgentId((row.getLong("agent_id"))));
         affectation.setVehicule((VehiculeModel) vehiculeDAO.findByVehiculeId((row.getLong("vehicule_id"))));
         affectation.setDemandeMission((DemandeMissionModel) demandeMissionDAO.findByDemandeMissionId((row.getLong("demande_mission_id"))));
         return affectation;
     }
     }
                
                );
	
        return affectation;
        
    }

    //query single row with BeanPropertyRowMapper (Customer.class)
    @Override
    public AffectationModel findByAffectationId2(long agtId) {
        String sql = "SELECT * FROM affectation WHERE id_affectation = ?";

        AffectationModel affectation = (AffectationModel) getJdbcTemplate().queryForObject(sql, new Object[]{agtId},
                new BeanPropertyRowMapper(AffectationModel.class));
        
        return affectation;
    }

    
    @Override
    public List<AffectationModel> tousAffectations() {
        

        String sql = "SELECT * FROM affectation";

        List<AffectationModel> lesaffectations = new ArrayList<AffectationModel>();
        
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
        try{
            for (Map row : rows) {
                AffectationModel affectation = new AffectationModel();
                affectation.setIdAffectation((Long) (row.get("id_affectation")));
                affectation.setEtat((String) (row.get("etat")));
                affectation.setDateDebut((Date) (row.get("date_debut")));
                affectation.setDateFin((Date) (row.get("date_fin")));
                affectation.setChauffeur((AgentModel) agentDao.findByAgentId((Long) (row.get("agent_id"))));
                affectation.setVehicule((VehiculeModel) vehiculeDAO.findByVehiculeId((Long) (row.get("vehicule_id"))));
                affectation.setDemandeMission((DemandeMissionModel) demandeMissionDAO.findByDemandeMissionId((Long) (row.get("demande_mission_id"))));

                lesaffectations.add(affectation);
            }
        return lesaffectations;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    
    }

    //query mutiple rows with BeanPropertyRowMapper (AffectationModel.class)
    @Override
    public List<AffectationModel> tousAffectations2() {
        String sql = "SELECT * FROM affectation";
        try {
        List<AffectationModel> lesaffectations  = getJdbcTemplate().query(sql,
				new BeanPropertyRowMapper(AffectationModel.class));
		
        return lesaffectations;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public String findAffectationLibelleById(long agtId) {
        String sql = "SELECT libelle FROM affectation WHERE id_affectation = ?";

        String libelle = (String) getJdbcTemplate().queryForObject(
                sql, new Object[]{agtId}, String.class);

        return libelle;

    }

    @Override
    public int findTotalAffectationModel() {
        String sql = "SELECT COUNT(*) FROM affectation";

        int total = getJdbcTemplate().queryForObject(sql,Integer.class);
        
        return total;
    }

    @Override
    public void deleteAffectation(long idaffectation) {
        AffectationModel affectation=this.findByAffectationId2(idaffectation);
        String sql = "DELETE FROM affectation where id_affectation=?";
        getJdbcTemplate().update(sql,affectation.getIdAffectation());
    }

    @Override
    public List<AffectationModel> tousAffectationsByEtat(String etat) {
        
        String sql = "SELECT * FROM affectation where etat='"+etat+"'";
        List<AffectationModel> lesaffectations = new ArrayList<AffectationModel>();

        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
        try {
            for (Map row : rows) {
                AffectationModel affectation = new AffectationModel();
                affectation.setIdAffectation((Long)(row.get("id_affectation")));
                affectation.setEtat((String)(row.get("etat")));
                affectation.setDateDebut((Date)(row.get("date_debut")));
                affectation.setDateFin((Date)(row.get("date_fin")));
                affectation.setChauffeur((AgentModel) agentDao.findByAgentId((Long)(row.get("agent_id"))));
                affectation.setVehicule((VehiculeModel) vehiculeDAO.findByVehiculeId((Long)(row.get("vehicule_id"))));
                affectation.setDemandeMission((DemandeMissionModel) demandeMissionDAO.findByDemandeMissionId((Long)(row.get("demande_mission_id"))));

                lesaffectations.add(affectation);
            }
            return lesaffectations;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    
}
