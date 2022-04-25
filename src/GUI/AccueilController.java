/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Abonnement;
import javafx.geometry.Insets;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import services.AbonnementService;

/**
 * FXML Controller class
 *
 * @author Skander
 */
public class AccueilController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
     private MediaView mediaView;
    @FXML
    private Tab PffresPane;
    @FXML
    private Tab AboonementsPane;
    @FXML
    private Tab AcitivitéPane1;
    @FXML
    private AnchorPane ActivitéPanel;
    @FXML
    private Tab AcitivitéPane11;
    @FXML
    private AnchorPane CoachPanel;
    @FXML
    private Tab AcitivitéPane111;
    @FXML
    private AnchorPane ReclamtionPanel1;
    @FXML
    private Tab ProcductsPane;
    @FXML
    private GridPane grid_ab;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        allact_ab();
        
        // TODO
    /*  Media media =new Media ("file:///C:/Users/oussa/OneDrive/Documents/NetBeansProjects/MbodyUpjava/src/img/bg.mp4");  //badel path mta3 video
        MediaPlayer player = new MediaPlayer(media);
        mediaView.setMediaPlayer(player);
        player.setVolume(0);
        player.play();*/
    }    
    public void allact_ab(){
    
             int column =0;
        int row = 1000;
        AbonnementService actS = new AbonnementService();
        try {
          
               try {
                     List<Abonnement> la = actS.afficherAbonnement();
                     System.out.println(la);
                   //for (Activitie actt : actS.afficheractivities()) 
                   for (int i = 0; i < la.size(); i++) 
                   {
                      // System.out.println(la.get(i));
                       FXMLLoader fxmlLoader = new FXMLLoader();
                       fxmlLoader.setLocation(getClass().getResource("/GUI/item_hadil.fxml"));
                       AnchorPane anchorPane = fxmlLoader.load();
                      
                      
                       Item_Front_Controller itemController = fxmlLoader.getController();
                       itemController.safe(la.get(i));
                       
                       
                       if (column == 3) {
                           column = 0;
                           row++;
                       }
                       
                       grid_ab.add(anchorPane, column++, row); //(child,column,row)
                       //set grid width
                       grid_ab.setMinWidth(Region.USE_COMPUTED_SIZE);
                       grid_ab.setPrefWidth(Region.USE_COMPUTED_SIZE);
                       grid_ab.setMaxWidth(Region.USE_PREF_SIZE);
                       
                       //set grid height
                       grid_ab.setMinHeight(Region.USE_COMPUTED_SIZE);
                       grid_ab.setPrefHeight(Region.USE_COMPUTED_SIZE);
                       grid_ab.setMaxHeight(Region.USE_PREF_SIZE);
                       
                       GridPane.setMargin(anchorPane, new Insets(30));
                   } 
               
               } catch (SQLException cv) {
                   System.out.println(cv);
               }
        } catch (IOException e) {
            System.out.println(e);
            
        }
    }
}
