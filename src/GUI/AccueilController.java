/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Activitie;
import entities.Category;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import services.ActivityService;

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
    private GridPane grid;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Media media =new Media ("file:///C:/Users/oussa/OneDrive/Documents/NetBeansProjects/MbodyUpjava/src/img/bg.mp4");  //badel path mta3 video
        MediaPlayer player = new MediaPlayer(media);
        mediaView.setMediaPlayer(player);
        player.setVolume(0);
        player.play();
        allact();
         
    }    
    
    
    public void allact(){
    
             int column =0;
        int row = 1000;
        ActivityService actS = new ActivityService();
        try {
          
               try {
                     List<Activitie> la = actS.afficheractivities();
                     System.out.println(la);
                   //for (Activitie actt : actS.afficheractivities()) 
                   for (int i = 0; i < la.size(); i++) 
                   {
                      // System.out.println(la.get(i));
                       FXMLLoader fxmlLoader = new FXMLLoader();
                       fxmlLoader.setLocation(getClass().getResource("/GUI/item.fxml"));
                       AnchorPane anchorPane = fxmlLoader.load();
                       Category c = new Category();
                        c.setId(1);
                                c.setNom_Cat("lool");
                       Activitie aa = new Activitie();
                       aa.setNom_Act("ahah");
                       aa.setDescription_Act("hhh");
                       aa.setDate_Act("hahahah");
                       aa.setCategory(c);
                       
                       ItemController itemController = fxmlLoader.getController();
                       itemController.safe(la.get(i));
                       
                       
                       if (column == 3) {
                           column = 0;
                           row++;
                       }
                       
                       grid.add(anchorPane, column++, row); //(child,column,row)
                       //set grid width
                       grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                       grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                       grid.setMaxWidth(Region.USE_PREF_SIZE);
                       
                       //set grid height
                       grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                       grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                       grid.setMaxHeight(Region.USE_PREF_SIZE);
                       
                       GridPane.setMargin(anchorPane, new Insets(30));
                   } 
               
               } catch (SQLException cv) {
                   System.out.println(cv);;
               }
        } catch (IOException e) {
            System.out.println(e);;
        }
    }
    
}
