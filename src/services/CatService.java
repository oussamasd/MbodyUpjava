/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Category;
import entities.Exercice;
import java.sql.Connection;
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
public class CatService {
    Connection connexion;
    Statement stm;

    public CatService() {
        connexion = MyDB.getInstance().getConnexion();
    }
        public List<Category> affichercat() throws SQLException {
        List<Category> Categories = new ArrayList<>();
        String req = "select * from category";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Category p = new Category();
            
                    
            p.setId(rst.getInt("id"));
            p.setNom_Cat(rst.getString("nom_cat"));
            
            
            
            Categories.add(p);
        }
        return Categories;
    }
          public Category uncat(int catid) throws SQLException {
        
        String req = "select * from category where id ="+catid;
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

            Category p = new Category();
              while (rst.next()) {
            p.setId(rst.getInt("id"));
            p.setNom_Cat(rst.getString("nom_cat"));
              }
        return p;
    }
           public int countCat(int idcat) throws SQLException {
        int cnt = 0;
        String req = "select count(*) from activity where category_id="+idcat;
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
           cnt = rst.getInt("count(*)");
        }
        return cnt;
    }

    
}
