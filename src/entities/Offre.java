/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author User
 */
public class Offre {
    
     private int abonnement;
    private int id; 
    private String nom; 
    private String description; 
    private String delai;

    public Offre(int abonnement, int id, String nom, String description, String delai) {
        this.abonnement = abonnement;
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.delai = delai;
    }
     public Offre(int abonnement, String nom, String description, String delai) {
        this.abonnement = abonnement;
        this.nom = nom;
        this.description = description;
        this.delai = delai;
    }
      public Offre() {
       
    }

    public Offre(String nom, String description, String delai) {
        this.nom = nom;
        this.description = description;
        this.delai = delai;
    }

     
    public int getAbonnement() {
        return abonnement;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public String getDelai() {
        return delai;
    }

    public void setAbonnement(int abonnement) {
        this.abonnement = abonnement;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDelai(String delai) {
        this.delai = delai;
    }

    @Override
    public String toString() {
        return "Offre{" + "abonnement=" + abonnement + ", id=" + id + ", nom=" + nom + ", description=" + description + ", delai=" + delai + '}';
    }
    
    
    
}