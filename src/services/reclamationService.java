/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import entities.Reclamation;
import entities.TypeReclamation;
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
 * @author macbook
 */
public class reclamationService implements IPersonne<Reclamation> {

    Connection connexion;
    Statement stm;
      private  Connection con;
     private Statement ste;
         private PreparedStatement pre;

    public reclamationService() {
        connexion = MyDB.getInstance().getConnexion();
    }

    @Override
    public void ajouterp(Reclamation p) throws SQLException {
        String req = "INSERT INTO `reclamation` (`nom`, `description`) VALUES ( '"
                + p.getNom() + "', '" + p.getDescription()+ "')";
        stm = connexion.createStatement();
        stm.executeUpdate(req);

    }

//  @Override
//    public void ajouterp(Reclamation p) throws SQLException {
//        String req = "INSERT INTO `reclamation` (`nom`, `description`,`type_reclamation_id`) VALUES ( '"
//                + p.getNom() + "', '" + p.getDescription()+ "',"+p.getTypeReclamation().getId()+")";
//        stm = connexion.createStatement();
//        stm.executeUpdate(req);
//
//    }
    
    
    
//    public void ajouterProduitPs(Reclamation p) throws SQLException {
//        String req = "INSERT INTO `reclamation` (`nom`, `description`, `type_reclamation_id`) "
//                + "VALUES ( ?, ?, ?) ";
//        PreparedStatement ps = connexion.prepareStatement(req);
//        ps.setString(1, p.getNom());
//        ps.setString(2, p.getDescription());
//        ps.executeUpdate();
//    }

    @Override
    public List<Reclamation> afficherpersonne() throws SQLException {
        List<Reclamation> reclamations = new ArrayList<>();
        String req = "select * from reclamation";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Reclamation p = new Reclamation();
            p.setId(rst.getInt("id"));
            p.setDescription(rst.getString("description"));
            p.setNom( rst.getString("nom"));
           
           
            reclamations.add(p);
        }
        return reclamations;
    }
    
    
      @Override
    public List<Reclamation> affichernom() throws SQLException {
        List<Reclamation> reclamations = new ArrayList<>();
        String req = "select nom from reclamation";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);
        
        while (rst.next()) {
            Reclamation p = new Reclamation();
            
            p.setNom( rst.getString("nom"));
            reclamations.add(p);
        }
        return reclamations;
    }
    
      
  
     public void supprimer(Reclamation v) {

        
            String sql = "delete from reclamation where nom = ?";
try {
            pre = connexion.prepareStatement(sql);
            pre.setString(1, v.getNom());
            pre.executeUpdate();
            System.out.println("Reclamation supprimé avec succées");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
     
     
       public void supprimerReservation(String nom) {

        
            String sql = "delete from reclamation where id = ?";
try {
            pre = connexion.prepareStatement(sql);
            pre.setString(1, (String) nom);
            pre.executeUpdate();
            System.out.println("Reclamation supprimé avec succées");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
       
        public void deleteReclamation(String ref) {
    
           String sql = "delete from reclamation where nom = ?";
try {
            pre = connexion.prepareStatement(sql);
            pre.setString(1, (String) ref);
            pre.executeUpdate();
            System.out.println("Reclamation supprimé avec succées");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
            
        
    
    }
       
      public boolean chercher(String ref)  {
        String req="select * from reclamation";
        List<Integer> list = new ArrayList<>();
        
        try {
            Statement stm = connexion.createStatement();
            ResultSet rs = stm.executeQuery(req);
            while(rs.next()){
                list.add(rs.getInt(2));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(reclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        return list.contains(ref);
    }
       public void modifierRec(Reclamation h) {
        String req = "update reclamation set nom = ? , description = ?   where nom = ?";

        try {
            pre = connexion.prepareStatement(req);
            pre.setString(1, h.getNom());
            pre.setString(2, h.getDescription());
            pre.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

}
}
