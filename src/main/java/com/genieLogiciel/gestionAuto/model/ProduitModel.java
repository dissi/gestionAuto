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
@Table(name="produit")
public class ProduitModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduit;
    @Column(name = "typeProduit", length = 128, nullable = false)
    private String typeProduit;
    @Column(name = "libelle", length = 128, nullable = false)
    private String libelle;

    public Long getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(Long idProduit) {
        this.idProduit = idProduit;
    }

    public String getTypeProduit() {
        return typeProduit;
    }

    public void setTypeProduit(String typeProduit) {
        this.typeProduit = typeProduit;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public ProduitModel() {
        super();
    }
    public ProduitModel(String typeProduit, String libelle) {
        this.typeProduit = typeProduit;
        this.libelle = libelle;
    }
    
}
