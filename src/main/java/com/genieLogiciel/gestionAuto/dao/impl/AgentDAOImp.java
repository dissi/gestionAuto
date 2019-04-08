/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.genieLogiciel.gestionAuto.dao.impl;

import javax.sql.DataSource;
import java.util.List;
import  com.genieLogiciel.gestionAuto.mapper.AgentMapper;
import com.genieLogiciel.gestionAuto.dao.AgentDAO;
import com.genieLogiciel.gestionAuto.dao.FonctionDAO;
import com.genieLogiciel.gestionAuto.model.AgentModel;
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
public class AgentDAOImp extends JdbcDaoSupport implements AgentDAO {
    
    @Autowired
    private FonctionDAOImp fonctionDaoImp;
    
    @Autowired
    public AgentDAOImp(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

   
    
    
    @Override
    public void addAgent(AgentModel agent) {
        String sql = "INSERT INTO agent (`date_embauche`, `date_naissance`, "
                + "`date_retraite`, `nom_agent`, `premis_agent`, `prenom_agent`, `fonction_id`) VALUES (?,?,?,?,?,?,?)";
	getJdbcTemplate().update(sql, new Object[] {agent.getDateEmbauche(),agent.getDateNaissance()
        ,agent.getDateRetraite(),agent.getNom(),agent.getPermis(),agent.getPrenom(),agent.getFonction().getIdFonction()});
    }
    @Override
    public void updateAgent(AgentModel agent) {
        String sql = "update agent set `date_embauche`=?, `date_naissance`=?, "
                + "`date_retraite`=?, `nom_agent`=?, `premis_agent`=?, `prenom_agent`=?,`fonction_id`=? where id_agent=?";
        getJdbcTemplate().update(sql,agent.getDateEmbauche(),agent.getDateNaissance()
        ,agent.getDateRetraite(),agent.getNom(),agent.getPermis(),agent.getPrenom(),agent.getFonction().getIdFonction(), agent.getIdAgent());
	
    }
    
    @Override
    public void insertBatchSQL(String sql) {
        getJdbcTemplate().batchUpdate(new String[]{sql});
    }

    @Override
    public AgentModel findByAgentId(long agtId) {
        //String sql = "SELECT * FROM agent WHERE id_agent = ?";
       
        String sql = "SELECT a.id_agent,a.date_embauche,a.date_naissance,a.date_retraite,a.nom_agent,a.premis_agent,a.prenom_agent,a.fonction_id,f.id_fonction,f.libelle FROM agent AS a ,fonction AS f WHERE f.id_fonction = a.fonction_id AND a.id_agent = ?";
 
        AgentModel agent = (AgentModel)getJdbcTemplate().queryForObject(
				sql, new Object[] { agtId }, new RowMapper() {
     public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
      AgentModel agentT = new AgentModel();
     
         agentT.setIdAgent(rs.getLong("id_agent"));
         agentT.setNom(rs.getString("nom_agent"));
         agentT.setPrenom(rs.getString("prenom_agent"));
         agentT.setPermis(rs.getString("premis_agent"));
         agentT.setDateNaissance(rs.getDate("date_naissance"));
         agentT.setDateEmbauche(rs.getDate("date_embauche"));
         agentT.setDateRetraite(rs.getDate("date_retraite"));
         FonctionModel fonctT = new FonctionModel(rs.getLong("fonction_id"),rs.getString("libelle"));
         agentT.setFonction(fonctT);
         return agentT;
     }
     }
                
                );
	
        return agent;
        
    }

