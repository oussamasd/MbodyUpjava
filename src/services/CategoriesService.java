/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Categories;
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
 * @author User
 */
public class CategoriesService {
    
    Connection connexion;
    Statement stm;
    
    public CategoriesService() {
        connexion = MyDB.getInstance().getConnexion();
    }
    public void ajouterCategories(Categories c) throws SQLException {
        String req = "INSERT INTO `categories` () VALUES ( '15', '"
                + c.getNom()+ "', '" + c.getType()+ "', '" + c.getDescription() + "', '" + "') ";
       
        stm = connexion.createStatement();
        stm.executeUpdate(req);

    }
      public void ajouterCategoriesCa(Categories c) throws SQLException {
        String req = "INSERT INTO `categories` ( `nom`, `type`, `description`) "
                + "VALUES (? ,?, ?) ";
        PreparedStatement ps = connexion.prepareStatement(req);
      
        ps.setString(1, c.getNom());
        ps.setString(2, c.getType());
        ps.setString(3, c.getDescription());
       
        ps.executeUpdate();
    }
        public List<Categories> afficherCategories() throws SQLException {
        List<Categories> categorieses = new ArrayList<>();
        String req = "select * from categories";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
           Categories c = new Categories();
           
        
          
           c.setId(rst.getInt("id"));
           c.setNom(rst.getString("nom"));
           c.setType(rst.getString("type"));
            c.setDescription(rst.getString("description"));
          
                          
            categorieses.add(c);
        }
        return categorieses;
    }
         public void SupprimerCategories(Categories o) throws SQLException {
    
              String req ="delete from categories where id= ?";

        PreparedStatement pst=connexion.prepareStatement(req);
             int id = o.getId();
             pst.setInt(1,id);
             pst.executeUpdate();
  
     
    }
          public void ModifierCategories(Categories o) throws SQLException {
          String req = "UPDATE `categories` SET `nom`='"+o.getNom()+"',`type`='"+o.getType()+"',`description`='"+o.getDescription()+"' WHERE id= "+o.getId();
        PreparedStatement ps = connexion.prepareStatement(req);
        ps.executeUpdate();
    }
          
           /*  public List<Offre> refreshOffre(){
         List<Offre>myList1 = new ArrayList<>();

        try{
        String requete3 = "SELECT * FROM offre";
        Statement st = connexion.createStatement();
        ResultSet rst =  st.executeQuery(requete3);
            while(rst.next()){
                Offre o = new Offre();
            //  o.setAbonnement(rst.getInt("nom_abonnement_id"));
           o.setDescription(rst.getString("description"));
           o.setId(rst.getInt("id"));
           o.setDelai(rst.getString("delai"));
           o.setNom(rst.getString("nom"));

                myList1.add(o);}   
              
          } catch (SQLException ex) {
              System.out.println(ex.getMessage());
          };
          return myList1;
    }*/
         
}
