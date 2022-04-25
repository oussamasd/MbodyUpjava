/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import entities.TypeReclamation;
import java.util.logging.Level;
import java.util.logging.Logger;


import utils.MyDB;

/**
 *
 * @author macbook
 */
public class TypeReclamationService  {

    Connection connexion;
    Statement stm;
    private PreparedStatement pre;

    public TypeReclamationService() {
        connexion = MyDB.getInstance().getConnexion();
    }

    
    public void ajouterType(TypeReclamation p) throws SQLException {
        String req = "INSERT INTO `type_reclamation` (`nom`) VALUES ( '"
                + p.getNom()+ "')";
        stm = connexion.createStatement();
        stm.executeUpdate(req);

    }

    public void ajouterProduitPs(TypeReclamation p) throws SQLException {
        String req = "INSERT INTO `type_reclamation` (`nom`) "
                + "VALUES ( ?) ";
        PreparedStatement ps = connexion.prepareStatement(req);
        ps.setString(1, p.getNom());
        ps.executeUpdate();
    }

    
    public List<TypeReclamation> afficherType() throws SQLException {
        List<TypeReclamation> reclamations = new ArrayList<>();
        String req = "select * from type_reclamation";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            TypeReclamation p = new TypeReclamation();
         
            p.setId(rst.getInt("id"));
            p.setNom( rst.getString("nom"));
           
            reclamations.add(p);
        }
        return reclamations;
    }

    
    public List<TypeReclamation> affichernom() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   public void deleteTypeReclamation(String ref) {
    
           String sql = "delete from type_reclamation where nom = ?";
try {
            pre = connexion.prepareStatement(sql);
            pre.setString(1, (String) ref);
            pre.executeUpdate();
            System.out.println("TypeReclamation supprimé avec succées");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
            
  
}
 public void modifierTypeRec(TypeReclamation h, int id) {
        String req = "update type_reclamation set nom = ?   where id = ?";

        try {
            pre = connexion.prepareStatement(req);
             pre.setInt(1, id);
            pre.setString(2, h.getNom());
            pre.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

}
 
 public boolean update(TypeReclamation t, int id) throws SQLException {
       if(chercher(id)){
       
        pre=connexion.prepareStatement("UPDATE type_reclamation SET   nom =? WHERE id = ?");
         
    pre.setString(1, t.getNom());

  
    
    pre.setInt(2, id);
    pre.executeUpdate();
  pre.executeUpdate();
     
         return true;}
        System.out.println("update invalid: typeRec nexiste pas");
        return false;
    }
 
 public boolean chercher(int id) throws SQLException {
         String req="select * from type_reclamation";
        List<Integer> list = new ArrayList<>();
        
        try {
            stm=connexion.createStatement();
            ResultSet rs = stm.executeQuery(req);
            while(rs.next()){
                list.add(rs.getInt(1));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(TypeReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        return list.contains(id);
    }
 
   public ArrayList<TypeReclamation> TrierParId() {

        ArrayList<TypeReclamation> List = new ArrayList<>();
        try {

//            String requete = "select * from membre where role != 'Admin' ORDER BY id DESC ";
            String req = "select * from type_reclamation ORDER BY nom";
          PreparedStatement pre = connexion.prepareStatement(req);

            ResultSet rs = pre.executeQuery(req);

            while (rs.next()) {
                TypeReclamation m = new TypeReclamation();

               
                m.setNom(rs.getString("nom"));
               
              
               
          

                List.add(m);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return List;
    }
}
