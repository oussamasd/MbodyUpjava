/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Produit;
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
public class ProduitService {
    
    Connection connexion;
    Statement stm;
    
    public ProduitService() {
        connexion = MyDB.getInstance().getConnexion();
    }
    public void ajouterCategories(Produit p) throws SQLException {
        String req = "INSERT INTO `produits` () VALUES ( '15', '"
                + p.getNom()+ "', '" + p.getPrix()+ "', '" + p.getQuantite()+ "', '" + "') ";
       
        stm = connexion.createStatement();
        stm.executeUpdate(req);

    }
      public void ajouterProduitP(Produit c) throws SQLException {
        String req = "INSERT INTO `produits` ( `nom`, `prix`, `quantite`,`photo` ,`nom_cat_id`) "
                + "VALUES (? ,?, ?, ?,?) ";
        PreparedStatement ps = connexion.prepareStatement(req);
      
        ps.setString(1, c.getNom());
        ps.setInt(2, c.getPrix());
        ps.setInt(3, c.getQuantite());
         ps.setString(4, c.getPhoto());
           ps.setInt(5, c.getCat_id());
         
        
       
        ps.executeUpdate();
    }
        public List<Produit> afficherProduit() throws SQLException {
        List<Produit> categorieses = new ArrayList<>();
        String req = "select * from produits";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
           Produit c = new Produit();
           
        
          
           c.setId(rst.getInt("id"));
           c.setCat_id(rst.getInt("nom_cat_id"));
           c.setNom(rst.getString("nom"));
           c.setPrix(rst.getInt("prix"));
            c.setQuantite(rst.getInt("quantite"));
               c.setPhoto(rst.getString("photo"));
             
          
                          
            categorieses.add(c);
        }
        return categorieses;
    }
         public void SupprimerProduit(Produit o) throws SQLException {
    
              String req ="delete from produits where id= ?";

        PreparedStatement pst=connexion.prepareStatement(req);
             int id = o.getId();
             pst.setInt(1,id);
             pst.executeUpdate();
  
     
    }
          public void ModifierProduit(Produit o) throws SQLException {
          String req = "UPDATE `produits` SET `nom`='"+o.getNom()+"',`prix`='"+o.getPrix()+"',`quantite`='"+o.getQuantite()+"' WHERE id= "+o.getId();
        PreparedStatement ps = connexion.prepareStatement(req);
        ps.executeUpdate();
    }
          
           
       public void SupprimerProduitaAll(Produit o) throws SQLException {
    
              String req ="delete from produits where id > 0";

        PreparedStatement pst=connexion.prepareStatement(req);
             int id = o.getId();
             pst.setInt(1,id);
             pst.executeUpdate();
  
     
    }   
}
