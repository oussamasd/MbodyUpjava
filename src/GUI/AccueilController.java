/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import entities.Categories;
import entities.Produit;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.media.MediaView;
import services.ProduitService;

/**
 * FXML Controller class
 *
 * @author Mehdi AZZAZ
 */
public class AccueilController implements Initializable {

    @FXML
    private MediaView mediaView;
    @FXML
    private Tab PffresPane;
    @FXML
    private Tab AboonementsPane;
    @FXML
    private GridPane grid_ab;
    @FXML
    private Tab AcitivitéPane1;
    @FXML
    private AnchorPane ActivitéPanel;
    @FXML
    private Tab AcitivitéPane11;
    @FXML
    private AnchorPane CoachPanel;
    @FXML
    private AnchorPane ReclamtionPanel1;
    @FXML
    private Tab ProcductsPane;
    @FXML
    private Tab ProduitPane;
    @FXML
    private Tab AcitivitéPane112;
    @FXML
    private AnchorPane CoachPanel1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         allact_ab();
    }    
     public void allact_ab(){
    
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
                      
                      
                       ItemController ItemController = fxmlLoader.getController();
                       ItemController.safe(la.get(i));
                       
                       
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
