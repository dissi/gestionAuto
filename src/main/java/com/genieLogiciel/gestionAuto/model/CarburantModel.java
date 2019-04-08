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
@Table(name = "carburant")
public class CarburantModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCarburant;
    private String libelle;
    private double prix;

    public Long getIdCarburant() {
        return idCarburant;
    }

    public void setIdCarburant(Long idCarburant) {
        this.idCarburant = idCarburant;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public CarburantModel() {
        super();
    }

    public CarburantModel(String libelle, double prix) {
        this.libelle = libelle;
        this.prix = prix;
    }
    
    
}
