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
@Table(name="fonction")
public class FonctionModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private long idFonction;
   private String libelle;

    public long getIdFonction() {
        return idFonction;
    }

    public void setIdFonction(Long idFonction) {
        this.idFonction = idFonction;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public FonctionModel() {
        super();
    }

    public FonctionModel(String libelle) {
        this.libelle = libelle;
    }

    public FonctionModel(long idFonction, String libelle) {
        this.idFonction = idFonction;
        this.libelle = libelle;
    }
    
    
}
