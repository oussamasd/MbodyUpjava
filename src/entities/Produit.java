/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;

/**
 *
 * @author user
 */
public class Produit {
    public static String pathfile;
    public static String filename="";
    private int id;
    private String nom;
    private int prix;
    private int quantite;
    private String photo;
    private int cat_id;

    public int getCat_id() {
        return cat_id;
    }

    public void setCat_id(int cat_id) {
        this.cat_id = cat_id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
     

    public Produit() {
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

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Produit(String nom, int prix, int quantite) {
        this.nom = nom;
        this.prix = prix;
        this.quantite = quantite;
         
    }

    public Produit(int id, String nom, int prix, int quantite , String photo, int cat_id) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.quantite = quantite;
        this.photo = photo;
        this.cat_id = cat_id;
        
    }

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", nom=" + nom + ", prix=" + prix + ", quantite=" + quantite + ", photo=" + photo + ", cat_id=" + cat_id + '}';
    }
    
    public String Sres()
    {
        return "Nom : "+nom+"\n prix :"+prix+"" ;
    }

   
    
    
}
