/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import entities.users;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author oussa
 */

public class UserService {
    /*
    Connection connexion;
     Statement stm;
     public void ajouterusers(users u) throws SQLException {
        String req = "INSERT INTO `user` (`nom`, `prenom`, `cin`, `email`, `password`, `numero`,`adresse`) "
                + "VALUES ( ?, ?,?,?,?,?,?) ";
        PreparedStatement ps = connexion.prepareStatement(req);
        ps.setString(1,u.getNom() );
        ps.setString(2,u.getPrenom() );
        ps.setInt(3, u.getCin());
        ps.setString(4, u.getEmail());
        ps.setString(5, u.getPassword());
        ps.setInt(6,u.getNumero());
        ps.setString(7, u.getAdresse());
        ps.executeUpdate();
        
      
    }

    
    public List<users> afficherusers() throws SQLException {
        List<users> resulta = new ArrayList<>();
        String req = "select * from activity";
        
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);
        ExerciceService ex = new ExerciceService();
        while (rst.next()) {
            users u = new users();
            
               u.setExercices(ex.getExerciceAct(rst.getInt("id")));
            u.setId(rst.getInt("id"));
            u.setNom_Act(rst.getString("nom_act"));
            u.setDescription_Act(rst.getString("description_act"));
           //p.setCategory(category);
           //p.setImages(images);
           u.setDate_Act(rst.getString("date_act"));
           u.setTemp_act(rst.getString("temp_act"));
           u.setQuantity(rst.getInt("quantite"));
          
            
            
            resulta.add(u);
        }
        return resulta;
    }
    
    */
}
