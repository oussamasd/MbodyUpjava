/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Exercice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import utils.MyDB;

/**
 *
 * @author macbook
 */
public class ExerciceService implements IExercice<Exercice> {

    Connection connexion;
    Statement stm;

    public ExerciceService() {
        connexion = MyDB.getInstance().getConnexion();
    }

    @Override
    public void ajouterEx(Exercice p) throws SQLException {
        String req = "INSERT INTO `exercice` (`nom_exercice`, `dure_exercice`, `description_exercice`, `category_id`) VALUES ( '"
                + p.getNom_Exercice() + "', '" + p.getDure_Exercice() +"', '" +p.getDescription_Exercice()+"', '"+ p.getCategory().getId()+ "') ";
        stm = connexion.createStatement();
        stm.executeUpdate(req);

    }

    public void ajouterProduitPs(Exercice p) throws SQLException {
        String req = "INSERT INTO `personne` (`nom`, `prenom`) "
                + "VALUES ( ?, ?) ";
        PreparedStatement ps = connexion.prepareStatement(req);
       // ps.setString(1, p.getNom());
        //ps.setString(2, p.getPrenom());
        ps.executeUpdate();
    }

    @Override
    public List<Exercice> afficherexercice() throws SQLException {
        List<Exercice> Exercices = new ArrayList<>();
        String req = "select * from exercice";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Exercice p = new Exercice();
            
                    
            p.setId(rst.getInt("id"));
            p.setDescription_Exercice(rst.getString("description_exercice"));
            p.setDure_Exercice(rst.getString("dure_exercice"));
            p.setNom_Exercice(rst.getString("nom_exercice"));
            
            
            
            Exercices.add(p);
        }
        return Exercices;
    }
     public List<Exercice> getExerciceAct(int actid) throws SQLException {
        List<Exercice> Exercices = new ArrayList<>();
        String req = "SELECT * FROM `activity_exercice` WHERE activity_id="+actid;
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            for(Exercice ex : afficherexercice())
            {
                if(ex.getId()==rst.getInt("exercice_id")){
                    
                 Exercices.add(ex);
                }
            
            }
                    
         /*   p.setId(rst.getInt("id"));
            p.setDescription_Exercice(rst.getString("description_exercice"));
            p.setDure_Exercice(rst.getString("dure_exercice"));
            p.setNom_Exercice(rst.getString("nom_exercice"));*/
            
            
            
           
        }
        return Exercices;
    }
     
     public void DeleteExerciceAct(int idact) throws SQLException {
        
        String req = "DELETE FROM `activity_exercice` WHERE activity_id ="+idact;
        stm = connexion.createStatement();
        stm.executeUpdate(req);

        
        
    }
     public void DeleteExercic(int idex) throws SQLException {
         String req1 = "DELETE FROM `activity_exercice` WHERE exercice_id ="+idex;
        stm = connexion.createStatement();
        stm.executeUpdate(req1);
        
        String req = "DELETE FROM `exercice` WHERE id ="+idex;
        stm = connexion.createStatement();
        stm.executeUpdate(req);

        
        
    }
    
}
