/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.genieLogiciel.gestionAuto.dao.impl;

import javax.sql.DataSource;
import java.util.List;
import  com.genieLogiciel.gestionAuto.mapper.DemandeMissionMapper;
import com.genieLogiciel.gestionAuto.dao.DemandeMissionDAO;
import com.genieLogiciel.gestionAuto.dao.FonctionDAO;
import com.genieLogiciel.gestionAuto.dao.AgentDAO;
import com.genieLogiciel.gestionAuto.dao.EntrepotDAO;
import com.genieLogiciel.gestionAuto.dao.ProduitDAO;
import com.genieLogiciel.gestionAuto.dao.ZoneDAO;
import com.genieLogiciel.gestionAuto.model.AgentModel;
import com.genieLogiciel.gestionAuto.model.DemandeMissionModel;
import com.genieLogiciel.gestionAuto.model.EntrepotModel;
import com.genieLogiciel.gestionAuto.model.FonctionModel;
import com.genieLogiciel.gestionAuto.model.ProduitModel;
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
public class DemandeMissionDAOImp extends JdbcDaoSupport implements DemandeMissionDAO {
    
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
    public DemandeMissionDAOImp(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    @Override
    public void addDemandeMission(DemandeMissionModel demandeMission) {
        String sql = "INSERT INTO demandemission (`date_debut`, `datedemande`,"
                + " `description`, `objet`, `date_fin`, `etat`, `quantite`,"
                + " `agent_id`, `zone_id`, `entrepot_id`, `produit_id`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";     
        
	getJdbcTemplate().update(sql, new Object[] {
            demandeMission.getDateDebut(),
            demandeMission.getDatedemande(),
            demandeMission.getDescription(),
            demandeMission.getObjet(),
            demandeMission.getDateFin(),
            demandeMission.getEtat(),
            demandeMission.getQuantite(),
            demandeMission.getAgentM().getIdAgent(),
            demandeMission.getZone().getIdZone(),
            demandeMission.getEntrepot().getIdEntrepot(),
            demandeMission.getProduit().getIdProduit()});
    }
    @Override
    public void updateDemandeMission(DemandeMissionModel demandeMission) {
                System.out.println("XXXXX");
               System.out.println("agt"+demandeMission.getAgentM().getPrenom());
                System.out.println("zne"+demandeMission.getZone().getIdZone());
                System.out.println("pdt"+demandeMission.getProduit().getIdProduit());
                System.out.println("entre"+demandeMission.getEntrepot().getIdEntrepot());
                System.out.println("qtt"+demandeMission.getQuantite());
                System.out.println("dmde"+demandeMission.getDatedemande());
                System.out.println("dfin"+demandeMission.getDateFin());
                System.out.println("debut"+demandeMission.getDateDebut());
                System.out.println("etat"+demandeMission.getEtat());
                System.out.println("desc"+demandeMission.getDescription());
                System.out.println("objet"+demandeMission.getObjet());
                System.out.println("id"+demandeMission.getIdDemandeMission());
        String sql = "update demandemission set `date_debut`=?, `datedemande`=?,"
                + " `description`=?, `objet`=?, `date_fin`=?, `etat`=?, `quantite`=?,"
                + " `agent_id`=?, `zone_id`=?, `entrepot_id`=?, `produit_id`=? where id_demande_mission=?";
        getJdbcTemplate().update(sql,demandeMission.getDateDebut(),demandeMission.getDatedemande()
        ,demandeMission.getDescription(),demandeMission.getObjet(),
        demandeMission.getDateFin(),demandeMission.getEtat(),
        demandeMission.getQuantite(),demandeMission.getAgentM().getIdAgent()
        ,demandeMission.getZone().getIdZone(),demandeMission.getEntrepot().getIdEntrepot(),demandeMission.getProduit().getIdProduit(), demandeMission.getIdDemandeMission());
	
    }
    
    @Override
    public void insertBatchSQL(String sql) {
        getJdbcTemplate().batchUpdate(new String[]{sql});
    }

    @Override
    public DemandeMissionModel findByDemandeMissionId(long agtId) {
      
        String sql = "SELECT * FROM demandemission WHERE id_demande_mission = ?";
 
        DemandeMissionModel demandeMission = (DemandeMissionModel)getJdbcTemplate().queryForObject(
				sql, new Object[] { agtId }, new RowMapper() {
     public Object mapRow(ResultSet row, int rowNum) throws SQLException {
         DemandeMissionModel demandeMission = new DemandeMissionModel();
         demandeMission.setIdDemandeMission((row.getLong("id_demande_mission")));
         demandeMission.setDescription((row.getString("description")));
         demandeMission.setObjet((row.getString("objet")));
         demandeMission.setEtat((row.getString("etat")));
         demandeMission.setDateDebut((row.getDate("date_debut")));
         demandeMission.setDateFin((row.getDate("date_fin")));
         demandeMission.setDatedemande((row.getDate("datedemande")));
         demandeMission.setQuantite((row.getDouble("quantite")));
         demandeMission.setAgentM((AgentModel) agentDao.findByAgentId((row.getLong("agent_id"))));
         demandeMission.setEntrepot((EntrepotModel) entrepotDao.findByEntrepotId2((row.getLong("entrepot_id"))));
         demandeMission.setZone((ZoneModel) zoneDao.findByZoneId2((row.getLong("zone_id"))));
         demandeMission.setProduit((ProduitModel) produitDao.findByProduitId2((row.getLong("produit_id"))));
         
         return demandeMission;
     }
     }
                
                );
	
        return demandeMission;
        
    }

    //query single row with BeanPropertyRowMapper (Customer.class)
    @Override
    public DemandeMissionModel findByDemandeMissionId2(long agtId) {
        String sql = "SELECT * FROM demandemission WHERE id_demande_mission = ?";

        DemandeMissionModel demandeMission = (DemandeMissionModel) getJdbcTemplate().queryForObject(sql, new Object[]{agtId},
                new BeanPropertyRowMapper(DemandeMissionModel.class));
        
        return demandeMission;
    }

    
    @Override
    public List<DemandeMissionModel> tousDemandeMissions() {
        

        String sql = "SELECT * FROM demandemission";

        List<DemandeMissionModel> lesdemandeMissions = new ArrayList<DemandeMissionModel>();
        
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
        try{
            for (Map row : rows) {
                DemandeMissionModel demandeMission = new DemandeMissionModel();
                demandeMission.setIdDemandeMission((Long) (row.get("id_demande_mission")));
                demandeMission.setDescription((String) (row.get("description")));
                demandeMission.setObjet((String) (row.get("objet")));
                demandeMission.setEtat((String) (row.get("etat")));
                demandeMission.setDateDebut((Date) (row.get("date_debut")));
                demandeMission.setDateFin((Date) (row.get("date_fin")));
                demandeMission.setDatedemande((Date) (row.get("datedemande")));
                demandeMission.setQuantite((double) (row.get("quantite")));
                demandeMission.setAgentM((AgentModel)  agentDao.findByAgentId2((long)(row.get("agent_id"))));
                demandeMission.setEntrepot((EntrepotModel)  entrepotDao.findByEntrepotId2((long)(row.get("entrepot_id"))));
                demandeMission.setZone((ZoneModel) zoneDao.findByZoneId2((long)(row.get("zone_id"))));
                demandeMission.setProduit((ProduitModel) produitDao.findByProduitId2((long)(row.get("produit_id"))));
                lesdemandeMissions.add(demandeMission);
            }
        return lesdemandeMissions;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    
    }

    //query mutiple rows with BeanPropertyRowMapper (DemandeMissionModel.class)
    @Override
    public List<DemandeMissionModel> tousDemandeMissions2() {
        String sql = "SELECT * FROM demandemission";
        try {
        List<DemandeMissionModel> lesdemandeMissions  = getJdbcTemplate().query(sql,
				new BeanPropertyRowMapper(DemandeMissionModel.class));
		
        return lesdemandeMissions;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public String findDemandeMissionLibelleById(long agtId) {
        String sql = "SELECT libelle FROM demandemission WHERE id_demande_mission = ?";

        String libelle = (String) getJdbcTemplate().queryForObject(
                sql, new Object[]{agtId}, String.class);

        return libelle;

    }

    @Override
    public int findTotalDemandeMissionModel() {
        String sql = "SELECT COUNT(*) FROM demandeMission";

        int total = getJdbcTemplate().queryForObject(sql,Integer.class);
        
        return total;
    }

    @Override
    public void deleteDemandeMission(long iddemandeMission) {
        DemandeMissionModel demandeMission=this.findByDemandeMissionId2(iddemandeMission);
        String sql = "DELETE FROM demandeMission where id_demande_mission=?";
        getJdbcTemplate().update(sql,demandeMission.getIdDemandeMission());
    }

    @Override
    public List<DemandeMissionModel> tousDemandeMissionsByEtat(String etat) {
        
        String sql = "SELECT * FROM demandemission where etat='"+etat+"'";
        List<DemandeMissionModel> lesdemandeMissions = new ArrayList<DemandeMissionModel>();

        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
        try {
            for (Map row : rows) {
                DemandeMissionModel demandeMission = new DemandeMissionModel();
                demandeMission.setIdDemandeMission((Long) (row.get("id_demande_mission")));
                demandeMission.setDescription((String) (row.get("description")));
                demandeMission.setObjet((String) (row.get("objet")));
                demandeMission.setEtat((String) (row.get("etat")));
                demandeMission.setDateDebut((Date) (row.get("date_debut")));
                demandeMission.setDateFin((Date) (row.get("date_fin")));
                demandeMission.setDatedemande((Date) (row.get("datedemande")));
                demandeMission.setQuantite((double) (row.get("quantite")));
//                System.out.println("XXXXX");
//                System.out.println("agt"+row.get("agent_id"));
//                System.out.println("zne"+row.get("zone_id"));
//                System.out.println("pdt"+row.get("produit_id"));
//                System.out.println("entre"+row.get("entrepot_id"));
//                System.out.println("qtt"+row.get("quantite"));
//                System.out.println("dmde"+row.get("datedemande"));
//                System.out.println("dfin"+row.get("date_fin"));
//                System.out.println("debut"+row.get("date_debut"));
//                System.out.println("etat"+row.get("etat"));
//                System.out.println("desc"+row.get("description"));
//                System.out.println("id"+row.get("id_demande_mission"));
                demandeMission.setAgentM((AgentModel) agentDao.findByAgentId((long) (row.get("agent_id"))));
                //System.out.println("agtModel" + (AgentModel) agentDao.findByAgentId2((long) (row.get("agent_id"))));
                demandeMission.setEntrepot((EntrepotModel) entrepotDao.findByEntrepotId2((long) (row.get("entrepot_id"))));
                
               // System.out.println("entrepotModel"+(EntrepotModel) entrepotDao.findByEntrepotId2((long) (row.get("entrepot_id"))));
                demandeMission.setZone((ZoneModel) zoneDao.findByZoneId2((long) (row.get("zone_id"))));
                
               // System.out.println("ZoneModel"+(ZoneModel) zoneDao.findByZoneId2((long) (row.get("zone_id"))));
                demandeMission.setProduit((ProduitModel) produitDao.findByProduitId2((long) (row.get("produit_id"))));
                
               // System.out.println("pdtModel"+(ProduitModel) produitDao.findByProduitId2((long) (row.get("produit_id"))));
                lesdemandeMissions.add(demandeMission);
            }
            return lesdemandeMissions;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    
}
