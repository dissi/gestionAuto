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
@Table(name="entrepot")
public class EntrepotModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEntrepot;
    private String nomEntrepot;
    private String lieu;

    public Long getIdEntrepot() {
        return idEntrepot;
    }

    public void setIdEntrepot(Long idEntrepot) {
        this.idEntrepot = idEntrepot;
    }

    public String getNomEntrepot() {
        return nomEntrepot;
    }

    public void setNomEntrepot(String nomEntrepot) {
        this.nomEntrepot = nomEntrepot;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public EntrepotModel() {
        super();
    }

    public EntrepotModel(String nomEntrepot, String lieu) {
        this.nomEntrepot = nomEntrepot;
        this.lieu = lieu;
    }
    
    
    
}
