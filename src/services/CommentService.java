/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Activitie;
import entities.Comment;
import entities.Exercice;
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
 * @author oussa
 */
public class CommentService {
    Connection connexion;
    Statement stm;

    public CommentService() {
        connexion = MyDB.getInstance().getConnexion();
    }
    
    public void ajouterCom(Comment p) throws SQLException {
      String req = "INSERT INTO `comt` (`activity_id`, `text`, `date`,`id_user`) "
                + "VALUES ( ?, ?,?,?) ";
        PreparedStatement ps = connexion.prepareStatement(req);
        ps.setInt(1, p.getActivity_id());
        ps.setString(2, p.getText());
        ps.setString(3, p.getDt());
        ps.setInt(4, p.getId_user());
       
        ps.executeUpdate();
        
    }
       public List<Comment> getCmtAct(int actid) throws SQLException {
        List<Comment> cmts = new ArrayList<>();
        String req = "SELECT * FROM `comt` WHERE activity_id="+actid;
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
           Comment c = new Comment();
           c.setId(rst.getInt("id"));
           c.setId_user(rst.getInt("id_user"));
           c.setText(rst.getString("text"));
           c.setDt(rst.getString("date"));
           c.setActivity_id(rst.getInt("activity_id"));
           cmts.add(c);
           
                    
                 
                
            
            }
                    
        
            
            
                   return cmts;

        }
          public void DeleteCmt(int idact) throws SQLException {
        
        String req = "DELETE FROM `comt` WHERE activity_id ="+idact;
        stm = connexion.createStatement();
        stm.executeUpdate(req);

        
        
    }
    
    
}
