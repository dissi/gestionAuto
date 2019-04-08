/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.genieLogiciel.gestionAuto.model;

import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author dissirama
 */
@Entity
@Table(name = "rotation")
public class RotationModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRotation;
    private String Observation;
    private String etat;
    private Date dateRotation;
    private double chargement;
    
    @ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
    @JoinColumn(name="affectationId")
    private AffectationModel affectation;

    public Long getIdRotation() {
        return idRotation;
    }

    public void setIdRotation(Long idRotation) {
        this.idRotation = idRotation;
    }

    public String getObservation() {
        return Observation;
    }

    public void setObservation(String Observation) {
        this.Observation = Observation;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Date getDateRotation() {
        return dateRotation;
    }

    public void setDateRotation(Date dateRotation) {
        this.dateRotation = dateRotation;
    }

    public double getChargement() {
        return chargement;
    }

    public void setChargement(double chargement) {
        this.chargement = chargement;
    }

    public AffectationModel getAffectation() {
        return affectation;
    }

    public void setAffectation(AffectationModel affectation) {
        this.affectation = affectation;
    }

    public RotationModel() {
        super();
    }

    public RotationModel(String Observation, String etat, Date dateRotation, double chargement, AffectationModel affectation) {
        this.Observation = Observation;
        this.etat = etat;
        this.dateRotation = dateRotation;
        this.chargement = chargement;
        this.affectation = affectation;
    }
    
    
}
