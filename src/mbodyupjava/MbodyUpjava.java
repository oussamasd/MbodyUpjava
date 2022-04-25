/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbodyupjava;

import entities.Activitie;
import entities.Category;
import entities.Exercice;
import entities.ImageAct;
import java.sql.SQLException;
import services.ActivityService;
import services.ExerciceService;
import services.ImageActService;

/**
 *
 * @author oussa
 */
public class MbodyUpjava {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         ImageActService si = new ImageActService();
        ExerciceService se = new ExerciceService();
        ActivityService as = new ActivityService();
        Category c = new Category();
        c.setId(1);
        Exercice p = new Exercice("cou","30 min","hellooo world", c);
        Activitie act = new Activitie("saddddddd", "2022-03-04","17:00",3 ,"loooooooooooooooool", c);
       

       
        try {
             act.setExercices(se.afficherexercice());
             System.out.println("exercice ajoute in act success");
        } catch (SQLException e) {
        }
         
          try {
           // se.ajouterEx(p);
           //as.ajouterActivity(act);
           //si.DeleteImage(41);
           //si.ajouterImage(new ImageAct("hhhhh", 44));
            Activitie act2 = new Activitie(50, "nooo", "2022-03-04","17:00", "yesyes", c, se.afficherexercice());
           //as.modifierActivity(act2);
           as.SupprimerActivity(act2);
            System.out.println("ajout avec succes");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         try {
            System.out.println(as.afficheractivities());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
