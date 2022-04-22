/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Activitie;
import entities.Exercice;
import entities.ImageAct;
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
 * @author oussa
 */
public class ImageActService {
    Connection connexion;
    Statement stm;

    public ImageActService() {
        connexion = MyDB.getInstance().getConnexion();
    }

    
  

    public void ajouterImage(ImageAct p) throws SQLException {
        String req = "INSERT INTO `image_act_ex`(`image_url`, `activity_id`) "
                + "VALUES ( ?, ?) ";
        PreparedStatement ps = connexion.prepareStatement(req);
        ps.setString(1, p.getUrl());
        ps.setInt(2, p.getIdact());
        ps.executeUpdate();
    }

    
    public List<ImageAct> afficherImage(Activitie act) throws SQLException {
        List<ImageAct> images = new ArrayList<>();
        String req = "select * from image_act_ex WHERE activity_id ="+act.getId();
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            ImageAct p = new ImageAct();
            
                    
            p.setId(rst.getInt("id"));
            p.setIdact(rst.getInt("activity_id"));
            p.setUrl(rst.getString("image_url"));
            
            
            
            
            images.add(p);
        }
        return images;
    }
    
     public void DeleteImage(int idact) throws SQLException {
        
        String req = "DELETE FROM `image_act_ex` WHERE activity_id ="+idact;
        stm = connexion.createStatement();
        stm.executeUpdate(req);

        
        
    }
     
    
    
}
