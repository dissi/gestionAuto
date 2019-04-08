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
@Table(name="zone", uniqueConstraints = { //
                @UniqueConstraint(name = "nomZone_UK", columnNames = "nomZone") })
public class ZoneModel {
    @Id
    @Column(name = "idZone")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idZone;
    @Column(name = "nomZone", length = 128, nullable = false)
    private String nomZone;

    public long getIdZone() {
        return idZone;
    }

    public void setIdZone(long idZone) {
        this.idZone = idZone;
    }

    public String getNomZone() {
        return nomZone;
    }

    public void setNomZone(String nomZone) {
        this.nomZone = nomZone;
    }

    public ZoneModel() {
        super();
    }

    public ZoneModel(String nomZone) {
        this.nomZone = nomZone;
    }

    public ZoneModel(long idZone, String nomZone) {
        this.idZone = idZone;
        this.nomZone = nomZone;
    }
    
    
     
    
}
