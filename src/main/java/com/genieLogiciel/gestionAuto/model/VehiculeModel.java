/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.genieLogiciel.gestionAuto.model;

import javax.persistence.*;

/**
 *
 * @author dissirama
 */
@Entity
@Table(name="vehicule")
public class VehiculeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVehicule;
    private String immatriculation;
    private String typeVehicule;
    private String typecarburant;
    private String etat;
    private double consomMoyenne;
    private double charge;

    public Long getIdVehicule() {
        return idVehicule;
    }

    public void setIdVehicule(Long idVehicule) {
        this.idVehicule = idVehicule;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public String getTypeVehicule() {
        return typeVehicule;
    }

    public void setTypeVehicule(String typeVehicule) {
        this.typeVehicule = typeVehicule;
    }

    public String getTypecarburant() {
        return typecarburant;
    }

    public void setTypecarburant(String typecarburant) {
        this.typecarburant = typecarburant;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public double getConsomMoyenne() {
        return consomMoyenne;
    }

    public void setConsomMoyenne(double consomMoyenne) {
        this.consomMoyenne = consomMoyenne;
    }

    public double getCharge() {
        return charge;
    }

    public void setCharge(double charge) {
        this.charge = charge;
    }

    public VehiculeModel() {
        super();
    }

    public VehiculeModel(String immatriculation, String typeVehicule, String typecarburant, String etat, double consomMoyenne, double charge) {
        this.immatriculation = immatriculation;
        this.typeVehicule = typeVehicule;
        this.typecarburant = typecarburant;
        this.etat = etat;
        this.consomMoyenne = consomMoyenne;
        this.charge = charge;
    }
    
    
}
