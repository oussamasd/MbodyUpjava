/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Categorie;
import entities.Entreneur;
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
public class entreneurService {
 

/**
 *
 * @author User
 */

       private Connection conn;
       private Statement ste;
       private PreparedStatement pste;
       private int nb1,nb2,nb3;
    public entreneurService() {
        conn = MyDB.getInstance().getConnexion();
    }
    public void ajouterEntraineur (Entreneur a) throws SQLException {
        String req = "INSERT INTO `entreneur`(`id`, `nom`, `prenom`, `age`, `type`, `ncategorie_id`, `image`) VALUES  (?,?,?,?,?,?,?)";
        try {
            pste = conn.prepareStatement(req);
            pste.setInt(1, a.getId());
            pste.setString(2, a.getNom());  
            pste.setString(3, a.getPrenom());
            pste.setString(4, a.getAge());  
            pste.setString(5, a.getType());
            pste.setInt(6, a.getCateg_id());  
            pste.setString(7, a.getImage());
            pste.execute();
            System.out.println("entreneur créée");
        } catch (SQLException ex) {
                       System.out.println("error creee entreneur");
        }
    }         
    public void modifier(Entreneur c,int ref) {
    String req = "UPDATE `entreneur` SET `nom`=?,`prenom`=?,`age`=?,`type`=?,`ncategorie_id`=?,`image`=? WHERE where id="+ref;
        System.out.println(req);
    try {
            pste = conn.prepareStatement(req);
            pste.setString(1, c.getNom());
            pste.setString(2, c.getPrenom());
            pste.setString(3, c.getAge());
            pste.setString(4, c.getType());
            pste.setInt(5, c.getCateg_id());
            pste.setString(6, c.getImage()); 
            
           pste.executeUpdate();
            int rowsUpdated = pste.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("La modification de entreneur a été éffectuée avec succès ");
            }
        } catch (SQLException ex) {
        System.out.println("services.entreneurService.modifier()");        }
    } 
    
    
        public void supprimer(Entreneur a) {
try {
            String req = "DELETE FROM `entreneur` WHERE `entreneur`.`id` = "+ a.getId()+ "";
            pste = conn.prepareStatement(req);
            pste.executeUpdate();
            System.out.println("entreneur supprimé");
        } catch (SQLException ex) {
            Logger.getLogger(Entreneur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        //*********************************************************************************************
            
    public List<Entreneur> afficher() {
List<Entreneur> e = new ArrayList<>();
        String req = "SELECT * FROM `entreneur`;"; 
        try {
            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while(rs.next()){
                Entreneur c = new Entreneur();
                c.setId(rs.getInt("id"));
                c.setNom(rs.getString("nom"));
                c.setPrenom(rs.getString("prenom"));
                c.setAge(rs.getString("age"));
                c.setType(rs.getString("type"));
                c.setCateg_id(rs.getInt("ncategorie_id"));
                c.setImage(rs.getString("image"));
                e.add(c);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Entreneur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return e ;
    }
    
            //*********************************************************************************************

        public List<Entreneur> afficherAGEDESC() {
List<Entreneur> e = new ArrayList<>();
        String req = "SELECT * FROM `entreneur` ORDER BY age DESC"; 
        try {
            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while(rs.next()){
                Entreneur c = new Entreneur();
                c.setId(rs.getInt("id"));
                c.setNom(rs.getString("nom"));
                c.setPrenom(rs.getString("prenom"));
                c.setAge(rs.getString("age"));
                c.setType(rs.getString("type"));
                c.setCateg_id(rs.getInt("ncategorie_id"));
                c.setImage(rs.getString("image"));
                e.add(c);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Entreneur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return e ;
    }
                //*********************************************************************************************
        public List<Entreneur> afficherAGEASC() {
List<Entreneur> e1 = new ArrayList<>();
        String req = "SELECT * FROM `entreneur` ORDER BY age ASC"; 
        try {
            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while(rs.next()){
                Entreneur c = new Entreneur();
                c.setId(rs.getInt("id"));
                c.setNom(rs.getString("nom"));
                c.setPrenom(rs.getString("prenom"));
                c.setAge(rs.getString("age"));
                c.setType(rs.getString("type"));
                c.setCateg_id(rs.getInt("ncategorie_id"));
                c.setImage(rs.getString("image"));
                e1.add(c);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Entreneur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return e1 ;
    }
    
        
            //*********************************************************************************************

        public List<Entreneur> afficherNOMDESC() {
List<Entreneur> e = new ArrayList<>();
        String req = "SELECT * FROM `entreneur` ORDER BY nom DESC"; 
        try {
            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while(rs.next()){
                Entreneur c = new Entreneur();
                c.setId(rs.getInt("id"));
                c.setNom(rs.getString("nom"));
                c.setPrenom(rs.getString("prenom"));
                c.setAge(rs.getString("age"));
                c.setType(rs.getString("type"));
                c.setCateg_id(rs.getInt("ncategorie_id"));
                c.setImage(rs.getString("image"));
                e.add(c);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Entreneur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return e ;
    }
                //*********************************************************************************************
        public List<Entreneur> afficherNOMASC() {
List<Entreneur> e1 = new ArrayList<>();
        String req = "SELECT * FROM `entreneur` ORDER BY nom ASC"; 
        try {
            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while(rs.next()){
                Entreneur c = new Entreneur();
                c.setId(rs.getInt("id"));
                c.setNom(rs.getString("nom"));
                c.setPrenom(rs.getString("prenom"));
                c.setAge(rs.getString("age"));
                c.setType(rs.getString("type"));
                c.setCateg_id(rs.getInt("ncategorie_id"));
                c.setImage(rs.getString("image"));
                e1.add(c);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Entreneur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return e1 ;
    }
        
            //*********************************************************************************************

        public List<Entreneur> afficherPRENOMDESC() {
List<Entreneur> e = new ArrayList<>();
        String req = "SELECT * FROM `entreneur` ORDER BY prenom DESC"; 
        try {
            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while(rs.next()){
                Entreneur c = new Entreneur();
                c.setId(rs.getInt("id"));
                c.setNom(rs.getString("nom"));
                c.setPrenom(rs.getString("prenom"));
                c.setAge(rs.getString("age"));
                c.setType(rs.getString("type"));
                c.setCateg_id(rs.getInt("ncategorie_id"));
                c.setImage(rs.getString("image"));
                e.add(c);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Entreneur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return e ;
    }
                //*********************************************************************************************
        public List<Entreneur> afficherPRENOMASC() {
List<Entreneur> e1 = new ArrayList<>();
        String req = "SELECT * FROM `entreneur` ORDER BY prenom ASC"; 
        try {
            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while(rs.next()){
                Entreneur c = new Entreneur();
                c.setId(rs.getInt("id"));
                c.setNom(rs.getString("nom"));
                c.setPrenom(rs.getString("prenom"));
                c.setAge(rs.getString("age"));
                c.setType(rs.getString("type"));
                c.setCateg_id(rs.getInt("ncategorie_id"));
                c.setImage(rs.getString("image"));
                e1.add(c);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Entreneur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return e1 ;
    }
    
    
    
    public List<Entreneur> getByid (int id) {
        List<Entreneur> entreneur = new ArrayList<>();
        String req = "select * from entreneur where id='"+id+"';";
        
        try {
           ste = conn.createStatement();
            ResultSet resultSet = ste.executeQuery(req);
            
          
            if (resultSet.next()){
                Entreneur c= new Entreneur();
                c.setId(resultSet.getInt(1)); 
                c.setNom(resultSet.getString(2));
                c.setPrenom(resultSet.getString(3));
                c.setAge(resultSet.getString(4));
                c.setType(resultSet.getString(5));
                c.setCateg_id(resultSet.getInt(6));
                c.setImage(resultSet.getString(7));
                entreneur.add(c);
                System.out.println(c.toString());
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return entreneur;
    }
    public int pendingType() throws SQLException {
        String req = "SELECT count(id) from entreneur where type ='salsa';";
       
           ste = conn.createStatement();
       //rs=ste.executeQuery(req);
         ResultSet rst = ste.executeQuery(req);
   

        while (rst.next()) {
            nb1 = rst.getInt(1);
        }
        return nb1;
    }
       public int confirmedType() throws SQLException {
        String req = "SELECT count(id) from entreneur where type ='boxe';";
       
           ste = conn.createStatement();
       //rs=ste.executeQuery(req);
         ResultSet rst = ste.executeQuery(req);

        while (rst.next()) {
            nb2 = rst.getInt(1);
        }
        return nb2;
    }
       public int cancelledType() throws SQLException {
        //String req = "SELECT COUNT(*),categories FROM abonnement GROUP BY categories";
        String req = "SELECT count(id) from entreneur where type ='cardio';";

        
           ste = conn.createStatement();
       //rs=ste.executeQuery(req);
         ResultSet rst = ste.executeQuery(req);
         
        while (rst.next()) {
            nb3 = rst.getInt(1);
           
        }
        return nb3;
    }

    public String QRdonner(int id) {

     String req = "select * from entreneur where id='"+id+"';";
        String final5 = "";
        try {
            ste = conn.createStatement();
            ResultSet resultSet = ste.executeQuery(req);

            if (resultSet.next()){
                Entreneur c= new Entreneur();
                c.setId(resultSet.getInt(1)); 
                c.setNom(resultSet.getString(2));
                c.setPrenom(resultSet.getString(3));
               // c.setAge(resultSet.getString(4));
               c.setAge(Integer.toString(resultSet.getInt(4)));
                c.setType(resultSet.getString(5));
                c.setCateg_id(resultSet.getInt(6));
                c.setImage(resultSet.getString(7));              
                final5=c.toString();
              
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    
    return final5;
    }


 
}