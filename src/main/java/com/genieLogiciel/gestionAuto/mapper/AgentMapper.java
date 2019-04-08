/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.genieLogiciel.gestionAuto.mapper;

import com.genieLogiciel.gestionAuto.dao.FonctionDAO;
import com.genieLogiciel.gestionAuto.dao.impl.FonctionDAOImp;
import com.genieLogiciel.gestionAuto.model.AgentModel;
import com.genieLogiciel.gestionAuto.model.FonctionModel;
import com.genieLogiciel.gestionAuto.service.PersonnelService;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;


/**
 *
 * @author dissirama
 */
public class AgentMapper implements RowMapper<AgentModel> {
    
//    public static final String BASE_SQL //
//            = "SELECT `id_agent`, `date_embauche`, `date_naissance`, `date_retraite`, `nom_agent`, `premis_agent`, `prenom_agent`, `fonction_id` FROM agent";
//    @Autowired
    public static final String BASE_SQL //
            = "SELECT `id_agent`, `date_embauche`, `date_naissance`, `date_retraite`, `nom_agent`, `premis_agent`, `prenom_agent`, `fonction_id` FROM agent";
    @Autowired
    public PersonnelService personnelService;
    @Autowired
    private FonctionDAOImp fonctDAO;
    
    @Override
    public AgentModel mapRow(ResultSet rs, int i) throws SQLException {
        AgentModel agent = new AgentModel();
        agent.setIdAgent(rs.getLong("id_agent"));
        agent.setNom(rs.getString("nom_agent"));
        agent.setPrenom(rs.getString("prenom_agent"));
        agent.setPermis(rs.getString("premis_agent"));
        agent.setDateNaissance(rs.getDate("date_naissance"));
        agent.setDateEmbauche(rs.getDate("date_embauche"));
        agent.setDateRetraite(rs.getDate("date_retraite"));
        //FonctionModel fonctss= new FonctionModel(rs.getLong("fonction_id"),"libelle");
        FonctionModel fonctss= personnelService.findByFonctionId2(rs.getLong("fonction_id"));
      
        agent.setFonction(fonctss);

        return agent;
    }
    

}
