/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.genieLogiciel.gestionAuto.mapper;

import com.genieLogiciel.gestionAuto.dao.AgentDAO;
import com.genieLogiciel.gestionAuto.dao.impl.AgentDAOImp;
import com.genieLogiciel.gestionAuto.model.AffectationModel;
import com.genieLogiciel.gestionAuto.model.AgentModel;
import com.genieLogiciel.gestionAuto.model.DemandeMissionModel;
import com.genieLogiciel.gestionAuto.model.EntrepotModel;
import com.genieLogiciel.gestionAuto.model.ProduitModel;
import com.genieLogiciel.gestionAuto.model.VehiculeModel;
import com.genieLogiciel.gestionAuto.model.ZoneModel;
import com.genieLogiciel.gestionAuto.service.PersonnelService;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;


/**
 *
 * @author dissirama
 */
public class AffectationMapper implements RowMapper<AffectationModel> {
    
//    public static final String BASE_SQL //
//            = "SELECT `id_affectation`, `datedemande`, `date_fin`, `date_retraite`, `description`, `premis_affectation`, `predescription`, `fonction_id` FROM affectation";
//    @Autowired
    public static final String BASE_SQL //
            = "SELECT `id_affectation`, `date_affectation`, `date_debut`, `date_fin`, `etat`, `agent_id`, `demande_mission_id`, `vehicule_id` FROM `affectation`";
    @Autowired
    public PersonnelService personnelService;
        
    @Override
    public AffectationModel mapRow(ResultSet rs, int i) throws SQLException {
        AffectationModel affectation = new AffectationModel();
        affectation.setIdAffectation(rs.getLong("id_affectation"));
        affectation.setEtat(rs.getString("etat"));
        affectation.setDateDebut(rs.getDate("date_debut"));
        affectation.setDateFin(rs.getDate("date_fin"));
        affectation.setDateAffectation(rs.getDate("date_affectation"));
        AgentModel agent= personnelService.findByAgentId(rs.getLong("agent_id"));
        affectation.setChauffeur(agent);
        VehiculeModel vehicule= personnelService.findByVehiculeId(rs.getLong("vehicule_id"));
        affectation.setVehicule(vehicule);
        DemandeMissionModel zone= personnelService.findByDemandeMissionId(rs.getLong("demande_mission_id"));
        affectation.setDemandeMission(zone);

        return affectation;
    }
    

}
