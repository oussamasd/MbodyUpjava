/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import entities.Categories;
import entities.Produit;
import entities.ReservationProduit;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
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
    private Tab ProcductsPane;
    @FXML
    private Tab ProduitPane;
    @FXML
    private Tab AcitivitéPane112;
    @FXML
    private AnchorPane CoachPanel1;
    @FXML
    private GridPane grid_ab1;

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

    @FXML
    private void goRes(ActionEvent event) {
         {
         try{
               
                 Parent root  = FXMLLoader.load(getClass().getResource("/gui/ReservationProduit.fxml"));
                 
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                 Scene scene = new Scene(root);
                 stage.setScene(scene);
                 stage.show();

        }catch(IOException ex){
            System.out.println(ex);
        
        }
        
         }}
    
}
