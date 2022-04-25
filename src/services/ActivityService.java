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
 * @author macbook
 */
public class ActivityService  {

    Connection connexion;
    Statement stm;

    public ActivityService() {
        connexion = MyDB.getInstance().getConnexion();
    }

    
    public void ajouterEx(Exercice p) throws SQLException {
        String req = "INSERT INTO `personne` (`nom`, `prenom`) VALUES ( '"
                + p.getNom_Exercice() + "', '" + p.getDure_Exercice() + "') ";
        stm = connexion.createStatement();
        stm.executeUpdate(req);

    }

    public void ajouterActivity(Activitie p) throws SQLException {
        ImageActService ims = new ImageActService();
        String req = "INSERT INTO `activity` (`nom_act`, `date_act`, `temp_act`, `description_act`, `category_id`, `quantite`) "
                + "VALUES ( ?, ?,?,?,?,?) ";
        PreparedStatement ps = connexion.prepareStatement(req);
        ps.setString(1, p.getNom_Act());
        ps.setString(2, p.getDate_Act());
        ps.setString(3, p.getTemp_act());
        ps.setString(4, p.getDescription_Act());
        ps.setInt(5, p.getCategory().getId());
        ps.setInt(6, p.getQuantity());
        ps.executeUpdate();
        
        for(Exercice ex : p.getExercices()){
            String reqEx = "INSERT INTO `activity_exercice`(`activity_id`, `exercice_id`) VALUES ('"+backlastid()+"','"+ex.getId()+"')";
            stm = connexion.createStatement();
            stm.executeUpdate(reqEx);
        
        }
         for(ImageAct img : p.getImages()){
             img.setIdact(backlastid());
          ims.ajouterImage(img);
        
        }
    }
     public void SupprimerActivity(Activitie p) throws SQLException {
         ExerciceService exs = new ExerciceService();
         ImageActService ims = new ImageActService();
         if(p.getExercices()!=null){
            for(Exercice e : p.getExercices()){
                exs.DeleteExerciceAct(p.getId());
             
         
            }
         }
          if(p.getImages()!=null){
            for(ImageAct im : p.getImages()){
              
             ims.DeleteImage(p.getId());
             
         
         }
         }
         
          
          
            String req = "DELETE FROM `activity` WHERE id ="+p.getId();
        stm = connexion.createStatement();
        stm.executeUpdate(req);

         
         
         
         
         
         
      
    }
      public void modifierActivity(Activitie p) throws SQLException {
        ImageActService ims = new ImageActService();
        String req = "UPDATE `activity` SET `nom_act`='"+p.getNom_Act()+"',`date_act`='"+p.getDate_Act()+"',`temp_act`='"+p.getTemp_act()+"',`description_act`='"+p.getDescription_Act()+"',`category_id`='"+p.getCategory().getId()+"',`quantite`='"+p.getQuantity()+"' WHERE id= "+p.getId();
           stm = connexion.createStatement();
        stm.executeUpdate(req);
        /*
        for(Exercice ex : p.getExercices()){
            String reqEx = "INSERT INTO `activity_exercice`(`activity_id`, `exercice_id`) VALUES ('"+backlastid()+"','"+ex.getId()+"')";
            stm = connexion.createStatement();
            stm.executeUpdate(reqEx);
        
        }
         for(ImageAct img : p.getImages()){
             img.setIdact(backlastid());
          ims.ajouterImage(img);
        
        }
*/
    }

    
    public List<Activitie> afficheractivities() throws SQLException {
        List<Activitie> resulta = new ArrayList<>();
        String req = "select * from activity";
        
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);
        ExerciceService ex = new ExerciceService();
        while (rst.next()) {
            Activitie p = new Activitie();
            
               p.setExercices(ex.getExerciceAct(rst.getInt("id")));
            p.setId(rst.getInt("id"));
            p.setNom_Act(rst.getString("nom_act"));
            p.setDescription_Act(rst.getString("description_act"));
           //p.setCategory(category);
           //p.setImages(images);
           p.setDate_Act(rst.getString("date_act"));
           p.setTemp_act(rst.getString("temp_act"));
           p.setQuantity(rst.getInt("quantite"));
          
            
            
            resulta.add(p);
        }
        return resulta;
    }
    
    public int backlastid() throws SQLException{
    int last = 0;
     String req = "select * from activity";
        
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);
        while (rst.next()) {
         if(rst.last())
           {
           
               last=rst.getInt("id");
           }
            
            
        
        }
    
    return last;
    }
}