    //query single row with BeanPropertyRowMapper (Customer.class)
    @Override
    public AgentModel findByAgentId2(long agtId) {
        String sql = "SELECT * FROM agent WHERE id_agent = ?";

        AgentModel agent = (AgentModel) getJdbcTemplate().queryForObject(sql, new Object[]{agtId},
                new BeanPropertyRowMapper(AgentModel.class));
        
        return agent;
    }

    
    @Override
    public List<AgentModel> tousAgents() {
        

        String sql = "SELECT * FROM agent";

        List<AgentModel> lesagents = new ArrayList<AgentModel>();
        
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
        try{
            for (Map row : rows) {
                AgentModel agent = new AgentModel();
                agent.setIdAgent((Long) (row.get("id_agent")));
                agent.setNom((String) row.get("nom_agent"));
                agent.setPrenom((String) row.get("prenom_agent"));
                agent.setPermis((String) row.get("premis_agent"));
                agent.setFonction((FonctionModel)  fonctionDaoImp.findByFonctionId2((long)(row.get("fonction_id"))));
                agent.setDateNaissance((Date) row.get("date_naissance"));
                agent.setDateEmbauche((Date) row.get("date_embauche"));
                agent.setDateRetraite((Date) row.get("date_retraite"));
                lesagents.add(agent);
            }
        return lesagents;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    
    }
    @Override
    public List<AgentModel> tousAgentsFonction(long fonction_id) {
        

        String sql = "SELECT * FROM agent where fonction_id="+fonction_id;

        List<AgentModel> lesagents = new ArrayList<AgentModel>();
        
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
        try{
            for (Map row : rows) {
                AgentModel agent = new AgentModel();
                agent.setIdAgent((Long) (row.get("id_agent")));
                agent.setNom((String) row.get("nom_agent"));
                agent.setPrenom((String) row.get("prenom_agent"));
                agent.setPermis((String) row.get("premis_agent"));
                agent.setFonction((FonctionModel)  fonctionDaoImp.findByFonctionId2((long)(row.get("fonction_id"))));
                agent.setDateNaissance((Date) row.get("date_naissance"));
                agent.setDateEmbauche((Date) row.get("date_embauche"));
                agent.setDateRetraite((Date) row.get("date_retraite"));
                lesagents.add(agent);
            }
        return lesagents;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    
    }

    //query mutiple rows with BeanPropertyRowMapper (AgentModel.class)
    @Override
    public List<AgentModel> tousAgents2() {
        String sql = "SELECT * FROM agent";
        try {
        List<AgentModel> lesagents  = getJdbcTemplate().query(sql,
				new BeanPropertyRowMapper(AgentModel.class));
		
        return lesagents;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public String findAgentLibelleById(long agtId) {
        String sql = "SELECT libelle FROM agent WHERE id_agent = ?";

        String libelle = (String) getJdbcTemplate().queryForObject(
                sql, new Object[]{agtId}, String.class);

        return libelle;

    }

    @Override
    public int findTotalAgentModel() {
        String sql = "SELECT COUNT(*) FROM agent";

        int total = getJdbcTemplate().queryForObject(sql,Integer.class);
        
        return total;
    }

    @Override
    public void deleteAgent(long idagent) {
       AgentModel agent=this.findByAgentId2(idagent);
        String sql = "DELETE FROM agent where id_agent=?";
        getJdbcTemplate().update(sql,agent.getIdAgent());
    }
    @Override
    public List<AgentModel> allChauffeursByDate(Date datedebut) {
        String sql = "SELECT * FROM agent where agent.fonction_id=2  AND agent.id_agent not in (SELECT affectation.agent_id FROM `affectation` INNER JOIN demandemission on affectation.demande_mission_id=demandemission.id_demande_mission  WHERE affectation.date_fin >'"+datedebut+"')";

        List<AgentModel> lesagents = new ArrayList<AgentModel>();

        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
        try {
            for (Map row : rows) {
                AgentModel agent = new AgentModel();
                agent.setIdAgent((Long) (row.get("id_agent")));
                agent.setNom((String) row.get("nom_agent"));
                agent.setPrenom((String) row.get("prenom_agent"));
                agent.setPermis((String) row.get("premis_agent"));
                agent.setFonction((FonctionModel) fonctionDaoImp.findByFonctionId2((long) (row.get("fonction_id"))));
                agent.setDateNaissance((Date) row.get("date_naissance"));
                agent.setDateEmbauche((Date) row.get("date_embauche"));
                agent.setDateRetraite((Date) row.get("date_retraite"));
                lesagents.add(agent);
            }
            return lesagents;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }
    
}
