/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Reserve;
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
public class ReserveService  {
    
    Connection connexion;
    Statement stm;
    
    public ReserveService() {
        connexion = MyDB.getInstance().getConnexion();
    }
   

    
      public void ajouterProduitP(Reserve c) throws SQLException {
        String req = "INSERT INTO `reserve` ( `nom`, `prix`) "
                + "VALUES (? ,?) ";
        PreparedStatement ps = connexion.prepareStatement(req);
      
        ps.setString(1, c.getNom());
        ps.setInt(2, c.getPrix());
        
         
        
       
        ps.executeUpdate();
    }
        public List<Reserve> afficherReserve() throws SQLException {
        List<Reserve> categorieses = new ArrayList<>();
        String req = "select * from reserve";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
           Reserve c = new Reserve();
           
        
          
           c.setId(rst.getInt("id"));
          
           c.setNom(rst.getString("nom"));
           c.setPrix(rst.getInt("prix"));
          
          
                          
            categorieses.add(c);
        }
        return categorieses;
    }
         public void SupprimerReserve(Reserve o) throws SQLException {
    
              String req ="delete from reserve where id= ?";

        PreparedStatement pst=connexion.prepareStatement(req);
             int id = o.getId();
             pst.setInt(1,id);
             pst.executeUpdate();
  
     
    }
       
           
       public void SupprimerProduitaAll(Reserve o) throws SQLException {
    
              String req ="delete from reserve where id > 0";

        PreparedStatement pst=connexion.prepareStatement(req);
             int id = o.getId();
             pst.setInt(1,id);
             pst.executeUpdate();
  
     
    }   

    public void afficherReserve(Reserve a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
