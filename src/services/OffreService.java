/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Offre;
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
public class OffreService {
    
    Connection connexion;
    Statement stm;
    
    public OffreService() {
        connexion = MyDB.getInstance().getConnexion();
    }
    public void ajouterOffre(Offre o) throws SQLException {
        String req = "INSERT INTO `offre` () VALUES ( '15', '"
                + o.getDelai() + "', '" + o.getNom() + "', '" + o.getDescription() + "', '" + "') ";
       
        stm = connexion.createStatement();
        stm.executeUpdate(req);

    }
      public void ajouterOffreOF(Offre o) throws SQLException {
        String req = "INSERT INTO `offre` (`nom_abonnement_id` ,`delai`, `nom`, `description`) "
                + "VALUES (? ,?, ?,?) ";
        PreparedStatement ps = connexion.prepareStatement(req);
        ps.setInt(1,o.getAbonnement());
        ps.setString(2, o.getDelai());
        ps.setString(3, o.getNom());
        ps.setString(4, o.getDescription());
       
        ps.executeUpdate();
    }
        public List<Offre> afficherOffre() throws SQLException {
        List<Offre> offres = new ArrayList<>();
        String req = "select * from offre";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
           Offre o = new Offre();
           
           o.setAbonnement(rst.getInt("nom_abonnement_id"));
           o.setDescription(rst.getString("description"));
           o.setId(rst.getInt("id"));
           o.setDelai(rst.getString("delai"));
           o.setNom(rst.getString("nom"));
          
                          
            offres.add(o);
        }
        return offres;
    }
         public void SupprimerOffre(Offre o) throws SQLException {
    
              String req ="delete from offre where id= ?";

        PreparedStatement pst=connexion.prepareStatement(req);
             int id = o.getId();
             pst.setInt(1,id);
             pst.executeUpdate();
  
     
    }
          public void ModifierOffre(Offre o) throws SQLException {
          String req = "UPDATE `offre` SET `nom`='"+o.getNom()+"',`description`='"+o.getDescription()+"' WHERE id= "+o.getId();
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
