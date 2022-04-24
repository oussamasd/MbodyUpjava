/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javafx.scene.chart.PieChart;
import javafx.scene.image.ImageView;

/**
 *
 * @author User
 */
public class Abonnement {
    public static String pathfile; 
    public static String filename="";
    private ImageView imgv;
    private int id; 
    private String nom; 
    private String categorie; 
    private String description;
    private String photo;

    private float  prix; 

    public ImageView getImgv() {
        return imgv;
    }

    public void setImgv(ImageView imgv) {
        this.imgv = imgv;
    }
    
    
     public Abonnement() {
       
    }

    public Abonnement(int id, String nom, String categorie, String description, float prix,String photo) {
        this.id = id;
        this.nom = nom;
        this.categorie = categorie;
        this.description = description;
        this.prix = prix;
        this.photo = photo;

    }
      public Abonnement( String nom, String categorie, String description, float prix,String photo) {
        this.nom = nom;
        this.categorie = categorie;
        this.description = description;
        this.prix = prix;
        this.photo = photo;

    }



    public Abonnement(String text, String text0, String text1) {
         //To change body of generated methods, choose Tools | Templates.
    }

  
    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getCategorie() {
        return categorie;
    }

    public String getDescription() {
        return description;
    }

    public float getPrix() {
        return prix;
    }
      public String getPhoto() {
        return photo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }
  

    public void setPhoto(String photo) {
        this.photo = photo;
    }
    @Override
    public String toString() {
        return id+"_"+ nom;
    }

    public void add(PieChart.Data data) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     
}
