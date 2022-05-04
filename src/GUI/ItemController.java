/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Produit;
import entities.Categories;

import entities.Activitie;
import entities.ImageAct;
import entities.ReservationProduit;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author oussa
 */
public class ItemController  {

   

    
    @FXML
    private VBox chosenFruitCard;
    @FXML
    private ImageView fruitImg;
    private Label nomAct;
    private Label catact;
    private Label descact;
    
    
    Produit ac ;
    private Label prixab;
    @FXML
    private Label tnom;
    @FXML
    private Label prix;
    @FXML
    private Label quantite;
     Stage stage ;
     Scene scene ;

    
    
   public void safe(Produit act){
       ac = act;
       tnom.setText(act.getNom());
       
       prix.setText(String.valueOf(act.getPrix()));
        quantite.setText(String.valueOf(act.getQuantite()));

       if(act.getPhoto()!=null){
          
          File file = new File(act.getPhoto());
           try {
               String localUrl = file.toURI().toURL().toString();
               fruitImg.setFitHeight(200);
               fruitImg.setFitWidth(200);
               Image image = new Image(localUrl);
               fruitImg.setImage(image);
               
               
           
           } catch (MalformedURLException ex) {
               System.out.println(ex);;
           }
           
         
       
     }else{
           
           Image empty = new Image("/img/empty.jpg");
           fruitImg.setFitHeight(200);
            fruitImg.setFitWidth(200);
           fruitImg.setImage(empty);
           
           
       
       }
   }

   
   
   
    @FXML
    private void réserve(ActionEvent event) {
         try{
                ReservationProduit.lp.add(ac);
                 Parent root  = FXMLLoader.load(getClass().getResource("/gui/ReservationProduit.fxml"));
                 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                 scene = new Scene(root);
                 stage.setScene(scene);
                 stage.show();

        }catch(IOException ex){
            System.out.println(ex);
        
        }
            
      
    }
    

   

    
}
