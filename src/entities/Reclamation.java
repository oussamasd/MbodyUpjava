/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author user
 */
public class Reclamation {
    private int id;
    private String nom,description;
    private TypeReclamation typeReclamation;

    public Reclamation() {
    }
    
    public Reclamation(int id, String nom, String description,TypeReclamation typeReclamation) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.typeReclamation=typeReclamation;
    }

    public Reclamation(String nom, String description,TypeReclamation typeReclamation) {
        this.nom = nom;
        this.description = description;
         this.typeReclamation=typeReclamation;
    }

    public Reclamation(String nom, String description) {
        this.nom = nom;
        this.description = description;
    }
    

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", nom=" + nom + ", description=" + description + ", typeReclamation=" + typeReclamation + '}';
    }

   

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TypeReclamation getTypeReclamation() {
        return typeReclamation;
    }

    public void setTypeReclamation(TypeReclamation typeReclamation) {
        this.typeReclamation = typeReclamation;
    }

    public Reclamation(String nom) {
        this.nom = nom;
    }

   
    
    
    
}
