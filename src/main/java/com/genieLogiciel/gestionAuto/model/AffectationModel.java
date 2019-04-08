/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.genieLogiciel.gestionAuto.model;

import java.util.Date;
import javax.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author dissirama
 */
@Entity
@Table(name = "affectation")
public class AffectationModel {
    @Id
    @GeneratedValue
    private Long idAffectation;
    private String etat;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateAffectation;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateDebut;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateFin;
    
     @ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
    @JoinColumn(name="demandeMissionId")
    private DemandeMissionModel demandeMission;
     
     @ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
    @JoinColumn(name="vehiculeId")
    private VehiculeModel vehicule;
    
    @ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
    @JoinColumn(name="agentId")
    private AgentModel chauffeur;

    public Long getIdAffectation() {
        return idAffectation;
    }

    public void setIdAffectation(Long idAffectation) {
        this.idAffectation = idAffectation;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Date getDateAffectation() {
        return dateAffectation;
    }

    public void setDateAffectation(Date dateAffectation) {
        this.dateAffectation = dateAffectation;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public DemandeMissionModel getDemandeMission() {
        return demandeMission;
    }

    public void setDemandeMission(DemandeMissionModel demandeMission) {
        this.demandeMission = demandeMission;
    }

    public VehiculeModel getVehicule() {
        return vehicule;
    }

    public void setVehicule(VehiculeModel vehicule) {
        this.vehicule = vehicule;
    }

    public AgentModel getChauffeur() {
        return chauffeur;
    }

    public void setChauffeur(AgentModel chauffeur) {
        this.chauffeur = chauffeur;
    }

    public AffectationModel() {
        super();
    }

    public AffectationModel(String etat, Date dateAffectation, Date dateDebut, Date dateFin, DemandeMissionModel demandeMission, VehiculeModel vehicule, AgentModel chauffeur) {
        this.etat = etat;
        this.dateAffectation = dateAffectation;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.demandeMission = demandeMission;
        this.vehicule = vehicule;
        this.chauffeur = chauffeur;
    }

    public AffectationModel(Long idAffectation, String etat, Date dateAffectation, Date dateDebut, Date dateFin, DemandeMissionModel demandeMission, VehiculeModel vehicule, AgentModel chauffeur) {
        this.idAffectation = idAffectation;
        this.etat = etat;
        this.dateAffectation = dateAffectation;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.demandeMission = demandeMission;
        this.vehicule = vehicule;
        this.chauffeur = chauffeur;
    }
    
    
}
