/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.genieLogiciel.gestionAuto.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author dissirama
 */
@Entity
@Table(name="agent")
public class AgentModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idAgent;
    @Column(name = "nomAgent")
    private String nom;
    @Column(name = "prenomAgent",length = 150)
    private String prenom;
    @Column(name = "premisAgent",length = 150)
    private String permis;
    @Column(name = "dateNaissance")
    private Date dateNaissance;
    @Column(name = "dateEmbauche")
    private Date dateEmbauche;
     @Column(name = "dateRetraite")
    private Date dateRetraite;
    @ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
    @JoinColumn(name="fonctionId")
    private FonctionModel fonction;
    
    public long getIdAgent() {
        return idAgent;
    }

    public void setIdAgent(long idAgent) {
        this.idAgent = idAgent;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPermis() {
        return permis;
    }

    public void setPermis(String permis) {
        this.permis = permis;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public Date getDateEmbauche() {
        return dateEmbauche;
    }

    public void setDateEmbauche(Date dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
    }

    public Date getDateRetraite() {
        return dateRetraite;
    }

    public void setDateRetraite(Date dateRetraite) {
        this.dateRetraite = dateRetraite;
    }

    public AgentModel() {
        super();
    }

    public FonctionModel getFonction() {
        return fonction;
    }

    public void setFonction(FonctionModel fonction) {
        this.fonction = fonction;
    }
    
    public AgentModel(String nom, String prenom, String permis, Date dateNaissance, Date dateEmbauche, Date dateRetraite, FonctionModel fonction) {
        this.nom = nom;
        this.prenom = prenom;
        this.permis = permis;
        this.dateNaissance = dateNaissance;
        this.dateEmbauche = dateEmbauche;
        this.dateRetraite = dateRetraite;
        this.fonction = fonction;
    }

    public AgentModel(long idAgent, String nom, String prenom, String permis, Date dateNaissance, Date dateEmbauche, Date dateRetraite, FonctionModel fonction) {
        this.idAgent = idAgent;
        this.nom = nom;
        this.prenom = prenom;
        this.permis = permis;
        this.dateNaissance = dateNaissance;
        this.dateEmbauche = dateEmbauche;
        this.dateRetraite = dateRetraite;
        this.fonction = fonction;
    }
    
    
    
    
}
