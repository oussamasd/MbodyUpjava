/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbodyupjava;

import entities.Mail;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import services.ActivityService;

/**
 *
 * @author oussa
 */
public class FXMain extends Application {
    
   Stage stage;
    Parent parent;
    
    @Override
    public void start(Stage primaryStage) {
       this.stage = primaryStage;
        try {
            parent = FXMLLoader.load(getClass().getResource("/GUI/Accueil.fxml"));
            Scene scene = new Scene(parent);
            stage.setTitle("MbodyUP");
            stage.setScene(scene);
            stage.show();
            /* Mail.send(
    "oussama.saddi3@gmail.com",
    "oussama123456789",
    "kasmi.iskander4@gmail.com",
    "Bienvenu sur WayToLearnX",
    "mail de test!"
  );*/
           // System.out.println(new ActivityService().countparticipation(47));
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
      
    }}


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
