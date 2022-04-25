/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author oussa
 */
public class users {
    int id ;
    int cin;
    String prenom;
    String nom;
    String email;
    String password ;
    String adresse;
    int numero ;
    String role;

    public int getId() {
        return id;
    }

    public int getCin() {
        return cin;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNom() {
        return nom;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getAdresse() {
        return adresse;
    }

    public int getNumero() {
        return numero;
    }

    public String getRole() {
        return role;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "users{" + "id=" + id + ", cin=" + cin + ", prenom=" + prenom + ", nom=" + nom + ", email=" + email + ", password=" + password + ", adresse=" + adresse + ", numero=" + numero + ", role=" + role + '}';
    }
    public users()
    {
    }
    
}
