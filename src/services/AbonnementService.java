/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Abonnement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import utils.MyDB;

/**
 *
 * @author User
 */
public class AbonnementService {
    Connection connexion;
    Statement stm;
    private int nb1, nb2, nb3, nb4;

    
    public AbonnementService() {
        connexion = MyDB.getInstance().getConnexion();
    }
    public void ajouterAbonnement(Abonnement a) throws SQLException {
      /*  String req = "INSERT INTO `abonnement` (`nom`, `categories`,`description`,`prix`) VALUES ( '"
                + a.getNom() + "', '" + a.getCategorie() + "', '" + a.getDescription() + "', '"+ a.getPrix() + "') ";
       
        stm = connexion.createStatement();
        stm.executeUpdate(req);*/
        String requete2 = "INSERT INTO abonnement (nom,categories,description,prix,photo) "
                 + "VALUES(?,?,?,?,?)";
        
        try {
           PreparedStatement ps = connexion.prepareStatement(requete2);  //utilise pour les requete dynamique
           ps.setString(1, a.getNom());
        ps.setString(2, a.getCategorie());
        ps.setString(3, a.getDescription());
        ps.setFloat(4, a.getPrix());
       ps.setString(5, a.getPhoto());

           ps.executeUpdate();
           
           System.out.println("votre abonnement est ajoute");
        
        
        } catch (SQLException ex) {
             System.err.println(ex.getMessage());
        }

    }
      public void ajouterAbonnementAb(Abonnement a) throws SQLException {
        String req = "INSERT INTO `abonnement` (`nom`, `categories`,`description`,`prix`,`photo`) "
                + "VALUES ( ?, ?,?,?) ";
        PreparedStatement ps = connexion.prepareStatement(req);
        ps.setString(1, a.getNom());
        ps.setString(2, a.getCategorie());
        ps.setString(3, a.getDescription());
        ps.setFloat(4, a.getPrix());
        ps.setString(5, a.getPhoto());

        ps.executeUpdate();
    }
        public List<Abonnement> afficherAbonnement() throws SQLException {
        List<Abonnement> abonnements = new ArrayList<>();
        String req = "select * from abonnement";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
           Abonnement a = new Abonnement();
           
           a.setCategorie(rst.getString("categories"));
           a.setId(rst.getInt("id"));
           a.setDescription(rst.getString("description"));
           a.setNom(rst.getString("nom"));
           a.setPrix(rst.getFloat("prix"));
           a.setPhoto(rst.getString("photo"));
                            
            abonnements.add(a);
        }
        return abonnements;
    }
         public void SupprimerAbonnement(Abonnement a) throws SQLException {
        //String req1 = "DELETE FROM `offre` WHERE nom_abonnement_id='"+a+"' ";
       String req ="delete from abonnement where id= ?";

        PreparedStatement pst=connexion.prepareStatement(req);
             int id = a.getId();
             pst.setInt(1,id);
             pst.executeUpdate();

   /* String req = "DELETE FROM `abonnement` WHERE id='"+a+"' ";
       
        stm = connexion.createStatement();
        stm.executeUpdate(req);*/
    }
 public void ModifierAbonnementAb(Abonnement a) throws SQLException {
          String req = "UPDATE `abonnement` SET `nom`='"+a.getNom()+"',`categories`='"+a.getCategorie()+"',`description`='"+a.getDescription()+"',`prix`='"+a.getPrix()+"',`photo`='"+a.getPhoto()+"' WHERE id= "+a.getId();
        PreparedStatement ps = connexion.prepareStatement(req);
       
        ps.executeUpdate();
    }
       public ObservableList<Abonnement> getRepasList() throws SQLException {
           
        ObservableList<Abonnement> abo = FXCollections.observableArrayList();
        
         List <Abonnement> id = new ArrayList<>(); 
        Statement stm = connexion.createStatement();
        String query = "select * from abonnement";

        ResultSet rst = stm.executeQuery(query);
        rst = stm.executeQuery(query);
        Abonnement repas;
        while (rst.next()) {
            Abonnement a = new Abonnement();
           
           a.setCategorie(rst.getString("categories"));
           a.setId(rst.getInt("id"));
           a.setDescription(rst.getString("description"));
           a.setNom(rst.getString("nom"));
           a.setPrix(rst.getFloat("prix"));
           a.setPhoto(rst.getString("photo"));
                            
            abo.add(a);
        
        }
        return abo;

    }
        public List <Abonnement> liste2() throws SQLException
    {
        String req = "select * from abonnement"; 
        
       List <Abonnement> list = new ArrayList<>(); 
       
        stm = connexion.createStatement();
       //rs=ste.executeQuery(req); 
         ResultSet rst = stm.executeQuery(req);
        rst = stm.executeQuery(req);
       
       while (rst.next())
       {
  Abonnement a = new Abonnement();
           
           a.setCategorie(rst.getString("categories"));
           a.setId(rst.getInt("id"));
           a.setDescription(rst.getString("description"));
           a.setNom(rst.getString("nom"));
           a.setPrix(rst.getFloat("prix"));
           a.setPhoto(rst.getString("photo"));
                            
            list.add(a);       }
       
       

    return list; 
    }
          public List <Abonnement> tri() throws SQLException
    {
        Statement stm = connexion.createStatement(); 
        String query= "SELECT * from abonnement ORDER BY nom";
        
               List <Abonnement> list = new ArrayList<>(); 

        
         Abonnement r = new Abonnement();
          ResultSet rs = stm.executeQuery(query);
            rs = stm.executeQuery(query);
            while (rs.next())
       {
  Abonnement a = new Abonnement();
           
           a.setCategorie(rs.getString("categories"));
           a.setId(rs.getInt("id"));
           a.setDescription(rs.getString("description"));
           a.setNom(rs.getString("nom"));
           a.setPrix(rs.getFloat("prix"));
           a.setPhoto(rs.getString("photo"));
                            
            list.add(a);
       }     
       return list;
    }
           public int pendingType() throws SQLException {
        String req = "SELECT count(id) from abonnement where categories ='simple';";
        
           stm = connexion.createStatement();
       //rs=ste.executeQuery(req); 
         ResultSet rst = stm.executeQuery(req);
    

        while (rst.next()) {
            nb1 = rst.getInt(1);
        }
        return nb1;
    }
       public int confirmedType() throws SQLException {
        String req = "SELECT count(id) from abonnement where categories ='couple';";
        
        stm = connexion.createStatement();
       //rs=ste.executeQuery(req); 
         ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            nb2 = rst.getInt(1);
        }
        return nb2;
    }
       public int cancelledType() throws SQLException {
        //String req = "SELECT COUNT(*),categories FROM abonnement GROUP BY categories";
               String req = "SELECT count(id) from abonnement where categories ='enfant';";

         stm = connexion.createStatement();
       //rs=ste.executeQuery(req); 
         ResultSet rst = stm.executeQuery(req);
         
        while (rst.next()) {
            nb3 = rst.getInt(1);
            
        }
        return nb3;
    }

    public void add(PieChart.Data data) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
