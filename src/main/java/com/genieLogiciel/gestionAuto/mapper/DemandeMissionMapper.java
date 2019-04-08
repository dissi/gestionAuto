/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.genieLogiciel.gestionAuto.mapper;

import com.genieLogiciel.gestionAuto.dao.AgentDAO;
import com.genieLogiciel.gestionAuto.dao.impl.AgentDAOImp;
import com.genieLogiciel.gestionAuto.model.DemandeMissionModel;
import com.genieLogiciel.gestionAuto.model.AgentModel;
import com.genieLogiciel.gestionAuto.model.EntrepotModel;
import com.genieLogiciel.gestionAuto.model.ProduitModel;
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
public class DemandeMissionMapper implements RowMapper<DemandeMissionModel> {
    
//    public static final String BASE_SQL //
//            = "SELECT `id_demandeMission`, `datedemande`, `date_fin`, `date_retraite`, `description`, `premis_demandeMission`, `predescription`, `fonction_id` FROM demandeMission";
//    @Autowired
    public static final String BASE_SQL //
            = "SELECT `id_demande_mission`, `date_debut`, `datedemande`, `description`, `objet`, `date_fin`, `etat`, `quantite`,`agent_id`, `zone_id`, `entrepot_id`, `produit_id` FROM `demandemission`";
    @Autowired
    public PersonnelService personnelService;
        
    @Override
    public DemandeMissionModel mapRow(ResultSet rs, int i) throws SQLException {
        DemandeMissionModel demandeMission = new DemandeMissionModel();
        demandeMission.setIdDemandeMission(rs.getLong("id_demande_mission"));
        demandeMission.setDescription(rs.getString("description"));
        demandeMission.setObjet(rs.getString("objet"));
        demandeMission.setDateDebut(rs.getDate("date_debut"));
        demandeMission.setDateFin(rs.getDate("date_fin"));
        demandeMission.setDatedemande(rs.getDate("datedemande"));
        demandeMission.setQuantite(rs.getFloat("quantite"));
        AgentModel agent= personnelService.findByAgentId2(rs.getLong("agent_id"));
        demandeMission.setAgentM(agent);
        EntrepotModel entrepot= personnelService.findByEntrepotId2(rs.getLong("entrepot_id"));
        demandeMission.setEntrepot(entrepot);
        ZoneModel zone= personnelService.findByZoneId2(rs.getLong("zone_id"));
        demandeMission.setZone(zone);
        ProduitModel produit= personnelService.findByProduitId2(rs.getLong("produit_id"));
        demandeMission.setProduit(produit);

        return demandeMission;
    }
    

}
