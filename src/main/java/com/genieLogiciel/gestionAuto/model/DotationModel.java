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
@Table(name="dotation")
public class DotationModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDotation;
    private String description;
    private double quantite;
    private Date dateDotation;
     @ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
    @JoinColumn(name="vehiculeId")
    private VehiculeModel vehicule;
    
    @ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
    @JoinColumn(name="carburantId")
    private CarburantModel carburant;
    
    public Long getIdDotation() {
        return idDotation;
    }

    public void setIdDotation(Long idDotation) {
        this.idDotation = idDotation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public Date getDateDotation() {
        return dateDotation;
    }

    public void setDateDotation(Date dateDotation) {
        this.dateDotation = dateDotation;
    }

    public VehiculeModel getVehicule() {
        return vehicule;
    }

    public void setVehicule(VehiculeModel vehicule) {
        this.vehicule = vehicule;
    }

    public CarburantModel getCarburant() {
        return carburant;
    }

    public void setCarburant(CarburantModel carburant) {
        this.carburant = carburant;
    }
    
    public DotationModel() {
        super();
    }

    public DotationModel(String description, double quantite, Date dateDotation, VehiculeModel vehicule, CarburantModel carburant) {
        this.description = description;
        this.quantite = quantite;
        this.dateDotation = dateDotation;
        this.vehicule = vehicule;
        this.carburant = carburant;
    }
   
    
    
    
    
}
