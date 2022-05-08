/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Categorie;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.MyDB;

/**
 *
 * @author Lounga
 */
public class categorieService {
//    Connection connexion;
//    Statement stm;
    private Connection conn;
   private Statement ste;
   private PreparedStatement pste;
   
    public categorieService() {
        conn = MyDB.getInstance().getConnexion();
    }
      public void ajouter(Categorie C) {
 String req = "INSERT INTO `categorie`(`type`, `tage`) VALUES (?,?)";//`id`, 
        try {
            pste = conn.prepareStatement(req);
            
            pste.setString(1, C.getType());  
            pste.setString(2, C.getTag());
            pste.execute();
            System.out.println("categorie créée");
        } catch (SQLException ex) {
        System.out.println("error creee categorie");
        }
        

    }
   
          
    public void modifier(Categorie C,int id){
        
      String req = "UPDATE `categorie` set type=?,tage=? where id="+id;//`id`,
        System.out.println(req);
        try {
            pste = conn.prepareStatement(req);
            
            pste.setString(1, C.getType());  
            pste.setString(2, C.getTag());
            pste.execute();
            System.out.println("categorie modifié");
        } catch (SQLException ex) {
        System.out.println("error modification categorie");
        }
        

        
//     String req = "UPDATE `categorie` SET `type`=? , `tage` =? WHERE  `id`"+ c.getId()+ "";  
//    try {
//            pste = conn.prepareStatement(req);
//            pste.setString(1, c.getType());
//            pste.setString(2, c.getTag());
//            pste.executeUpdate();
//            int rowsUpdated = pste.executeUpdate();System.out.println("..test");
//            if (rowsUpdated > 0) {
//                System.out.println("La modification de categorie a été éffectuée avec succès ");
//            }
//        } catch (SQLException ex) {
//          System.out.println("services.categorieService.modifier()");
//        }
    } 
    
        public void supprimer(Categorie C) {
try {
            String req = "DELETE FROM `categorie` WHERE `id`= "+ C.getId()+ "";
            pste = conn.prepareStatement(req);
            pste.executeUpdate();
            System.out.println("categorie supprimé");
        } catch (SQLException ex) {
            System.out.println("categorie non supprimé");
            
        }
    }
        
            
    public List<Categorie> afficher() {
List<Categorie> cat = new ArrayList<>();
        String req = "SELECT * FROM `categorie`"; 
        try {
            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while(rs.next()){
                Categorie c = new Categorie();
                c.setId(rs.getInt("id"));
                c.setType(rs.getString("type"));
                c.setTag(rs.getString("tage"));
                cat.add(c);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Categorie.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cat ;
    }
    
    
    public List<Categorie> getByid (int id) {
        List<Categorie> categorie = new ArrayList<>();
        String req = "select * from categorie where id='"+id+"';";
        
        try {
           ste = conn.createStatement();
            ResultSet resultSet = ste.executeQuery(req);
            
          
            if (resultSet.next()){
                Categorie c= new Categorie();
                c.setId(resultSet.getInt(1)); 
                c.setType(resultSet.getString(2));
                c.setTag(resultSet.getString(2));

                categorie.add(c);
                System.out.println(c.toString());
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return categorie;
    }
    }
    

