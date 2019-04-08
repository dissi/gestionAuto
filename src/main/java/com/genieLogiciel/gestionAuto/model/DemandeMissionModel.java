/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.genieLogiciel.gestionAuto.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author dissirama
 */
@Entity
@Table(name="demandemission")
public class DemandeMissionModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDemandeMission;
    @Column(name = "objet")
    private String objet;
    @Column(name = "description")
    private String description;
    @Column(name = "etat")
    private String etat;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "datedemande")
    private Date datedemande;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "dateDebut")
    private Date dateDebut;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "dateFin")
    private Date dateFin;
    @Column(name = "quantite")
    private double quantite;
    
    @ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
    @JoinColumn(name="agentId")
    private AgentModel agentM;
    
    @ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
    @JoinColumn(name="zoneId")
    private ZoneModel zone;
    
    @ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
    @JoinColumn(name="entrepotId")
    private EntrepotModel entrepot;
    
    @ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
    @JoinColumn(name="produitId")
    private ProduitModel produit;

    public Long getIdDemandeMission() {
        return idDemandeMission;
    }

    public void setIdDemandeMission(Long idDemandeMission) {
        this.idDemandeMission = idDemandeMission;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Date getDatedemande() {
        return datedemande;
    }

    public void setDatedemande(Date datedemande) {
        this.datedemande = datedemande;
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

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public AgentModel getAgentM() {
        return agentM;
    }

    public void setAgentM(AgentModel agent) {
        this.agentM = agent;
    }

    public ZoneModel getZone() {
        return zone;
    }

    public void setZone(ZoneModel zone) {
        this.zone = zone;
    }

    public EntrepotModel getEntrepot() {
        return entrepot;
    }

    public void setEntrepot(EntrepotModel entrepot) {
        this.entrepot = entrepot;
    }

    public ProduitModel getProduit() {
        return produit;
    }

    public void setProduit(ProduitModel produit) {
        this.produit = produit;
    }

    public DemandeMissionModel() {
        super();
    }

    public DemandeMissionModel(String objet, String description, String etat, Date datedemande, Date dateDebut, Date dateFin, double quantite, AgentModel agent, ZoneModel zone, EntrepotModel entrepot, ProduitModel produit) {
        this.objet = objet;
        this.description = description;
        this.etat = etat;
        this.datedemande = datedemande;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.quantite = quantite;
        this.agentM = agent;
        this.zone = zone;
        this.entrepot = entrepot;
        this.produit = produit;
    }

    public DemandeMissionModel(Long idDemandeMission, String objet, String description, String etat, Date datedemande, Date dateDebut, Date dateFin, double quantite, AgentModel agentM, ZoneModel zone, EntrepotModel entrepot, ProduitModel produit) {
        this.idDemandeMission = idDemandeMission;
        this.objet = objet;
        this.description = description;
        this.etat = etat;
        this.datedemande = datedemande;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.quantite = quantite;
        this.agentM = agentM;
        this.zone = zone;
        this.entrepot = entrepot;
        this.produit = produit;
    }
    
    
    
}
