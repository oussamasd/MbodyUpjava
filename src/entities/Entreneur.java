/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Lounga
 */
public class Entreneur {
    private int id;
    private String nom;
    private String prenom;
    private String age;
    private String type;
    private int categ_id;
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getCateg_id() {
        return categ_id;
    }

    public void setCateg_id(int categ_id) {
        this.categ_id = categ_id;
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Entreneur() {
    }

    @Override
    public String toString() {
        return "entreneur{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", age=" + age + ", type=" + type + ", categ_id=" + categ_id + ", image=" + image + '}';
    }

    public Entreneur(String nom, String prenom, String age, String type, int categ_id, String image) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.type = type;
        this.categ_id = categ_id;
        this.image = image;
    }

    public Entreneur(int id, String nom, String prenom, String age, String type, int categ_id, String image) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.type = type;
        this.categ_id = categ_id;
        this.image = image;
    }



}
