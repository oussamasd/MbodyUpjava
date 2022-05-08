/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Abonnement;
import entities.Activitie;
import entities.Category;
import entities.Produit;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import services.AbonnementService;
import services.ActivityService;
import services.ProduitService;

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
    @FXML
    private GridPane grid_ab;
    @FXML
    private MenuItem profilee;
    @FXML
    private MenuItem setingss;
    @FXML
    private GridPane grid_ab1;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Media media =new Media ("file:///C:/Users/oussa/OneDrive/Documents/NetBeansProjects/MbodyUpjava/src/img/bg.mp4");  //badel path mta3 video
        MediaPlayer player = new MediaPlayer(media);
        mediaView.setMediaPlayer(player);
        player.setVolume(0);
        player.play();
        allact();
        allact_ab();
        allact_produit();
         
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
                   System.out.println(cv);
               }
        } catch (IOException e) {
            System.out.println(e);
        }
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

    @FXML
    private void Profilebtn(ActionEvent event) {
             try {
            Parent parent = FXMLLoader.load(getClass().getResource("Profile.fxml"));
            Scene scene = new Scene(parent);
            Stage thstage = new Stage();
            thstage.setScene(scene);
            thstage.initStyle(StageStyle.UTILITY);
            thstage.show();
        } catch (IOException ex) {
            Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void settingsbtn(ActionEvent event) {
              try {
            Parent parent = FXMLLoader.load(getClass().getResource("Settings.fxml"));
            Scene scene = new Scene(parent);
            Stage thstage = new Stage();
            thstage.setScene(scene);
            thstage.initStyle(StageStyle.UTILITY);
            thstage.show();
        } catch (IOException ex) {
            Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void goRes(ActionEvent event) {
        
           try{
               
                 Parent root  = FXMLLoader.load(getClass().getResource("/gui/ReservationProduit.fxml"));
                 
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                 Scene scene = new Scene(root);
                 stage.setScene(scene);
                 stage.show();

        }catch(IOException ex){
            System.out.println(ex);
        
        }
    }
    
     public void allact_produit(){
    
             int column =0;
        int row = 1000;
         ProduitService actS = new ProduitService();
        try {
          
               try {
                     List<Produit> la = actS.afficherProduit();
                     System.out.println(la);
                   //for (Activitie actt : actS.afficheractivities()) 
                   for (int i = 0; i < la.size(); i++) 
                   {
                      // System.out.println(la.get(i));
                       FXMLLoader fxmlLoader = new FXMLLoader();
                       fxmlLoader.setLocation(getClass().getResource("/GUI/item_mehdi.fxml"));
                       AnchorPane anchorPane = fxmlLoader.load();
                      
                      
                       ItemControllermehdi ItemController = fxmlLoader.getController();
                       ItemController.safe(la.get(i));
                       
                       
                       if (column == 3) {
                           column = 0;
                           row++;
                       }
                       
                       grid_ab1.add(anchorPane, column++, row); //(child,column,row)
                       //set grid width
                       grid_ab1.setMinWidth(Region.USE_COMPUTED_SIZE);
                       grid_ab1.setPrefWidth(Region.USE_COMPUTED_SIZE);
                       grid_ab1.setMaxWidth(Region.USE_PREF_SIZE);
                       
                       //set grid height
                       grid_ab1.setMinHeight(Region.USE_COMPUTED_SIZE);
                       grid_ab1.setPrefHeight(Region.USE_COMPUTED_SIZE);
                       grid_ab1.setMaxHeight(Region.USE_PREF_SIZE);
                       
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
