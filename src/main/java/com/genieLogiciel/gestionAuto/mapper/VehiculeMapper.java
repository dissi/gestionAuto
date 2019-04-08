/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.genieLogiciel.gestionAuto.mapper;

import com.genieLogiciel.gestionAuto.dao.impl.FonctionDAOImp;
import com.genieLogiciel.gestionAuto.model.VehiculeModel;
import com.genieLogiciel.gestionAuto.service.PersonnelService;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;


/**
 *
 * @author dissirama
 */
public class VehiculeMapper implements RowMapper<VehiculeModel> {
    
//    public static final String BASE_SQL //
//            = "SELECT `id_vehicule`, `date_embauche`, `date_naissance`, `date_retraite`, `nom_vehicule`, `premis_vehicule`, `prenom_vehicule`, `fonction_id` FROM vehicule";
//    @Autowired
    public static final String BASE_SQL //
            = "SELECT `id_vehicule`, `date_embauche`, `date_naissance`, `date_retraite`, `nom_vehicule`, `premis_vehicule`, `prenom_vehicule`, `fonction_id` FROM vehicule";
    @Autowired
    public PersonnelService personnelService;
    @Autowired
    private FonctionDAOImp fonctDAO;
    
    @Override
    public VehiculeModel mapRow(ResultSet rs, int i) throws SQLException {
        VehiculeModel vehicule = new VehiculeModel();
        vehicule.setIdVehicule(rs.getLong("id_vehicule"));
        vehicule.setImmatriculation(rs.getString("immatriculation"));
        vehicule.setTypecarburant(rs.getString("typecarburant"));
        vehicule.setTypeVehicule(rs.getString("type_vehicule"));
        vehicule.setConsomMoyenne(rs.getDouble("consom_moyenne"));
        vehicule.setCharge(rs.getDouble("charge"));
        vehicule.setEtat(rs.getString("etat"));
       
        return vehicule;
    }
    

}
